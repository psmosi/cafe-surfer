package com.kh.cafesurfer.domain.searchBar.dao;

import com.kh.cafesurfer.domain.searchBar.SearchBar;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SearchBarDAOImpl implements SearchBarDAO{

  private final JdbcTemplate jdbcTemplate;

  @Override
  public List<SearchBar> hashTagAll() {
    StringBuffer sql = new StringBuffer();

    sql.append(" select * from hashtag_classification ");
    sql.append(" where not hashtag_id between 1700 and 3000 ");
    sql.append(" order by hashtag_id ");

    List<SearchBar> hashTagList = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(SearchBar.class));

    return hashTagList;
  }


  @Override
  public Long plusHahTagSearchCount(Long hashtagId) {
    StringBuffer sql = new StringBuffer();

    sql.append("update hashtag_classification ");
    sql.append("set hashtag_search_count = hashtag_search_count + 1 ");
    sql.append("where hashtag_id = ? ");

    long cnt = jdbcTemplate.update(sql.toString(), hashtagId);

    return cnt;
  }

  @Override
  public Long minusHahTagSearchCount(Long hashtagId) {
    StringBuffer sql = new StringBuffer();

    sql.append("update hashtag_classification ");
    sql.append("set hashtag_search_count = hashtag_search_count - 1 ");
    sql.append("where hashtag_id = ? ");

    long cnt = jdbcTemplate.update(sql.toString(), hashtagId);

    return cnt;
  }
}
