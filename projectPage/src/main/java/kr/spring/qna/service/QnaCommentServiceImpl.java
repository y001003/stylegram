package kr.spring.qna.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.qna.dao.QnaCommentMapper;
import kr.spring.qna.domain.QnaCommentVO;

@Service("qnaCommentService")
public class QnaCommentServiceImpl implements QnaCommentService{

	@Resource
	private QnaCommentMapper qnaCommentMapper;
	
	@Override
	public int commentCount(int qb_num) throws Exception {
		// TODO Auto-generated method stub
		return qnaCommentMapper.commentCount(qb_num);
	}

	@Override
	public List<QnaCommentVO> commentList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return qnaCommentMapper.commentList(map);
	}

	@Override
	public QnaCommentVO commentSelect(int qr_num) {
		// TODO Auto-generated method stub
		return qnaCommentMapper.commentSelect(qr_num);
	}

	@Override
	public int commentInsert(QnaCommentVO qnaCommentVO) {
		// TODO Auto-generated method stub
		return qnaCommentMapper.commentInsert(qnaCommentVO);
	}

	@Override
	public int commentUpdate(QnaCommentVO qnaCommentVO) {
		// TODO Auto-generated method stub
		return qnaCommentMapper.commentUpdate(qnaCommentVO);
	}

	@Override
	public int commentDelete(int qr_num) {
		// TODO Auto-generated method stub
		return qnaCommentMapper.commentDelete(qr_num);
	}

}
