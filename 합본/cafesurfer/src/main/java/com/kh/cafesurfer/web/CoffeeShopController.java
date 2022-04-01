package com.kh.cafesurfer.web;

import com.kh.cafesurfer.domain.coffeeShop.CoffeeShop;
import com.kh.cafesurfer.domain.coffeeShop.svc.CoffeeShopSVC;
import com.kh.cafesurfer.web.form.coffeShop.*;
import com.kh.cafesurfer.web.form.memberShip.MemberGender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
public class CoffeeShopController {
  private final CoffeeShopSVC coffeeShopSVC;

  // 주차가능여부
//  @ModelAttribute("parking")
//  public List<String> parking() {
//    List<String> parking = new ArrayList<>();
//    parking.add("아니요");   // 불가능
//    parking.add("예");   // 가능
//
//    return parking;
//  }
  @ModelAttribute("ynParking")
  public ynParking[] ynParking(){

    return ynParking.values();
  }

  // 24시간운영
//  @ModelAttribute("allDay")
//  public List<String> allDay() {
//    List<String> allDay = new ArrayList<>();
//    allDay.add("아니요");   // 불가능
//    allDay.add("예");   // 가능
//
//    return allDay;
//  }


  @ModelAttribute("ynAllDay")
  public ynAllDay[] ynAllDay(){

    return ynAllDay.values();
  }

  //문자열로 Enum객체에서 상수요소 찾아오기
  private MemberGender ynAllDay(String allDay) {
    MemberGender finded = null;
    for(MemberGender g : MemberGender.values()){
      if(g.getDescription().equals(allDay)){
        finded = MemberGender.valueOf(g.name());
        break;
      }
    }
    return finded;
  }
  //문자열로 Enum객체에서 상수요소 찾아오기
  private MemberGender ynParking(String parking) {
    MemberGender finded = null;
    for(MemberGender g : MemberGender.values()){
      if(g.getDescription().equals(parking)){
        finded = MemberGender.valueOf(g.name());
        break;
      }
    }
    return finded;
  }

  // 1-1) 등록화면
  @GetMapping("/{memberEmail}/shopAdd")
  public String joinForm(@ModelAttribute CoffeeShopJoinForm coffeeShopJoinForm) {
    log.info("shopAdd() 호출됨");
    return "/shopAdd";
  }

  // 1-2) 등록처리
  @PostMapping("/{memberEmail}/shopAdd")
  public String join(
      @Valid
      @ModelAttribute CoffeeShopJoinForm coffeeShopJoinForm,
      RedirectAttributes redirectAttributes) {

      CoffeeShop coffeeShop = new CoffeeShop();


      coffeeShop.setShopName(coffeeShopJoinForm.getShopName());
      coffeeShop.setShopAddress(coffeeShopJoinForm.getShopAddress());
      coffeeShop.setShopTel(coffeeShopJoinForm.getShopTel());
      coffeeShop.setYnParking(coffeeShopJoinForm.getYnParking());
      coffeeShop.setYnAllDay(coffeeShopJoinForm.getYnAllDay());

      CoffeeShop joinedCoffeeShop = coffeeShopSVC.addCoffeeShop(coffeeShop);

      redirectAttributes.addAttribute("shopId", joinedCoffeeShop.getShopId());
//      BeanUtils.copyProperties(coffeeShopJoinForm, coffeeShop);

      return "coffeeShop/joinSuccess";
  }


  // 2-1) 수정화면
  @GetMapping("/{shopId}/shopModify")
  public String modifyForm(@PathVariable("shopId") Long shopId,
                           Model model) {

    CoffeeShop coffeeShop = coffeeShopSVC.findByShopId(shopId);

    CoffeeShopModifyForm coffeeShopModifyForm = new CoffeeShopModifyForm();
    coffeeShop.setShopName(coffeeShopModifyForm.getShopName());
    coffeeShop.setShopAddress(coffeeShopModifyForm.getShopAddress());
    coffeeShop.setShopTel(coffeeShopModifyForm.getShopTel());
    coffeeShop.setYnParking(coffeeShopModifyForm.getYnParking());
    coffeeShop.setYnAllDay(coffeeShopModifyForm.getYnAllDay());

    model.addAttribute("coffeeShopModifyForm", coffeeShopModifyForm);

    return "shopModify";
  }

  // 2-2) 수정처리
  @PatchMapping("/shopModify")
  public String modify(@PathVariable Long shopId,
                       @Valid @ModelAttribute CoffeeShopModifyForm coffeeShopModifyForm,
                       RedirectAttributes redirectAttributes) {
    CoffeeShop coffeeShop = new CoffeeShop();

    BeanUtils.copyProperties(coffeeShopModifyForm, coffeeShop);
    coffeeShopSVC.modifyCoffeeShop(shopId, coffeeShop);

    redirectAttributes.addAttribute("shopId", shopId);

    return "redirect:/shop/{shopId}";
  }






  // 3) 삭제처리
  @DeleteMapping("/{shopId}/out")
  public String out(@PathVariable Long shopId){
    log.info("outForm 호출됨!");

    coffeeShopSVC.removeCoffeeShop(shopId);
    return "redirect:/shop";
  }



  // 4) 조회(전체)
  @GetMapping
  public String list(Model model){

    List<CoffeeShop> list = coffeeShopSVC.findAll();
    model.addAttribute("list", list);

//    return "coffeeShop/coffeeShopList";
    return "admin/member/coffeeshops";
  }


  // 5-A) 조회(개별)_조회수 증가 반영 [미구현]
  // 5-B) 조회(개별)_조회수 증가 미반영
  @GetMapping("/detail/{shopId}")
  public String member(
      HttpServletRequest request,
      @PathVariable("shopId") Long shopId,
      Model model){

    CoffeeShop coffeeShop = coffeeShopSVC.findByShopId(shopId);
//    log.info("coffeeShop={}", coffeeShop);

    CoffeeShopDetailForm coffeeShopDetailForm = new CoffeeShopDetailForm();

    coffeeShopDetailForm.setShopId(coffeeShop.getShopId());
    coffeeShopDetailForm.setShopName(coffeeShop.getShopName());
    coffeeShopDetailForm.setShopAddress(coffeeShop.getShopAddress());
    coffeeShopDetailForm.setShopTel(coffeeShop.getShopTel());
    coffeeShopDetailForm.setViewCnt(coffeeShop.getViewCnt());
    coffeeShopDetailForm.setShopBookmarkCnt(coffeeShop.getShopBookmarkCnt());
    coffeeShopDetailForm.setShopReviewCnt(coffeeShop.getShopReviewCnt());
    coffeeShopDetailForm.setYnParking(coffeeShop.getYnParking());
    coffeeShopDetailForm.setYnAllDay(coffeeShop.getYnAllDay());
    coffeeShopDetailForm.setShopCdate(coffeeShop.getShopCdate());

    log.info("coffeeShopDetailForm={}", coffeeShopDetailForm);
    model.addAttribute("coffeeShopDetailForm", coffeeShopDetailForm);

    String view = null;
    HttpSession session = request.getSession(false);
    view = (session == null) ? "contentBeforeLogin" : "contentAfterLogin" ;

    return view;
  }


  // 조회수 증가(카운트)


  // 북마크 증가(카운트)


  // 리뷰수 증가(카운트)

}
