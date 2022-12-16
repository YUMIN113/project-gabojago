package com.bitcamp.gabojago.vo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageMakerDTO {
    int page; // 현재 페이지
    int size; // 출력할 게시글 개수
    int start;
    int end;
    boolean prev;
    boolean next;
    int total; // 총 페이지 수

    List<Recommendation> dtoList;

    /* 생성자 */

    public PageMakerDTO(int page, int size, int total, List<Recommendation> dtoList) {
        int lastPage;
        if(total == 0) {
            lastPage = 1;
        } else {
            lastPage = (int) Math.ceil(((double) total / (double) size));
        }
        this.page = page;
        this.size = size; // 한페이지에 보여줄 전체 코스 추천글 게시글 갯수
        this.total = total;
        this.dtoList = dtoList;

        this.end = (int)(Math.ceil((page+1)/3.0))*3;
        this.start = this.end - 2;

        if(lastPage < this.end) {
            this.end = lastPage;
        }

        this.prev = start > 1;
        this.next = lastPage > this.end;
    }
}
