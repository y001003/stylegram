package kr.spring.qna.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.qna.dao.QnaMapper;
import kr.spring.qna.domain.QnaVO;

@Service("qnaService")
public class QnaServiceimpl implements QnaService{

	@Resource
	private QnaMapper mapperQna;
	
	@Override
	public void writeQna(QnaVO qna) {
		qna.setQb_num(mapperQna.selectqbNum());
		mapperQna.writeQna(qna);
		mapperQna.writeQnaInfo(qna);
	}

	@Override
	public QnaVO selectQna(Integer qb_num) {
		return mapperQna.selectQna(qb_num);
	}

	@Override
	public void modifyQna(QnaVO qna) {
		mapperQna.modifyQna(qna);
		mapperQna.modifyQnaInfo(qna);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return mapperQna.selectRowCount(map);
	}

	@Override
	public List<QnaVO> listQna(Map<String, Object> map) {
		return mapperQna.listQna(map);
	}
	
	@Override
	public void deleteQna(Integer qb_num) {
		mapperQna.deleteQnaInfo(qb_num);
		mapperQna.deleteQnaReply(qb_num);
		mapperQna.deleteQna(qb_num);
	}

}