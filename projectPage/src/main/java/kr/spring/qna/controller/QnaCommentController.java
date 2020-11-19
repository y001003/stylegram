package kr.spring.qna.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.qna.domain.QnaCommentVO;
import kr.spring.qna.service.QnaCommentService;
import kr.spring.util.ReplyPager;

@Controller
public class QnaCommentController {

	private Logger log = Logger.getLogger(this.getClass());

	@Inject
	@Resource
	private QnaCommentService qnaCommentService;

	@ModelAttribute
	public QnaCommentVO initCommand() {
		return new QnaCommentVO();
	}
	@RequestMapping("/qna/listComment.do")
	public ModelAndView list(@RequestParam("qb_num") int qb_num, ModelAndView mav, @RequestParam(defaultValue="1") int curPage, HttpSession session) throws Exception {
		//페이징 처리
		int count = qnaCommentService.commentCount(qb_num);
		ReplyPager replyPager = new ReplyPager(count, curPage);
		int start = replyPager.getPageBegin();//페이징 시작번호
		int end = replyPager.getPageEnd();

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("qb_num", qb_num);
		map.put("start", start);
		map.put("end", end);


		List<QnaCommentVO> list = qnaCommentService.commentList(map);//댓글 목록
		mav.setViewName("qnaReplyList");
		mav.addObject("c_list",list);//뷰에 전달할 데이터 저장
		mav.addObject("replyPager",replyPager);
		mav.addObject("commentCount", count);
		return mav;//뷰로 이동
	}

	@RequestMapping("/qna/listCommentJson.do")
	@ResponseBody
	public List<QnaCommentVO> listJson(@RequestParam("qb_num") int qb_num, @RequestParam(defaultValue="1") int curPage) throws Exception{
		int count = qnaCommentService.commentCount(qb_num);
		ReplyPager replyPager = new ReplyPager(count, curPage);
		int start = replyPager.getPageBegin();//페이징 시작번호
		int end = replyPager.getPageEnd();

		if(log.isDebugEnabled()) {
			log.debug("list 돌기 전에 : "+qb_num+start+end);
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("qb_num", qb_num);
		map.put("start", start);
		map.put("end", end);


		List<QnaCommentVO> list = qnaCommentService.commentList(map);//댓글 목록
		if(log.isDebugEnabled()) {
			log.debug("list : " + list);
		}
		return list;
	}

	@RequestMapping("/qna/insertComment.do")
	@ResponseBody
	public String insert(@Valid QnaCommentVO qnaCommentVO,BindingResult result,HttpServletRequest request,HttpSession session) {

		//현재 댓글 작성자
		int m_num=(Integer) session.getAttribute("m_num");
		qnaCommentVO.setM_num(m_num);


		if(log.isDebugEnabled()) {
			log.debug("commentVO : " + qnaCommentVO);
		}

		qnaCommentService.commentInsert(qnaCommentVO);

		int qb_num = qnaCommentVO.getQb_num();

		return "redirect:/qna/detailQna.do?qb_num="+qb_num;
	}

	@RequestMapping("/qna/updateComment.do")
	public String update(@RequestParam("qr_num") int qr_num,ModelAndView mav, HttpServletRequest request, BindingResult result,Model model, HttpSession session) {

		QnaCommentVO qnaCommentVO = qnaCommentService.commentSelect(qr_num);
		qnaCommentVO.setQr_content((String) request.getParameter("updateText"));  

		if(log.isDebugEnabled()) {
			log.debug("수정 후 commentVO : " + qnaCommentVO);
		}
		qnaCommentService.commentUpdate(qnaCommentVO);

		int qb_num = qnaCommentVO.getQb_num();


		mav.setViewName("detailQna");


		return "redirect:/qna/detailQna.do?qb_num="+qb_num;
	}

	@RequestMapping("/qna/deleteComment.do")
	public String delete(@RequestParam("qr_num") int qr_num) {
		QnaCommentVO qnaCommentVO = qnaCommentService.commentSelect(qr_num);
		int qb_num = qnaCommentVO.getQb_num();

		qnaCommentService.commentDelete(qr_num);


		return "redirect:/qna/detailQna.do?qb_num="+qb_num;
	}

}

