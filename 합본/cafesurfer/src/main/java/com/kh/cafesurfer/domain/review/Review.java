package com.kh.cafesurfer.domain.review;

import lombok.Data;

import java.time.LocalDateTime;

@Data

public class Review {
  private Long reviewId;               // REVIEW_ID	        NUMBER(5,0)
  private String reviewContent;        // REVIEW_CONTENT	  CLOB
  private LocalDateTime reviewCdate;   // REVIEW_CDATE	    TIMESTAMP(6)
  private int reportCnt;               // REPORT_COUNT	    NUMBER(3,0)
  private Long memberId;               // MEMBER_ID	        NUMBER(5,0)
  private Long shopId;                 // SHOP_ID           NUMBER(5,0)

//  public Review(String reviewContent, long memberId, long shopId) {
//    this.reviewContent = reviewContent;
//    this.memberId = memberId;
//    this.shopId = shopId;
//
//  }
}



