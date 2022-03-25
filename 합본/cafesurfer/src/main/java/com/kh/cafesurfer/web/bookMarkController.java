package com.kh.cafesurfer.web;


import com.kh.cafesurfer.domain.bookMark.BookMark;
import com.kh.cafesurfer.domain.bookMark.svc.BookMarkSVC;
import com.kh.cafesurfer.web.form.bookMark.BookMarkJoinForm;
import com.kh.cafesurfer.web.form.bookMark.BookMarkListForm;
import com.kh.cafesurfer.web.form.login.LoginMemberShip;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/bookMark")
public class bookMarkController {

    private final BookMarkSVC bookMarkSVC;
//등록
@GetMapping("/join")
public String joinForm(Model model,
                      HttpSession session){
  LoginMemberShip loginMemberShip = (LoginMemberShip)session.getAttribute(SessionConst.LOGIN_MEMBER);

  BookMarkJoinForm bookMarkJoinForm = new BookMarkJoinForm();

  bookMarkJoinForm.setMemberId(loginMemberShip.getMemberId());

  model.addAttribute("bookMarkJoinForm", bookMarkJoinForm);

  return "bookMark/joinForm";
}

  //작성처리
  @PostMapping("/join")
  public String add(
//                    @Valid
      @ModelAttribute BookMarkJoinForm bookMarkJoinForm,  // @Valid : 유효성체크
      BindingResult bindingResult,  //폼객체에 바인딩될때 오류내용이 저장되는 객체
      HttpSession session,
      RedirectAttributes redirectAttributes) throws IOException {
    log.info("bookMarkJoinForm={}",bookMarkJoinForm);

//     유효성체크 로직
    if (bindingResult.hasErrors()){
      log.info("join/bindingResult={}", bindingResult);
      return "join/joinForm";
    }
    BookMark bookMark = new BookMark();
    BeanUtils.copyProperties(bookMarkJoinForm,bookMark);
    //세션 가져오기
    LoginMemberShip LoginMemberShip = (LoginMemberShip)session.getAttribute(SessionConst.LOGIN_MEMBER);
    //세션 정보가 없으면 로그인페이지로 이동
    if(LoginMemberShip == null){
      return "redirect:/login";
    }
    //세션에서 이메일, 별칭가져오기
    bookMark.setMemberId(LoginMemberShip.getMemberId());


    return "redirect:/bookMark/{memberId}";
  }




  //삭제
//   @DeleteMapping("")
  @GetMapping("/{memberId}/del")
  public String del(@PathVariable Long memberId,
                    @PathVariable Long shopId){

    bookMarkSVC.deleteBookMark(memberId,shopId);

    return "redirect:/bookMark";
  }


    //조회
    // 4) 조회(전체)
    @GetMapping("/{memberId}")
    public String bookMarkList(
        @PathVariable("memberId") Long memberId,
        Model model){

      BookMark bookMark = bookMarkSVC.selectBookMarkByMemberId(memberId);
      BookMarkListForm bookMarkListForm = new BookMarkListForm();

      BeanUtils.copyProperties(bookMark, bookMarkListForm);
      model.addAttribute("bookMarkForm", bookMarkListForm);

      return "bookMark/bookMarkList";
    }

}
