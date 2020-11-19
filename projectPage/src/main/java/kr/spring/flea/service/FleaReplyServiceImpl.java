package kr.spring.flea.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.flea.dao.FleaReplyMapper;
import kr.spring.flea.domain.FleaReplyVO;

@Service
public class FleaReplyServiceImpl implements FleaReplyService {

	@Resource
	private FleaReplyMapper fleaReplyMapper;
	
	@Override
	public int replyCount(int fb_num) throws Exception {
		return fleaReplyMapper.replyCount(fb_num);
	}

	@Override
	public List<FleaReplyVO> replyList(Map<String, Object> map) throws Exception {
		return fleaReplyMapper.replyList(map);
	}

	@Override
	public FleaReplyVO replySelect(int fr_num) {
		return fleaReplyMapper.replySelect(fr_num);
	}

	@Override
	public void replyInsert(FleaReplyVO fleaReplyVO) {
		fleaReplyMapper.replyInsert(fleaReplyVO);
	}

	@Override
	public void replyUpdate(FleaReplyVO fleaReplyVO) {
		fleaReplyMapper.replyUpdate(fleaReplyVO);
	}

	@Override
	public void replyDelete(int fr_num) {
		fleaReplyMapper.replyDelete(fr_num);
	}
	
}
