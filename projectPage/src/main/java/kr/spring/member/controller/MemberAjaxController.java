package kr.spring.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.member.domain.MemberVO;
import kr.spring.member.service.MemberService;

@Controller
public class MemberAjaxController {

	@Resource
	MemberService memberService;
	
	@RequestMapping("/member/confirmId.do")
	@ResponseBody
	public Map<String, String> prcess(@RequestParam String m_id){
		
		Map<String, String> map = new HashMap<String, String>();
		
		MemberVO member = memberService.selectCheckMember(m_id);
		
		if(member!=null) {
			//중복
			map.put("result", "idDuplicated");
		}else {
			//미중복
			map.put("result", "idNotFound");
		}
		
		return map;
	}
}	