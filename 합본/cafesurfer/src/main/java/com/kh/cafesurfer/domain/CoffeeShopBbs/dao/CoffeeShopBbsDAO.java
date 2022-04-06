package com.kh.cafesurfer.domain.CoffeeShopBbs.dao;

import java.util.List;

public interface CoffeeShopBbsDAO {
  /**
   * 원글작성
   * @param coffeeShopBbs
   * @return 게시글 번호
   */
  Long saveOrigin(CoffeeShopBbs coffeeShopBbs);

  /**
   * 목록
   * @return
   */
  List<CoffeeShopBbs> findAll();
  List<CoffeeShopBbs> findAll(int startRec, int endRec);

  /**
   * 검색
   * @param coffeeShopBbsFirterCondition 분류, 시작레코드번호, 종료레코드번호, 검색유형, 검색어
   * @return
   */
  List<CoffeeShopBbs> findAll(CoffeeShopBbsFirterCondition coffeeShopBbsFirterCondition);



  /**
   * 상세조회
   * @param shopId 게시글번호
   * @return
   */
  CoffeeShopBbs findByBbsId(Long shopId);

  /**
   * 삭제
   * @param shopId 게시글번호
   * @return   삭제건수
   */
  int deleteBbsId(Long shopId);

  /**
   * 수정
   * @param shopId  게시글번호
   * @param coffeeShopBbs 수정내용
   * @return    수정건수
   */
  int updateByBbsId(Long shopId, CoffeeShopBbs coffeeShopBbs);

  /**전체건수
   *
   * @return 게시글 전체건수
   */
  int totalCount();

  int totalCount(CoffeeShopBbsFirterCondition coffeeShopBbsFirterCondition);

  // 조회수 증가(카운트)
  Long updateViewCnt(Long shopId);

  // 북마크 증가(카운트)
  Long updateBookmarkCnt(Long shopId);

  // 리뷰수 증가(카운트)
  Long updateShopReviewCnt(Long shopId);

}
