package com.kh.cafesurfer.domain.memberShip.svc;

import com.kh.cafesurfer.domain.memberShip.MemberShip;

import java.util.List;

public interface MemberShipSVC {
  /**
   * 등록
   * @param memberShip
   * @return
   */
  MemberShip addMember(MemberShip memberShip);

  /**
   * 수정
   * @param memberShip
   */
  void modifyMember(MemberShip memberShip);

  /**
   * 조회 by email
   * @param memberEmail
   * @return
   */
  MemberShip memberFindByEmail(String memberEmail);

  /**
   * 조회 by id
   * @param memberId
   * @return
   */
  MemberShip memberFindByMemberId(Long memberId);

  /**
   * 전체조회
   * @return
   */
  List<MemberShip> FindAll();

  /**
   * 탈퇴
   * @param memberEmail
   */
  void removeMember(String memberEmail);

  /**
   * 회원 유무 체크
   * @param memberEmail
   * @return
   */
  boolean existMember(String memberEmail);

  /**
   * 로그인 인증
   * @param memberEmail
   * @param memberPasswd
   * @return
   */
  MemberShip login(String memberEmail, String memberPasswd);

  /**
   * 비밀번호 일치 여부 체크
   * @param memberEmail
   * @param memberPasswd
   * @return
   */
  boolean isMember(String memberEmail, String memberPasswd);

  /**
   * 아이디 찾기
   * @param nickname
   * @return
   */
  String findEmailByName(String nickname);
}
