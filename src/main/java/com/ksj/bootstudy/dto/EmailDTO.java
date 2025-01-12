package com.ksj.bootstudy.dto;

import lombok.Data;

@Data
public class EmailDTO {
    private String id;
    private String sendAt;
    private String uuid;
    private String receiver;
}
