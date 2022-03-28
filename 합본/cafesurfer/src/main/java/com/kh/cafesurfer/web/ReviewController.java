package com.kh.cafesurfer.web;

import com.kh.cafesurfer.domain.review.Review;
import com.kh.cafesurfer.domain.review.svc.ReviewSVC;
import com.kh.cafesurfer.web.form.review.AddForm;
import com.kh.cafesurfer.web.form.review.DetailForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
  
  private final ReviewSVC reviewSVC;
  
  // 1-1) 리뷰작성 화면
  @GetMapping("/add")
  public String addForm(@ModelAttribute AddForm addForm) {
    
    return "review/addForm";
  }
  
  // 1-2) 리뷰작성 처리
  @PostMapping("/add")
  public String add(@Valid
                    @ModelAttribute AddForm addForm,
                    RedirectAttributes redirectAttributes) {
    
    Review review = new Review();
    
    review.setReviewContent(review.getReviewContent());
    review.setMemberId(review.getMemberId());
    review.setShopId(review.getShopId());
  
    Review addReview = reviewSVC.addReview(review);
    
    redirectAttributes.addAttribute("reviewId", addReview);
    
    return "review/addSuccess";
  }
  
  // 2) 전체조회
  @GetMapping
  public String list(Model model) {
  
    List<Review> list = reviewSVC.findAll();
    model.addAttribute("list", list);
    
    return "review/reviewList";
  }
  
  
  // 건별조회(리뷰Id)
  @GetMapping("/listRid/{reviewId}")
  public String reviewId(
      @PathVariable("reviewId") Long reviewId,
      Model model) {
    
    Review review = reviewSVC.findByReviewId(reviewId);
  
    DetailForm detailForm = new DetailForm();
    
    detailForm.setReviewId(review.getReviewId());
    detailForm.setReviewContent(review.getReviewContent());
    detailForm.setReviewCdate(review.getReviewCdate());
    detailForm.setReportCnt(review.getReportCnt());
    detailForm.setMemberId(review.getMemberId());
    detailForm.setShopId(review.getShopId());
 
    model.addAttribute("detailForm", detailForm);
    
    return "review/listRid";
  }
  
  // 회원별 조회(회원Id)
  @GetMapping("/listMid/{memberId}")
  public String memberId(@PathVariable("memberId") Long memberId,
                         Model model) {
    
    List<Review> list = reviewSVC.findByMemberId(memberId);
    model.addAttribute("list", list);
  
    return "review/listMid";
  }
  
  // 커피숍별 조회(커피숍Id)
  @GetMapping("/listSid/{shopId}")
  public String shopId(@PathVariable("shopId") Long shopId,
                         Model model) {
  
    List<Review> list = reviewSVC.findByShopId(shopId);
    model.addAttribute("list", list);
  
    return "review/listSid";
  }
  
  // 삭제
  
  
}
