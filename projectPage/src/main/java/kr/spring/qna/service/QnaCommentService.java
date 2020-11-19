package kr.spring.qna.service;

import java.util.List;
import java.util.Map;

import kr.spring.qna.domain.QnaCommentVO;

public interface QnaCommentService {
	public int commentCount(int qb_num) throws Exception;//댓글 갯수
	public List<QnaCommentVO> commentList(Map<String,Object> map) throws Exception;//댓글 목록
	public QnaCommentVO commentSelect(int qr_num);
	public int commentInsert(QnaCommentVO qnaCommentVO);//댓글 작성
	public int commentUpdate(QnaCommentVO qnaCommentVO);//댓글 수정
	public int commentDelete(int qr_num);
}
