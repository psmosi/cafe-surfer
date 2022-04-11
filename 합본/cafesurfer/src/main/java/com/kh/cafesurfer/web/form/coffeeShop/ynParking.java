package com.kh.cafesurfer.web.form.coffeeShop;
public enum ynParking {
  Parking("예"), noParking("아니오");

  private final String description;

  ynParking(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
