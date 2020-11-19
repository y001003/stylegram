package kr.spring.qna.service;

import java.util.List;
import java.util.Map;

import kr.spring.qna.domain.QnaVO;

public interface QnaService {
	
	public List<QnaVO> listQna(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public void writeQna(QnaVO qna);	
	public QnaVO selectQna(Integer qb_num);
	public void modifyQna(QnaVO qna);
	public void deleteQna(Integer qb_num);
	
	
}