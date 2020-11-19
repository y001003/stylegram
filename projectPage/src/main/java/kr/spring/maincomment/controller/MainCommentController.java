package kr.spring.maincomment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.mainboard.domain.MainBoardVO;
import kr.spring.mainboard.service.MainBoardService;
import kr.spring.maincomment.domain.MainCommentVO;
import kr.spring.maincomment.service.MainCommentService;
import kr.spring.util.ReplyPager;

@Controller
public class MainCommentController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Inject
	@Resource
	private MainCommentService mainCommentService;
	
	@ModelAttribute
	public MainCommentVO initCommand() {
		return new MainCommentVO();
	}
	@RequestMapping("/mainBoard/listComment.do")
	public ModelAndView list(@RequestParam("mb_num") int mb_num, ModelAndView mav, @RequestParam(defaultValue="1") int curPage, HttpSession session) throws Exception {
		//페이징 처리
		int count = mainCommentService.commentCount(mb_num);
		ReplyPager replyPager = new ReplyPager(count, curPage);
		int start = replyPager.getPageBegin();//페이징 시작번호
		int end = replyPager.getPageEnd();

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("mb_num", mb_num);
		map.put("start", start);
		map.put("end", end);
		
		
		List<MainCommentVO> list = mainCommentService.commentList(map);//댓글 목록
		mav.setViewName("mainReplyList");
		mav.addObject("c_list",list);//뷰에 전달할 데이터 저장
		mav.addObject("replyPager",replyPager);
		mav.addObject("commentCount", count);
		return mav;//뷰로 이동
	}
	
	@RequestMapping("/mainBoard/listCommentJson.do")
	@ResponseBody
	public List<MainCommentVO> listJson(@RequestParam("mb_num") int mb_num, @RequestParam(defaultValue="1") int curPage) throws Exception{
		int count = mainCommentService.commentCount(mb_num);
		ReplyPager replyPager = new ReplyPager(count, curPage);
		int start = replyPager.getPageBegin();//페이징 시작번호
		int end = replyPager.getPageEnd();
		
		if(log.isDebugEnabled()) {
			log.debug("list 돌기 전에 : "+mb_num+start+end);
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("mb_num", mb_num);
		map.put("start", start);
		map.put("end", end);
		
		
		List<MainCommentVO> list = mainCommentService.commentList(map);//댓글 목록
		if(log.isDebugEnabled()) {
			log.debug("list : " + list);
		}
		return list;
	}
	
	@RequestMapping("/mainBoard/insertComment.do")
	@ResponseBody
	public String insert(@Valid MainCommentVO mainCommentVO,BindingResult result,HttpServletRequest request,HttpSession session) {
		
		//현재 댓글 작성자
		int m_num=(Integer) session.getAttribute("m_num");
		mainCommentVO.setM_num(m_num);
		
		
		if(log.isDebugEnabled()) {
			log.debug("commentVO : " + mainCommentVO);
		}
		
		mainCommentService.commentInsert(mainCommentVO);
		
		int mb_num = mainCommentVO.getMb_num();
		
		return "redirect:/mainBoard/detail.do?mb_num="+mb_num;
	}
	
	@RequestMapping("/mainBoard/updateComment.do")
	public String update(@RequestParam("mr_num") int mr_num,ModelAndView mav, HttpServletRequest request, BindingResult result,Model model, HttpSession session) {
		
		MainCommentVO mainCommentVO = mainCommentService.commentSelect(mr_num);
		mainCommentVO.setMr_content((String) request.getParameter("updateText"));  
		
		if(log.isDebugEnabled()) {
			log.debug("수정 후 commentVO : " + mainCommentVO);
		}
		mainCommentService.commentUpdate(mainCommentVO);
		
		int mb_num = mainCommentVO.getMb_num();

		
		mav.setViewName("mainBoardView");
		

		return "redirect:/mainBoard/detail.do?mb_num="+mb_num;
	}
	
	@RequestMapping("/mainBoard/deleteComment.do")
	public String delete(@RequestParam("mr_num") int mr_num) {
		MainCommentVO mainCommentVO = mainCommentService.commentSelect(mr_num);
		int mb_num = mainCommentVO.getMb_num();
		
		mainCommentService.commentDelete(mr_num);
		
		
		return "redirect:/mainBoard/detail.do?mb_num="+mb_num;
	}
	
	
	

	

	
	
}
