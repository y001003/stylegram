package kr.spring.member.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.mainboard.domain.MainBoardVO;
import kr.spring.member.dao.MemberMapper;
import kr.spring.member.domain.FollowVO;
import kr.spring.member.domain.MemberVO;

@Service("memberService")
public class MemberServiceimpl implements MemberService {
	
	@Resource
	private MemberMapper memberMapper;

	@Override
	public void insert(MemberVO memberVO) {
		//m_num값 불러와서 입력
		memberVO.setM_num(memberMapper.selectMnum());
		//promember 테이블에 데이터 입력
		memberMapper.insert(memberVO);
		//promember_detail 테이블에 데이터 입력
		memberMapper.insertDetail(memberVO);
	}

	@Override
	public MemberVO selectMember(Integer m_num) {
		return memberMapper.selectMember(m_num);
	}

	@Override
	public void update(MemberVO member) {
		memberMapper.update(member);
		memberMapper.updateNickname(member);
	}

	@Override
	public void delete(Integer m_num) {
		memberMapper.deleteAuth(m_num);
		memberMapper.delete(m_num);
	}

	@Override
	public MemberVO selectCheckMember(String id) {
		return memberMapper.selectCheckMember(id);
	}

	@Override
	public void updateAuth(MemberVO member) {
		memberMapper.updateAuth(member);
	}

	@Override
	public void updateAuth_detail(MemberVO member) {
		memberMapper.updateAuthDetail(member);
	}

	@Override
	public void updatePwd(MemberVO member) {
		memberMapper.updatePwd(member);
	}

	@Override
	public void deleteProfile(Integer m_num) {
		memberMapper.deleteProfile(m_num);
	}

	@Override
	public List<MemberVO> selectList(Map<String, Object> map) {
		return memberMapper.selectList(map);
	}

	@Override
	public List<MainBoardVO> selectBoardList(Map<String, Object> map) {
		return memberMapper.selectBoardList(map);
	}

	@Override
	public int selectMemberCount(Map<String, Object> map) {
		return memberMapper.selectMemberCount(map);
	}

	@Override
	public void manageUpdate(MemberVO member) {
		memberMapper.manageUpdate(member);
	}

	@Override
	public void follow(FollowVO follow) {
		memberMapper.follow(follow);
	}

	@Override
	public void unfollow(FollowVO follow) {
		memberMapper.unfollow(follow);
	}

	@Override
	public int followCheck(FollowVO follow) {
		return memberMapper.followCheck(follow);
	}

	@Override
	public List<MemberVO> selectFollower(Map<String, Object> map) {
		return memberMapper.selectFollower(map);
	}

	@Override
	public List<MemberVO> selectFollowing(Map<String, Object> map) {
		return memberMapper.selectFollowing(map);
	}

	@Override
	public int followerCount(Integer num) {
		return memberMapper.followerCount(num);
	}

	@Override
	public int followingCount(Integer num) {
		return memberMapper.followingCount(num);
	}
}