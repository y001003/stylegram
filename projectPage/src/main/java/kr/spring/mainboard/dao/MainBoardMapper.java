package kr.spring.mainboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.mainboard.domain.MainBoardVO;

public interface MainBoardMapper {
	@Select("SELECT promainboard_seq.nextval FROM dual")
	public Integer selectMbnum();
	public void insert(MainBoardVO board);
	@Insert("INSERT INTO promaininfo VALUES (#{mb_num}, #{mb_topinfo}, #{mb_pantsinfo}, #{mb_capinfo}, #{mb_shoesinfo})")
	public void insertInfo(MainBoardVO board);
	public MainBoardVO selectBoard(Integer mb_num);//mb_num으로 진행
	@Select("SELECT * FROM (SELECT * FROM promainboard b JOIN promember m ON b.m_num = m.m_num where mb_num = #{mb_num})")
	public MainBoardVO selectMnum(Integer mb_num);
	public void update(MainBoardVO board);
	@Update("UPDATE promaininfo SET mb_topinfo = #{mb_topinfo}, mb_pantsinfo = #{mb_pantsinfo}, mb_capinfo = #{mb_capinfo}, mb_shoesinfo = #{mb_shoesinfo} WHERE mb_num = #{mb_num}")
	public void updateInfo(MainBoardVO board);
	@Delete("DELETE FROM promaininfo WHERE mb_num = #{mb_num}")
	public void deleteInfo(Integer num);
	@Delete("DELETE FROM promainreply WHERE mb_num = #{mb_num}")
	public void deleteReply(Integer num);
	@Delete("DELETE FROM prolikes WHERE mb_num = #{mb_num}")
	public void deleteLike(Integer num);
	public void delete(Integer num);
	public List<MainBoardVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	
	@Delete("DELETE FROM promainimages WHERE mb_num=#{mb_num}")
	public void deletePictures(Integer mb_num);
	
	
}
