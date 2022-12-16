package com.bitcamp.gabojago.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageResponseDto<T> {

    int page;
    int size;

    int start;
    int end;

    int total;

    boolean prev;
    boolean next;

    List<T> dtoList;

    public PageResponseDto(int page,
                           int size,
                           int total,
                           List<T> dtoList) {

        int lastPage = (int)Math.ceil(((double) total / (double) size));

        this.page = page;
        this.size = size;
        this.total = total;
        this.dtoList = dtoList;

        this.start = (((page / 2) * 2) + 1);
        this.end = Math.min(lastPage, (start + 1));
        this.prev = start != 1;
        this.next = lastPage > end;
    }
}
