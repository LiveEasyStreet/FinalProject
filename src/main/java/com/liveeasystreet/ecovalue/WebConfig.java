package com.liveeasystreet.ecovalue;

import com.liveeasystreet.ecovalue.interceptor.MemberLoginCheckInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 내가 만든 인터셉터 적용<hr>
     * 인터셉터를 적용해 들어갈 수 있는 URL 과<br>
     * 들어갈 수 없는 URL 구분
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MemberLoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/", "/assets/**", "/login", "logout", "/forgot-**",
                        "/add", "/upcycleInfo", "/favicon.ico", "/error"
                );
    }
}
