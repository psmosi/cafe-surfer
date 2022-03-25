package com.kh.cafesurfer.domain.bookMark.dao;

import com.kh.cafesurfer.domain.bookMark.BookMark;

public interface BookMarkDAO {

  //등록
  void insertBookMark(Long memberId, Long shopId);
  //제거
  void deleteBookMark(Long memberId, Long shopId);
  //조회
  BookMark selectBookMarkByMemberId(Long memberId);


}
