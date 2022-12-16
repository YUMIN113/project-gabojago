package com.bitcamp.gabojago.service;

import com.bitcamp.gabojago.dao.MyRecommendationDao;
import com.bitcamp.gabojago.vo.Recommendation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyRecommendationServiceImpl implements MyRecommendationService {

    private MyRecommendationDao myRecommendationDao;

    public MyRecommendationServiceImpl(MyRecommendationDao myRecommendationDao) {
        this.myRecommendationDao = myRecommendationDao;
    }

    // 게시물 총개수
    @Override
    public int count(String id) throws Exception {
        return myRecommendationDao.count(id);
    }

    // 게시물 목록 + paging
    @Override
    public List<Recommendation> myRecommendationListPage(int displayPost, int size, String id) throws Exception {
        return myRecommendationDao.myRecommendationListPage(displayPost, size, id);
    }
}
