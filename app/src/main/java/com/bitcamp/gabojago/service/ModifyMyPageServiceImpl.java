package com.bitcamp.gabojago.service;


import com.bitcamp.gabojago.dao.ModifyMyPageDao;
import com.bitcamp.gabojago.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModifyMyPageServiceImpl implements ModifyMyPageService {
    @Autowired
    ModifyMyPageDao modifyMyPageDao;


    @Override
    public boolean profileUpdate(Member member) throws Exception {
        if (modifyMyPageDao.profileUpdate(member) == 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean myAccountUpdate(Member member) throws Exception {
        if (modifyMyPageDao.myAccountUpdate(member) == 0) {
            return false;
        }
        return true;
    }
    @Override
    public Member get(String id) throws Exception {
        return modifyMyPageDao.findById(id);
    }

    // 닉네임 중복 확인
    @Override
    public int nickCheck(String nickName) throws Exception {
        int result = modifyMyPageDao.findByNickName(nickName);
        return result;
    }

    @Override
    public int pwCheck(String memberId, String memberPw) throws Exception {
        int result = modifyMyPageDao.checkPassword(memberId, memberPw);
        return result;
    }
    @Override
    public int resignMember(String memberId, String memberPw) throws Exception {
        int result = modifyMyPageDao.resignMemberStatus(memberId, memberPw);
        return result;
    }
}

