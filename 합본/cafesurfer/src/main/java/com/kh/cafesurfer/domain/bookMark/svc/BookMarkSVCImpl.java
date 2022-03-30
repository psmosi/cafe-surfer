package com.kh.cafesurfer.domain.bookMark.svc;

import com.kh.cafesurfer.domain.bookMark.BookMark;
import com.kh.cafesurfer.domain.bookMark.dao.BookMarkDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookMarkSVCImpl implements BookMarkSVC{

  private final BookMarkDAO bookMarkDAO;
  
  //등록
  @Override
  public BookMark insertBookMark(Long memberId, Long shopId) {
    bookMarkDAO.insertBookMark(memberId,shopId);
    return bookMarkDAO.insertBookMark(memberId,shopId);
  }
  
  //삭제
  @Override
  public void deleteBookMark(Long memberId, Long shopId) {
    bookMarkDAO.deleteBookMark(memberId,shopId);
  }
  
  //조회
  @Override
  public BookMark selectBookMarkByMemberId(Long memberId) {
    return bookMarkDAO.selectBookMarkByMemberId(memberId);
  }
}
