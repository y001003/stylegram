package kr.spring.qna.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.qna.domain.QnaVO;

public interface QnaMapper {
	@Select("SELECT proqna_seq.nextval FROM dual")
	public Integer selectqbNum();
	public List<QnaVO> listQna(Map<String,Object> map);
	public void writeQna(QnaVO qna);
	@Insert("INSERT INTO proqnainfo VALUES (#{qb_num}, #{qb_topinfo}, #{qb_pantsinfo}, #{qb_capinfo}, #{qb_shoesinfo})")
	public void writeQnaInfo(QnaVO qna);
	public QnaVO selectQna(Integer qb_num);
	@Update("UPDATE proqnainfo SET qb_topinfo = #{qb_topinfo}, qb_pantsinfo = #{qb_pantsinfo}, qb_capinfo = #{qb_capinfo}, qb_shoesinfo = #{qb_shoesinfo} WHERE qb_num = #{qb_num} ")
	public void modifyQnaInfo(QnaVO qna);
	public void modifyQna(QnaVO qna);
	public int selectRowCount(Map<String,Object> map);
	@Delete("DELETE FROM proqnainfo WHERE qb_num = #{qb_num}")
	public void deleteQnaInfo(Integer qb_num);
	@Delete("DELETE FROM proqnareple WHERE qb_num = #{qb_num}")
	public void deleteQnaReply(Integer qb_num);
	public void deleteQna(Integer qb_num);
}
