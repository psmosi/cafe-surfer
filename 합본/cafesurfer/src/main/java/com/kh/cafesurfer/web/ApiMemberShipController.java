package com.kh.cafesurfer.web;

import com.kh.cafesurfer.domain.membership.MemberShip;
import com.kh.cafesurfer.domain.membership.svc.MemberShipSVC;
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
public class ApiMemberShipController {

  private final MemberShipSVC memberShipSVC;

  @ResponseBody  //http응답 메세지 바디에 직접 쓰기
  // (반환 타입이 객체이면 java객체 =>json 포맷 문자열로 변환후)
  @GetMapping("/api/members")
  public ApiResult<List<MemberShip>> members() {
    List<MemberShip> list = memberShipSVC.selectAll();
    ApiResult<List<MemberShip>> result = new ApiResult<>("00", "success", list);
    return result;
  }

  @ResponseBody
  @GetMapping("/api/member")
  public ApiResult<MemberShip> member(@RequestParam String memberEmail) {
    MemberShip memberShip = memberShipSVC.selectMemberByEmail(memberEmail);
    ApiResult<MemberShip> result = new ApiResult<>("00","success",memberShip);
    return result;
  }

  @ResponseBody
  @GetMapping("/api/members/{memberEmail}/exist")
  public ApiResult<MemberShip> existMember(@PathVariable String memberEmail){

    boolean existMember = memberShipSVC.existMember(memberEmail);

    if (existMember){
      return new ApiResult("00","success","OK");
    }else {
      return new ApiResult("99","fail","NOK");
    }
  }

  @ResponseBody
  @PutMapping("/api/members/memberEmail/find")
  public ApiResult<String> findEmailByName(
      @RequestBody String memberName
  ) {
    log.info("memberName={}",memberName);
    ApiResult<String> result = null;

    String memberEmail = memberShipSVC.findEmailByName(memberName);

    //StringUtils.isEmpty() : null또는 ""문자열 인지 체크
    //if(email == null || email.equals(""))
    if (!StringUtils.isEmpty(memberEmail)) {
      result = new ApiResult<>("00", "success", memberEmail);
    }else {
      result = new ApiResult<>("99", "fail", "찾고자하는 아이디가 없습니다");
    }
    return result;
  }

}
