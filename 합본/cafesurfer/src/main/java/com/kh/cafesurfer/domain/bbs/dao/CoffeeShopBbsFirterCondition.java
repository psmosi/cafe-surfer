package com.kh.cafesurfer.domain.bbs.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class CoffeeShopBbsFirterCondition {
  private Long shopId;          // SHOP_ID	            NUMBER(5,0)
  private int startRec;         //시작레코드
  private int endRec;           //종료레코드
  private String searchType;    //검색유형
  private String keyword;       //검색어

  public CoffeeShopBbsFirterCondition(Long shopId, String searchType, String keyword) {
    this.shopId = shopId;
    this.searchType = searchType;
    this.keyword = keyword;
  }
}
