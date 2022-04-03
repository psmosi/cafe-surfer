package com.kh.cafesurfer.domain.CoffeeShopBbs.dao;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor

public class CoffeeShopCoffeeShopBbsDAOImpl implements CoffeeShopBbsDAO {

  private final JdbcTemplate jdbcTemplate;

  //등록
  @Override
  public Long saveOrigin(CoffeeShopBbs coffeeShopBbs) {
    // SQL 작성
    StringBuffer sql = new StringBuffer();

    sql.append("insert into coffeeshop (shop_id, shop_name, shop_address, shop_tel, parking, allday ) ");
    sql.append(" values(coffeeshop_shop_id_seq.nextval, ? , ? , ? , ? , ? ) ");

    // SQL 실행
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(
            sql.toString(),
            new String[]{"shop_id"} //insert 후 insert 레코드 중 반환할 컬럼명, keyHolder에 저장됨.
        );
        pstmt.setString(1, coffeeShopBbs.getShopName());
        pstmt.setString(2, coffeeShopBbs.getShopAddress());
        pstmt.setString(3, coffeeShopBbs.getShopTel());
        pstmt.setString(4, coffeeShopBbs.getYnParking());
        pstmt.setString(5, coffeeShopBbs.getYnAllDay());
        return pstmt;
      }
    }, keyHolder);

    return Long.valueOf(keyHolder.getKeys().get("shop_id").toString());

  }

  //전체조회
  @Override
  public List<CoffeeShopBbs> findAll() {
    StringBuffer sql = new StringBuffer();

    sql.append("select ");
    sql.append("    shop_id, ");
    sql.append("    shop_name, ");
    sql.append("    shop_address, ");
    sql.append("    shop_tel, ");
    sql.append("    view_count, ");
    sql.append("    shop_bookmark_count, ");
    sql.append("    shop_review_count, ");
    sql.append("    parking, ");
    sql.append("    allday, ");
    sql.append("    shop_cdate ");
    sql.append("from coffeeshop ");
    sql.append(" order by shop_id desc ");

    List<CoffeeShopBbs> list = jdbcTemplate.query(
        sql.toString(),
        new BeanPropertyRowMapper<>(CoffeeShopBbs.class)
    );

    return list;
  }



  @Override
  public List<CoffeeShopBbs> findAll(int startRec, int endRec) {
    StringBuffer sql = new StringBuffer();

    sql.append(" SELECT t1.* ");
    sql.append(" from( ");
    sql.append("     SELECT ");
    sql.append("     ROW_NUMBER() OVER (ORDER BY shop_id DESC) no, ");
    sql.append("    shop_id, ");
    sql.append("    shop_name, ");
    sql.append("    shop_address, ");
    sql.append("    shop_tel, ");
    sql.append("    view_count, ");
    sql.append("    shop_bookmark_count, ");
    sql.append("    shop_review_count, ");
    sql.append("    parking, ");
    sql.append("    allday, ");
    sql.append("    shop_cdate ");
    sql.append("     FROM ");
    sql.append("     coffeeshop) t1 ");
    sql.append(" where t1.no BETWEEN ? and ? ");

    List<CoffeeShopBbs> list = jdbcTemplate.query(sql.toString(),
        new BeanPropertyRowMapper<>(CoffeeShopBbs.class),
        startRec, endRec
    );

    return list;
  }



  //검색
  @Override
  public List<CoffeeShopBbs> findAll(CoffeeShopBbsFirterCondition coffeeShopBbsFirterCondition) {
    StringBuffer sql = new StringBuffer();

    sql.append(" select t1.* ");
    sql.append(" from( ");
    sql.append("     SELECT  ROW_NUMBER() OVER (ORDER BY shop_id DESC) no, ");
    sql.append("    shop_id, ");
    sql.append("    shop_name, ");
    sql.append("    shop_address, ");
    sql.append("    shop_tel, ");
    sql.append("    view_count, ");
    sql.append("    shop_bookmark_count, ");
    sql.append("    shop_review_count, ");
    sql.append("    parking, ");
    sql.append("    allday, ");
    sql.append("    shop_cdate ");
    sql.append("     FROM coffeeshop ");
    sql.append("     where ");

    //분류
    sql = dynamicQuery(coffeeShopBbsFirterCondition, sql);
    ;

    sql.append(" ) t1 ");
    sql.append(" where t1.no between ? and ? ");


    List<CoffeeShopBbs> list = null;

    //게시판 전체
    if (StringUtils.isEmpty(String.valueOf(coffeeShopBbsFirterCondition.getShopId()))) {
      list = jdbcTemplate.query(
          sql.toString(),
          new BeanPropertyRowMapper<>(CoffeeShopBbs.class),
          coffeeShopBbsFirterCondition.getStartRec(),
          coffeeShopBbsFirterCondition.getEndRec()
      );
      //게시판 분류
    } else {
      list = jdbcTemplate.query(
          sql.toString(),
          new BeanPropertyRowMapper<>(CoffeeShopBbs.class),
          coffeeShopBbsFirterCondition.getShopId(),
          coffeeShopBbsFirterCondition.getStartRec(),
          coffeeShopBbsFirterCondition.getEndRec()
      );
    }


    return list;
  }

  //조회
  @Override
  public CoffeeShopBbs findByBbsId(Long shopId) {

    StringBuffer sql = new StringBuffer();

    sql.append(" SELECT ");
    sql.append("    shop_id, ");
    sql.append("    shop_name, ");
    sql.append("    shop_address, ");
    sql.append("    shop_tel, ");
    sql.append("    view_count, ");
    sql.append("    shop_bookmark_count, ");
    sql.append("    shop_review_count, ");
    sql.append("    parking, ");
    sql.append("    allday, ");
    sql.append("    shop_cdate ");
    sql.append(" FROM ");
    sql.append("     coffeeshop ");
    sql.append(" where shop_id = ? ");

    CoffeeShopBbs coffeeShopBbsItem = null;
    try {
      coffeeShopBbsItem = jdbcTemplate.queryForObject(
          sql.toString(),
          new BeanPropertyRowMapper<>(CoffeeShopBbs.class),
          shopId);
    } catch (Exception e) { //1건을 못찾으면
      coffeeShopBbsItem = null;
    }

    return coffeeShopBbsItem;
  }

  //삭제
  @Override
  public int deleteBbsId(Long shopId) {
    StringBuffer sql = new StringBuffer();
    sql.append(" DELETE FROM coffeeshop ");
    sql.append(" WHERE  shop_id = ? ");

    int updateItemCount = jdbcTemplate.update(sql.toString(), shopId);

    return updateItemCount;
  }

  //수정
  @Override
  public int updateByBbsId(Long shopId, CoffeeShopBbs coffeeShopBbs) {
    StringBuffer sql = new StringBuffer();

    sql.append(" update coffeeshop ");
    sql.append(" set shop_id = ?, ");
    sql.append("    shop_name, ");
    sql.append("    shop_address, ");
    sql.append("    shop_tel, ");
    sql.append("    view_count, ");
    sql.append("    shop_bookmark_count, ");
    sql.append("    shop_review_count, ");
    sql.append("    parking, ");
    sql.append("    allday, ");
    sql.append("    shop_cdate ");
    sql.append(" where shop_id = ? ");

    int updatedItemCount = jdbcTemplate.update(
        sql.toString(),
        coffeeShopBbs.getShopId(),
        coffeeShopBbs.getShopName(),
        coffeeShopBbs.getShopAddress(),
        coffeeShopBbs.getShopTel(),
        coffeeShopBbs.getViewCnt(),
        coffeeShopBbs.getShopBookmarkCnt(),
        coffeeShopBbs.getShopReviewCnt(),
        coffeeShopBbs.getYnParking(),
        coffeeShopBbs.getYnAllDay(),
        coffeeShopBbs.getShopCdate(),
        shopId
    );

    return updatedItemCount;
  }


  //전체건수
  @Override
  public int totalCount() {

    String sql = "select count(*) from coffeeshop";

    Integer cnt = jdbcTemplate.queryForObject(sql, Integer.class);

    return cnt;
  }

  @Override
  public int totalCount(CoffeeShopBbsFirterCondition coffeeShopBbsFirterCondition) {

    StringBuffer sql = new StringBuffer();

    sql.append("select count(*) ");
    sql.append("  from coffeeshop  ");
    sql.append(" where  ");

    sql = dynamicQuery(coffeeShopBbsFirterCondition, sql);

    Integer cnt = 0;
    //게시판 전체 검색 건수
    if(StringUtils.isEmpty(String.valueOf(coffeeShopBbsFirterCondition.getShopId()))) {
      cnt = jdbcTemplate.queryForObject(
          sql.toString(), Integer.class
      );
      //게시판 분류별 검색 건수
    }else{
      cnt = jdbcTemplate.queryForObject(
          sql.toString(), Integer.class,
          coffeeShopBbsFirterCondition.getShopId()
      );
    }

    return cnt;
  }

  private StringBuffer dynamicQuery(CoffeeShopBbsFirterCondition coffeeShopBbsFirterCondition, StringBuffer sql) {
    //분류
    if(StringUtils.isEmpty(String.valueOf(coffeeShopBbsFirterCondition.getShopId()))){

    }else{
      sql.append("       shop_id = ? ");
    }

    //분류,검색유형,검색어 존재
    if(!StringUtils.isEmpty(String.valueOf(coffeeShopBbsFirterCondition.getShopId())) &&
        !StringUtils.isEmpty(coffeeShopBbsFirterCondition.getSearchType()) &&
        !StringUtils.isEmpty(coffeeShopBbsFirterCondition.getKeyword())){

      sql.append(" AND ");
    }

    //검색유형
    switch (coffeeShopBbsFirterCondition.getSearchType()){
      case "TC":  //shopId + 커피숍이름
        sql.append("    (  shop_id    like '%"+ coffeeShopBbsFirterCondition.getKeyword()+"%' ");
        sql.append("    or shop_name like '%"+ coffeeShopBbsFirterCondition.getKeyword()+"%' )");
        break;
      case "T":   //shopId
        sql.append("       shop_id    like '%"+ coffeeShopBbsFirterCondition.getKeyword()+"%' ");
        break;
      case "C":   //커피숍이름
        sql.append("       shop_name like '%"+ coffeeShopBbsFirterCondition.getKeyword()+"%' ");
        break;
      case "E":   //커피숍 주소
        sql.append("       shop_address    like '%"+ coffeeShopBbsFirterCondition.getKeyword()+"%' ");
        break;
      case "N":   //키피숍 전화번호
        sql.append("       shop_tel like '%"+ coffeeShopBbsFirterCondition.getKeyword()+"%' ");
        break;
      default:
    }
    return sql;
  }


  /**
   * 조회수 카운트
   * @param shopId
   * @return
   */
  @Override
  public Long updateViewCnt(Long shopId) {

    StringBuffer sql = new StringBuffer();

    sql.append("update coffeeshop ");
    sql.append("set view_count = view_count + 1 ");
    sql.append("where shop_id = ? ");

    long cnt = jdbcTemplate.update(sql.toString(), shopId);

    return cnt;
  }

  /**
   * 북마크 카운트
   * @param shopId
   * @return
   */
  @Override
  public Long updateBookmarkCnt(Long shopId) {

    StringBuffer sql = new StringBuffer();

    sql.append("update coffeeshop ");
    sql.append("shop_bookmark_count = shop_bookmark_count + 1 ");
    sql.append("where shop_id = ? ");

    long cnt = jdbcTemplate.update(sql.toString(), shopId);

    return cnt;
  }

  /**
   * 리뷰수 카운트
   * @param shopId
   * @return
   */
  @Override
  public Long updateShopReviewCnt(Long shopId) {

    StringBuffer sql = new StringBuffer();

    sql.append("update coffeeshop ");
    sql.append("shop_review_count = shop_review_count ");
    sql.append("where shop_Id = ? ");

    long cnt = jdbcTemplate.update(sql.toString(), shopId);

    return cnt;
  }

}