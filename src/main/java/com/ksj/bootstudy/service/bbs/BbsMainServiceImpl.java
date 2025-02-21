package com.ksj.bootstudy.service.bbs;

import com.ksj.bootstudy.mapper.BbsMainMapper;
import com.ksj.bootstudy.vo.BbsAttachVO;
import com.ksj.bootstudy.vo.BbsCommentVO;
import com.ksj.bootstudy.vo.BbsMainVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BbsMainServiceImpl implements BbsMainService {

    @Autowired
    BbsMainMapper bbsMainMapper;

    @Override
    public List<BbsMainVO> selectBbsList(BbsMainVO bbsMainVO) {
        return bbsMainMapper.selectBbsList(bbsMainVO);
    }

    @Override
    public BbsMainVO selectBbsInfo(BbsMainVO bbsMainVO) {
        return bbsMainMapper.selectBbsInfo(bbsMainVO);
    }

    @Override
    public List<BbsAttachVO> selectBbsAttachList(BbsAttachVO bbsAttachVO) {
        return bbsMainMapper.selectBbsAttachList(bbsAttachVO);
    }

    @Override
    public List<BbsCommentVO> selectBbsCmtList(BbsCommentVO bbsCommentVO) {
        return bbsMainMapper.selectBbsCmtList(bbsCommentVO);
    }

    @Override
    public void createCmt(BbsCommentVO bbsCommentVO) {
        BbsCommentVO resultCmtVO = bbsMainMapper.selectCmtMaxSeq(bbsCommentVO);
        int seq = resultCmtVO.getBbsCmtSeq();

        bbsCommentVO.setBbsCmtSeq(seq+1);
        bbsMainMapper.createCmt(bbsCommentVO);
    }

    @Override
    public BbsCommentVO selectCmt(BbsCommentVO bbsCommentVO) {
        return bbsMainMapper.selectCmt(bbsCommentVO);
    }

    @Override
    public void createBbs(BbsMainVO bbsMainVO) {
        BbsMainVO resultVO = bbsMainMapper.selectMaxBbsNo(bbsMainVO);
        if (resultVO == null) {
            bbsMainVO.setBbsNo(1);
        } else {
            int bbsNo = resultVO.getBbsNo();
            bbsMainVO.setBbsNo(bbsNo + 1);
        }
        bbsMainMapper.createBbs(bbsMainVO);
    }
}
