package com.ksj.bootstudy.mapper;

import com.ksj.bootstudy.vo.BbsMasterVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BbsMasterMapper {

    public List<BbsMasterVO> selectBbsMasterListPage(BbsMasterVO bbsMasterVO);

    public BbsMasterVO selectBbsMasterInfo(BbsMasterVO bbsMasterVO);

    public void updateBbsMasterOne(BbsMasterVO bbsMasterVO);

    public void createBbsMaster(BbsMasterVO bbsMasterVO);

    public BbsMasterVO selectBbsMaxKey();

    public void deleteBbsMaster(BbsMasterVO bbsMasterVO);
}
