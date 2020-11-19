package kr.spring.notice.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.notice.dao.NoticeMapper;
import kr.spring.notice.domain.NoticeVO;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService{

	@Resource
	NoticeMapper noticeMapper;
	
	@Override
	public List<NoticeVO> selectList(Map<String, Object> map) {
		return noticeMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return noticeMapper.selectRowCount(map);
	}

	@Override
	public void insert(NoticeVO notice) {
		noticeMapper.insert(notice);
	}

	@Override
	public NoticeVO selectNotice(Integer nb_num) {
		
		return noticeMapper.selectNotice(nb_num);
	}

	@Override
	public void update(NoticeVO notice) {
		noticeMapper.update(notice);
	}

	@Override
	public void delete(Integer nb_num) {
		noticeMapper.delete(nb_num);
	}
	
	
}
