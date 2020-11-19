package kr.spring.qna.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import kr.spring.qna.domain.QnaVO;

import kr.spring.qna.service.QnaService;
import kr.spring.util.PagingUtil;

@Controller
public class QnaController {
	private Logger log = Logger.getLogger(this.getClass());
	
	int rowCount = 10;//한화면에 보여줄 게시물 수
	int pageCount = 10;//한화면에 보여줄 페이지 수

	@Resource
	private QnaService qnaService;
	
	//자바빈 초기화
	@ModelAttribute
	public QnaVO initCommand() {
		return new QnaVO();
	}
	
	//목록
	@RequestMapping("/qna/listQna.do")
	public ModelAndView process(
		@RequestParam(value="pageNum",defaultValue="1") 
		int currentPage,
		@RequestParam(value="keyfield",defaultValue="") 
		String keyfield,
		@RequestParam(value="keyword",defaultValue="") 
		String keyword,
		@RequestParam(value="filter",defaultValue="") 
		String filter) 
		{
	
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("keyfield",keyfield);
		map.put("keyword",keyword);
		map.put("filter",filter);
		
		int count = qnaService.selectRowCount(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : "+ count + keyfield + keyword);
		}
		
		//페이징처리 
		
		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,rowCount,pageCount,"listQna.do");
		
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<QnaVO> listQna = null;
		if(count > 0) {
			listQna = qnaService.listQna(map);
		}
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("listQna");
		mav.addObject("count",count);
		mav.addObject("listQna",listQna);
		mav.addObject("pagingHtml",page.getPagingHtml());
		mav.addObject("filter",filter);
		mav.addObject("keyfield", keyfield);
		mav.addObject("keyword", keyword);
		
		
		return mav; 
	}
	
	
	//글쓰기 호출
	@RequestMapping(value="/qna/writeQna.do", method=RequestMethod.GET)
	public String form() {
		return "writeQna";
	}
	//글쓰기 처리
	@RequestMapping(value="/qna/writeQna.do",method=RequestMethod.POST)
	public String submit(@Valid QnaVO qnaVO,BindingResult result,HttpServletRequest request,HttpSession session) {
		
		if(log.isDebugEnabled()) {
			log.debug("QNA <<QnaVO>> : " + qnaVO);
		}
		
		Integer user_num = (Integer)session.getAttribute("m_num");
		qnaVO.setQb_usernum(user_num);
		
		if(qnaVO.getQb_topinfo() == null) {
			qnaVO.setQb_topinfo("0");
		}
		if(qnaVO.getQb_pantsinfo() == null) {
			qnaVO.setQb_pantsinfo("0");
		}
		if(qnaVO.getQb_capinfo() == null) {
			qnaVO.setQb_capinfo("0");
		}
		if(qnaVO.getQb_shoesinfo() == null) {
			qnaVO.setQb_shoesinfo("0");
		}
		
		if(result.hasErrors()) {
			return "writeQna";
		}
			
		qnaService.writeQna(qnaVO);
		//구현 후 수정 
		return "redirect:listQna.do";
	}
	
	//상세페이지
	@RequestMapping(value="/qna/detailQna.do")
	public ModelAndView selectQna(@RequestParam ("qb_num")int qb_num) {
		
		//로그
		if(log.isDebugEnabled()) {
			log.debug("<<qb_num>> : " + qb_num);
		}
		
		QnaVO qna = qnaService.selectQna(qb_num);
		
		if(log.isDebugEnabled()) {
			log.debug("<<qna>> : " + qna);
		}
		
		return new ModelAndView("detailQna","qna",qna);
		
	}
	//이미지 처리
		@RequestMapping("qna/imageView.do")
		public ModelAndView viewImage(@RequestParam("qb_num")int qb_num) {
			
			QnaVO qna = qnaService.selectQna(qb_num);
			
			if(log.isDebugEnabled()) {
				log.debug("<<업로드 사진>> : " + qna.getQb_photo());
			}
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("imageView");
			mav.addObject("imageFile", qna.getQb_photo());
			mav.addObject("filename", qna.getQb_filename());
			
			return mav;
		}
		
		//글 수정 호출
		@RequestMapping(value="qna/modifyQna.do", method=RequestMethod.GET)
		public String form(@RequestParam("qb_num") int qb_num, Model model) {
			
			QnaVO qnaVO = qnaService.selectQna(qb_num);
			if(log.isDebugEnabled()) {
				log.debug("<<수정 전 qnaVO>> : " + qnaVO);
			}
			model.addAttribute("qnaVO", qnaVO);
			ModelAndView mav = new ModelAndView();
			mav.setViewName("imageView");
			mav.addObject("imageFile", qnaVO.getQb_photo());
			mav.addObject("filename", qnaVO.getQb_filename());
			
			return "modifyQna";
		}
		
		//글 수정 처리
		@RequestMapping(value="/qna/modifyQna.do", method=RequestMethod.POST)
		public String updateForm(@Valid QnaVO qnaVO,BindingResult result, HttpServletRequest request) {
			
			if(qnaVO.getQb_topinfo() == null) {
				qnaVO.setQb_topinfo("0");
			}
			if(qnaVO.getQb_pantsinfo() == null) {
				qnaVO.setQb_pantsinfo("0");
			}
			if(qnaVO.getQb_capinfo() == null) {
				qnaVO.setQb_capinfo("0");
			}
			if(qnaVO.getQb_shoesinfo() == null) {
				qnaVO.setQb_shoesinfo("0");
			}
			
			qnaService.modifyQna(qnaVO);
			
			//로그 표시
			if(log.isDebugEnabled()) {
				log.debug("<<QnaVO>> : " + qnaVO);
			}
			
			if(result.hasErrors()) {
				return "writeQna";
			}
			
			int qb_num = qnaVO.getQb_num();
			
			return "redirect:/qna/detailQna.do?qb_num="+ qb_num;
		}
		
		//글 삭제
		@RequestMapping(value="qna/deleteQna.do")
		public String deleteQna(@RequestParam("qb_num")int qb_num) {
		//로그 표시
			if(log.isDebugEnabled()) {
				log.debug("<<qn_num>> : " + qb_num);
				}

			//삭제 
			qnaService.deleteQna(qb_num);
				
			return "redirect:/qna/listQna.do";

			}
}
