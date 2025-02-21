package com.ksj.bootstudy.service.bbs;

import com.ksj.bootstudy.vo.BbsMasterVO;

import java.util.List;

public interface BbsMasterService {

    public List<BbsMasterVO> selectBbsMasterListPage(BbsMasterVO bbsMasterVO);

    public BbsMasterVO selectBbsMasterInfo(BbsMasterVO bbsMasterVO);

    public void updateBbsMasterOne(BbsMasterVO bbsMasterVO);

    public void createBbsMaster(BbsMasterVO bbsMasterVO);

    public void deleteBbsMaster(BbsMasterVO bbsMasterVO);

    boolean canUserWrite(String bbsId, String username);
}
