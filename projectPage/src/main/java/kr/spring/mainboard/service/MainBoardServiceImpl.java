package kr.spring.mainboard.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.mainboard.dao.MainBoardMapper;
import kr.spring.mainboard.domain.MainBoardVO;


@Service("mainBoardService") 
public class MainBoardServiceImpl implements MainBoardService{
	
	@Resource
	private MainBoardMapper mainBoardMapper;
	
	@Override
	public Integer selectMbnum() {
		// TODO Auto-generated method stub
		return mainBoardMapper.selectMbnum();
	}
	
	@Override
	public void insert(MainBoardVO board) {
		
		mainBoardMapper.insert(board);
		mainBoardMapper.insertInfo(board);
		
	}

	@Override
	public MainBoardVO selectBoard(Integer mb_num) {
		return mainBoardMapper.selectBoard(mb_num);
	}

	@Override
	public void update(MainBoardVO board) {
		mainBoardMapper.update(board);
		mainBoardMapper.updateInfo(board);
	}

	@Override
	public void delete(Integer mb_num) {
		mainBoardMapper.deleteInfo(mb_num);
		mainBoardMapper.deleteReply(mb_num);
		mainBoardMapper.deleteLike(mb_num);
		mainBoardMapper.deletePictures(mb_num);
		mainBoardMapper.delete(mb_num);
	}

	@Override
	public List<MainBoardVO> selectList(Map<String, Object> map) {
		return mainBoardMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return mainBoardMapper.selectRowCount(map);
	}

	@Override
	public MainBoardVO selectMnum(Integer mb_num) {
		return mainBoardMapper.selectMnum(mb_num);
	}


}
