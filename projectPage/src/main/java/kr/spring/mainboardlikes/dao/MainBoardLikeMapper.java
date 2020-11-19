package kr.spring.mainboardlikes.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.mainboardlikes.domain.MainBoardLikeVO;

public interface MainBoardLikeMapper {
	@Select("SELECT COUNT(*) FROM prolikes WHERE mb_num = #{mb_num} and m_num = #{m_num}")
	public int likeCheck(MainBoardLikeVO likeVO);
	@Insert("INSERT INTO prolikes VALUES (#{mb_num}, #{m_num})")
	public void likes(MainBoardLikeVO likeVO);
	@Delete("DELETE FROM prolikes WHERE mb_num = #{mb_num} AND m_num = #{m_num}")
	public void unlikes(MainBoardLikeVO likeVO);
	@Select("SELECT COUNT(*) FROM prolikes WHERE mb_num = #{mb_num}")
	public int likeCount(Integer mb_num);
}