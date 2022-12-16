package com.bitcamp.gabojago.service;


import com.bitcamp.gabojago.vo.Member;

public interface ModifyMyPageService {


    boolean profileUpdate(Member member) throws Exception;

    boolean myAccountUpdate(Member member) throws Exception;

    Member get(String id) throws Exception;

    // 닉네임 중복 체크
    int nickCheck(String nickName) throws Exception;

    int pwCheck(String memberId, String memberPw) throws Exception;

    int resignMember(String memberId, String memberPw) throws Exception;

}
