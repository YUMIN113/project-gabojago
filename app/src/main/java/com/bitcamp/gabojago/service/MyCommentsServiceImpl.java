package com.bitcamp.gabojago.service;

import com.bitcamp.gabojago.dao.MyCommentsDao;
import com.bitcamp.gabojago.vo.ExhibitionReview;
import com.bitcamp.gabojago.vo.JangComment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyCommentsServiceImpl implements MyCommentsService {

    MyCommentsDao myCommentsDao;

    public MyCommentsServiceImpl(MyCommentsDao myCommentsDao) {
        this.myCommentsDao = myCommentsDao;
    }


    // paging_게시물 총개수 (코스추천 댓글)
    @Override
    public int myRecommendationCommentCount(String id) throws Exception {
        return myCommentsDao.myRecommendationCommentCount(id);
    }

    // 게시물 목록 + paging (코스추천 댓글)
    @Override
    public List<JangComment> myRecommendationCommentListPage(int displayPost, int size, String id) throws Exception {
        return myCommentsDao.myRecommendationCommentListPage(displayPost, size, id);
    }


    // paging_게시물 총개수 (전시회 댓글)
    @Override
    public int count(String id) throws Exception {
        return myCommentsDao.count(id);
    }

    // 게시물 목록 + paging (전시회 댓글)
    @Override
    public List<ExhibitionReview> myExhibitionReviewListPage(int displayPost, int size, String id) throws Exception {
        return myCommentsDao.myExhibitionReviewListPage(displayPost, size, id);
    }
}
