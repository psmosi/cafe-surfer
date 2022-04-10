package com.kh.cafesurfer.web.form.coffeeShop;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CoffeeShopListForm {

  private Long shopId;                   // SHOP_ID	              NUMBER(5,0)
  private String shopName;               // SHOP_NAME	            NVARCHAR2(30 CHAR)
  private String shopAddress;            // SHOP_ADDRESS          VARCHAR2(105 BYTE)
  private String shopTel;                // SHOP_TEL	            VARCHAR2(15 BYTE)
  private Long viewCnt;                  // VIEW_COUNT	          NUMBER(5,0)
  private Long shopBookmarkCnt;          // SHOP_BOOKMARK_COUNT 	NUMBER(5,0)
  private Long shopReviewCnt;            // SHOP_REVIEW_COUNT     NUMBER(5,0)
  private String ynParking;                 // PARKING	              NUMBER
  private String ynAllDay;                  // 24HOURS	              NUMBER
  private LocalDateTime shopCdate;       // SHOP_CDATE            TIMESTAMP(6)


  private String memberEmail;
  private String memberName;
  private Long memberId;                    //  MEMBER_ID	NUMBER(5,0)
  private String memberPasswd;    //  MEMBER_PASSWD	VARCHAR2(20 BYTE)
  private String memberGender;    //  MEMBER_GENDER	CHAR(3 BYTE)
  private Long memberAge;         //  MEMBER_AGE	NUMBER(8,0)
  private String memberTel;       //  MEMBER_TEL	VARCHAR2(15 BYTE)


  private Long uploadfileId;      //  UPLOADFILE_ID	NUMBER(10,0)
  private String code;            //  CODE	VARCHAR2(11 BYTE)
  private Long rid;               //  RID	NUMBER(10,0)
  private String store_filename;  //  STORE_FILENAME	VARCHAR2(50 BYTE)
  private String upload_filename; //  UPLOAD_FILENAME	VARCHAR2(50 BYTE)
  private String fsize;           //  FSIZE	VARCHAR2(45 BYTE)
  private String ftype;           //  FTYPE	VARCHAR2(50 BYTE)
  private LocalDateTime cdate;    //  CDATE	TIMESTAMP(6)
  private LocalDateTime udate;    //  UDATE	TIMESTAMP(6)

  //가게별 대표 해시태그 4개
  private String hcategoryh0101; //음료
  private String hcategoryh0102; //지역
  private String hcategoryh0103; //분위기
  private String hcategoryh0104; //뷰


}
