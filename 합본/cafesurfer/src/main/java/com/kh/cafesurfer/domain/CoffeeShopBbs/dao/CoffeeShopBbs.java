package com.kh.cafesurfer.domain.CoffeeShopBbs.dao;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CoffeeShopBbs {
  private Long shopId;                  // SHOP_ID	            NUMBER(5,0)
  private String shopName;              // SHOP_NAME	          NVARCHAR2(30 CHAR)
  private String shopAddress;           // SHOP_ADDRESS         VARCHAR2(105 BYTE)
  private String shopTel;               // SHOP_TEL	            VARCHAR2(15 BYTE)
  private Long viewCnt;               // VIEW_COUNT	            NUMBER(5,0)
  private Long shopBookmarkCnt;         // SHOP_BOOKMARK_COUNT	NUMBER(5,0)
  private Long shopReviewCnt;           // SHOP_REVIEW_COUNT    NUMBER(5,0)
  private String ynParking;                // PARKING	            NUMBER
  private String ynAllDay;                // 24HOURS	              NUMBER
  private LocalDateTime shopCdate;      // SHOP_CDATE           TIMESTAMP(6)
  private String bcategoryB0101;
  private String bcategoryB0102;
  private String bcategoryB0103;
  private String bcategoryB0104;


  private String memberEmail;
  private String memberName;
  private Long memberId;                    //  MEMBER_ID	NUMBER(5,0)




  //  private Long bbsId;           //게시글 번호 BBS_ID	NUMBER(10,0)
//  private String bcategory;     //분류 BCATEGORY	VARCHAR2(11 BYTE)
//  private String title;         //제목 TITLE	VARCHAR2(150 BYTE)
//  private String email;         //EMAIL	VARCHAR2(50 BYTE)
//  private String nickname;      //별칭 NICKNAME	VARCHAR2(30 BYTE)
//  private int hit;              //조회수 HIT	NUMBER(5,0)
//  private String bcontent;      //내용 BCONTENT	CLOB
//  private Long pbbsId;          //부모 게시글 번호 PBBS_ID	NUMBER(10,0)
//  private Long bgroup;          //답글그룹 BGROUP	NUMBER(10,0)
//  private int step;             //답글단계 STEP	NUMBER(3,0)
//  private int bindent;          //답글 들여쓰기 BINDENT	NUMBER(3,0)
//  private CoffeeShopBbsStatus status;     //게시글 상태 STATUS	CHAR(1 BYTE) (D:삭제,I:임시저장,W:경고)
//  private LocalDateTime cdate;  //생성일 CDATE	TIMESTAMP(6)
//  private LocalDateTime udate;  //수정일 UDATE	TIMESTAMP(6)
}