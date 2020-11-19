package kr.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginCheckInterceptor 
                        extends HandlerInterceptorAdapter{
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			                 HttpServletResponse response,
			               Object handler)throws Exception {
		//로그 표시
		if(log.isDebugEnabled()) {
			log.debug("<<LoginCheckInterceptor 진입>>");
		}
		
		//로그인 여부 체크
		HttpSession session = request.getSession();
		if(session.getAttribute("user_id")==null) {
			//로그인이 되어 있지 않기 때문에 로그인 폼 호출
			response.sendRedirect(
			request.getContextPath()+"/member/login.do");
			return false;
		}
		
		return true;
	}
}






