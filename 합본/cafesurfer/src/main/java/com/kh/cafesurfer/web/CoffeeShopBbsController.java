package com.kh.cafesurfer.web;


import com.kh.cafesurfer.domain.CoffeeShopBbs.dao.CoffeeShopBbs;
import com.kh.cafesurfer.domain.CoffeeShopBbs.dao.CoffeeShopBbsFirterCondition;
import com.kh.cafesurfer.domain.CoffeeShopBbs.svc.CoffeeShopBbsSVC;
import com.kh.cafesurfer.domain.common.code.CodeDAO;
import com.kh.cafesurfer.domain.common.file.UploadFile;
import com.kh.cafesurfer.domain.common.file.svc.UploadFileSVC;
import com.kh.cafesurfer.domain.common.paging.FindCriteria;
import com.kh.cafesurfer.web.form.coffeShop.*;
import com.kh.cafesurfer.web.form.login.LoginMemberShip;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
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
  private final CodeDAO codeDAO;
  private final UploadFileSVC uploadFileSVC;


  @ModelAttribute("ynParking")
  public ynParking[] ynParking(){

    return ynParking.values();
  }

  @ModelAttribute("ynAllDay")
  public ynAllDay[] ynAllDay(){

    return ynAllDay.values();
  }

  @Autowired
  @Qualifier("fc5") //동일한 타입의 객체가 여러개있을때 빈이름을 명시적으로 지정해서 주입받을때
  private FindCriteria fc;

  //게시판 코드,디코드 가져오기
  @ModelAttribute("classifier")
  public List<Code> classifier() {
    return codeDAO.code("B01");
  }


//  @ModelAttribute("bbsTitle")
//  public Map<String, String> bbsTitle() {
//    List<Code> codes = codeDAO.code("B01");
//    Map<String, String> btitle = new HashMap<>();
//    for (Code code : codes) {
//      btitle.put(code.getCode(), code.getDecode());
//    }
//    return btitle;
//  }



  @GetMapping("/add")
  public String addForm(Model model,
                        HttpSession session) {

    LoginMemberShip loginMember = (LoginMemberShip) session.getAttribute(SessionConst.LOGIN_MEMBER);


    CoffeeShopJoinForm coffeeShopJoinForm = new CoffeeShopJoinForm();
    coffeeShopJoinForm.setMemberEmail(loginMember.getMemberEmail());
    coffeeShopJoinForm.setMemberName(loginMember.getMemberName());
    model.addAttribute("coffeeShopJoinForm", coffeeShopJoinForm);

    return "shopAdd";
  }

  //작성처리
  @PostMapping("/add")
  public String add(
//                    @Valid
      @ModelAttribute CoffeeShopJoinForm coffeeShopJoinForm,  // @Valid : 유효성체크
      BindingResult bindingResult,  //폼객체에 바인딩될때 오류내용이 저장되는 객체
      HttpSession session,
      RedirectAttributes redirectAttributes) throws IOException {
    log.info("coffeeShopJoinForm={}", coffeeShopJoinForm);

//     유효성체크 로직
    if (bindingResult.hasErrors()) {
      log.info("add/bindingResult={}", bindingResult);
      return "shopAdd";
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
    if (coffeeShopJoinForm.getFiles1().size() == 0 || coffeeShopJoinForm.getFiles2().size() == 0 || coffeeShopJoinForm.getFiles3().size() == 0 || coffeeShopJoinForm.getFiles4().size() == 0) {
      originId = coffeeShopBbsSVC.saveOrigin(coffeeShopBbs);
    } else {
      originId = coffeeShopBbsSVC.saveOrigin(coffeeShopBbs, coffeeShopJoinForm.getFiles1(),coffeeShopJoinForm.getFiles2(),coffeeShopJoinForm.getFiles3(),coffeeShopJoinForm.getFiles4());
    }
    redirectAttributes.addAttribute("originId", originId);

//     <= 서버응답 302 get http://서버:port/bbs/10
//     => 클라이언트요청 get http://서버:port/bbs/10
    return "redirect:/coffeeShopBbs/{originId}";
  }

  @GetMapping({"/list",
      "/list/{reqPage}",
      "/list/{reqPage}//",
      "/list/{reqPage}/{searchType}/{keyword}"})
  public String listAndReqPage(
      @PathVariable(required = false) Optional<Integer> reqPage,
      @PathVariable(required = false) Optional<String> searchType,
      @PathVariable(required = false) Optional<String> keyword,
      HttpServletRequest request,
      Model model) {
    log.info("/list 요청됨{},{},{}",reqPage,searchType,keyword);

    //FindCriteria 값 설정
    fc.getRc().setReqPage(reqPage.orElse(1)); //요청페이지, 요청없으면 1
    fc.setSearchType(searchType.orElse(""));  //검색유형
    fc.setKeyword(keyword.orElse(""));        //검색어

    List<CoffeeShopBbs> list = null;

      //검색어 있음
      if(searchType.isPresent() && keyword.isPresent()){
        CoffeeShopBbsFirterCondition coffeeShopBbsFirterCondition = new CoffeeShopBbsFirterCondition(
            fc.getRc().getStartRec(), fc.getRc().getEndRec(),
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

    HttpSession session = request.getSession(false);
    if (session != null) {
      LoginMemberShip loginMemberShip = (LoginMemberShip) session.getAttribute(SessionConst.LOGIN_MEMBER);
      if(loginMemberShip.getMemberEmail().equals("admin@kh.com")) {
        return "coffeeShopBbs/list";
      }
      return "mainAfterLogin";
    } else {
      return "mainBeforeLogin";
    }


//    return "coffeeShopBbs/list";
//    return "mainAfterLogin";
//    return "mainBeforeLogin";
  }


  //조회
//  @ResponseBody //제이슨
  @GetMapping("/{shopId}")
  public String detail(@PathVariable Long shopId, Model model) {


    CoffeeShopBbs coffeeShopBbs = coffeeShopBbsSVC.findByBbsId(shopId);

    CoffeeShopDetailForm coffeeShopDetailForm = new CoffeeShopDetailForm();


    BeanUtils.copyProperties(coffeeShopBbs, coffeeShopDetailForm);
    model.addAttribute("coffeeShopDetailForm", coffeeShopDetailForm);


    //첨부조회
    List<UploadFile> attachFiles1 = uploadFileSVC.getFilesByCodeWithRid(coffeeShopBbs.getBcategoryB0101(), coffeeShopBbs.getShopId());
    List<UploadFile> attachFiles2 = uploadFileSVC.getFilesByCodeWithRid(coffeeShopBbs.getBcategoryB0102(), coffeeShopBbs.getShopId());
    List<UploadFile> attachFiles3 = uploadFileSVC.getFilesByCodeWithRid(coffeeShopBbs.getBcategoryB0103(), coffeeShopBbs.getShopId());
    List<UploadFile> attachFiles4 = uploadFileSVC.getFilesByCodeWithRid(coffeeShopBbs.getBcategoryB0104(), coffeeShopBbs.getShopId());

//    if (attachFiles1.size() > 0 || attachFiles2.size() > 0 || attachFiles3.size() > 0 || attachFiles4.size() > 0) {
//      log.info("attachFiles1={}", attachFiles1);
//      model.addAttribute("attachFiles1", attachFiles1);
//      log.info("attachFiles2={}", attachFiles2);
//      model.addAttribute("attachFiles2", attachFiles2);
//      log.info("attachFiles3={}", attachFiles3);
//      model.addAttribute("attachFiles3", attachFiles3);
//      log.info("attachFiles4={}", attachFiles4);
//      model.addAttribute("attachFiles4", attachFiles4);
//    }
    if (attachFiles1.size() > 0) {
      log.info("attachFiles1={}", attachFiles1);
      model.addAttribute("attachFiles1", attachFiles1);
    }
    if (attachFiles2.size() > 0) {
      log.info("attachFiles2={}", attachFiles2);
      model.addAttribute("attachFiles2", attachFiles2);
    }
    if (attachFiles3.size() > 0) {
      log.info("attachFiles3={}", attachFiles3);
      model.addAttribute("attachFiles3", attachFiles3);
    }
    if (attachFiles4.size() > 0) {
      log.info("attachFiles4={}", attachFiles4);
      model.addAttribute("attachFiles4", attachFiles4);
    }



//    return "coffeeShopBbs/detailForm";
    return "contentAfterLogin";
  }

  //삭제
  @GetMapping("/{shopId}/del")
  public String del(@PathVariable Long shopId) {

    coffeeShopBbsSVC.deleteBbsId(shopId);
    return "mainBeforeLogin";
  }

  //수정양식
  @GetMapping("/{shopId}/edit")
  public String editForm(
      @PathVariable Long shopId,
      Model model) {

    CoffeeShopBbs coffeeShopBbs = coffeeShopBbsSVC.findByBbsId(shopId);

    CoffeeShopModifyForm coffeeShopModifyForm = new CoffeeShopModifyForm();
    BeanUtils.copyProperties(coffeeShopBbs, coffeeShopModifyForm);
    model.addAttribute("coffeeShopModifyForm", coffeeShopModifyForm);

    log.info("coffeeShopModifyForm={}", coffeeShopModifyForm);
//    //첨부조회
//    List<UploadFile> attachFiles = uploadFileSVC.getFilesByCodeWithRid(coffeeShopBbs.getBcategory(), coffeeShopBbs.getShopId());
//    if (attachFiles.size() > 0) {
//      log.info("attachFiles={}", attachFiles);
//      model.addAttribute("attachFiles", attachFiles);
//    }
    //첨부조회
    List<UploadFile> attachFiles1 = uploadFileSVC.getFilesByCodeWithRid(coffeeShopBbs.getBcategoryB0101(), coffeeShopBbs.getShopId());
    List<UploadFile> attachFiles2 = uploadFileSVC.getFilesByCodeWithRid(coffeeShopBbs.getBcategoryB0102(), coffeeShopBbs.getShopId());
    List<UploadFile> attachFiles3 = uploadFileSVC.getFilesByCodeWithRid(coffeeShopBbs.getBcategoryB0103(), coffeeShopBbs.getShopId());
    List<UploadFile> attachFiles4 = uploadFileSVC.getFilesByCodeWithRid(coffeeShopBbs.getBcategoryB0104(), coffeeShopBbs.getShopId());

    if (attachFiles1.size() > 0 || attachFiles2.size() > 0 || attachFiles3.size() > 0 || attachFiles4.size() > 0) {
      log.info("attachFiles1={}", attachFiles1);
      model.addAttribute("attachFiles1", attachFiles1);
      log.info("attachFiles2={}", attachFiles2);
      model.addAttribute("attachFiles2", attachFiles2);
      log.info("attachFiles3={}", attachFiles3);
      model.addAttribute("attachFiles3", attachFiles3);
      log.info("attachFiles4={}", attachFiles4);
      model.addAttribute("attachFiles4", attachFiles4);
    }

    return "shopModify";
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
      return "shopModify";
    }
    CoffeeShopBbs coffeeShopBbs = new CoffeeShopBbs();
    BeanUtils.copyProperties(coffeeShopModifyForm, coffeeShopBbs);
    coffeeShopBbsSVC.updateByBbsId(shopId, coffeeShopBbs );

    if (coffeeShopModifyForm.getFiles1().size() == 0 || coffeeShopModifyForm.getFiles2().size() == 0 || coffeeShopModifyForm.getFiles3().size() == 0 || coffeeShopModifyForm.getFiles4().size() == 0) {
      coffeeShopBbsSVC.updateByBbsId(shopId, coffeeShopBbs);
    } else {
      coffeeShopBbsSVC.updateByBbsId( coffeeShopBbs,shopId, coffeeShopModifyForm.getFiles1(), coffeeShopModifyForm.getFiles2(), coffeeShopModifyForm.getFiles3(), coffeeShopModifyForm.getFiles4());
    }
    redirectAttributes.addAttribute("shopId", shopId);
    return "redirect:/coffeeShopBbs/{shopId}";
  }


  @Configuration
  public class WebConfig implements WebMvcConfigurer {


    private String connectPath = "/attach/**";

    private String resourcePath = "file:/attach/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler(connectPath)
          .addResourceLocations(resourcePath);
    }
  }

}
