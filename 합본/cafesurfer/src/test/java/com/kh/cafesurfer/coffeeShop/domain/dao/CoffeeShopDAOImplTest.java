package com.kh.cafesurfer.coffeeShop.domain.dao;

import com.kh.cafesurfer.domain.coffeeShop.CoffeeShop;
import com.kh.cafesurfer.domain.coffeeShop.dao.CoffeeShopDAO;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class CoffeeShopDAOImplTest {
  
  @Autowired
  CoffeeShopDAO coffeeShopDAO;
  
  @Test
  @DisplayName("커피숍 등록")
  void insertCoffeeShop() {
  
    CoffeeShop coffeeShop = new CoffeeShop();
    
    coffeeShop.setShopName("다방3");
    coffeeShop.setShopAddress("학원옆");
    coffeeShop.setShopTel("052-355-3113");
    coffeeShop.setYnParking(0);
    coffeeShop.setYnAllDay(0);
  
    CoffeeShop insertCoffeeShop = coffeeShopDAO.insertCoffeeShop(coffeeShop);
    Assertions.assertThat(insertCoffeeShop.getShopName()).isEqualTo("다방3");
  }

  @Test
  @DisplayName("커피숍 수정")
  void updateCoffeeShop() {

    Long shopId = 1L;

    CoffeeShop beforeUpdateCoffeeShop = coffeeShopDAO.selectOne(shopId);
    log.info("beforeUpdateCoffeeShop{}", beforeUpdateCoffeeShop);

    beforeUpdateCoffeeShop.setShopName("커피숍2");
    beforeUpdateCoffeeShop.setShopAddress("서울");
    beforeUpdateCoffeeShop.setShopTel("02-7355-3113");
    beforeUpdateCoffeeShop.setYnParking(1);
    beforeUpdateCoffeeShop.setYnAllDay(0);

    coffeeShopDAO.updateCoffeeShop(shopId, beforeUpdateCoffeeShop);

    CoffeeShop afterUpdateCoffeeShop = coffeeShopDAO.selectOne(shopId);

    Assertions.assertThat(beforeUpdateCoffeeShop.getShopName()).isEqualTo(afterUpdateCoffeeShop.getShopName());
    Assertions.assertThat(beforeUpdateCoffeeShop.getShopAddress()).isEqualTo(afterUpdateCoffeeShop.getShopAddress());
    Assertions.assertThat(beforeUpdateCoffeeShop.getShopTel()).isEqualTo(afterUpdateCoffeeShop.getShopTel());
    Assertions.assertThat(beforeUpdateCoffeeShop.getYnParking()).isEqualTo(afterUpdateCoffeeShop.getYnParking());
    Assertions.assertThat(beforeUpdateCoffeeShop.getYnAllDay()).isEqualTo(afterUpdateCoffeeShop.getYnAllDay());
    log.info("afterUpdateCoffeeShop={}",afterUpdateCoffeeShop);
  }

  @Test
  @DisplayName("커피숍 삭제")
  void deleteCoffeeShop() {

    Long shopId = 1L;

    CoffeeShop deleteCoffeeShop = coffeeShopDAO.deleteCoffeeShop(shopId);
    Assertions.assertThat(coffeeShopDAO.selectOne(1L)).isNull();
  }
  
  @Test
  @DisplayName("커피숍 전체조회")
  void selectAll() {
    
    List<CoffeeShop> list = coffeeShopDAO.selectAll();
    Assertions.assertThat(list.size()).isEqualTo(3);
  }
  
  @Test
  @DisplayName("커피숍 개별조회")
  void selectOne() {

    Long shopId = 1L;
    CoffeeShop findedCoffeeShop = coffeeShopDAO.selectOne(shopId);
    Assertions.assertThat(findedCoffeeShop.getShopName()).isEqualTo("다방1");
    log.info("findedCoffeeShop={}", findedCoffeeShop);
  }

  @Test
  @DisplayName("조회수 카운트")
  void viewCnt() {

    Long shopId = 1L;
    // 조회 전 조회수
    Long beforeUpdateViewCnt = coffeeShopDAO.selectOne(shopId).getViewCnt();
    // 조회
    coffeeShopDAO.updateViewCnt(3L);
    // 조회 후 조회수
    Long afterUpdateViewCnt = coffeeShopDAO.selectOne(shopId).getViewCnt();
    // 조회 후 조회수 = 조회 전 조회수 +1
    Assertions.assertThat(afterUpdateViewCnt-beforeUpdateViewCnt).isEqualTo(1);
  }

//    // when
//    Long shopId = 3L;
//
//    CoffeeShop coffeeShop = coffeeShopDAO.selectOne(shopId);
//    Long currentView = coffeeShop.getViewCnt();
//
//    // try
//    coffeeShopDAO.updateViewCnt(coffeeShop.getShopId());
//
//    // then
//    CoffeeShop viewCntAfterUpdate = coffeeShopDAO.selectOne(shopId);
//    Assertions.assertThat(viewCntAfterUpdate.getViewCnt()).isEqualTo(currentView+1);
//  }

  @Test
  @DisplayName("북마크 카운트")
  void bookmarkCnt() {
  }

  @Test
  @DisplayName("리뷰 카운트")
  void shopReviewCnt() {
  }
}