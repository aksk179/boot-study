package com.ksj.bootstudy.service.bbs;

import com.ksj.bootstudy.mapper.BbsMasterMapper;
import com.ksj.bootstudy.vo.BbsMasterVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        if (newBbsMasterVO == null) {
            newBbsMasterVO = new BbsMasterVO();
            newBbsMasterVO.setBbsId("BBS000");
        }
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

    public boolean canUserWrite(String bbsId, String username) {
        // 공지사항 게시판 ID 목록
        List<String> noticeBoards = List.of("BBS001");

        // 자유게시판이면 true 반환 (누구나 작성 가능)
        if (!noticeBoards.contains(bbsId)) {
            return true;
        }

        // 공지사항이면 관리자 권한 체크
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
    }
}
