package kr.spring.main.dao;

import java.util.List;
import java.util.Map;

import kr.spring.main.domain.MainVO;

public interface MainMapper {
	public List<MainVO> selectList(Map<String,Object> map);
}