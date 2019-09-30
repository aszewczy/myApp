package pl.szewczyk.test.myApp;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtFilter implements javax.servlet.Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)  // filter który sprawdza poprawność tokenu (filterchain - element który możemy jeszcze przekazać )
			throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String header = httpServletRequest.getHeader("Authorization");    //Header przekazuje info na temat rodzaju autoryznacji 
			
		if(httpServletRequest == null || !header.startsWith("Bearer ")) {
			throw new ServletException("Wrong or empty header");	
		}else {
			try {
			String token = header.substring(7);
			Claims claims = Jwts.parser().setSigningKey("test123").parseClaimsJws(token).getBody();  //wyciąganie info na temat elementu który sie znajduje w tokenie
			request.setAttribute("claims" , claims);							
		}catch(Exception ex) {
			throw new ServletException("Wrong key(token)");
		}
		}
		
		chain.doFilter(request, response);  //te prawidłowe claimsy przekazuje do obiektu filterChain - ma możliwiśc przekazania elementu od klienta do serwera gdy wszystko jest ok
		
	}

}
