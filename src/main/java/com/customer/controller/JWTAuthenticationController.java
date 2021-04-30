package com.customer.controller;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.customer.service.JwtUserDetailsService;
import com.customer.util.JWTTokenUtil;
import com.cutomer.model.AuthenticationFailedResponse;
import com.cutomer.model.JwtRequest;
import com.cutomer.model.JwtResponse;


@RestController
@RequestMapping ("authenticationController")
public class JWTAuthenticationController {
	

@Autowired
private AuthenticationManager authenticationManager;
private final Logger logger =  LoggerFactory.getLogger(JWTAuthenticationController.class);
@Autowired JwtUserDetailsService userDetailsService;
@Autowired JWTTokenUtil  jwtTokenUtil;

	
	@RequestMapping(value ="/api/v1/authenticate",method=RequestMethod.POST)
		public ResponseEntity<?>  CreateAuthenticationToken(@RequestBody  JwtRequest  authenticationRequest) throws Exception
	
	{
		 AuthenticationFailedResponse authenticationFailedResponse = new AuthenticationFailedResponse();
		logger.info("userName"+authenticationRequest.getUserName());
		logger.info("CreateAuthenticationToken");
		
		try
		{
		authenticate(authenticationRequest.getUserName(),authenticationRequest.getPassword());
		logger.info("Called  authenticate");
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
		logger.info("UserDetails"+userDetails);
		
		
		logger.info("userDetails"+userDetails);
		final String token = jwtTokenUtil.generatetoken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
		}
		catch (BadCredentialsException e) {
			logger.info("invalid credentials", e);
			authenticationFailedResponse.setStatus(0);
			authenticationFailedResponse.setMessage("Authentication Failed");
			return ResponseEntity.ok(authenticationFailedResponse);
		}
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			logger.info("inside   authenticate");
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		
		logger.info("Caled   authenticate");
		
		} catch (DisabledException e) {
		throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
		throw new Exception("INVALID_CREDENTIALS", e);
		}
	catch (Exception e)
	{
		throw new Exception("INVALID_CREDENTIALS", e);
	}
		}
}
