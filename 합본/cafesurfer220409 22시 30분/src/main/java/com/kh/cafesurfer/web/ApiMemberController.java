package com.kh.cafesurfer.web;


import com.kh.cafesurfer.domain.memberShip.MemberShip;
import com.kh.cafesurfer.domain.memberShip.svc.MemberShipSVC;
import com.kh.cafesurfer.web.api.ApiResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ApiMemberController {

  private final MemberShipSVC memberSVC;

  @ResponseBody //http응답 메세지 바디에 직접 쓰기
  //(반환타입이 객체이면 java객체=>json포맷 문자열로 변환 후)
  @GetMapping("/api/members")
  public ApiResult<List<MemberShip>> members() {
    List<MemberShip> list = memberSVC.FindAll();
    ApiResult<List<MemberShip>> result = new ApiResult<>("00", "success", list);
    return result;
  }

  @ResponseBody
  @GetMapping("/api/member")
  public ApiResult<MemberShip> member(@RequestParam String email) {
    MemberShip memberShip = memberSVC.memberFindByEmail(email);
    ApiResult<MemberShip> result = new ApiResult<>("00", "success", memberShip);
    return result;
  }

  @ResponseBody
  @GetMapping("/api/members/{email}/exist")
  public ApiResult<MemberShip> existMember(@PathVariable String email) {

    boolean existMember = memberSVC.existMember(email);

    if (existMember) {
      return new ApiResult("00", "success", "OK");
    } else {
      return new ApiResult("99", "fail", "NOK");
    }
  }

  @ResponseBody
  @PutMapping("/api/members/email/find")
  public ApiResult<String> findEmailByNickname(@RequestBody String name) {

    log.info("name={}",name);
    ApiResult<String> result = null;
    String email = memberSVC.findEmailByName(name);

    //StringUtils.isEmpty() : null 또는 ""문자열인지 체크
    //if(email == null || email.equals(""))
    if (!StringUtils.isEmpty(email)) {
      result = new ApiResult<>("00", "success", email);
    } else {
      result = new ApiResult<>("99", "fail", "찾고자 하는 아이디가 없습니다.");
    }

    return result;
  }

}