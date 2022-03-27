package com.kh.cafesurfer.web;

import com.kh.cafesurfer.domain.memberShip.MemberShip;
import com.kh.cafesurfer.domain.memberShip.svc.MemberShipSVC;
import com.kh.cafesurfer.web.form.login.LoginForm;
import com.kh.cafesurfer.web.form.login.LoginMemberShip;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

  private final MemberShipSVC memberShipSVC;
  
  //로그인
  @GetMapping("/login")
  public String loginForm(Model model){
    model.addAttribute("loginForm", new LoginForm());
    return "login/loginForm";
  }
  //로그인처리
  @PostMapping("/login")
  public String login(
      @Valid // 폼데이터를 폼객체에 바인딩 할때 유효성체크를 어노테이션으로 할수 있다.
      @ModelAttribute // 폼객체를 모델객체에 자동 추가해준다. view에서 모델객체 이름으로 접근
          LoginForm loginForm,
      BindingResult bindingResult, //폼데이터를 폼객체에 바인딩할때 유효성체크후 오류정보 저장
      HttpServletRequest request,
      @RequestParam(required = false,defaultValue = "/") String redirectUrl
  ){

    //필드 유효성 체크
    if(bindingResult.hasErrors()){
      log.info("loginError={}", bindingResult);
      return "login/loginForm";
    }

    //오브젝트 체크 : 회원유무
    if(!memberShipSVC.existMember(loginForm.getMemberEmail())) {
      bindingResult.reject("loginFail.email");

      return "login/loginForm";
    }

    //오브젝트 체크 :로그인
    MemberShip memberShip = memberShipSVC.login(loginForm.getMemberEmail(), loginForm.getMemberPasswd());
    if(memberShip == null){
      bindingResult.reject("loginFail.passwd");
      return "login/loginForm";
    }

    //회원 세션 정보
    LoginMemberShip loginMemberShip = new LoginMemberShip(memberShip.getMemberId(),  memberShip.getMemberEmail(), memberShip.getMemberName());




    //인증성공
    //세션이 있으면 세션 반환, 없으면 새로이 생성
    HttpSession session = request.getSession(true);
    session.setAttribute(SessionConst.LOGIN_MEMBER, loginMemberShip);

    log.info("LOGIN_MEMBER={}",SessionConst.LOGIN_MEMBER);

    return "redirect:"+redirectUrl;  //url재요청
  }
  //로그아웃
  @GetMapping("/logout")
  public String logout(HttpServletRequest request){

    //세션이 있으면 세션을 반환하고 없으면 null반환
    HttpSession session = request.getSession(false);
    if(session != null){
      session.invalidate();  //세션 제거
    }

    return "redirect:/";
  }
  
}
