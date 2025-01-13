package com.ksj.bootstudy.controller.member;

import com.ksj.bootstudy.service.member.MemberService;
import com.ksj.bootstudy.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class MemberRestController {

    @Autowired
    MemberService memberService;

    @RequestMapping(value = "/admin/get_member_list.do", method = RequestMethod.POST)
    public List<MemberVO> selectMemberList(MemberVO memberVO) {
        return memberService.selectMemberList(memberVO);
    }

    @RequestMapping(value = "/login/check_duplicate.do", method = RequestMethod.POST)
    public ResponseEntity<String> isDuplicate(@RequestParam("id") String id) {
        MemberVO memberVO = new MemberVO();
        memberVO.setId(id);
        return ResponseEntity.ok(memberService.isDuplicate(memberVO));
    }

    @RequestMapping(value = "/user/chk_current_passwd.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> chkPasswd(@RequestBody MemberVO memberVO) {
        return ResponseEntity.ok(memberService.chkPasswd(memberVO));
    }

    @RequestMapping(value = "/chkLogin.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> chkLogin(@RequestBody MemberVO memberVO) {
        MemberVO member = memberService.selectMemberOne(memberVO);
        if (member.getStatus().equals("Y")) {
            return ResponseEntity.ok(memberService.loginMember(memberVO));
        } else {
            return ResponseEntity.ok("isStatusN");
        }
    }

    @RequestMapping(value = "/admin/delete_member.do", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> deleteMember(@RequestBody MemberVO memberVO) {
        Map<String, String> result = new HashMap<>();
        log.info("MemberVO : " + memberVO.toString());
        try {
            memberService.deleteMember(memberVO);
            result.put("code", "Y");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", "N");
        }
        return ResponseEntity.ok(result);
    }
}
