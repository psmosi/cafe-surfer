package com.kh.cafesurfer.domain.bookMark;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookMark {

private LocalDateTime bookmarkChkdate;    //  BOOKMARK_CHKDATE	TIMESTAMP(6)
private Long memberId;                    //  MEMBER_ID	NUMBER(5,0)
private Long shopId;                      //  SHOP_ID	NUMBER(5,0)

}
