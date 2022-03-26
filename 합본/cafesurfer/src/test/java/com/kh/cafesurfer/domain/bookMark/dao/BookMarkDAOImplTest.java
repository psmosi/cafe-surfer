package com.kh.cafesurfer.domain.bookMark.dao;

import com.kh.cafesurfer.domain.bookMark.BookMark;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest  // springboot 환경에서 테스트 진행
class BookMarkDAOImplTest {

  @Autowired
  private BookMarkDAO bookMarkDAO;

  @Test
  @DisplayName("등록") //테스트케이스 이름
  void insertBookMark() {
    BookMark bookMark = new BookMark();

    bookMark.setMemberId(1L);
    bookMark.setShopId(1L);


    bookMarkDAO.insertBookMark(bookMark.getMemberId(), bookMark.getShopId());


  }

  @Test
  @DisplayName("삭제")
  void deleteBookMark() {
    BookMark bookMark = new BookMark();

    bookMark.setMemberId(1L);
    bookMark.setShopId(1L);

    bookMarkDAO.deleteBookMark(bookMark.getMemberId(), bookMark.getShopId());

  }

  @Test
  @DisplayName("조회")
  void selectBookMarkByMemberId() {

    BookMark bookMark = new BookMark();

    bookMark.setMemberId(1L);

    BookMark bookMark1 = bookMarkDAO.selectBookMarkByMemberId(bookMark.getMemberId());

    Assertions.assertThat(bookMark1.getMemberId()).isEqualTo(1L);


  }
}