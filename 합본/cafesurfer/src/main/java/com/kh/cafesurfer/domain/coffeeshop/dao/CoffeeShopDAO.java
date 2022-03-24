package com.kh.cafesurfer.domain.coffeeshop.dao;

import com.kh.cafesurfer.domain.coffeeshop.CoffeeShop;

import java.util.List;

public interface CoffeeShopDAO {

  // 등록
  CoffeeShop insertCoffeeShop(CoffeeShop coffeeShop);
  
  // 수정
  CoffeeShop updateCoffeeShop(Long shopId, CoffeeShop coffeeShop);
  
  // 삭제
  CoffeeShop deleteCoffeeShop(Long shopId);
  
  // 조회(전체)
  List<CoffeeShop> selectAll();
  
  // 조회(개별)
  CoffeeShop selectOne(Long shopId);

  // 조회수 증가(카운트)
  Long updateViewCnt(Long shopId);

  // 북마크 증가(카운트)
  Long updateBookmarkCnt(Long shopId);

  // 리뷰수 증가(카운트)
  Long updateShopReviewCnt(Long shopId);

}
