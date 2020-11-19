package kr.spring.member.service;

import java.util.List;
import java.util.Map;

import kr.spring.mainboard.domain.MainBoardVO;
import kr.spring.member.domain.FollowVO;
import kr.spring.member.domain.MemberVO;

public interface MemberService {
	//회원정보
	public void insert(MemberVO memberVO);
	public MemberVO selectCheckMember(String id);
	public MemberVO selectMember(Integer m_num);
	public void updateAuth(MemberVO member);
	public void updateAuth_detail(MemberVO member);
	public void deleteProfile(Integer m_num);
	public void update(MemberVO member);
	public void updatePwd(MemberVO member);
	public void manageUpdate(MemberVO member);
	public void delete(Integer m_num);
	
	public List<MainBoardVO> selectBoardList(Map<String, Object> map);
	public List<MemberVO> selectList(Map<String, Object> map);
	public int selectMemberCount(Map<String, Object> map);
	
	//팔로우
	public void follow(FollowVO follow);
	public void unfollow(FollowVO follow);
	public int followCheck(FollowVO follow);
	
	//팔로우 리스트
	public List<MemberVO> selectFollower(Map<String, Object> map);
	public List<MemberVO> selectFollowing(Map<String, Object> map);
	public int followerCount(Integer num);
	public int followingCount(Integer num);
}