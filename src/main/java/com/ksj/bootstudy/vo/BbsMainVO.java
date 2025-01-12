package com.ksj.bootstudy.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BbsMainVO {
    private String bbsId;
    private int bbsNo;
    private String bbsTitle;
    private String bbsContents;
    private LocalDateTime bbsWtime;
    private String bbsWriterName;
    private String bbsWriterId;
    private int bbsViewCount;
    private int newTime;
}
