package kr.spring.mainboard.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.mainboard.domain.MainBoardVO;
import kr.spring.mainboard.domain.MainPicturesVO;
import kr.spring.mainboard.service.MainBoardService;
import kr.spring.mainboard.service.MainPicturesService;
import kr.spring.mainboardlikes.domain.MainBoardLikeVO;
import kr.spring.mainboardlikes.service.MainBoardLikeService;

//메인화면 
@Controller 
public class MainBoardController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private MainBoardService mainBoardService;
	
	@Resource
	private MainPicturesService mainPictureService;
	
	@Resource
	private MainBoardLikeService likeService;
	
	@ModelAttribute
	public MainBoardVO initCommand() {
		return new MainBoardVO();
	}
	@ModelAttribute
	public MainPicturesVO initCommand2() {
		return new MainPicturesVO();
	}
	//글쓰기 폼 호출
	@RequestMapping(value="/mainBoard/write.do",method=RequestMethod.GET)
	public String form() {
		return "mainBoardWrite";
	}

	//글쓰기 처리
	@RequestMapping(value= "/mainBoard/write.do",method=RequestMethod.POST)
	public String write(@Valid MainBoardVO mainBoardVO, MainPicturesVO mainPictures,BindingResult result,HttpSession session) throws IOException {
		
		if(log.isDebugEnabled()) {
			log.debug("메인보드 <<MainBoardVO>>" + mainBoardVO);
		}
		
		
		if(log.isDebugEnabled()) {
			log.debug("메인보드 <<MainPicturesVO>>" + mainPictures);
		}
		
		
		if(result.hasErrors()) {
			return "mainBoardWrite";
		}
		int mb_num = 0;
		mb_num = mainBoardService.selectMbnum(); 
		mainBoardVO.setMb_num(mb_num);
		mainBoardService.insert(mainBoardVO);
		
		//이미지
		if(mainPictures.getUploadPicture()!=null) {
		mainPictures.setMb_num(mb_num);
		mainPictures.setM_num((Integer) session.getAttribute("m_num"));
		mainPictureService.insert(mainPictures);
		}
		
		return "redirect:/main/main.do";
	}

	//글 상세 페이지
	@RequestMapping(value="/mainBoard/detail.do")
	public ModelAndView selectBoard(@RequestParam("mb_num") int num, HttpSession session) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<mb_num>> : " + num);
			
		}
		
		MainBoardVO mainBoard = mainBoardService.selectBoard(num);
		MainBoardVO mainBoardVO = mainBoardService.selectMnum(num);
		
		List<MainPicturesVO> mainPictures = mainPictureService.selectPictures(num);
		
		MainBoardLikeVO likeVO = new MainBoardLikeVO();
		
		
		likeVO.setMb_num(num);
		if(session.getAttribute("m_num") == null) {
			return new ModelAndView("redirect:/member/login.do");
		}else {
			likeVO.setM_num((Integer)session.getAttribute("m_num"));
		}
		
		int likeCheck = likeService.likeCheck(likeVO);
		int likeCount = likeService.likeCount(num);
		if(log.isDebugEnabled()) {
			log.debug("<<불러올 내용>> : " + mainBoard);
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mainBoardView");
		mav.addObject("mainBoard", mainBoard);
		mav.addObject("mnumVO", mainBoardVO);
		mav.addObject("mainPictures", mainPictures);
		mav.addObject("likeCheck", likeCheck);
		mav.addObject("likeCount", likeCount);
		
		return mav;
	}
	
	//이미지 보기
	@RequestMapping("/mainBoard/imageView.do")
	public ModelAndView viewImage(@RequestParam("mb_num") int mb_num) {
		
		MainBoardVO mainBoard = mainBoardService.selectBoard(mb_num);
		
		if(log.isDebugEnabled()) {
			log.debug("<<불러올 사진>> : " + mainBoard.getMb_photo());
			
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", mainBoard.getMb_photo());
		mav.addObject("filename", mainBoard.getMb_filename());
		return mav;
	}
	//추가 이미지 뷰 처리
	@RequestMapping("/mainBoard/imageView2.do")
	public ModelAndView viewImage2(@RequestParam("i_num") int i_num) {
		
		MainPicturesVO mainPicture = mainPictureService.select(i_num);
		
		
		if(log.isDebugEnabled()) {
			log.debug("<<불러올 사진>> : " + mainPicture.getI_photo());
			
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", mainPicture.getI_photo());
		mav.addObject("filename", mainPicture.getI_filename());
		return mav;
	}
	
	//글 수정 폼 호출
	@RequestMapping(value="/mainBoard/update.do",method=RequestMethod.GET)
	public String updateForm(@RequestParam("mb_num") int mb_num, Model model) {
		
		MainBoardVO mainBoardVO = mainBoardService.selectBoard(mb_num);
		if(log.isDebugEnabled()) {
			log.debug("<<수정 전 BoardVO>>" + mainBoardVO);
		}

		model.addAttribute("mainBoardVO",mainBoardVO);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", mainBoardVO.getMb_photo());
		mav.addObject("filename", mainBoardVO.getMb_filename());
		
		//추가 이미지 처리
		List<MainPicturesVO> mainPictures = mainPictureService.selectPictures(mb_num);
		
		model.addAttribute("mainPictures",mainPictures);
		
		return "mainBoardModify";
	}
	
	//글 수정 처리
	@RequestMapping(value="/mainBoard/update.do",method=RequestMethod.POST)
	public String submitUpdate(@Valid MainBoardVO mainBoardVO,MainPicturesVO mainPictures, HttpSession session,BindingResult result) throws IOException {
		
		if(log.isDebugEnabled()) {
			log.debug("<<수정 후 BoardVO>>" + mainBoardVO);
		}
		
		
		if(log.isDebugEnabled()) {
			log.debug("<<수정 후 MainPicturesVO>>" + mainPictures);
		}
		
		if(result.hasErrors()) {
			return "mainBoardModify";
		}
		mainBoardService.update(mainBoardVO);
		int mb_num = mainBoardVO.getMb_num();
		
		if(mainPictures.getUploadPicture() != null) { 
			mainPictureService.updatePictures(mainPictures);
		}
		
		
		return "redirect:/mainBoard/detail.do?mb_num="+mb_num;
	}
	
	//글 삭제 처리
	@RequestMapping(value="/mainBoard/delete.do")
	public String delete(@RequestParam("mb_num") int mb_num) {
		
		if(log.isDebugEnabled()) {
			log.debug("mb_num : " + mb_num);
		}
		mainBoardService.delete(mb_num);
		
		return "redirect:/main/main.do";
	}
	
	//추가 이미지 삭제
	@RequestMapping(value="/mainBoard/deletePicture.do")
	public String pictureDelete(@RequestParam("i_num") int i_num) {
		MainPicturesVO pictureVO = mainPictureService.select(i_num);
		int mb_num = pictureVO.getMb_num();
		mainPictureService.deletePictures(i_num);
		
		return "redirect:/mainBoard/update.do?mb_num="+mb_num;
	}
}