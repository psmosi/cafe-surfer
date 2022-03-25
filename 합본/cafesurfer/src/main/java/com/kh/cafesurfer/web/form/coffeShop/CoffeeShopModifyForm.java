package com.kh.cafesurfer.web.form.coffeShop;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CoffeeShopModifyForm {

  @NotBlank
  @Size(min = 1, max = 30)
  private String shopName;              // SHOP_NAME	            VARCHAR2(30 CHAR)
  @NotBlank @Size(min = 1, max = 105)
  private String shopAddress;           // SHOP_ADDRESS           VARCHAR2(105 BYTE)
  @NotBlank @Size(min = 8, max = 15)
  private String shopTel;               // SHOP_TEL	              VARCHAR2(15 BYTE)
  @NotBlank
  private int ynParking;                // PARKING	              NUMBER
  @NotBlank
  private int ynAllDay;                 // 24HOURS	              NUMBER



//  // 전체목록
//  private Long shopId;                  // SHOP_ID	            NUMBER(5,0)
//  private String shopName;              // SHOP_NAME	          NVARCHAR2(30 CHAR)
//  private String shopAddress;           // SHOP_ADDRESS         VARCHAR2(105 BYTE)
//  private String shopTel;               // SHOP_TEL	            VARCHAR2(15 BYTE)
//  private Long viewCnt;                 // VIEW_COUNT	          NUMBER(5,0)
//  private Long shopBookmarkCnt;         // SHOP_BOOKMARK_COUNT	NUMBER(5,0)
//  private Long shopReviewCnt;           // SHOP_REVIEW_COUNT    NUMBER(5,0)
//  private int ynParking;                // PARKING	            NUMBER
//  private int ynAllDay;                 // 24HOURS	            NUMBER
//  private LocalDateTime shopCdate;      // SHOP_CDATE           TIMESTAMP(6)
}
