package kr.spring.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.main.domain.MainVO;
import kr.spring.main.service.MainService;
import kr.spring.mainboard.domain.MainBoardVO;
import kr.spring.mainboard.service.MainBoardService;
import kr.spring.mainboardlikes.service.MainBoardLikeService;
import kr.spring.util.PagingUtil;

@Controller
public class MainController {
	
	int rowCount = 10;//한화면에 보여줄 게시물 수
	int pageCount = 10;//한화면에 보여줄 페이지 수
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private MainBoardService mainBoardService;
	
	@Resource
	private MainService mainService;
	
	@Resource
	private MainBoardLikeService likeService;
	
	@ModelAttribute
	public MainBoardVO initCommand() {
		return new MainBoardVO();
	}
	
	
	@RequestMapping("/main/main.do")
	public ModelAndView process(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
								@RequestParam(value="keyfield",defaultValue="") String keyfield,
								@RequestParam(value="keyword",defaultValue="") String keyword,
								@RequestParam(value="filter",defaultValue="") String filter) {
		
		//검색 기능 구현 (아직 태그 검색 미구현)
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("keyfield",keyfield);
		map.put("keyword",keyword);
		map.put("filter",filter);
		
		int count = mainBoardService.selectRowCount(map);
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : "+ count + keyfield + keyword);
		}
		
		//페이징처리 
		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,rowCount,pageCount,"main.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<MainVO> mainList = null;
		if(count > 0) {
			mainList = mainService.selectList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main");
		mav.addObject("count",count);
		mav.addObject("mainList", mainList);
		mav.addObject("pagingHtml",page.getPagingHtml());
		mav.addObject("filter",filter);
		mav.addObject("keyfield", keyfield);
		mav.addObject("keyword", keyword);
		
		return mav; 
	}
	//이미지 보기
	@RequestMapping("/main/imageView.do")
	public ModelAndView viewImage(@RequestParam("mb_num") int num) {
		
		MainBoardVO mainBoard = mainBoardService.selectBoard(num);
		
		if(log.isDebugEnabled()) {
			log.debug("<<불러올 사진>> : " + mainBoard.getMb_photo());
			
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", mainBoard.getMb_photo());
		mav.addObject("filename", mainBoard.getMb_filename());
		return mav;
	}
	
	//좋아요 수
	@RequestMapping("/main/likesCount.do")
	public ModelAndView likesCount(@RequestParam("mb_num") int num) {
		
		int likesCount = likeService.likeCount(num);
		
		return new ModelAndView("likesCount", "likesCount", likesCount);
	}
}
