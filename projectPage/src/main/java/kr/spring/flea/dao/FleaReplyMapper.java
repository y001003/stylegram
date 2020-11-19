package kr.spring.flea.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.flea.domain.FleaReplyVO;

public interface FleaReplyMapper {
	@Select("SELECT COUNT(*) FROM profleareply WHERE fb_num=#{fb_num}")
	public int replyCount(int fb_num) throws Exception;
	public List<FleaReplyVO> replyList(Map<String,Object> map) throws Exception;
	@Select("SELECT * FROM profleareply WHERE fr_num=#{fr_num}")
	public FleaReplyVO replySelect(int fr_num);
	@Insert("INSERT INTO profleareply (fr_num,fb_num,m_num,fr_content,fr_regdate) VALUES (profleareply_seq.nextval,#{fb_num},#{m_num},#{fr_content},SYSDATE)") 
	public void replyInsert(FleaReplyVO fleaReplyVO);
	@Update("UPDATE profleareply SET fr_content=#{fr_content} WHERE fr_num=#{fr_num}")
	public void replyUpdate(FleaReplyVO fleaReplyVO);
	@Delete("DELETE FROM profleareply WHERE fr_num=#{fr_num}")
	public void replyDelete(int fr_num);
}
