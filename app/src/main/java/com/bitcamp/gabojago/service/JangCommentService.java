package com.bitcamp.gabojago.service;

import com.bitcamp.gabojago.vo.JangComment;

import java.util.List;


public interface JangCommentService {

    List<JangComment> jangCommentList(int recono) throws Exception;

    void jangCommentInsert(JangComment jangComment) throws Exception;

    boolean jangCommentDelete(int cmno) throws Exception;

    JangComment getJangCommentByCmno(int cmno) throws Exception;

}
