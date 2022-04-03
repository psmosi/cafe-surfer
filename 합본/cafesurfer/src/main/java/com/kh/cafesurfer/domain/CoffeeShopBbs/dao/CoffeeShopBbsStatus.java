package com.kh.cafesurfer.domain.CoffeeShopBbs.dao;

public enum CoffeeShopBbsStatus {
  D("삭제"), I("임시저장"), W("경고");

  private final String description;

  CoffeeShopBbsStatus(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

}
