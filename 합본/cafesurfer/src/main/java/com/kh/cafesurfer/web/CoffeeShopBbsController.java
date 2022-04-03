package com.kh.cafesurfer.web;

import com.kh.cafesurfer.domain.CoffeeShopBbs.dao.CoffeeShopBbs;
import com.kh.cafesurfer.domain.CoffeeShopBbs.dao.CoffeeShopBbsFirterCondition;
import com.kh.cafesurfer.domain.CoffeeShopBbs.svc.CoffeeShopBbsSVC;
import com.kh.cafesurfer.domain.common.file.UploadFile;
import com.kh.cafesurfer.domain.common.file.svc.UploadFileSVC;
import com.kh.cafesurfer.domain.common.paging.FindCriteria;
import com.kh.cafesurfer.web.form.coffeShop.CoffeeShopDetailForm;
import com.kh.cafesurfer.web.form.coffeShop.CoffeeShopJoinForm;
import com.kh.cafesurfer.web.form.coffeShop.CoffeeShopListForm;
import com.kh.cafesurfer.web.form.coffeShop.CoffeeShopModifyForm;
import com.kh.cafesurfer.web.form.login.LoginMemberShip;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/coffeeShopBbs")
@RequiredArgsConstructor

public class CoffeeShopBbsController {

  private final CoffeeShopBbsSVC coffeeShopBbsSVC;
  private final UploadFileSVC uploadFileSVC;

  @Autowired
  @Qualifier("fc10") //동일한 타입의 객체가 여러개있을때 빈이름을 명시적으로 지정해서 주입받을때
  private FindCriteria fc;

  @GetMapping("/add")
  public String addForm(Model model,
                        @RequestParam(required = false) Optional<Long> shopId,
                        HttpSession session) {


    LoginMemberShip loginMember = (LoginMemberShip) session.getAttribute(SessionConst.LOGIN_MEMBER);

    CoffeeShopJoinForm coffeeShopJoinForm = new CoffeeShopJoinForm();
    coffeeShopJoinForm.setMemberEmail(loginMember.getMemberEmail());
    coffeeShopJoinForm.setMemberName(loginMember.getMemberName());
    model.addAttribute("coffeeShopJoinForm", coffeeShopJoinForm);


    return "coffeeShopBbs/addForm";
  }

  //작성처리
  @PostMapping("/add")
  public String add(
      @ModelAttribute CoffeeShopJoinForm coffeeShopJoinForm,
      @RequestParam(required = false) Optional<Long> shopId,
      BindingResult bindingResult,  //폼객체에 바인딩될때 오류내용이 저장되는 객체
      HttpSession session,
      RedirectAttributes redirectAttributes) throws IOException {
    log.info("coffeeShopJoinForm={}", coffeeShopJoinForm);

//     유효성체크 로직
    if (bindingResult.hasErrors()) {
      log.info("add/bindingResult={}", bindingResult);
      return "coffeeShopBbs/addForm";
    }

    CoffeeShopBbs coffeeShopBbs = new CoffeeShopBbs();
    BeanUtils.copyProperties(coffeeShopJoinForm, coffeeShopBbs);
    //세션 가져오기
    LoginMemberShip loginMember = (LoginMemberShip) session.getAttribute(SessionConst.LOGIN_MEMBER);
    //세션 정보가 없으면 로그인페이지로 이동
    if (loginMember == null) {
      return "redirect:/login";
    }
    //세션에서 이메일, 별칭가져오기
    coffeeShopBbs.setMemberEmail(loginMember.getMemberEmail());
    coffeeShopBbs.setMemberName(loginMember.getMemberName());

    Long originId = 0l;
    //파일첨부유무
    if (coffeeShopJoinForm.getFiles().size() == 0) {
      originId = coffeeShopBbsSVC.saveOrigin(coffeeShopBbs);
    } else {
      originId = coffeeShopBbsSVC.saveOrigin(coffeeShopBbs, coffeeShopJoinForm.getFiles());
    }
    redirectAttributes.addAttribute("id", originId);


//     <= 서버응답 302 get http://서버:port/bbs/10
//     => 클라이언트요청 get http://서버:port/bbs/10
    return "redirect:/coffeeShopBbs/{shopId}";
  }


  @GetMapping({"/list",
      "/list/{reqPage}",
      "/list/{reqPage}//",
      "/list/{reqPage}/{searchType}/{keyword}"})
  public String listAndReqPage(
      @PathVariable(required = false) Optional<Integer> reqPage,
      @PathVariable(required = false) Optional<String> searchType,
      @PathVariable(required = false) Optional<String> keyword,
      @RequestParam(required = false) Optional<Long> shopId,
      Model model) {
    log.info("/list 요청됨{},{},{},{}",reqPage,searchType,keyword,shopId);

    //FindCriteria 값 설정
    fc.getRc().setReqPage(reqPage.orElse(1)); //요청페이지, 요청없으면 1
    fc.setSearchType(searchType.orElse(""));  //검색유형
    fc.setKeyword(keyword.orElse(""));        //검색어

    List<CoffeeShopBbs> list = null;

    //게시물 목록 전체
      //검색어 있음
      if(searchType.isPresent() && keyword.isPresent()){
        CoffeeShopBbsFirterCondition coffeeShopBbsFirterCondition = new CoffeeShopBbsFirterCondition(
            shopId.get(),fc.getRc().getStartRec(), fc.getRc().getEndRec(),
            searchType.get(),
            keyword.get());
        fc.setTotalRec(coffeeShopBbsSVC.totalCount(coffeeShopBbsFirterCondition));
        fc.setSearchType(searchType.get());
        fc.setKeyword(keyword.get());
        list = coffeeShopBbsSVC.findAll(coffeeShopBbsFirterCondition);

        //검색어 없음
      }else {
        //총레코드수
        fc.setTotalRec(coffeeShopBbsSVC.totalCount());
        list = coffeeShopBbsSVC.findAll(fc.getRc().getStartRec(), fc.getRc().getEndRec());
      }

    List<CoffeeShopListForm> partOfList = new ArrayList<>();
    for (CoffeeShopBbs coffeeShopBbs : list) {
      CoffeeShopListForm coffeeShopListForm = new CoffeeShopListForm();
      BeanUtils.copyProperties(coffeeShopBbs, coffeeShopListForm);
      partOfList.add(coffeeShopListForm);
    }

    model.addAttribute("list", partOfList);
    model.addAttribute("fc",fc);


    return "coffeeShopBbs/list";
  }


  //조회
//  @ResponseBody //제이슨
  @GetMapping("/{shopId}")
  public String detail(@PathVariable Long shopId,
//                       @RequestParam(required = false) Optional<Long> shopId,
                       Model model) {

//    String cate = getShopId(shopId);

    CoffeeShopBbs detailBbs = coffeeShopBbsSVC.findByBbsId(shopId);

    CoffeeShopDetailForm coffeeShopDetailForm = new CoffeeShopDetailForm();

    BeanUtils.copyProperties(detailBbs, coffeeShopDetailForm);
    model.addAttribute("coffeeShopDetailForm", coffeeShopDetailForm);
//    model.addAttribute("category", cate);

    //첨부조회
    List<UploadFile> attachFiles = uploadFileSVC.getFilesByCodeWithRid(detailBbs.getShopId());
    if (attachFiles.size() > 0) {
      log.info("attachFiles={}", attachFiles);
      model.addAttribute("attachFiles", attachFiles);
    }

//      return detailForm;
//    return "coffeeShopBbs/detailForm";
    return "/contentAfterLogin";
  }

  //삭제
  @GetMapping("/{id}/del")
  public String del(@PathVariable Long shopId) {

    coffeeShopBbsSVC.deleteBbsId(shopId);

    return "redirect:/coffeeShopBbs/list";
  }

  //수정양식
  @GetMapping("/{shopId}/edit")
  public String editForm(
      @PathVariable Long shopId,
      Model model) {


    CoffeeShopBbs coffeeShopBbs  = coffeeShopBbsSVC.findByBbsId(shopId);

    CoffeeShopModifyForm coffeeShopModifyForm = new CoffeeShopModifyForm();
    BeanUtils.copyProperties(coffeeShopBbs, coffeeShopModifyForm);
    model.addAttribute("editForm", coffeeShopModifyForm);

    log.info("editForm={}", coffeeShopModifyForm);
    //첨부조회
    List<UploadFile> attachFiles = uploadFileSVC.getFilesByCodeWithRid(coffeeShopBbs.getShopId());
    if (attachFiles.size() > 0) {
      log.info("attachFiles={}", attachFiles);
      model.addAttribute("attachFiles", attachFiles);

    }

    return "coffeeShopBbs/editForm";
  }

  //수정처리
  @PostMapping("/{shopId}/edit")
  public String edit(
      @PathVariable Long shopId,
      @Valid @ModelAttribute CoffeeShopModifyForm coffeeShopModifyForm,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes
  ) {
    if (bindingResult.hasErrors()) {
      return "coffeeShopBbs/editForm";
    }

    CoffeeShopBbs coffeeShopBbs = new CoffeeShopBbs();
    BeanUtils.copyProperties(coffeeShopModifyForm, coffeeShopBbs);
    coffeeShopBbsSVC.updateByBbsId(shopId, coffeeShopBbs);

    if (coffeeShopModifyForm.getFiles().size() == 0) {
      coffeeShopBbsSVC.updateByBbsId(shopId, coffeeShopBbs);
    } else {
      coffeeShopBbsSVC.updateByBbsId(shopId, coffeeShopBbs, coffeeShopModifyForm.getFiles());
    }
    redirectAttributes.addAttribute("shopId", shopId);
    return "redirect:/coffeeShopBbs/{shopId}";
  }



}
