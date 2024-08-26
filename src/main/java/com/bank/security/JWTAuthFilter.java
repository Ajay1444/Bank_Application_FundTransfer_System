package com.bank.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mysql.cj.util.StringUtils;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JWTHelper jwtHelper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// GetToken
		String requestToken = request.getHeader("Authorization");
		String username = "";
		String token = "";

		if (!StringUtils.isNullOrEmpty(requestToken) && requestToken.startsWith("Bearer")) {
			token = requestToken.substring(7);
			try {
				username = this.jwtHelper.extractUsername(token);
			} catch (IllegalArgumentException e) {
				System.out.println("unable to get jwt token");
			} catch (ExpiredJwtException e) {
				System.out.println("token expired");
			} catch (MalformedJwtException e) {
				System.out.println("invalid jwt");
			}
		} else {
			System.out.println("JWT token does not start with bearer");
		}

		if (!StringUtils.isNullOrEmpty(username) && null == SecurityContextHolder.getContext().getAuthentication()) {
			UserDetails userDeatils = this.userDetailsService.loadUserByUsername(username);
			if (this.jwtHelper.validateToken(token, userDeatils)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDeatils, null, userDeatils.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			} else {
				System.out.println("invalid jwt token");
			}
		} else {
			System.out.println("username is null or context is not null");
		}
		filterChain.doFilter(request, response);
	}

}
