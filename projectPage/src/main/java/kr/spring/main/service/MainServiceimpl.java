package kr.spring.main.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.main.dao.MainMapper;
import kr.spring.main.domain.MainVO;

@Service("mainService")
public class MainServiceimpl implements MainService {

	@Resource
	private MainMapper mainMapper; 
	
	@Override
	public List<MainVO> selectList(Map<String, Object> map) {
		return mainMapper.selectList(map);
	}
}