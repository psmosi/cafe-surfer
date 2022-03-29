package com.kh.cafesurfer.web;


import com.kh.cafesurfer.domain.memberShip.MemberShip;
import com.kh.cafesurfer.domain.memberShip.svc.MemberShipSVC;
import com.kh.cafesurfer.web.form.memberShip.DetailForm;
import com.kh.cafesurfer.web.form.memberShip.MemberGender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

  private final MemberShipSVC memberShipSVC;

  //성별
  @ModelAttribute("memberGender")
  public MemberGender[] gender(){

    return MemberGender.values();  //[MALE, FEMALE]
  }

  //관리자 홈
  @GetMapping
  public String home(){
    return "admin/home";
  }

  //회원전체조회
  @GetMapping("/members")
  public String members(Model model){

    List<MemberShip> memberAll = memberShipSVC.selectAll();
    model.addAttribute("memberAll", memberAll);

    return "admin/member/members";
  }

  @GetMapping("/apimembers")
  public String apiMembers(){

    return "admin/member/apimembers";
  }

  //회원개별조회
  @GetMapping("/members/{memberEmail}")
  public String member(
      @PathVariable("memberEmail") String memberEmail,
      Model model){

    MemberShip memberShip = memberShipSVC.selectMemberByEmail(memberEmail);
    log.info("memberShip={}", memberShip);


    DetailForm detailForm = new DetailForm();
    detailForm.setMemberId(memberShip.getMemberId());
    detailForm.setMemberEmail(memberShip.getMemberEmail());
    detailForm.setMemberPasswd(memberShip.getMemberPasswd());
    detailForm.setMemberName(memberShip.getMemberName());
    detailForm.setMemberGender(getGender(memberShip.getMemberGender()));
    detailForm.setMemberAge(memberShip.getMemberAge());
    detailForm.setMemberTel(memberShip.getMemberTel());
    detailForm.setMemberOwner(memberShip.getMemberOwner());

    log.info("detailForm={}", detailForm);
    model.addAttribute("detailForm", detailForm);

    return "admin/member/member";
  }

  //회원탈퇴
  @GetMapping("/members/{memberEmail}/del")
  public String out(
      @PathVariable("memberEmail") String memberEmail){

    memberShipSVC.selectMemberByEmail(memberEmail);

    return "redirect:/admin/members";
  }

  //문자열로 Enum객체에서 상수요소 찾아오기
  private MemberGender getGender(String gender) {
    MemberGender finded = null;
    for(MemberGender g : MemberGender.values()){
      if(g.getDescription().equals(gender)){
        finded = MemberGender.valueOf(g.name());
        break;
      }
    }
    return finded;
  }

  //콤마를 구분자로하는 문자열을 문자열 요소로갖는 리스트로 변환
  private List<String> stringToList(String str) {
    String[] array = str.split(",");
    log.info("array={}", array.length);
    List<String> list = new ArrayList<>();
    for (int i = 0; i < array.length; i++) {
      list.add(array[i]);
    }
    return list;
  }

}
