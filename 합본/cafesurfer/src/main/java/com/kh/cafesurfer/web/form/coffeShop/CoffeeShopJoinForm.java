package com.kh.cafesurfer.web.form.coffeShop;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class CoffeeShopJoinForm {

  @NotBlank
  @Size(min = 1, max = 30)
  private String shopName;                // SHOP_NAME	          NVARCHAR2(30 CHAR)
  @NotBlank
  @Size(min = 1, max = 105)
  private String shopAddress;             // SHOP_ADDRESS         VARCHAR2(105 BYTE)
  @NotBlank
  @Size(min = 8, max = 15)
  private String shopTel;                 // SHOP_TEL	            VARCHAR2(15 BYTE)

  private String ynParking;                  // PARKING	            NUMBER

  private String ynAllDay;                    // 24HOURS	              NUMBER

  private Long memberId;                    //  MEMBER_ID	NUMBER(5,0)
  private Long shopId;                      //  SHOP_ID	NUMBER(5,0)
  private String memberEmail;
  private String memberName;

  private String bcategoryB0101;
  private String bcategoryB0102;
  private String bcategoryB0103;
  private String bcategoryB0104;

  private List<MultipartFile> files1;   //첨부파일
  private List<MultipartFile> files2;   //첨부파일
  private List<MultipartFile> files3;   //첨부파일
  private List<MultipartFile> files4;   //첨부파일

//  private  String bcategory;
//  private  String gubun;                //  gubun                varchar2(11)   default 'M0101





//  // 전체목록
//  private Long shopId;                  // SHOP_ID	            NUMBER(5,0)
//  private String shopName;              // SHOP_NAME	          NVARCHAR2(30 CHAR)
//  private String shopAddress;           // SHOP_ADDRESS         VARCHAR2(105 BYTE)
//  private String shopTel;               // SHOP_TEL	            VARCHAR2(15 BYTE)
//  private Long viewCnt;                 // VIEW_COUNT	            NUMBER(5,0)
//  private Long shopBookmarkCnt;         // SHOP_BOOKMARK_COUNT	NUMBER(5,0)
//  private Long shopReviewCnt;           // SHOP_REVIEW_COUNT    NUMBER(5,0)
//  private int ynParking;                // PARKING	            NUMBER
//  private int ynAllDay;                 // 24HOURS	              NUMBER
//  private LocalDateTime shopCdate;      // SHOP_CDATE           TIMESTAMP(6)

}
