package com.kh.cafesurfer.web;

import com.kh.cafesurfer.domain.searchBar.SearchBar;
import com.kh.cafesurfer.domain.searchBar.svc.SearchBarSVC;
import com.kh.cafesurfer.web.api.ApiResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchBarRestController {

  private final SearchBarSVC searchBarSVC;

  //검색 카운트 증가
  @PostMapping
  public void addSearchCount(@RequestBody Long hashTagId) {
    searchBarSVC.plusHahTagSearchCount(hashTagId);
  }

  //검색 카운트 감소
  @DeleteMapping
  public void subtractSearchCount(@RequestBody Long hashTagId) {
    searchBarSVC.minusHahTagSearchCount(hashTagId);
  }

  //검색바 해시태그 전체 조회
  @GetMapping("/hashtag")
  public ApiResult<Object> selectAll(){
    List<SearchBar> list = searchBarSVC.hashTagAll();
    return new ApiResult<>("00","success", list);
  }
}
