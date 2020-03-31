package com.mytest.springsecuritydemo2.common.config;

import com.mytest.springsecuritydemo2.common.sercurity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private UserDetailsServiceImpl jwtUserDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;
    @Autowired
    private RsaSigner signer;

    @Override
    public void configure(WebSecurity web) throws Exception {
        //allow Swagger URL to be accessed without authentication
        web.ignoring().antMatchers("/v2/api-docs",//swagger api json
                "/swagger-resources/configuration/ui",//用来获取支持的动作
                "/swagger-resources",//用来获取api-docs的URI
                "/swagger-resources/configuration/security",//安全选项
                "/swagger-ui.html");
        web.ignoring().antMatchers(
                "/favicon.ico");
        web.ignoring().antMatchers(
                "/error");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //登录过滤器
        JwtSignInProcessingFilter jwtSignInProcessFilter = new JwtSignInProcessingFilter();
        jwtSignInProcessFilter.setAuthenticationManager(this.authenticationManagerBean());
        //登录成功处理器
        JwtSignInSuccessHandler jwtSignInSuccessHandler = new JwtSignInSuccessHandler();
        jwtSignInSuccessHandler.setSigner(signer);
        jwtSignInProcessFilter.setAuthenticationSuccessHandler(jwtSignInSuccessHandler);
        //登录失败处理器
        jwtSignInProcessFilter.setAuthenticationFailureHandler(new JwtSignInFailureHandler());

        //登录过滤器的授权提供者(就这么叫吧)
        JwtAuthenticationProvider provider = new JwtAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(jwtUserDetailsService);
        http
                .authorizeRequests()
                .antMatchers("/v2/api-docs",//swagger api json
                        "/swagger-resources/configuration/ui",//用来获取支持的动作
                        "/swagger-resources",//用来获取api-docs的URI
                        "/swagger-resources/configuration/security",//安全选项
                        "/doc.html",
                        "/webjars/**").permitAll()
                .accessDecisionManager(accessDecisionManager())
                .antMatchers(HttpMethod.OPTIONS)        //跨域请求会先进行一次options请求
                .permitAll()
                .anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
                        fsi.setSecurityMetadataSource(filterSecurityMetadataSource());
                        // fsi.setAccessDecisionManager(accessDecisionManager());
                        return fsi;
                    }
                })
                .and()
                .formLogin().disable()//禁用表单登录
                .sessionManagement().disable()
                .csrf().disable()
                .authenticationProvider(provider)
                .headers().frameOptions().disable()
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtSignInProcessFilter, JwtAuthenticationFilter.class)
                .exceptionHandling()
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .authenticationEntryPoint(jwtAuthenticationEntryPoint);
    }

    @Bean
    public FilterInvocationSecurityMetadataSource filterSecurityMetadataSource() {
        return new FilterSecurityMetadataSource();
    }

    /**
     * UnanimousBased:只有全体同意了才授权访问
     * AffirmativeBased:只要一个以上voter同意访问就授权访问
     * ConsensusBased:大多数voter同意访问就授权访问
     *
     * @return
     */

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<?>> decisionVoters = Arrays.asList(
                new WebExpressionVoter(),
                new PermissionBasedVoter(),
                new AuthenticatedVoter());
        return new UnanimousBased(decisionVoters);
    }

    // 密码明文加密方式配置
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * RSA私钥
     */
    private static final String PRIVATE_KEY = "-----BEGIN RSA PRIVATE KEY-----" +
            "MIICWwIBAAKBgQCM1YBbzMijYIp4/mf1+gdVBXQMJEv5KpuTDh6DiTGJAk1yrsWA" +
            "RfqjpC83/t0xzpmvHa1M7WykUg5E0PmneNddyD/MTjkCDNhqBgr0AnJTZsTnEjMa" +
            "PB0cXeVF1ty1p+ZBuvHKMvhJwqgNmQd7uGpl2Rq1gR1L86YTWSkYceSoNwIDAQAB" +
            "AoGAcYrr+pcGp5l86oGJhWm4IZbM8cENs2vjk9LNTRT9580AbdZ0Cq/gm7ASFZ4X" +
            "7UD47JMLljrQ3UX+lQK6VIf7cTUGZdR1nVArOqJaMKVvCYkwqR6bm5Gc6qx6XWAW" +
            "0/PY2LcWt0cW1Q1CU65M1oM08P+ohQvE4kJI45RcoIl6VwkCQQCO0Za4bYiZWtzE" +
            "UzRka+kHa//h1YjYbQVglPLb5FuOdSm62eGQThfQRpyLU1WD6sATv9yPWxUaRCEL" +
            "Fh+s/YfrAkEA/HFDLl/Nl439/A5Q05HWhMKWZ8tt8k448mNNlefJUK1ApCuWdDWm" +
            "kBTk8ytjRvdFlVKvVVXUV8LeSyWMXpR55QJACe/rXMnCR2lbEw33B0W64RlSpJQH" +
            "AYgUZ7P1cfdhp3fff3DJkRDd90/ydH9H4/Xhh35CCnd78GftJKhVa+P4IQJABYv3" +
            "je1M9yeHjSJDZGKv8/rSkzVFFS3i0nCcI88T/VHROco7ZBJJtqC+5xjs9YI5ZS6L" +
            "67QXFlaRy9TnYKyigQJAHjuzdwDgKBj2orf6k05ri+Ks1nGvp5S4JxzcGCmkQB+l" +
            "6KOJ8lAFma4qxWKaMeNi0ekrzkSrJNEt5yJPbw1Lmg==" +
            "-----END RSA PRIVATE KEY-----";

    /**
     * 使用私钥签名
     */
    @Bean
    public RsaSigner getSigner() {
        return new RsaSigner(PRIVATE_KEY);
    }

    /**
     * 使用公钥验签(这里是可以通过私钥生成密钥对,包含公钥)
     */
    @Bean
    public RsaVerifier getVerifier() {
        return new RsaVerifier(PRIVATE_KEY);
    }
}
