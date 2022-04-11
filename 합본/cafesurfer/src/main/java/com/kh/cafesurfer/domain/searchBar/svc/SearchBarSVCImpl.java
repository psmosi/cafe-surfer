package com.kh.cafesurfer.domain.searchBar.svc;

import com.kh.cafesurfer.domain.searchBar.SearchBar;
import com.kh.cafesurfer.domain.searchBar.dao.SearchBarDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchBarSVCImpl implements SearchBarSVC{

  private final SearchBarDAO searchBarDAO;

  @Override
  public List<SearchBar> hashTagAll() {
    return searchBarDAO.hashTagAll();
  }

  @Override
  public void plusHahTagSearchCount(Long hashTagId) {
    searchBarDAO.plusHahTagSearchCount(hashTagId);
  }

  @Override
  public void minusHahTagSearchCount(Long hashTagId) {
    searchBarDAO.minusHahTagSearchCount(hashTagId);
  }
}
