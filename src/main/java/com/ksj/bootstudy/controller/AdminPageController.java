package com.ksj.bootstudy.controller;

import com.ksj.bootstudy.service.bbs.BbsMainService;
import com.ksj.bootstudy.service.bbs.BbsMasterService;
import com.ksj.bootstudy.vo.BbsAttachVO;
import com.ksj.bootstudy.vo.BbsCommentVO;
import com.ksj.bootstudy.vo.BbsMainVO;
import com.ksj.bootstudy.vo.BbsMasterVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
public class AdminPageController {

    @Autowired
    BbsMasterService bbsMasterService;

    @Autowired
    BbsMainService bbsMainService;

    @RequestMapping(value = "/admin/{pageName}.page/{bbsId}")
    public String dynamicPage(@PathVariable("pageName") String pageName, @PathVariable("bbsId") String bbsId, Model model) {
        checkModel(pageName, model, bbsId, 0);
        return "/admin/" + pageName;
    }

    @RequestMapping(value = "/admin/{pageName}.page")
    public String dynamicPage(@PathVariable("pageName") String pageName, Model model) {
        checkModel(pageName, model, "", 0);
        return "/admin/" + pageName;
    }

    private void checkModel(String pageName, Model model, String bbsId, int bbsNo) {
        if (pageName.equals("bbs_master_list")) {
            List<BbsMasterVO> bbsMasterList = bbsMasterService.selectBbsMasterListPage(new BbsMasterVO());
            model.addAttribute("bbsMasterList", bbsMasterList);
        } else if (pageName.equals("create_bbs_master")) {
            //수정
            if (!bbsId.isEmpty()) {
                BbsMasterVO bbsMasterVO = new BbsMasterVO();
                bbsMasterVO.setBbsId(bbsId);
                BbsMasterVO resultVO = bbsMasterService.selectBbsMasterInfo(bbsMasterVO);
                model.addAttribute("bbsVO", resultVO);
                model.addAttribute("title", "수정");
                model.addAttribute("btnName", "저장");
            }
            //등록
            else {
                model.addAttribute("bbsVO", new BbsMasterVO());
                model.addAttribute("title", "등록");
                model.addAttribute("btnName", "등록");
            }
        }
    }
}
