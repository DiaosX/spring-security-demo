package com.mytest.springsecuritydemo3.common.sercurity;

import com.alibaba.fastjson.JSON;
import com.mytest.springsecuritydemo3.common.base.BaseResp;
import com.mytest.springsecuritydemo3.common.enums.JwtErrorEnum;
import com.mytest.springsecuritydemo3.common.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 请求验证过滤器
 * 请 Http 头中获取jwt token
 */
@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static RequestMatcher requestMatcher = new AntPathRequestMatcher("/v1/**");

    /**
     * 控制只拦截自己感兴趣的请求
     *
     * @param request
     * @return
     * @throws ServletException
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !requestMatcher.matches(request);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 从http头部读取jwt
        String jwtTokenHeader = request.getHeader(JwtTokenUtil.JWT_TOKEN_HEADER);
        if (StringUtils.isEmpty(jwtTokenHeader)) {
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(JSON.toJSONString(BaseResp.error(JwtErrorEnum.TOKEN_CAN_NOT_EMPTY)));
            return;
        }
        if (!jwtTokenHeader.startsWith(JwtTokenUtil.JWT_TOKEN_PREFIX)) {
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(JSON.toJSONString(BaseResp.error(JwtErrorEnum.TOKEN_FORMAT_ERROR)));
            return;
        }
        String jwtToken = jwtTokenHeader.substring(JwtTokenUtil.JWT_TOKEN_PREFIX.length() + 1); // The part after "Bearer "
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        //签发token
        JwtTokenVerifyResult result = jwtTokenUtil.verifyToken(jwtToken);
        if (!result.isSuccess()) {
            log.error("token验证失败");
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(JSON.toJSONString(BaseResp.error(JwtErrorEnum.TOKEN_INVALID_ERROR.getCode(), "token失效," + result.getErrMsg())));
            return;
        }
        Claims claims = result.getData();
        String userId = claims.get("userId", String.class);
        List<String> userPermission = claims.get("permission", List.class);
        List<GrantedAuthority> userAuthority = new ArrayList<>();
        userPermission.forEach(p -> userAuthority.add(new SimpleGrantedAuthority(p)));
        // 如果jwt正确解出账号信息，说明是合法用户，设置认证信息，认证通过
        if (!StringUtils.isEmpty(userId) && SecurityContextHolder.getContext().getAuthentication() == null) {
            JwtAuthenticationToken authResult = new JwtAuthenticationToken(userId, null, userAuthority);
            // 把请求的信息设置到UsernamePasswordAuthenticationToken details对象里面，包括发请求的ip等
            //AuthorityUtils.commaSeparatedStringToAuthorityList();
            authResult.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // 设置认证信息
            SecurityContextHolder.getContext().setAuthentication(authResult);
        }
        // 调用下一个过滤器
        filterChain.doFilter(request, response);
    }
}
