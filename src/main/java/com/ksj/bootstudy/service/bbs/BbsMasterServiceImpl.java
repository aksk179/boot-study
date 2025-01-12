package com.ksj.bootstudy.service.bbs;

import com.ksj.bootstudy.mapper.BbsMasterMapper;
import com.ksj.bootstudy.vo.BbsMasterVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BbsMasterServiceImpl implements BbsMasterService {

    @Autowired
    BbsMasterMapper bbsMasterMapper;

    @Override
    public List<BbsMasterVO> selectBbsMasterListPage(BbsMasterVO bbsMasterVO) {
        return bbsMasterMapper.selectBbsMasterListPage(bbsMasterVO);
    }

    @Override
    public BbsMasterVO selectBbsMasterInfo(BbsMasterVO bbsMasterVO) {
        return bbsMasterMapper.selectBbsMasterInfo(bbsMasterVO);
    }

    @Override
    public void updateBbsMasterOne(BbsMasterVO bbsMasterVO) {
        bbsMasterMapper.updateBbsMasterOne(bbsMasterVO);
    }

    @Override
    public void createBbsMaster(BbsMasterVO bbsMasterVO) {
        BbsMasterVO newBbsMasterVO = bbsMasterMapper.selectBbsMaxKey();
        String id = newBbsMasterVO.getBbsId();
        int num = Integer.parseInt(id.substring(3));
        log.info("num : " + num);
        id = String.format("BBS%03d", ++num);
        bbsMasterVO.setBbsId(id);

        bbsMasterMapper.createBbsMaster(bbsMasterVO);
    }

    @Override
    public void deleteBbsMaster(BbsMasterVO bbsMasterVO) {
        bbsMasterMapper.deleteBbsMaster(bbsMasterVO);
    }
}
