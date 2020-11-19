package kr.spring.flea.service;

import java.util.List;
import java.util.Map;

import kr.spring.flea.domain.FleaVO;

public interface FleaService {
	public int selectFb_num();
	public List<FleaVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public void insert(FleaVO flea);
	public FleaVO selectFlea(Integer fb_num);
	public void update(FleaVO flea);
	public void delete(int fb_num);
	public void deleteinfo(int fb_num);
}
