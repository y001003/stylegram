package kr.spring.mainboard.service;

import java.util.List;
import java.util.Map;

import kr.spring.mainboard.domain.MainBoardVO;

public interface MainBoardService {
	public Integer selectMbnum();
	public void insert(MainBoardVO board);
	public MainBoardVO selectBoard(Integer mb_num);//mb_num으로 진행
	public MainBoardVO selectMnum(Integer mb_num);
	public void update(MainBoardVO board);
	public void delete(Integer mb_num);
	public List<MainBoardVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
}
 