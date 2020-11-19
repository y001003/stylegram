package kr.spring.notice.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.notice.domain.NoticeVO;

public interface NoticeMapper {
	public List<NoticeVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	@Insert("INSERT INTO pronotice (nb_num,nb_title,nb_content,nb_file1,nb_filename1,nb_file2,nb_filename2,m_num) VALUES (pronotice_seq.nextval,#{nb_title},#{nb_content},#{nb_file1},#{nb_filename1},#{nb_file2},#{nb_filename2},#{m_num})")
	public void insert(NoticeVO notice);
	@Select("SELECT * FROM pronotice b JOIN promember m ON b.m_num=m.m_num WHERE b.nb_num=#{nb_num}")
	public NoticeVO selectNotice(Integer nb_num);
	public void update(NoticeVO notice);
	@Delete("DELETE FROM pronotice WHERE nb_num=#{nb_num}")
	public void delete(Integer nb_num);
}