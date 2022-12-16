
package com.bitcamp.gabojago.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
public class Notice {

    private int no;

    private String title;

    private String content;

    private Date createdDate;

    // 조회수
    private int hits;

}