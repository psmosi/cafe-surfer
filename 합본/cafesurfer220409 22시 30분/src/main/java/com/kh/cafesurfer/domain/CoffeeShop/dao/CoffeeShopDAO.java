package com.kh.cafesurfer.domain.CoffeeShop.dao;

import com.kh.cafesurfer.domain.CoffeeShop.CoffeeShop;

import java.util.List;

public interface CoffeeShopDAO {

  //등록
  Long insertCoffeeShop(CoffeeShop coffeeShop);

  //전체조회
  List<CoffeeShop> selectAll();
  List<CoffeeShop> selectAll(int startRec, int endRec);

  //전체목록 검색(관리자)
  List<CoffeeShop> selectAll(CoffeeShopFilterCondition coffeeShopFilterCondition);

  //개별 조회
  CoffeeShop selectOne(Long shopId);

  //삭제
  int deleteCoffeeShop(Long shopId);

  //수정
  int updateCoffeeShop(Long shopId, CoffeeShop coffeeShop);

  //전체건수
  int totalCount();
  int totalCount(CoffeeShopFilterCondition coffeeShopFilterCondition);

  //북마크 체크 여부 판단
  Boolean selectShopBookmark(Long shopId, Long memberId);

  // 조회수 증가(카운트)
  Long updateViewCnt(Long shopId);

  // 북마크 증가(카운트)
  Long updateBookmarkCnt(Long shopId);

  // 리뷰수 증가(카운트)
  Long updateShopReviewCnt(Long shopId);


}
