package kr.spring.flea.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
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
import org.springframework.web.servlet.ModelAndView;

import kr.spring.flea.domain.FleaPicturesVO;
import kr.spring.flea.domain.FleaVO;
import kr.spring.flea.service.FleaPicturesService;
import kr.spring.flea.service.FleaService;
import kr.spring.util.PagingUtil;

@Controller
public class FleaController {
	
	int rowCount = 10;//한화면에 보여줄 게시물 수
	int pageCount = 10;//한화면에 보여줄 페이지 수
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private FleaService fleaService;
	
	@Resource
	private FleaPicturesService fleaPicturesService;
	
	//자바빈(VO) 초기화
	@ModelAttribute
	public FleaVO initCommand() {
		return new FleaVO();
	}
	
	@ModelAttribute
	public FleaPicturesVO initCommand2() {
		return new FleaPicturesVO();
	}

	//목록
	@RequestMapping("/flea/fleaList.do")
	public ModelAndView fleaList(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
								 @RequestParam(value="keyfield",defaultValue="") String keyfield,
								 @RequestParam(value="keyword",defaultValue="") String keyword,
								 @RequestParam(value="filter",defaultValue="") String filter) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		map.put("filter", filter);
		
		int count = fleaService.selectRowCount(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<listParamInfo>> : " + count + keyfield + keyword);
		}
		
		//페이징처리
		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,rowCount,pageCount,"fleaList.do");
		
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<FleaVO> list = null;
		if(count > 0) {
			list = fleaService.selectList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("fleaList");
		mav.addObject("list", list);
		mav.addObject("count", count);
		mav.addObject("pagingHtml", page.getPagingHtml());
		mav.addObject("filter", filter);
		mav.addObject("keyfield", keyfield);
		mav.addObject("keyword", keyword);
		
		return mav;
	}

	//글쓰기
	@RequestMapping(value="/flea/fleaWrite.do",method=RequestMethod.GET)
	public String write() {
		return "fleaWrite";
	}
	
	@RequestMapping(value="/flea/fleaWrite.do",method=RequestMethod.POST)
	public String writeSubmit(@Valid FleaVO fleaVO, FleaPicturesVO fleaPictures, BindingResult result, HttpSession session, @RequestParam(value="fb_topcheck",required=false) String fb_topcheck, @RequestParam(value="fb_bottomcheck",required=false) String fb_bottomcheck, @RequestParam(value="fb_hatcheck",required=false) String fb_hatcheck, @RequestParam(value="fb_shoescheck",required=false) String fb_shoescheck) throws IOException {
		if(log.isDebugEnabled()) {
			log.debug("<<FleaVO>> : " + fleaVO);
		}
		if(log.isDebugEnabled()) {
			log.debug("<<FleaPicturesVO>> : " + fleaPictures);
		}
		
		if(result.hasErrors()) {
			System.out.println(result);
			return "fleaWrite";
		}
		
		fleaVO.setFb_usernum((Integer)session.getAttribute("m_num"));
		
		if(fb_topcheck != null) {
			fleaVO.setFb_topchecknum(1);
		}else {
			fleaVO.setFb_topchecknum(0);
		}
		if(fb_bottomcheck != null) {
			fleaVO.setFb_bottomchecknum(1);
		}else {
			fleaVO.setFb_bottomchecknum(0);
		}
		if(fb_hatcheck != null) {
			fleaVO.setFb_hatchecknum(1);
		}else {
			fleaVO.setFb_hatchecknum(0);
		}
		if(fb_shoescheck != null){
			fleaVO.setFb_shoeschecknum(1);
		}else {
			fleaVO.setFb_shoeschecknum(0);
		}
		
		int fb_num = fleaService.selectFb_num();
		
		fleaVO.setFb_num(fb_num);
		fleaService.insert(fleaVO);
		
		if(fleaPictures.getUploadPicture() != null) {
			fleaPictures.setFb_num(fb_num);
			fleaPictures.setM_num((Integer)session.getAttribute("m_num"));
			fleaPicturesService.insert(fleaPictures);
		}
		
		return "redirect:/flea/fleaList.do";
	}
	
	//글 상세
	@RequestMapping(value="/flea/fleaDetail.do")
	public ModelAndView detail(@RequestParam("fb_num") int fb_num, HttpSession session) {
		if(log.isDebugEnabled()) {
			log.debug("<<fb_num>> : " + fb_num);
		}
		
		FleaVO flea = fleaService.selectFlea(fb_num);
		
		List<FleaPicturesVO> fleaPictures = fleaPicturesService.selectPictures(fb_num);
		
		if(session.getAttribute("m_num") == null) {
			return new ModelAndView("redirect:/member/login.do");
		}else if((Integer)session.getAttribute("m_auth") == 2) {
			return new ModelAndView("redirect:/member/auth.do");
		}
		System.out.println(flea);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("fleaDetail");
		mav.addObject("flea", flea);
		mav.addObject("fleaPictures", fleaPictures);
		
		return mav;
	}

	//이미지 처리
	@RequestMapping("flea/imageView.do")
	public ModelAndView viewImage(@RequestParam("fb_num") int fb_num) {
		
		FleaVO flea = fleaService.selectFlea(fb_num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", flea.getFb_photo());
		mav.addObject("filename", flea.getFb_filename());
		
		return mav;
	}
	
	//추가 이미지 뷰 처리
	@RequestMapping("/flea/imageView2.do")
	public ModelAndView viewImage2(@RequestParam("i_num") int i_num) {
		
		FleaPicturesVO fleaPicture = fleaPicturesService.select(i_num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", fleaPicture.getI_photo());
		mav.addObject("filename", fleaPicture.getI_filename());
		
		return mav;
	}
	
	//글 수정
	@RequestMapping(value="/flea/fleaUpdate.do",method=RequestMethod.GET)
	public String update(@RequestParam("fb_num") int fb_num, Model model) {
		
		FleaVO fleaVO = fleaService.selectFlea(fb_num);
		
		model.addAttribute("fleaVO", fleaVO);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", fleaVO.getFb_photo());
		mav.addObject("filename", fleaVO.getFb_filename());
		
		//추가 이미지 처리
		List<FleaPicturesVO> fleaPictures = fleaPicturesService.selectPictures(fb_num);
		
		model.addAttribute("fleaPictures", fleaPictures);
		
		return "fleaUpdate";
	}
	
	@RequestMapping(value="/flea/fleaUpdate.do",method=RequestMethod.POST)
	public String updateSubmit(@Valid FleaVO fleaVO, FleaPicturesVO fleaPictures,BindingResult result) throws IOException {
		
		if(log.isDebugEnabled()) {
			log.debug("<<FleaVO>> : " + fleaVO);
		}
		
		if(result.hasErrors()) {
			return "fleaUpdate";
		}
		
		fleaService.update(fleaVO);
		
		if(fleaPictures.getUploadPicture() != null) {
			fleaPicturesService.updatePictures(fleaPictures);
		}
		
		int fb_num = fleaVO.getFb_num();
		
		return "redirect:/flea/fleaDetail.do?fb_num=" + fb_num;
	}
	
	//글 삭제
	@RequestMapping("/flea/fleaDelete.do")
	public String delete(@RequestParam("fb_num") int fb_num) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<fb_num>> : " + fb_num);
		}
		
		fleaService.deleteinfo(fb_num);
		fleaService.delete(fb_num);
		
		return "redirect:/flea/fleaList.do";
	}
	
	//추가 이미지 삭제
	@RequestMapping(value="/flea/deletePicture.do")
	public String pictureDelete(@RequestParam("i_num") int i_num) {
		FleaPicturesVO fleaPicturesVO = fleaPicturesService.select(i_num);
		int fb_num = fleaPicturesVO.getFb_num();
		fleaPicturesService.deletePictures(i_num);
		
		return "redirect:/flea/fleaUpdate.do?fb_num=" + fb_num;
	}
	
}
