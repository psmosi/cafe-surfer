package com.kh.cafesurfer.domain.coffeeshop.svc;

import com.kh.cafesurfer.domain.coffeeshop.CoffeeShop;

import java.util.List;

public interface CoffeeShopSVC {

  // 등록
  CoffeeShop addCoffeeShop(CoffeeShop coffeeShop);

  // 수정
  CoffeeShop modifyCoffeeShop(Long shopId, CoffeeShop coffeeShop);

  // 삭제
  CoffeeShop removeCoffeeShop(Long shopId);

  // 조회(전체)
  List<CoffeeShop> findAll();

  // 조회(개별)
  CoffeeShop findByShopId(Long shopId);

  // 조회수 증가(카운트)
  Long increaseViewCnt(Long shopId);

  // 북마크 증가(카운트)
  Long increaseBookmarkCnt(Long shopId);

  // 리뷰수 증가(카운트)
  Long increaseShopReviewCnt(Long shopId);

}
