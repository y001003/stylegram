package kr.spring.flea.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.flea.domain.FleaVO;

public interface FleaMapper {
	@Select("SELECT proflea_seq.nextval FROM dual")
	public int selectFb_num();
	public List<FleaVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	@Insert("INSERT INTO profleaboard (fb_num,fb_title,fb_usernum,fb_photo,fb_filename,fb_price,fb_content) VALUES (#{fb_num},#{fb_title},#{fb_usernum},#{fb_photo},#{fb_filename},#{fb_price},#{fb_content})")
	public void insert(FleaVO flea);
	@Insert("INSERT INTO profleainfo (fb_num,fb_topcheck,fb_bottomcheck,fb_hatcheck,fb_shoescheck) VALUES (#{fb_num},#{fb_topchecknum},#{fb_bottomchecknum},#{fb_hatchecknum},#{fb_shoeschecknum})")
	public void insertinfo(FleaVO flea);
	@Select("SELECT * FROM profleaboard b JOIN profleainfo i USING (fb_num) JOIN promember m ON b.fb_usernum = m.m_num JOIN promember_detail d ON b.fb_usernum = d.m_num WHERE fb_num=#{fb_num}")
	public FleaVO selectFlea(Integer fb_num);
	public void update(FleaVO flea);
	@Delete("DELETE FROM profleaboard WHERE fb_num=#{fb_num}")
	public void delete(int fb_num);
	@Delete("DELETE FROM profleareply WHERE fb_num=#{fb_num}")
	public void deletereply(int fb_num);
	@Delete("DELETE FROM profleainfo WHERE fb_num=#{fb_num}")
	public void deleteinfo(int fb_num);
}
