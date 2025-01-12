package com.ksj.bootstudy.vo;

import lombok.Data;

@Data
public class BbsMasterVO {
    private String bbsId;
    private String bbsName;
    private String bbsReplyYn;
    private String bbsFileYn;
    private int bbsFileSize;
    private int bbsNewHour;
    private String bbsWriterViewYn;
    private String bbsUserWriteYn;
}
