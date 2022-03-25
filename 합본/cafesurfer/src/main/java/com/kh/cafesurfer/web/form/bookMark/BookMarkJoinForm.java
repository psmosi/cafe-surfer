package com.kh.cafesurfer.web.form.bookMark;

import lombok.Data;

@Data
public class BookMarkJoinForm {
  private Long memberId;                    //  MEMBER_ID	NUMBER(5,0)
  private Long shopId;                      //  SHOP_ID	NUMBER(5,0)
}
