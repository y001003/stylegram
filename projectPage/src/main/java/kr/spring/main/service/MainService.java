package kr.spring.main.service;

import java.util.List;
import java.util.Map;

import kr.spring.main.domain.MainVO;

public interface MainService {
	public List<MainVO> selectList(Map<String,Object> map);
}