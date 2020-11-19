package kr.spring.maincomment.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.maincomment.dao.MainCommentMapper;
import kr.spring.maincomment.domain.MainCommentVO;

@Service("mainCommentService")
public class MainCommentServiceImpl implements MainCommentService{
	
	@Resource
	private MainCommentMapper mainCommentMapper;

	@Override
	public int commentCount(int mb_num) throws Exception {
		// TODO Auto-generated method stub
		return mainCommentMapper.commentCount(mb_num);
	}

	@Override
	public List<MainCommentVO> commentList(Map<String,Object> map) throws Exception {
		// TODO Auto-generated method stub
		return mainCommentMapper.commentList(map);
	}
	
	@Override
	public MainCommentVO commentSelect(int mr_num) {
		// TODO Auto-generated method stub
		return mainCommentMapper.commentSelect(mr_num);
	}


	@Override
	public void commentInsert(MainCommentVO mainCommentVO) {
		// TODO Auto-generated method stub
		mainCommentMapper.commentInsert(mainCommentVO);
	}

	
	
	@Override
	public int commentUpdate(MainCommentVO mainCommentVO) {
		// TODO Auto-generated method stub
		return mainCommentMapper.commentUpdate(mainCommentVO);
	}

	@Override
	public int commentDelete(int mr_num) {
		// TODO Auto-generated method stub
		return mainCommentMapper.commentDelete(mr_num);
	}




}
