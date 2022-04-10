package com.kh.cafesurfer.domain.searchBar.svc;

import com.kh.cafesurfer.domain.searchBar.SearchBar;

import java.util.List;

public interface SearchBarSVC {

  //해시태그 전체 목록 출력
  List<SearchBar> hashTagAll();

  //해시태그 검색 카운트 증가
  void plusHahTagSearchCount(Long hashTagId);

  //해시태그 검색 카운트 감소
  void minusHahTagSearchCount(Long hashTagId);
}
