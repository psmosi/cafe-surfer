package com.kh.cafesurfer.domain.bookMark.dao;

import com.kh.cafesurfer.domain.bookMark.BookMark;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BookMarkDAOImpl implements BookMarkDAO{

  private final JdbcTemplate jdbcTemplate;

  //등록
  @Override
  public BookMark insertBookMark(Long memberId, Long shopId ){

    StringBuffer sql = new StringBuffer();

    sql.append("insert into bookmark " );
    sql.append("values( default, ? , ?) " );

    jdbcTemplate.update(sql.toString(), memberId, shopId);
    return null;
  }

  //삭제
  @Override
  public void deleteBookMark(Long memberId, Long shopId) {

    StringBuffer sql = new StringBuffer();

   sql.append(" DELETE FROM bookmark ");
   sql.append("     WHERE ");
   sql.append(" member_id = ? ");
   sql.append(" AND shop_id = ? ");

    jdbcTemplate.update(sql.toString(), memberId, shopId);
  }

  //조회
  @Override
  public BookMark selectBookMarkByMemberId(Long memberId) {

    StringBuffer sql = new StringBuffer();


        sql.append(" select * from bookmark ");
        sql.append(" WHERE member_id = ? ");

    BookMark bookMark = jdbcTemplate.queryForObject(
        sql.toString(),
        new BeanPropertyRowMapper<>(BookMark.class),
        memberId
    );
    return bookMark;
  }

}
