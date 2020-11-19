package kr.spring.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.mainboard.domain.MainBoardVO;
import kr.spring.member.domain.FollowVO;
import kr.spring.member.domain.MemberVO;

public interface MemberMapper {
	@Select("SELECT promember_seq.nextval FROM dual")
	public Integer selectMnum();
	@Insert("INSERT INTO promember (m_num, m_id, m_nickname) VALUES (#{m_num}, #{m_id}, #{m_nickname})")
	public void insert(MemberVO memberVO);
	@Insert("INSERT INTO promember_detail (m_num, m_name, m_passwd) VALUES (#{m_num}, #{m_name}, #{m_passwd})")
	public void insertDetail(MemberVO memberVO);
	@Select("SELECT * FROM promember m JOIN promember_detail d ON m.m_num = d.m_num WHERE m_id=#{m_id}")
	public MemberVO selectCheckMember(String id);
	@Select("SELECT * FROM promember m JOIN promember_detail d ON m.m_num = d.m_num WHERE m.m_num=#{m_num}")
	public MemberVO selectMember(Integer m_num);
	@Update("UPDATE promember SET m_auth=3 WHERE m_num=#{m_num}")
	public void updateAuth(MemberVO member);
	@Update("UPDATE promember_detail SET m_phone=#{m_phone}, m_postcode=#{m_postcode}, m_address=#{m_address}, m_detailaddress=#{m_detailaddress} WHERE m_num=#{m_num}")
	public void updateAuthDetail(MemberVO member);
	@Update("UPDATE promember_detail SET m_image=NULL WHERE m_num=#{m_num}")
	public void deleteProfile(Integer m_num);
	public void update(MemberVO member);
	@Update("UPDATE promember SET m_nickname=#{m_nickname} WHERE m_num=#{m_num}")
	public void updateNickname(MemberVO member);
	@Update("UPDATE promember_detail SET m_passwd=#{m_passwd} WHERE m_num=#{m_num}")
	public void updatePwd(MemberVO member);
	@Update("UPDATE promember SET m_auth=0 WHERE m_num=#{m_num}")
	public void deleteAuth(Integer m_num);
	@Update("UPDATE promember SET m_auth=#{m_auth} WHERE m_num=#{m_num}")
	public void manageUpdate(MemberVO member);
	@Delete("DELETE FROM promember_detail WHERE m_num=#{m_num}")
	public void delete(Integer m_num);
	
	public List<MainBoardVO> selectBoardList(Map<String, Object> map);
	public List<MemberVO> selectList(Map<String, Object> map);
	public int selectMemberCount(Map<String, Object> map);
	
	//팔로우
	@Insert("INSERT INTO profollow (f_activeuser, f_passiveuser) VALUES (#{f_activeuser}, #{f_passiveuser})")
	public void follow(FollowVO follow);
	@Delete("DELETE FROM profollow WHERE f_activeuser=#{f_activeuser} AND f_passiveuser=#{f_passiveuser}")
	public void unfollow(FollowVO follow);
	@Select("SELECT COUNT(*) FROM profollow WHERE f_activeuser=#{f_activeuser} AND f_passiveuser=#{f_passiveuser}")
	public int followCheck(FollowVO follow);
	
	//팔로우 리스트
	public List<MemberVO> selectFollower(Map<String, Object> map);
	public List<MemberVO> selectFollowing(Map<String, Object> map);
	@Select("SELECT COUNT(*) FROM promember m JOIN promember_detail d ON m.m_num = d.m_num JOIN profollow f ON m.m_num = f.f_activeuser WHERE f.f_activeuser = #{f_activeuser}")
	public int followerCount(Integer num);
	@Select("SELECT COUNT(*) FROM promember m JOIN promember_detail d ON m.m_num = d.m_num JOIN profollow f ON m.m_num = f.f_passiveuser WHERE f.f_passiveuser = #{f_passiveuser}")
	public int followingCount(Integer num);
}