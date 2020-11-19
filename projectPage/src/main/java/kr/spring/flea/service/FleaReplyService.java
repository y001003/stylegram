package kr.spring.flea.service;

import java.util.List;
import java.util.Map;

import kr.spring.flea.domain.FleaReplyVO;

public interface FleaReplyService {
	public int replyCount(int fb_num) throws Exception;
	public List<FleaReplyVO> replyList(Map<String,Object> map) throws Exception;
	public FleaReplyVO replySelect(int fr_num);
	public void replyInsert(FleaReplyVO fleaReplyVO);
	public void replyUpdate(FleaReplyVO fleaReplyVO);
	public void replyDelete(int fr_num);
}
