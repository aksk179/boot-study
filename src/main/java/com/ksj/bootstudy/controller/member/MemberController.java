package com.ksj.bootstudy.controller.member;

import com.ksj.bootstudy.model.Menu;
import com.ksj.bootstudy.model.Role;
import com.ksj.bootstudy.service.email.EmailService;
import com.ksj.bootstudy.service.member.MemberService;
import com.ksj.bootstudy.service.role.RoleService;
import com.ksj.bootstudy.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.GrantedAuthority;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class MemberController {

    @Autowired
    MemberService memberService;

    @Autowired
    EmailService emailService;

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/admin/member_list.page")
    public String selectMemberListPage(Model model) {
        List<MemberVO> memberList = memberService.selectMemberList(new MemberVO());
        model.addAttribute("memberList", memberList);
        model.addAttribute("title", "회원 목록 조회");
        return "/admin/member_list";
    }

    @RequestMapping(value = "/login/member_register.page")
    public String registerMemberPage(Model model) {
        MemberVO memberVO = new MemberVO();
        model.addAttribute("member", memberVO);
        return "/login/member_register";
    }

    @RequestMapping(value = "/login/member_register.do")
    public String registerMember(@ModelAttribute MemberVO memberVO) {
        log.debug("memberVO : " + memberVO.toString());
        memberService.registerMember(memberVO);
        emailService.sendEmail(memberVO);
        log.debug("===================== 회원가입완료 ====================");
        return "/login/member_register_success";
    }

    @RequestMapping(value = "/admin/update_member.page", method = RequestMethod.GET)
    public String updateMemberAdminPage(@RequestParam("id") String id, Model model) {
        MemberVO memberVO = new MemberVO();
        memberVO.setId(id);
        MemberVO memberOne = memberService.selectMemberOne(memberVO);
        log.debug("memberOne : " + memberOne.toString());
        model.addAttribute("member", memberOne);
        return "/admin/update_member";
    }

    @RequestMapping(value = "/user/update_member_user.page", method = RequestMethod.GET)
    public String updateMemberUserPage(Model model) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String id = authentication.getName();

        MemberVO memberVO = new MemberVO();
        memberVO.setId(id);
        MemberVO memberOne = memberService.selectMemberOne(memberVO);
        log.debug("memberOne : " + memberOne.toString());
        model.addAttribute("member", memberOne);
        return "/user/update_member_user";
    }

    @RequestMapping(value = "/admin/update_member.do")
    public String updateMemberOneAdmin(@ModelAttribute MemberVO memberVO) {
        log.info("memberVO : " + memberVO.toString());
        memberService.updateMemberOne(memberVO);
        log.debug("===================== 회원정보수정완료 ====================");
        return "/admin/member_update_success";
    }

    @RequestMapping(value = "/user/update_member.do")
    public String updateMemberOneUser(@ModelAttribute MemberVO memberVO) {
        log.debug("memberVO : " + memberVO.toString());
        memberService.updateMemberOne(memberVO);
        log.debug("===================== 회원정보수정완료 ====================");
        return "/user/member_update_success";
    }

    @RequestMapping(value = "/login/member_login.page")
    public String loginMemberPage(Model model) {
        MemberVO memberVO = new MemberVO();
        model.addAttribute("member", memberVO);
        return "/login/member_login";
    }

    @RequestMapping(value = "/")
    public String mainPage(Model model) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            List<Menu> assignedMenus = new ArrayList<>();
            model.addAttribute("assignedMenus", assignedMenus);
            return "/member_main";
        }
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        System.out.println(roles); // [ROLE_USER, ROLE_ADMIN] 등 출력

        String roleId = roles.get(0).substring(5);
        if (roleId.equals("ANONYMOUS")) {
            List<Menu> assignedMenus = new ArrayList<>();
            model.addAttribute("assignedMenus", assignedMenus);
            return "/member_main";
        }
        System.out.println(roleId);

        Role role = roleService.findByRoleId(roleId);
        List<Menu> assignedMenus = role.getMenuList();
        model.addAttribute("assignedMenus", assignedMenus);

        return "/member_main";
    }
}
