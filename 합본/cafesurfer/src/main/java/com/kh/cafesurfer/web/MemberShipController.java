package com.kh.cafesurfer.web;

import com.kh.cafesurfer.domain.memberShip.MemberShip;
import com.kh.cafesurfer.domain.memberShip.svc.MemberShipSVC;
import com.kh.cafesurfer.web.form.memberShip.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberShipController {

  private final MemberShipSVC memberShipSVC;

  //성별
  @ModelAttribute("memberGender")
  public MemberGender[] gender(){

    return MemberGender.values();  //[MALE, FEMALE]
  }

  //회원가입
  @GetMapping("/join")
  public String joinForm(@ModelAttribute JoinForm joinForm){
    log.info("joinForm() 호출됨!");
//    return "memberShip/joinForm";
    return "memberJoin/memberJoinpage";
  }

  //회원가입처리
  @PostMapping("/join")
  public String join(
      @Valid @ModelAttribute JoinForm joinForm,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes){

    log.info("join() 호출됨!");
    log.info("MemberEmail={}, MemberPasswd={}, MemberPasswdChk={}, MemberName={},MemberGender={},MemberAge={},MemberTel={}",
        joinForm.getMemberEmail(),joinForm.getMemberPasswd(),
        joinForm.getMemberPasswdChk(),joinForm.getMemberName(),joinForm.getMemberGender(),joinForm.getMemberAge(),joinForm.getMemberTel());

    //1)유효성체크 - 필드오류
    if(bindingResult.hasErrors()){
      log.info("error={}", bindingResult);
      return "memberJoin/memberJoinpage";
    }
    //2)아이디 중복체크
    if(memberShipSVC.existMember(joinForm.getMemberEmail())){
      bindingResult.rejectValue("getMemberEmail","joinForm.MemberEmail.dup");
      log.info("error={}", bindingResult);
      return "memberJoin/memberJoinpage";
    }
    //3)유효성체크 - global 오류 (2개이상의 필드체크, 백앤드로직 수행시 발생오류)
    //비밀번호 != 비빌번호체크
    if(!joinForm.getMemberPasswd().equals(joinForm.getMemberPasswdChk()))   {
      bindingResult.reject("member.MemberPasswdChk");
      return "memberJoin/memberJoinpage";
    }

    //4)정상처리로직
    log.info("joinForm={}", joinForm);
    MemberShip memberShip = new MemberShip( null,
        joinForm.getMemberEmail(), joinForm.getMemberPasswd(), joinForm.getMemberName(),
        joinForm.getMemberGender().getDescription(),joinForm.getMemberAge(),joinForm.getMemberTel(),null);

    MemberShip joinedMember = memberShipSVC.insertMember(memberShip);
    log.info("MemberEmail={}, MemberPasswd={}, MemberName={}",
        joinedMember.getMemberEmail(),joinedMember.getMemberPasswd(),joinedMember.getMemberName());

    return "redirect:/login/loginForm";
  }

//  //문자열 리스트를 ','를 구분자로하는 문자열 변환
//  private String makeListToString(List<String> hobby) {
//    StringBuffer str = new StringBuffer();
//    for (int i=0; i<hobby.size(); i++) {
//      str.append(hobby.get(i));
//      if(i == hobby.size()-1) break;
//      str.append(",");
//    }
//    return str.toString();
//  }

  @GetMapping("/joinSuccess")
  public String joinSuccess(){
    return "memberShip/joinSuccess";
  }

//  //회원 수정
//  @GetMapping("/{memberEmail}/memberModify")
//  public String memberModify(){
//    log.info("memberModify() 호출됨");
//    return "memberJoin/memberModifypage";
//  }

  //회원수정
  @GetMapping("/{MemberEmail}/memberModify")
  public String modifyForm(
      @PathVariable("MemberEmail") String MemberEmail,
      Model model){
    log.info("memberModify() 호출됨");
    MemberShip memberShip = memberShipSVC.selectMemberByEmail(MemberEmail);

    ModifyForm modifyForm = new ModifyForm();
    modifyForm.setMemberEmail(memberShip.getMemberEmail());
    modifyForm.setMemberPasswd(memberShip.getMemberPasswd());
    modifyForm.setMemberName(memberShip.getMemberName());
    modifyForm.setMemberGender(getGender(memberShip.getMemberGender()));
    modifyForm.setMemberAge(memberShip.getMemberAge());
    modifyForm.setMemberTel(memberShip.getMemberTel());

    model.addAttribute("modifyForm", modifyForm);

    return "memberJoin/memberModifypage";
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
  //회원수정 처리
  @PostMapping("/{MemberEmail}/memberModify")
  public String modify(
      @Valid @ModelAttribute ModifyForm modifyForm,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes){

    //1) 유효성 체크  - 필드오류
    if(bindingResult.hasErrors()){
      log.info("bindingResult={}", bindingResult);
      return "memberJoin/memberModifypage";

    }

    //2) 비밀번호가 일치하는지 체크
    if(!memberShipSVC.isMember(modifyForm.getMemberEmail(), modifyForm.getMemberPasswd())){
      bindingResult.rejectValue("MemberPasswd","memberShip.MemberPasswdChk");
      log.info("bindingResult={}", bindingResult);
      return "memberJoin/memberModifypage";
    }

    //3) 회원정보 수정
    MemberShip memberShip = new MemberShip( null,
        modifyForm.getMemberEmail(), modifyForm.getMemberPasswd(), modifyForm.getMemberName(),
        modifyForm.getMemberGender().getDescription(),
        modifyForm.getMemberAge(),
        modifyForm.getMemberTel(),null);

    memberShipSVC.updateMember(memberShip);
    redirectAttributes.addAttribute("MemberEmail", memberShip.getMemberEmail());

    return "redirect:/members/{MemberEmail}/memberModifypage";  //회원 상세화면 이동
  }

  //회원상세
  @GetMapping("/{memberEmail}/detailForm")
  public String detail(@PathVariable String memberEmail, Model model){

    MemberShip memberShip = memberShipSVC.selectMemberByEmail(memberEmail);

    DetailForm detailForm = new DetailForm();
    detailForm.setMemberEmail(memberShip.getMemberEmail());
    detailForm.setMemberName(memberShip.getMemberName());
    detailForm.setMemberGender(getGender(memberShip.getMemberGender()));
    detailForm.setMemberAge(memberShip.getMemberAge());
    detailForm.setMemberTel(memberShip.getMemberTel());

    model.addAttribute("detailForm", detailForm);

    return "memberJoin/detailForm";
  }

  //회원탈퇴
  @GetMapping("/{memberEmail}/memberDel")
  public String memberDel(@ModelAttribute OutForm outForm ){
    log.info("memberDel 호출됨!");
    return "memberJoin/memberDelpage";
  }

  @PostMapping("/{memberEmail}/memberDel")
  public String memberDel(
      @Valid @ModelAttribute OutForm outForm,
      BindingResult bindingResult,
      HttpSession session){

    log.info("memberDel 호출됨");
    //1)유효성체크
    if(bindingResult.hasErrors()){
      log.info("bindingResult={}",bindingResult);
      return "/memberJoin/memberDelpage";
    }
    //2)동의 체크여부
    if(!outForm.getAgree()){
      bindingResult.rejectValue("agree",null, "탈퇴 안내를 확인하고 동의해 주세요.");
      return "/memberJoin/memberDelpage";
    }

    //3) 비밀번호가 일치하는지 체크
    if(!memberShipSVC.isMember(outForm.getMemberEmail(), outForm.getMemberPasswd())){
      bindingResult.rejectValue("MemberPasswd","memberShip.MemberPasswdChk");
      log.info("bindingResult={}", bindingResult);
      return "/memberJoin/memberDelpage";
    }

    //4) 탈퇴로직 수행
    //4-1) 회원정보 삭제
    memberShipSVC.deleteMember(outForm.getMemberEmail());
    //4-2) 센션정보 제거
    if(session != null){
      session.invalidate();
    }
    return "redirect:/members/outCompleted";
  }

  @GetMapping("/outCompleted")
  public String outCompleted(){

    return "memberShip/outCompleted"; //탈퇴수행 완료 view
  }

  //마이페이지(찜)
  @GetMapping("/myinfo")
  public String myinfo(){
    log.info("myinfo() 호출됨");
    return "mypage";
  }

  //커피숍 수정
  @GetMapping("/shopModify")
  public String shopModify(){
    log.info("shopModify() 호출됨");
    return "shopModify";
  }

  //커피숍 삭제
  @GetMapping("/shopDel")
  public String shopDel(){
    log.info("shopDel() 호출됨");
    return "shopDel";
  }

  //리뷰 목록
  @GetMapping("/review")
  public String review(){
    log.info("review() 호출됨");
    return "review";
  }

  //커피숍 조회

  //


  //아이디 찾기
  @GetMapping("/findEmail")
  public String findEmail(){

    return "memberShip/findEmail";
  }
}
