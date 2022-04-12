package com.kh.cafesurfer.domain.bookMark.dao;

import com.kh.cafesurfer.domain.bookMark.Bookmark;

import java.util.List;

public interface BookMarkDAO {

  //등록
  void insertBookMark(Long memberId, Long shopId);

  //제거
  void deleteBookMark(Long memberId, Long shopId);

  //조회
  List<Bookmark> selectBookMarkByMemberId(Long memberId);

  // 커피숍 리뷰 개수 증가
  Long plusBookmarkCnt(Long shopId);

  // 커피숍 리뷰 개수 감소
  Long minusBookmarkCnt(Long shopId);

}
