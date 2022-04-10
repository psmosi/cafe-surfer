package com.kh.cafesurfer.domain.searchBar.dao;


import com.kh.cafesurfer.domain.searchBar.SearchBar;

import java.util.List;

public interface SearchBarDAO {

  List<SearchBar> hashTagAll();

  Long plusHahTagSearchCount(Long hashtagId);

  Long minusHahTagSearchCount(Long hashtagId);
}
