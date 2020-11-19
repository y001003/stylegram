package kr.spring.flea.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.flea.dao.FleaMapper;
import kr.spring.flea.domain.FleaVO;

@Service("fleaService")
public class FleaServiceImpl implements FleaService {

	@Resource
	private FleaMapper fleaMapper;
	
	@Override
	public int selectFb_num() {
		return fleaMapper.selectFb_num();
	}
	
	@Override
	public List<FleaVO> selectList(Map<String, Object> map) {
		return fleaMapper.selectList(map);
	}
	
	@Override
	public int selectRowCount(Map<String, Object> map) {
		return fleaMapper.selectRowCount(map);
	}

	@Override
	public void insert(FleaVO flea) {
		fleaMapper.insert(flea);
		fleaMapper.insertinfo(flea);
	}
	
	@Override
	public FleaVO selectFlea(Integer fb_num) {
		return fleaMapper.selectFlea(fb_num);
	}

	@Override
	public void update(FleaVO flea) {
		fleaMapper.update(flea);
	}

	@Override
	public void delete(int fb_num) {
		fleaMapper.deletereply(fb_num);
		fleaMapper.delete(fb_num);
	}
	
	@Override
	public void deleteinfo(int fb_num) {
		fleaMapper.deleteinfo(fb_num);
	}

}
