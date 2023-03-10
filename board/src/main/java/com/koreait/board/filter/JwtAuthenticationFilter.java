package com.koreait.board.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.koreait.board.provider.TokenProvider;

@Component
//^ OncePerRequestFilter 한번만 작동하는 필터
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    @Autowired
    private TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        try {
            //? Request Header에 있는 Bearer Token을 가져옴
            String token = parseBearerToken(request);

            System.out.println(token);

            // //? token이 없으면
            if(token == null){
                throw new Exception();
            }

            //? token 유효성 검증
            // if(token != null && SecurityContextHolder.getContext().getAuthentication() == null){
                String sub = tokenProvider.validate(token);
                //! 공부가 필요한 부분
                //? request에 sub값을 포함 시켜주는 작업( Controller에 보내주기위해서는 request에 담아서 보내주는 과정)
                AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(sub, null,AuthorityUtils.NO_AUTHORITIES);
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    
                //? 빈 공간을 만드는 메소드(SecurityContext 객체)
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                //? SecurityContext 객체에 토큰을 넣는다
                securityContext.setAuthentication(authenticationToken);
                //? SecurityContextHolder에 SecurityContext 객체를 넣는다.
                SecurityContextHolder.setContext(securityContext);
            // }

        } catch (Exception e) {
            e.printStackTrace();
            // throw new UnauthorizationException();
        }

        filterChain.doFilter(request, response);
    }
    
    private String parseBearerToken(HttpServletRequest request){
        //? Request Header에 있는 Authorization 필드의 Value를 가져옴
        String authorizationValue = request.getHeader("Authorization");

        //? Authorization Value가 문자타입인지 
        //? (먼저 검사하는 이유 : authorizationValue가 null일시 .startsWith 메서드 호출 에러 발생 nullPointerException)
        boolean hasTokenValue = StringUtils.hasText(authorizationValue);
        if(!hasTokenValue) return null;

        //? Authorization Value가 "Bearer " 로 시작되는지
        boolean isBearer = authorizationValue.startsWith("Bearer ");
        if(!isBearer) return null;

        String token = authorizationValue.substring(7);

        return token;
        
    }
}
