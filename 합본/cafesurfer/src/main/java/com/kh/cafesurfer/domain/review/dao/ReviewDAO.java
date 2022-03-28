package com.kh.cafesurfer.domain.review.dao;

import com.kh.cafesurfer.domain.review.Review;

import java.util.List;

public interface ReviewDAO {

  // 등록
  Review insertReview(Review review);

  // 조회(전체)
  List<Review> selectAll();

  // 조회(리뷰Id로)
  Review selectOne(Long reviewId);

  // 조회(회원Id로)
  List<Review> selectByMemberId(Long memberId);

  // 조회(커피숍Id로)
  List<Review> selectByShopId(Long shopId);

  // 삭제
  int deleteReview(Long reviewId);

  // 신고수 증가(카운트)
  int insertReportCnt(Long reviewId);

}
