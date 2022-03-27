package com.kh.cafesurfer.domain.memberShip.svc;

import com.kh.cafesurfer.web.api.dao.MemberShipDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
class MemberShipSVCImplTest {

  @Autowired
  private MemberShipDAO memberShipDAO;

  @Test
  @DisplayName("회원유무체크:존재하는경우")
  void isMemberOk() {
    String email = "test3@kh.com";
    assertThat(memberShipDAO.existMember(email)).isTrue();
  }
  @Test
  @DisplayName("회원유무체크:존재하지 않는 경우")
  void isMemberNok() {
    String email = "zzz@kh.com";
    assertThat(memberShipDAO.existMember(email)).isFalse();
  }
}