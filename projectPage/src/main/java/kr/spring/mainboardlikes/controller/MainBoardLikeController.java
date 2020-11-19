package kr.spring.mainboardlikes.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.spring.mainboardlikes.domain.MainBoardLikeVO;
import kr.spring.mainboardlikes.service.MainBoardLikeService;

@Controller
public class MainBoardLikeController {

	@Resource
	private MainBoardLikeService likeService;
	
	//자바빈 초기화
	@ModelAttribute
	public MainBoardLikeVO initCommnad() {
		return new MainBoardLikeVO();
	}
	
	//좋아요
	@RequestMapping("/like/like.do")
	public String likes(@RequestParam("mb_num") int mb_num, HttpSession session) {
		
		MainBoardLikeVO likeVO = new MainBoardLikeVO();
		
		likeVO.setMb_num(mb_num);
		likeVO.setM_num((Integer)session.getAttribute("m_num"));
		
		likeService.likes(likeVO);
		
		return "redirect:/mainBoard/detail.do?mb_num="+mb_num;
	}
	
	//좋아요 취소
	@RequestMapping("/like/unlike.do")
	public String unlikes(@RequestParam("mb_num") int mb_num, HttpSession session) {
		
		MainBoardLikeVO likeVO = new MainBoardLikeVO();
		
		likeVO.setMb_num(mb_num);
		likeVO.setM_num((Integer)session.getAttribute("m_num"));
		
		likeService.unlikes(likeVO);
		
		return "redirect:/mainBoard/detail.do?mb_num="+mb_num;
	}
}