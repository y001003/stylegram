package kr.spring.notice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;

import kr.spring.notice.domain.NoticeVO;
import kr.spring.notice.service.NoticeService;
import kr.spring.util.PagingUtil;

//엄나엄니ㅏㅓㅇ리ㅏㅁㄴ러ㅣㅏㄴㅁㄹ
@Controller
public class NoticeController {
	private Logger log = Logger.getLogger(this.getClass());
	private int rowCount = 10;
	private int pageCount = 10;

	@Resource
	public NoticeService noticeService;

	@ModelAttribute
	public NoticeVO initCommand() {
		return new NoticeVO();
	}

	@RequestMapping("/noticeboard/list.do")
	public ModelAndView process(
			@RequestParam(value="pageNum", defaultValue="1")
			int currentPage,
			@RequestParam(value="keyfield", defaultValue="")
			String keyfield,
			@RequestParam(value="keyword", defaultValue="")
			String keyword) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);

		int count = noticeService.selectRowCount(map);

		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}
		PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, count, rowCount, pageCount, "list.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());

		List<NoticeVO> list = null;
		if(count > 0) {
			list = noticeService.selectList(map);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("noticeboardList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());

		return mav;
	}


	@RequestMapping(value="/noticeboard/write.do", method=RequestMethod.GET)
	public String form() {
		return "noticeboardWrite";
	}

	@RequestMapping(value="/noticeboard/write.do", method=RequestMethod.POST)
	public String submit(@Valid NoticeVO noticeVO, BindingResult result, HttpServletRequest request, HttpSession session) {

		if(log.isDebugEnabled()) {
			log.debug("<<NoticeVO>> : " + noticeVO);
		}

		if(result.hasErrors()) {
			return "noticeWrite";
		}

		//회원번호 받기
		//noticeVO.setM_num((Integer)session.getAttribute("user_num"));
		noticeVO.setM_num(3);
		
		//글 등록
		noticeService.insert(noticeVO);

		return "redirect:/noticeboard/list.do";
	}

	//글 상세
	@RequestMapping("/noticeboard/detail.do")
	public ModelAndView process(@RequestParam("nb_num") int nb_num) {

		//로그 표시
		if(log.isDebugEnabled()) {
			log.debug("<<nb_num>> : " + nb_num);
		}

		NoticeVO notice = noticeService.selectNotice(nb_num);
		//뷰 이름         속성명      속성값
		return new ModelAndView("noticeboardView","notice",notice);
	}


	@RequestMapping(value="/noticeboard/update.do", method=RequestMethod.GET)
	public String form(@RequestParam("nb_num") int nb_num, Model model) {

		NoticeVO noticeVO = noticeService.selectNotice(nb_num);

		model.addAttribute("noticeVO", noticeVO);

		return "noticeboardModify";
	}

	//글 수정 처리
	@RequestMapping(value="/noticeboard/update.do", method=RequestMethod.POST)
	public String submitUpdate(@Valid NoticeVO noticeVO, BindingResult result, HttpServletRequest request) {

		if(log.isDebugEnabled()) {
			log.debug("<<NoticeVO>> : " + noticeVO);
		}

		//유효성 체크 결과 에러가 있으면 폼 호출
		if(result.hasErrors()) {
			return "noticeboardModify";
		}

		noticeService.update(noticeVO);

		return "redirect:/noticeboard/list.do";
	}


	//글 삭제
		@RequestMapping("/noticeboard/delete.do")
		public String submit(@RequestParam("nb_num") int nb_num) {
			
			//로그 표시
			if(log.isDebugEnabled()) {
				log.debug("<<nb_num>> : " + nb_num);
			}
			
			//글 삭제
			noticeService.delete(nb_num);
			
			return "redirect:/noticeboard/list.do";
		}
		//이미지 처리
		@RequestMapping("/noticeboard/imageView.do")
		public ModelAndView viewImage(@RequestParam("nb_num") int nb_num, @RequestParam("file_num") int file_num) {
			NoticeVO notice = noticeService.selectNotice(nb_num);
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("imageView");
			
			if(file_num == 1) {
				mav.addObject("imageFile",notice.getNb_file1());
				mav.addObject("filename",notice.getNb_filename1());
			}else if(file_num == 2) {
				mav.addObject("imageFile",notice.getNb_file2());
				mav.addObject("filename",notice.getNb_filename2());
			}
			return mav;
		}
}
