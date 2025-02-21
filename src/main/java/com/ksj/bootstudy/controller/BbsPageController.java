package com.ksj.bootstudy.controller;

import com.ksj.bootstudy.model.Menu;
import com.ksj.bootstudy.service.bbs.BbsMainService;
import com.ksj.bootstudy.service.bbs.BbsMasterService;
import com.ksj.bootstudy.service.menu.MenuService;
import com.ksj.bootstudy.vo.BbsAttachVO;
import com.ksj.bootstudy.vo.BbsCommentVO;
import com.ksj.bootstudy.vo.BbsMainVO;
import com.ksj.bootstudy.vo.BbsMasterVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
public class BbsPageController {

    @Autowired
    BbsMasterService bbsMasterService;

    @Autowired
    BbsMainService bbsMainService;

    @RequestMapping(value = "/bbs/{pageName}.page/{bbsId}/{bbsNo}")
    public String dynamicPage(@PathVariable("pageName") String pageName, @PathVariable("bbsId") String bbsId, @PathVariable("bbsNo") int bbsNo, Model model) {
        checkModel(pageName, model, bbsId, bbsNo);
        return "/bbs/" + pageName;
    }

    @RequestMapping(value = "/bbs/{pageName}.page/{bbsId}")
    public String dynamicPage(@PathVariable("pageName") String pageName, @PathVariable("bbsId") String bbsId, Model model) {
        checkModel(pageName, model, bbsId, 0);
        return "/bbs/" + pageName;
    }

    @RequestMapping(value = "/bbs/{pageName}.page")
    public String dynamicPage(@PathVariable("pageName") String pageName, Model model) {
        checkModel(pageName, model, "", 0);
        return "/bbs/" + pageName;
    }

    @RequestMapping(value = "/{pageName}.page")
    public String dynamicPage(@PathVariable("pageName") String pageName) {
        // "manager/" 폴더 아래의 페이지로 동적 전환
        return "/" + pageName;
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
        } else if (pageName.equals("bbs_main")) {
            //selectBbsInfo이용해서 밑에 if-else문들 공통화 시키기 지금은 하드코딩임.
            //게시판 성격 조회
            BbsMasterVO bbsMasterVO = new BbsMasterVO();
            bbsMasterVO.setBbsId(bbsId);
            BbsMasterVO resultMasterVO = bbsMasterService.selectBbsMasterInfo(bbsMasterVO);
            model.addAttribute("masterVO", resultMasterVO);

            //게시판의 글 조회
            BbsMainVO bbsMainVO = new BbsMainVO();
            bbsMainVO.setBbsId(bbsId);
            List<BbsMainVO> resultMainVO = bbsMainService.selectBbsList(bbsMainVO);
            model.addAttribute("bbsList", resultMainVO);
        } else if (pageName.equals("bbs_view")) {
            //게시판 성격 조회
            BbsMasterVO bbsMasterVO = new BbsMasterVO();
            bbsMasterVO.setBbsId(bbsId);
            BbsMasterVO resultMasterVO = bbsMasterService.selectBbsMasterInfo(bbsMasterVO);
            model.addAttribute("masterVO", resultMasterVO);

            //bbs_main
            BbsMainVO bbsMainVO = new BbsMainVO();
            bbsMainVO.setBbsId(bbsId);
            bbsMainVO.setBbsNo(bbsNo);
            BbsMainVO resultVO = bbsMainService.selectBbsInfo(bbsMainVO);
            model.addAttribute("bbs", resultVO);

            //bbs_attach
            BbsAttachVO bbsAttachVO = new BbsAttachVO();
            bbsAttachVO.setBbsId(bbsId);
            bbsAttachVO.setBbsNo(bbsNo);
            List<BbsAttachVO> resultAttachVO = bbsMainService.selectBbsAttachList(bbsAttachVO);
            model.addAttribute("bbsAttachList", resultAttachVO);

            //bbs_cmt
            BbsCommentVO bbsCommentVO = new BbsCommentVO();
            bbsCommentVO.setBbsId(bbsId);
            bbsCommentVO.setBbsNo(bbsNo);
            List<BbsCommentVO> resultCmtVO = bbsMainService.selectBbsCmtList(bbsCommentVO);
            model.addAttribute("bbsCmtList", resultCmtVO);
        } else if (pageName.equals("create_bbs")) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            // 🔥 권한 체크
            if (!bbsMasterService.canUserWrite(bbsId, username)) {
                throw new AccessDeniedException("게시글 작성 권한이 없습니다.");
            }

            //게시판 성격 조회
            BbsMasterVO bbsMasterVO = new BbsMasterVO();
            bbsMasterVO.setBbsId(bbsId);
            BbsMasterVO resultMasterVO = bbsMasterService.selectBbsMasterInfo(bbsMasterVO);
            model.addAttribute("masterVO", resultMasterVO);

            //bbs_main
            BbsMainVO bbsMainVO = new BbsMainVO();
            bbsMainVO.setBbsId(bbsId);
            model.addAttribute("bbs", bbsMainVO);
            model.addAttribute("btnName", "등록");
        }
    }
}
