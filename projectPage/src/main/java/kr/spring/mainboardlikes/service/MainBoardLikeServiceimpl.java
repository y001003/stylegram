package kr.spring.mainboardlikes.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.mainboardlikes.dao.MainBoardLikeMapper;
import kr.spring.mainboardlikes.domain.MainBoardLikeVO;

@Service("mainBoardLikeService")
public class MainBoardLikeServiceimpl implements MainBoardLikeService {

	@Resource
	private MainBoardLikeMapper likeMapper;
	
	@Override
	public void likes(MainBoardLikeVO likeVO) {
		likeMapper.likes(likeVO);
	}

	@Override
	public void unlikes(MainBoardLikeVO likeVO) {
		likeMapper.unlikes(likeVO);
	}

	@Override
	public int likeCheck(MainBoardLikeVO likeVO) {
		return likeMapper.likeCheck(likeVO);
	}

	@Override
	public int likeCount(Integer mb_num) {
		return likeMapper.likeCount(mb_num);
	}
}