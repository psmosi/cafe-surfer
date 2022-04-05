package com.kh.cafesurfer.domain.coffeeShop.dao;



import com.kh.cafesurfer.domain.CoffeeShopBbs.dao.CoffeeShopBbs;
import com.kh.cafesurfer.domain.CoffeeShopBbs.dao.CoffeeShopBbsDAO;
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
  CoffeeShopBbsDAO coffeeShopBbsDAO;
  
  @Test
  @DisplayName("커피숍 등록")
  void insertCoffeeShop() {

    CoffeeShopBbs coffeeShop = new CoffeeShopBbs();
    
    coffeeShop.setShopName("다방3");
    coffeeShop.setShopAddress("학원옆");
    coffeeShop.setShopTel("052-355-3113");
    coffeeShop.setYnParking("예");
    coffeeShop.setYnAllDay("아니요");

    Long saveOriginId = coffeeShopBbsDAO.saveOrigin(coffeeShop);
    Assertions.assertThat(saveOriginId).isEqualTo(5);
    log.info("saveOriginId={}", saveOriginId);

  }

  @Test
  @DisplayName("커피숍 수정")
  void updateCoffeeShop() {

    Long shopId = 1L;

    CoffeeShopBbs beforeUpdateCoffeeShop = coffeeShopBbsDAO.findByBbsId(shopId);
    log.info("beforeUpdateCoffeeShop{}", beforeUpdateCoffeeShop);

    beforeUpdateCoffeeShop.setShopName("커피숍2");
    beforeUpdateCoffeeShop.setShopAddress("서울");
    beforeUpdateCoffeeShop.setShopTel("02-7355-3113");
    beforeUpdateCoffeeShop.setYnParking("예");
    beforeUpdateCoffeeShop.setYnAllDay("아니요");

    coffeeShopBbsDAO.updateByBbsId(shopId, beforeUpdateCoffeeShop);

    CoffeeShopBbs afterUpdateCoffeeShop = coffeeShopBbsDAO.findByBbsId(shopId);

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

    int deleteCoffeeShop = coffeeShopBbsDAO.deleteBbsId(shopId);

    CoffeeShopBbs findedBbsItem = coffeeShopBbsDAO.findByBbsId(shopId);
    Assertions.assertThat(findedBbsItem).isNull();

  }
  
  @Test
  @DisplayName("커피숍 전체조회")
  void selectAll() {
    
    List<CoffeeShopBbs> list = coffeeShopBbsDAO.findAll();
    Assertions.assertThat(list.size()).isEqualTo(3);
  }
  
  @Test
  @DisplayName("커피숍 개별조회")
  void selectOne() {

    Long shopId = 1L;
    CoffeeShopBbs findedCoffeeShop = coffeeShopBbsDAO.findByBbsId(shopId);
    Assertions.assertThat(findedCoffeeShop.getShopName()).isEqualTo("다방1");
    log.info("findedCoffeeShop={}", findedCoffeeShop);
  }

  @Test
  @DisplayName("조회수 카운트")
  void viewCnt() {

    Long shopId = 1L;
    // 조회 전 조회수
    Long beforeUpdateViewCnt = coffeeShopBbsDAO.findByBbsId(shopId).getViewCnt();
    // 조회
    coffeeShopBbsDAO.updateViewCnt(3L);
    // 조회 후 조회수
    Long afterUpdateViewCnt = coffeeShopBbsDAO.findByBbsId(shopId).getViewCnt();
    // 조회 후 조회수 = 조회 전 조회수 +1
    Assertions.assertThat(afterUpdateViewCnt-beforeUpdateViewCnt).isEqualTo(1);
  }



}