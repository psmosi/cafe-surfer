package com.kh.cafesurfer.web.form.login;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginMemberShip {
  private Long memberId;
  private String memberEmail;
  private String memberName;
}
