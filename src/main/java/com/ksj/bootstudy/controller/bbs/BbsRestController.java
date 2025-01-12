package com.ksj.bootstudy.controller.bbs;

import com.ksj.bootstudy.service.bbs.BbsMainService;
import com.ksj.bootstudy.vo.BbsCommentVO;
import com.ksj.bootstudy.vo.BbsMainVO;
import com.ksj.bootstudy.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class BbsRestController {

    @Autowired
    BbsMainService bbsMainService;

    @RequestMapping(value = "/bbs/createCmt.do", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createCmt(@RequestBody BbsCommentVO bbsCommentVO) {
        Map<String, Object> result = new HashMap<>();
        try {
            MemberVO memberVO = new MemberVO();
            memberVO.setId("TTTT5");
            memberVO.setName("황희찬");

            bbsCommentVO.setBbsCmtWriter(memberVO.getName());
            log.info("bbsCommentVO : " + bbsCommentVO.toString());
            bbsMainService.createCmt(bbsCommentVO);
            BbsCommentVO newBbsCmtVO = bbsMainService.selectCmt(bbsCommentVO);
            result.put("newBbsCmtVO", newBbsCmtVO);
            result.put("code", "Y");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", "N");
        }
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/bbs/createReply.do", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createReply(@RequestBody BbsCommentVO bbsReplyVO) {
        Map<String, Object> result = new HashMap<>();
        try {
            MemberVO memberVO = new MemberVO();
            memberVO.setId("TTTT3");
            memberVO.setName("이강인");

            bbsReplyVO.setBbsCmtWriter(memberVO.getName());
            log.info("bbsReplyVO : " + bbsReplyVO.toString());
            bbsMainService.createCmt(bbsReplyVO);
            BbsCommentVO newReplyVO = bbsMainService.selectCmt(bbsReplyVO);
            result.put("newReplyVO", newReplyVO);
            result.put("code", "Y");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", "N");
        }
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/bbs/create_bbs.do", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> createBbs(@RequestBody BbsMainVO bbsMainVO) {
        Map<String, String> result = new HashMap<>();
        log.info("BbsMainVO : " + bbsMainVO);
        try {
            MemberVO memberVO = new MemberVO();
            memberVO.setId("TTTT2");
            memberVO.setName("황희찬");

            bbsMainVO.setBbsWriterId(memberVO.getId());
            bbsMainVO.setBbsWriterName(memberVO.getName());
            log.info("BbsMainVO : " + bbsMainVO);
            bbsMainService.createBbs(bbsMainVO);
            result.put("code", "Y");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", "N");
        }
        return ResponseEntity.ok(result);
    }
}
