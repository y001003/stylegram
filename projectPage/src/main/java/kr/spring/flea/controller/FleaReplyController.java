package kr.spring.flea.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.flea.domain.FleaReplyVO;
import kr.spring.flea.service.FleaReplyService;
import kr.spring.util.ReplyPager;

@Controller
public class FleaReplyController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private FleaReplyService fleaReplyService;
	
	@ModelAttribute
	public FleaReplyVO initCommand() {
		return new FleaReplyVO();
	}
	
	@RequestMapping("/flea/replyList.do")
	public ModelAndView replyList(@RequestParam("fb_num") int fb_num, ModelAndView mav, @RequestParam(defaultValue="1") int curPage, HttpSession session) throws Exception {
		//페이징 처리
		int count = fleaReplyService.replyCount(fb_num);
		ReplyPager replyPager = new ReplyPager(count, curPage);
		int start = replyPager.getPageBegin();
		int end = replyPager.getPageEnd();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("fb_num", fb_num);
		map.put("start", start);
		map.put("end", end);
		
		List<FleaReplyVO> list = fleaReplyService.replyList(map);//댓글 목록
		mav.setViewName("fleaReplyList");
		mav.addObject("r_list", list);
		mav.addObject("replyPager", replyPager);
		mav.addObject("replyCount", count);
		
		return mav;
	}
	
	@RequestMapping("/flea/replyListJson.do")
	@ResponseBody
	public List<FleaReplyVO> listJson(@RequestParam("fb_num") int fb_num, @RequestParam(defaultValue="1") int curPage) throws Exception{
		int count = fleaReplyService.replyCount(fb_num);
		ReplyPager replyPager = new ReplyPager(count, curPage);
		int start = replyPager.getPageBegin();//페이징 시작번호
		int end = replyPager.getPageEnd();
		
		if(log.isDebugEnabled()) {
			log.debug("list 돌기 전에 : "+ fb_num + start + end);
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("fb_num", fb_num);
		map.put("start", start);
		map.put("end", end);
		
		List<FleaReplyVO> list = fleaReplyService.replyList(map);
		if(log.isDebugEnabled()) {
			log.debug("list : " + list);
		}
		
		return list;
	}
	
	@RequestMapping("/flea/insertReply.do")
	@ResponseBody
	public String insert(@Valid FleaReplyVO fleaReplyVO, BindingResult result, HttpServletRequest request, HttpSession session) {
		int m_num = (Integer)session.getAttribute("m_num");
		fleaReplyVO.setM_num(m_num);
		
		if(log.isDebugEnabled()) {
			log.debug("fleaReplyVO : " + fleaReplyVO);
		}
		
		fleaReplyService.replyInsert(fleaReplyVO);
		
		int fb_num = fleaReplyVO.getFb_num();
		
		return "redirect:/flea/fleaDetail.do?fb_num="+fb_num;
	}
	
	@RequestMapping("/flea/updateReply.do")
	@ResponseBody
	public String update(@RequestParam("fr_num") int fr_num, ModelAndView mav, HttpServletRequest request, BindingResult result, Model model, HttpSession session) {
		FleaReplyVO fleaReplyVO = fleaReplyService.replySelect(fr_num);
		fleaReplyVO.setFr_content((String)request.getParameter("updateContent"));
		
		if(log.isDebugEnabled()) {
			log.debug("수정 후 replyVO : " + fleaReplyVO);
		}
		
		fleaReplyService.replyUpdate(fleaReplyVO);
		
		int fb_num = fleaReplyVO.getFb_num();
		
		mav.setViewName("fleaDetail");
		
		return "redirect:/flea/fleaDetail.do?fb_num="+fb_num;
	}
	
	@RequestMapping("/flea/deleteReply.do")
	public String delete(@RequestParam("fr_num") int fr_num) {
		FleaReplyVO fleaReplyVO = fleaReplyService.replySelect(fr_num);
		
		int fb_num = fleaReplyVO.getFb_num();
		
		fleaReplyService.replyDelete(fr_num);
		
		return "redirect:/flea/fleaDetail.do?fb_num="+fb_num;
	}
	
}
