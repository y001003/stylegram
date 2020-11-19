package kr.spring.mainboardlikes.service;

import kr.spring.mainboardlikes.domain.MainBoardLikeVO;

public interface MainBoardLikeService {
	public void likes(MainBoardLikeVO likeVO);
	public void unlikes(MainBoardLikeVO likeVO);
	public int likeCheck(MainBoardLikeVO likeVO);
	public int likeCount(Integer mb_num);
}