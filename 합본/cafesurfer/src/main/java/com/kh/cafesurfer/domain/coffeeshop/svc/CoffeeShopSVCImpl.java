package com.kh.cafesurfer.domain.coffeeshop.svc;

import com.kh.cafesurfer.domain.coffeeshop.CoffeeShop;
import com.kh.cafesurfer.domain.coffeeshop.dao.CoffeeShopDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional    // 동시실행 >> 메소드 레벨, 클래스 레벨 모두 사용 가능
public class CoffeeShopSVCImpl implements CoffeeShopSVC {

  private final CoffeeShopDAO coffeeShopDAO;

  // 등록
  @Override
  public CoffeeShop addCoffeeShop(CoffeeShop coffeeShop) {
    return coffeeShopDAO.insertCoffeeShop(coffeeShop);
  }

  // 수정
  @Override
  public CoffeeShop modifyCoffeeShop(Long shopId, CoffeeShop coffeeShop) {
    return coffeeShopDAO.updateCoffeeShop(shopId, coffeeShop);
  }

  // 삭제
  @Override
  public CoffeeShop removeCoffeeShop(Long shopId) {
    return coffeeShopDAO.deleteCoffeeShop(shopId);
  }

  // 전체조회
  @Override
  public List<CoffeeShop> findAll() {
    return coffeeShopDAO.selectAll();
  }

//  // 개별조회 (조회수 증가 반영)
//  @Override
//  public CoffeeShop findByShopId(Long shopId) {
//    CoffeeShop findedCoffeeShop = coffeeShopDAO.selectOne(shopId);
//    coffeeShopDAO.updateViewCnt(shopId);
//
//    return findedCoffeeShop;
//  }

  // 개별조회 (조회수 증가 미반영)
  @Override
  public CoffeeShop findByShopId(Long shopId) {
    return coffeeShopDAO.selectOne(shopId);
  }

  // 조회수 카운트(증가)
  @Override
  public Long increaseViewCnt(Long shopId) {
    return coffeeShopDAO.updateViewCnt(shopId);
  }

  // 북마크 카운트(증가)
  @Override
  public Long increaseBookmarkCnt(Long shopId) {
    return coffeeShopDAO.updateBookmarkCnt(shopId);
  }

  // 리뷰 카운트(증가)
  @Override
  public Long increaseShopReviewCnt(Long shopId) {
    return coffeeShopDAO.updateShopReviewCnt(shopId);
  }
}
