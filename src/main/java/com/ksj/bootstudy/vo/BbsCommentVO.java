package com.ksj.bootstudy.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BbsCommentVO {
    private String bbsId;
    private int bbsNo;
    private int bbsCmtSeq;
    private String bbsCmtWriter;
    private LocalDateTime bbsCmtWtime;
    private String bbsComment;
    private int bbsCmtUpseq;
}
