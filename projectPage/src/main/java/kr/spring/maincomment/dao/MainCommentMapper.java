package kr.spring.maincomment.dao;

import java.util.List;
import java.util.Map;

import kr.spring.maincomment.domain.MainCommentVO;

public interface MainCommentMapper {
	public int commentCount(int mb_num) throws Exception;//댓글 갯수
	public List<MainCommentVO> commentList(Map<String,Object> map) throws Exception;//댓글 목록
	public MainCommentVO commentSelect(int mr_num);
	public int commentInsert(MainCommentVO mainCommentVO);//댓글 작성
	public int commentUpdate(MainCommentVO mainCommentVO);//댓글 수정
	public int commentDelete(int mr_num);
	
}
