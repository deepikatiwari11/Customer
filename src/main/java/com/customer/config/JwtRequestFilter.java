package com.customer.config;

import java.io.IOException;



import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.customer.service.JwtUserDetailsService;
import com.customer.util.JWTTokenUtil;



@Component
public class JwtRequestFilter extends OncePerRequestFilter{
	
@Autowired

JwtUserDetailsService jwtUserDetailsService;

@Autowired
JWTTokenUtil  jwtTokenUtil;

@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException {
	// TODO Auto-generated method stub
	
	final String requestTokenHeader = request.getHeader("Authorization");
	String jwtToken=null;
	String userName =null;
	if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer"))
		
		
	{
		
		
		jwtToken = requestTokenHeader.substring(7);
		userName=jwtTokenUtil.getUsernameFromToken(jwtToken);
		
	}
	
	else
		
	{
		
		logger.warn("JWT Token does not begin with Bearer String");
	}
	if(userName!=null && SecurityContextHolder.getContext().getAuthentication() == null)
		
	{
		UserDetails userDetails  = jwtUserDetailsService.loadUserByUsername(userName);
		// if token is valid configure Spring Security to manually set
					// authentication
		
		if(jwtTokenUtil.validateToken(jwtToken, userDetails))
			
		{
			
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					userDetails, null, userDetails.getAuthorities());
			usernamePasswordAuthenticationToken
					.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			// After setting the Authentication in the context, we specify
			// that the current user is authenticated. So it passes the
			// Spring Security Configurations successfully.
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}
	}
	logger.info("Calling filter chain");
	filterChain.doFilter(request, response);
}


}
