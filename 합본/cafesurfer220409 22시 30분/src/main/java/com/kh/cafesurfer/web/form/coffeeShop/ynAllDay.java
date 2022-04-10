package com.kh.cafesurfer.web.form.coffeeShop;
public enum ynAllDay {
   AllDay("예"), noAllDay("아니오");

  private final String description;

  ynAllDay(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
