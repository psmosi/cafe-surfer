package com.kh.cafesurfer.domain.CoffeeShopBbs.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class CoffeeShopBbsFirterCondition {
  private String category;      //분류코드
  private int startRec;         //시작레코드
  private int endRec;           //종료레코드
  private String searchType;    //검색유형
  private String keyword;       //검색어

  public CoffeeShopBbsFirterCondition(String category, String searchType, String keyword) {
    this.category = category;
    this.searchType = searchType;
    this.keyword = keyword;
  }
}
