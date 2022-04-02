package com.kh.cafesurfer.web;


import com.kh.cafesurfer.domain.bookMark.BookMark;
import com.kh.cafesurfer.domain.bookMark.svc.BookMarkSVC;
import com.kh.cafesurfer.web.form.bookMark.BookMarkJoinForm;
import com.kh.cafesurfer.web.form.bookMark.BookMarkListForm;
import com.kh.cafesurfer.web.form.bookMark.BookMarkOutForm;
import com.kh.cafesurfer.web.form.login.LoginMemberShip;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/bookMark")
public class bookMarkController {

    private final BookMarkSVC bookMarkSVC;


//  작성처리

  @PostMapping("/join/{shopId}")
  public String add(@ModelAttribute BookMarkJoinForm bookMarkJoinForm,
//      HttpServletRequest request,
      HttpSession session,
                    @PathVariable Long shopId
//      @RequestParam(value = "shopId", required=false) Long shopId
       ) throws IOException {


    BookMark bookMark = new BookMark();
    BeanUtils.copyProperties(bookMarkJoinForm,bookMark);

    //세션 가져오기
    LoginMemberShip LoginMemberShip = (LoginMemberShip)session.getAttribute(SessionConst.LOGIN_MEMBER);

    //세션 정보가 없으면 로그인페이지로 이동
    if(LoginMemberShip == null){
      return "redirect:/login";
    }
    //세션에서 아이디가져오기
//    bookMark.setMemberId(LoginMemberShip.getMemberId());
    Long memberId = LoginMemberShip.getMemberId();


//    log.info("shopId={}",byShopId);
    log.info("MemberId={}",memberId);
    log.info("shopId={}",shopId);

    bookMarkSVC.insertBookMark(memberId, shopId);
//    bookMarkSVC.insertBookMark(bookMarkJoinForm.getMemberId(), bookMarkJoinForm.getShopId());

    return "/shop/detail/{shopId}";
  }

  //상품 등록
//  @ResponseBody
//  @PostMapping("/join")
//  public ApiResult<BookMark> save(@RequestBody BookMark bookMark,
//                                  HttpSession session) {
//
//    //세션 가져오기
//    LoginMemberShip LoginMemberShip = (LoginMemberShip)session.getAttribute(SessionConst.LOGIN_MEMBER);
//
//    //세션 정보가 없으면 로그인페이지로 이동
//    if(LoginMemberShip == null){
//      return "redirect:/login";
//    }
//    //세션에서 아이디가져오기
//    bookMark.setMemberId(LoginMemberShip.getMemberId());
//
//    BookMark savedItem = bookMarkSVC.insertBookMark(bookMark.getMemberId(), bookMark.getShopId());
//
//
//    ApiResult<BookMark> result = new ApiResult<>("00", "success", savedItem);
//
//    return result;

//  }


  //삭제
  @DeleteMapping("/{memberId}/del")
  public String del(@ModelAttribute BookMarkOutForm bookMarkOutForm,
                    HttpSession session
                   ) throws IOException {
    log.info("bookMarkOutForm={}",bookMarkOutForm);

    BookMark bookMark = new BookMark();
    BeanUtils.copyProperties(bookMarkOutForm,bookMark);

    //세션 가져오기
    LoginMemberShip LoginMemberShip = (LoginMemberShip)session.getAttribute(SessionConst.LOGIN_MEMBER);

    //세션 정보가 없으면 로그인페이지로 이동
    if(LoginMemberShip == null){
      return "redirect:/login";
    }
    //세션에서 아이디가져오기
    bookMark.setMemberId(LoginMemberShip.getMemberId());

    bookMarkSVC.deleteBookMark(bookMarkOutForm.getMemberId(), bookMarkOutForm.getShopId());

    return "redirect:/bookMark/{memberId}";
  }


    //조회
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

//  private static String converMapToURLParameters(Map<String,String[]> paramMap)
//  throws UnsupportedEncodingException{
//    String paarmeterString = "";
//    for (Map.Entry<String, String[]> mapEntry : paramMap.entrySet()) {
//      for (String value : mapEntry.getValue()) {
//        paarmeterString += (paarmeterString.isEmpty() ? "?" : "&") + mapEntry.getKey() + "=" +
//            URLEncoder.encode(value, "UTF-8");
//      }
//    }
//    return paarmeterString;
//  }
}
