package com.ksj.bootstudy.service.email;

import com.ksj.bootstudy.vo.MemberVO;

public interface EmailService {
    public void sendEmail(MemberVO memberVO);

    public String verifyEmail(String token);
}
