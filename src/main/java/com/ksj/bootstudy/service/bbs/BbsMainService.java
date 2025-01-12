package com.ksj.bootstudy.service.bbs;

import com.ksj.bootstudy.vo.BbsAttachVO;
import com.ksj.bootstudy.vo.BbsCommentVO;
import com.ksj.bootstudy.vo.BbsMainVO;

import java.util.List;

public interface BbsMainService {

    public List<BbsMainVO> selectBbsList(BbsMainVO bbsMainVO);

    public BbsMainVO selectBbsInfo(BbsMainVO bbsMainVO);

    public List<BbsAttachVO> selectBbsAttachList(BbsAttachVO bbsAttachVO);

    public List<BbsCommentVO> selectBbsCmtList(BbsCommentVO bbsCommentVO);

    public void createCmt(BbsCommentVO bbsCommentVO);

    public BbsCommentVO selectCmt(BbsCommentVO bbsCommentVO);

    public void createBbs(BbsMainVO bbsMainVO);
}
