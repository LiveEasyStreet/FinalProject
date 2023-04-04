package com.liveeasystreet.ecovalue.config;

import com.liveeasystreet.ecovalue.repository.member.MemberMapper;
import com.liveeasystreet.ecovalue.repository.member.MemberRepository;
import com.liveeasystreet.ecovalue.repository.member.MyBatisMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MyBatisConfig {

    private final MemberMapper memberMapper;

    @Bean
    public MemberRepository memberRepository(){
        return new MyBatisMemberRepository(memberMapper);
    }

}
