package com.kh.cafesurfer.domain.review.dao;

import com.kh.cafesurfer.domain.review.Review;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ReviewDAOImpl implements ReviewDAO {

  private final JdbcTemplate jdbcTemplate;

  /**
   * 리뷰등록
   * @param review
   * @return
   */
  @Override
  public Review insertReview(Review review) {

    // SQL 작성
    StringBuffer sql = new StringBuffer();

    sql.append("insert into review (review_id, review_content, member_id, shop_id ) ");
    sql.append("values ( ");
    sql.append("    review_review_id_seq.nextval, ");
    sql.append("    ? , ");
    sql.append("    ?, ");
    sql.append("    ? ) ");

    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(
            sql.toString(),
            new String[]{"review_id"}
        );

        pstmt.setString(1, review.getReviewContent());
        pstmt.setLong(2, review.getMemberId());
        pstmt.setLong(3, review.getShopId());

        return pstmt;
      }
    }, keyHolder);

    long reviewId = keyHolder.getKey().longValue();
    return selectOne(reviewId);
  }

  /**
   * 리뷰 전체조회
   * @return
   */
  @Override
  public List<Review> selectAll() {

    StringBuffer sql = new StringBuffer();

    sql.append("select review_id, ");
    sql.append("       review_content, ");
    sql.append("       review_cdate, ");
    sql.append("       report_count, ");
    sql.append("       member_id, ");
    sql.append("       shop_id ");
    sql.append("  from review ");
    sql.append("order by review_id asc ");

    List<Review> list = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(Review.class));

    return list;
  }

  /**
   * 리뷰 단건조회(by reviewId)
   * @param reviewId
   * @return
   */
  @Override
  public Review selectOne(Long reviewId) {

    StringBuffer sql = new StringBuffer();

    sql.append("select review_id, ");
    sql.append("       review_content, ");
    sql.append("       review_cdate, ");
    sql.append("       report_count, ");
    sql.append("       member_id, ");
    sql.append("       shop_id ");
    sql.append("  from review ");
    sql.append(" where review_id = ? ");

    Review reviewItem = null;

    try {
      reviewItem = jdbcTemplate.queryForObject(
          sql.toString(),
          new BeanPropertyRowMapper<>(Review.class),
          reviewId
      );
    } catch (DataAccessException e) {
      reviewItem = null;
    }
    return reviewItem;
  }

  /**
   * 회원별 리뷰조회(by memberId)
   * @param memberId
   * @return
   */
  @Override
  public List<Review> selectByMemberId(Long memberId) {
    StringBuffer sql = new StringBuffer();
  
    sql.append("select review_id, ");
    sql.append("       review_content, ");
    sql.append("       review_cdate, ");
    sql.append("       report_count, ");
    sql.append("       member_id, ");
    sql.append("       shop_id ");
    sql.append("  from review ");
    sql.append(" where member_id = ? ");
    sql.append("order by review_id asc ");
  
    List<Review> list = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(Review.class), memberId);
  
    return list;
  }

  /**
   * 가게별 리뷰조회(by shopId)
   * @param shopId
   * @return
   */
  @Override
  public List<Review> selectByShopId(Long shopId) {
  
    StringBuffer sql = new StringBuffer();
  
    sql.append("select review_id, ");
    sql.append("       review_content, ");
    sql.append("       review_cdate, ");
    sql.append("       report_count, ");
    sql.append("       member_id, ");
    sql.append("       shop_id ");
    sql.append("  from review ");
    sql.append(" where shop_id = ? ");
    sql.append("order by review_id asc ");
  
    List<Review> list = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(Review.class), shopId);
  
    return list;
  }

  /**
   * 리뷰삭제
   * @param reviewId
   * @return
   */
  @Override
  public int deleteReview(Long reviewId) {
    
    StringBuffer sql = new StringBuffer();
  
    sql.append("select * from review ");
    sql.append("where shop_id = ? ");
  
    int updateItemCount = jdbcTemplate.update(sql.toString(),
        reviewId);
    
    return updateItemCount;
  }

  /**
   * 리뷰 신고수
   * @param reviewId
   * @return
   */
  @Override
  public int insertReportCnt(Long reviewId) {
    StringBuffer sql = new StringBuffer();
  
    sql.append("update review ");
    sql.append("set report_count = report_count + 1 ");
    sql.append("where review_id = ? ");
  
    int affectedRows = jdbcTemplate.update(sql.toString(), reviewId);
  
    return affectedRows;
  }
}
