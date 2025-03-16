package com.ksj.bootstudy.service;

import com.ksj.bootstudy.mapper.MemberMapper;
import com.ksj.bootstudy.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberVO memberVO = new MemberVO();
        memberVO.setId(username);

        MemberVO newMember = memberMapper.selectMemberPasswd(memberVO);
        log.info("newMember pwd : " + newMember.getPasswd());

        log.info("admin.encode : " + passwordEncoder.encode("admin"));

        return User.builder()
                .username(newMember.getId())
                .password(newMember.getPasswd())
                .roles(newMember.getRole())
                .build();
    }
}
