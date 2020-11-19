package kr.spring.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.spring.mainboard.domain.MainBoardVO;
import kr.spring.member.domain.FollowVO;
import kr.spring.member.domain.MemberVO;
import kr.spring.member.service.MemberService;
import kr.spring.util.AuthCheckException;
import kr.spring.util.AuthException;
import kr.spring.util.PagingUtil;

@Controller
public class MemberController {

	@Resource
	private MemberService memberService;
	
	//자바빈 초기화
	@ModelAttribute
	public MemberVO initCommand() {
		return new MemberVO();
	}
	
	//회원가입 폼 호출
	@RequestMapping(value="/member/register.do", method=RequestMethod.GET)
	public String registerForm() {
		return "registerForm";
	}
	//회원가입 처리
	@RequestMapping(value="/member/register.do", method=RequestMethod.POST)
	public String registerSubmit(@Valid MemberVO memberVO, BindingResult result) {
		
		//유효성 체크
		if(result.hasFieldErrors("m_id") || result.hasFieldErrors("m_name") || result.hasFieldErrors("m_passwd")) {
			return registerForm();
		}
		
		//회원가입처리
		memberService.insert(memberVO);
		
		return "redirect:/main/main.do";
	}
	
	
	//로그인 폼 호출
	@RequestMapping(value="/member/login.do", method=RequestMethod.GET)
	public String loginForm() {
		return "loginForm";
	}
	//로그인 처리
	@RequestMapping(value="/member/login.do", method=RequestMethod.POST)
	public String loginSubmit(@Valid MemberVO memberVO, BindingResult result, HttpSession session) {
		
		//유효성 체크
		if(result.hasFieldErrors("m_id") || result.hasFieldErrors("m_passwd")) {
			return loginForm();
		}
		
		try {
			MemberVO member = memberService.selectCheckMember(memberVO.getM_id());
			
			boolean checkPasswd = false;
			boolean checkAuth = false;
			
			if(member!=null) {
				//비밀번호 체크
				checkPasswd = member.isCheckedPasswd(memberVO.getM_passwd());
			}
			
			if(member!=null) {
				checkAuth = member.isCheckedAuth(memberVO.getM_auth());
			}
			
			if(checkPasswd) {
				//회원상태 체크
				if(checkAuth) {
				//로그인 성공
				session.setAttribute("m_num", member.getM_num());
				session.setAttribute("m_id", member.getM_id());
				session.setAttribute("m_nickname", member.getM_nickname());
				session.setAttribute("m_auth", member.getM_auth());
				session.setAttribute("m_image", member.getM_image());
				session.setAttribute("m_name", member.getM_name());
				session.setAttribute("m_address", member.getM_address());
				session.setAttribute("m_phone", member.getM_phone());
				session.setAttribute("m_public", member.getM_public());
				
				int followingCount = memberService.followingCount(member.getM_num());
				if((Integer)session.getAttribute("m_auth") == 4) {
					followingCount = -1;
				}
				session.setAttribute("following", followingCount);
			
				return "redirect:/main/main.do";
				
				}else {
					//로그인 불가능 회원상태
					throw new AuthException();
				}
			}else {
				//로그인 실패
				throw new AuthCheckException();
			}
		} catch (AuthException e) {
			//로그인 실패시 에러코드를 지정하고 폼을 호출함
			result.reject("authError");
			
			return loginForm();
		} catch (AuthCheckException e) {
			result.reject("invalidIdOrPassword");
			
			return loginForm();
		}
	}
	
	//로그아웃
	@RequestMapping("/member/logout.do")
	public String logout(HttpSession session) {
		//로그아웃 처리
		session.invalidate();
		
		return "redirect:/main/main.do";
	}
	
	//본인인증 폼 호출
	@RequestMapping(value="/member/auth.do", method=RequestMethod.GET)
	public String authForm(HttpSession session, Model model) {
		
		int m_num = (Integer)session.getAttribute("m_num");
		
		MemberVO memberVO = memberService.selectMember(m_num);
		
		model.addAttribute("memberVO", memberVO);
		
		return "authForm";
	}
	
	//본인인증 처리
	@RequestMapping(value="/member/auth.do", method=RequestMethod.POST)
	public String authSubmit(@Valid MemberVO memberVO, BindingResult result, HttpSession session) {
		
		if(result.hasFieldErrors("m_phone") || result.hasFieldErrors("m_address") || result.hasFieldErrors("m_postcode") || result.hasFieldErrors("m_detailaddress")) {
			return "authForm";
		}
		
		//본인인증 처리
		//promember 테이블
		memberService.updateAuth(memberVO);
		//promember_detail 테이블
		memberService.updateAuth_detail(memberVO);
		
		MemberVO member = memberService.selectMember(memberVO.getM_num());
		
		session.setAttribute("m_num", member.getM_num());
		session.setAttribute("m_id", member.getM_id());
		session.setAttribute("m_nickname", member.getM_nickname());
		session.setAttribute("m_auth", member.getM_auth());
		session.setAttribute("m_image", member.getM_image());
		session.setAttribute("m_address", member.getM_address());
		session.setAttribute("m_phone", member.getM_phone());
		session.setAttribute("m_public", member.getM_public());
		
		return "redirect:/main/main.do";
	}
	
	//회원 정보 페이지
	@RequestMapping("/member/detail.do")
	public ModelAndView detailForm(HttpSession session) {
		
		int m_num = (Integer)session.getAttribute("m_num");
		//회원정보
		MemberVO member = memberService.selectMember(m_num);
		
		//글정보
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("m_num", m_num);
		map.put("f_activeuser", (Integer)session.getAttribute("m_num"));
		map.put("f_passiveuser", (Integer)session.getAttribute("m_num"));
		
		List<MainBoardVO> writeList = memberService.selectBoardList(map);
		List<MemberVO> followerList = memberService.selectFollower(map);
		List<MemberVO> followingList = memberService.selectFollowing(map);
		
		int followerCount = memberService.followerCount((Integer)session.getAttribute("m_num"));
		int followingCount = memberService.followingCount((Integer)session.getAttribute("m_num"));
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("detailForm");
		mav.addObject("member", member);
		mav.addObject("writeList", writeList);
		mav.addObject("followerCount", followerCount);
		mav.addObject("followerList", followerList);
		mav.addObject("followingCount", followingCount);
		mav.addObject("followingList", followingList);
		
		return mav;
	}
	
	//다른사람 페이지 호출
	@RequestMapping("/member/anotherPage.do")
	public ModelAndView anotherPageForm(@RequestParam("m_num") int m_num, HttpSession session) {
		
		MemberVO member = memberService.selectMember(m_num);
		
		FollowVO follow = new FollowVO();
		if(session.getAttribute("m_num") == null) {
			return new ModelAndView("redirect:/member/login.do");
		}else {
			follow.setF_activeuser((Integer)session.getAttribute("m_num"));
		}
		follow.setF_passiveuser(m_num);
		
		int followCheck = memberService.followCheck(follow);
		
		//글정보
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("m_num", m_num);
		map.put("f_activeuser", m_num); 
		map.put("f_passiveuser", m_num);
		
		List<MainBoardVO> writeList = memberService.selectBoardList(map);
		List<MemberVO> followerList = memberService.selectFollower(map);
		List<MemberVO> followingList = memberService.selectFollowing(map);
		
		int followerCount = memberService.followerCount(m_num);
		int followingCount = memberService.followingCount(m_num);
		
		ModelAndView mav = new ModelAndView();
		
		System.out.println(session.getAttribute("m_num"));
		if(member.getM_num() == (Integer)session.getAttribute("m_num")) {
			mav.setViewName("redirect:detail.do");
			return mav;
		}
		mav.setViewName("anotherForm");
		mav.addObject("member", member);
		mav.addObject("followCheck", followCheck);
		mav.addObject("writeList", writeList);
		mav.addObject("followerList", followerList);
		mav.addObject("followingList", followingList);
		mav.addObject("followingCount", followingCount);
		mav.addObject("followerCount", followerCount);
		
		return mav;
	}
	
	//이미지 보기(마이페이지)
	@RequestMapping("/member/imageView.do")
	public ModelAndView viewImage(HttpSession session) {
		
		int m_num = (Integer)session.getAttribute("m_num");
		
		MemberVO member = memberService.selectMember(m_num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", member.getM_image());
		mav.addObject("filename", member.getM_filename());
		
		return mav;
	}
	
	//이미지보기(개인페이지)
	@RequestMapping("/member/imageViewProfile.do")
	public ModelAndView viewImage(@RequestParam("m_num") int m_num) {
		
		MemberVO member = memberService.selectMember(m_num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", member.getM_image());
		mav.addObject("filename", member.getM_filename());
		
		return mav;
	}
	
	//프로필사진 삭제
	@RequestMapping("/member/profileDelete.do")
	public String profileDelete(HttpSession session, Model model) {
		
		MemberVO member = memberService.selectMember((Integer)session.getAttribute("m_num"));
		
		memberService.deleteProfile(member.getM_num());
		
		model.addAttribute("member", member);
		
		return "redirect:/member/detail.do";
	}
	
	//회원정보수정 폼 호출
	@RequestMapping(value="/member/update.do", method=RequestMethod.GET)
	public String updateForm(HttpSession session, Model model) {
		int m_num = (Integer)session.getAttribute("m_num");
		
		MemberVO member = memberService.selectMember(m_num);
		
		model.addAttribute("memberVO", member);
		
		return "updateForm";
	}
	
	//회원정보수정 처리
	@RequestMapping(value="/member/update.do", method=RequestMethod.POST)
	public String updateSubmit(@Valid MemberVO member, BindingResult result, HttpSession session, Model model) {
		
		if(result.hasFieldErrors("m_address") || result.hasFieldErrors("m_postcode") || result.hasFieldErrors("m_detailaddress")) {
			return "updateForm";
		}
		
		//회원정보수정 처리
		memberService.update(member);
		
		session.setAttribute("m_image", member.getM_image());
		session.setAttribute("m_nickname", member.getM_nickname());
		session.setAttribute("m_address", member.getM_address());
		session.setAttribute("m_public", member.getM_public());
		
		int m_num = (Integer)session.getAttribute("m_num");
		
		MemberVO memberVO = memberService.selectMember(m_num);
		
		model.addAttribute("member", memberVO);
		
		return "redirect:/member/detail.do";
	}
	
	//비밀번호변경 폼 호출
	@RequestMapping(value="/member/changepwd.do", method=RequestMethod.GET)
	public String changepwdForm(HttpSession session, Model model) {
		int m_num = (Integer)session.getAttribute("m_num");
		
		MemberVO memberVO = new MemberVO();
		memberVO.setM_num(m_num);
		
		model.addAttribute("memberVO", memberVO);
		
		return "changepwdForm";
	}
	
	//비밀번호 변경 처리
	@RequestMapping(value="/member/changepwd.do", method=RequestMethod.POST)
	public String changepwdSubmit(@Valid MemberVO member, BindingResult result, HttpSession session, Model model, RedirectAttributes redirect) {
		
		if(result.hasFieldErrors("m_passwd")) {
			return "changepwdForm";
		}
		
		//비밀번호 확인
		MemberVO memberVO = memberService.selectMember(member.getM_num());
		
		if(!memberVO.getM_passwd().equals(member.getOld_passwd())) {
			result.rejectValue("old_passwd", "invalidPwd");
			
			return "changepwdForm";
		}
		
		//비밀번호 변경 처리
		memberService.updatePwd(member);
		
		redirect.addFlashAttribute("pwdresult", 1);
		
		//로그아웃
		session.invalidate();
		
		return "redirect:/main/main.do";
	}	
	
	//회원탈퇴 폼 호출
	@RequestMapping(value="/member/delete.do", method=RequestMethod.GET)
	public String deleteForm(HttpSession session, Model model) {
		
		int m_num = (Integer)session.getAttribute("m_num");
		
		MemberVO memberVO = new MemberVO();
		memberVO.setM_num(m_num);
		
		model.addAttribute("memberVO", memberVO);
		
		return "deleteForm";
	}
	
	//회원탈퇴 처리
	@RequestMapping(value="/member/delete.do", method=RequestMethod.POST)
	public String deleteSubmit(@Valid MemberVO member, BindingResult result, HttpSession session) {
		
		if(result.hasFieldErrors("m_passwd")) {
			return "deleteForm";
		}
		
		try {
			MemberVO memberVO = memberService.selectMember(member.getM_num());
			
			boolean check = false;
			
			if(memberVO != null) {
				//비밀번호 인증
				check = memberVO.isCheckedPasswd(member.getM_passwd());
			}
			
			if(check) {
				//인증 성공
				//회원탈퇴 처리
				memberService.delete(member.getM_num());
				
				//로그아웃
				session.invalidate();
				
				return "redirect:/main/main.do";
			}else {
				//인증 실패
				throw new AuthCheckException();
			}
			
		} catch (AuthCheckException e) {
			//인증 실패시 에러코드 지정하고 폼 호출
			result.rejectValue("m_passwd", "invalidPwd");
			return "deleteForm";
		}
	}
	
	//관리자 페이지 호출
	@RequestMapping("/member/admin.do")
	public String adminForm() {
		return "adminForm";
	}
	
	//관리자 페이지 - 회원관리 페이지 호출
	@RequestMapping("/member/managePage.do")
	public ModelAndView manageForm(@RequestParam(value="pageNum",defaultValue="1") int currentPage) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		int count = memberService.selectMemberCount(map);
		
		int rowCount = 12; //화면에 표시될 회원수
		int pageCount = 10; //화면에 표시될 페이지 수
		
		PagingUtil page = new PagingUtil(currentPage, count, rowCount, pageCount, "managePage.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<MemberVO> memberList = null;
		if(count > 0 ) {
			memberList = memberService.selectList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("manageForm");
		mav.addObject("count", count);
		mav.addObject("memberList", memberList);
		mav.addObject("pagingHtml",page.getPagingHtml());
		
		return mav;
	}
	
	//관리자 페이지 - 회원상세페이지 호출
	@RequestMapping(value="/member/manageDetail.do", method=RequestMethod.GET)
	public String manageDetailForm(@RequestParam("m_num") int m_num, Model model) {
		
		MemberVO member = memberService.selectMember(m_num);
		
		model.addAttribute("memberVO", member);
		
		return "manageDetailForm";
	}
	
	//관리자 페이지 - 회원상세페이지 정보 변경 처리
	@RequestMapping(value="/member/manageDetail.do", method=RequestMethod.POST)
	public String manageDetailSubmit(@Valid MemberVO member, BindingResult result) {
		
		memberService.manageUpdate(member);
		
		return "redirect:/member/managePage.do";
	}
	
	//팔로우
	@RequestMapping("/member/follow.do")
	public String follow(@RequestParam("m_num") int m_num, HttpSession session) {
		
		FollowVO follow = new FollowVO();
		
		follow.setF_activeuser((Integer)session.getAttribute("m_num"));
		follow.setF_passiveuser(m_num);
		
		memberService.follow(follow);
		
		return "redirect:/member/anotherPage.do?m_num="+m_num;
	}
	
	//언팔로우
	@RequestMapping("/member/unfollow.do")
	public String unfollow(@RequestParam("m_num") int m_num, HttpSession session) {
		
		FollowVO follow = new FollowVO();
		
		follow.setF_activeuser((Integer)session.getAttribute("m_num"));
		follow.setF_passiveuser(m_num);
		
		memberService.unfollow(follow);
		
		return "redirect:/member/anotherPage.do?m_num="+m_num;
	}
}	