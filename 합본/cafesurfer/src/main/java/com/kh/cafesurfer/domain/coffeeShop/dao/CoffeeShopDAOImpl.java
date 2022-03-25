package com.kh.cafesurfer.domain.coffeeShop.dao;

import com.kh.cafesurfer.domain.coffeeShop.CoffeeShop;
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
public class CoffeeShopDAOImpl implements CoffeeShopDAO {
  
  private final JdbcTemplate jdbcTemplate;
  
  /**
   * 등록
   * @param coffeeShop
   * @return
   */
  @Override
  public CoffeeShop insertCoffeeShop(CoffeeShop coffeeShop) {
    
    // SQL 작성
    StringBuffer sql = new StringBuffer();
  
    sql.append("insert into coffeeshop (shop_id, shop_name, shop_address, shop_tel, parking, allday ) ");
    sql.append(" values(coffeeshop_shop_id_seq.nextval, ? , ? , ? , ? , ? ) ");
//    values(coffeeshop_shop_id_seq.nextval, '카페 솔츠', '울산 남구 봉월로8번길 18 1층', '052-710-5252', default, default, default, 1, 0, default);
  
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(
            sql.toString(),
            new String[]{"shop_id"}
        );
        
        pstmt.setString(1, coffeeShop.getShopName());
        pstmt.setString(2, coffeeShop.getShopAddress());
        pstmt.setString(3, coffeeShop.getShopTel());
        pstmt.setInt(4, coffeeShop.getYnParking());
        pstmt.setInt(5, coffeeShop.getYnAllDay());
        
        return pstmt;
      }
    }, keyHolder);
    long shopId = keyHolder.getKey().longValue();
  
  
    return selectOne(shopId);
  }
  
  /**
   * 수정
   * @param coffeeShop
   * @return
   */
  @Override
  public CoffeeShop updateCoffeeShop(Long id, CoffeeShop coffeeShop) {
    
    StringBuffer sql = new StringBuffer();
  
    sql.append("update coffeeshop ");
    sql.append("   set shop_name = ? , ");       // SHOP_NAME
    sql.append("       shop_address = ? , ");       // SHOP_ADDRESS
    sql.append("       shop_tel = ? , ");       // SHOP_TEL
    sql.append("       parking = ? , ");       // VIEW_COUNT
    sql.append("       allday = ?, ");       // SHOP_BOOKMARK_COUNT
    sql.append("       shop_cdate = systimestamp ");       // SHOP_BOOKMARK_COUNT
    sql.append("where shop_id = ? ");
 
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(
            sql.toString(),
            new String[]{"shop_id"}
        );
        
        pstmt.setString(1, coffeeShop.getShopName());
        pstmt.setString(2, coffeeShop.getShopAddress());
        pstmt.setString(3, coffeeShop.getShopTel());
        pstmt.setLong(4, coffeeShop.getYnParking());
        pstmt.setLong(5, coffeeShop.getYnAllDay());
        pstmt.setLong(6, coffeeShop.getShopId());
        
        return pstmt;
      }
    }, keyHolder);
    
    return selectOne(coffeeShop.getShopId());
  }
  
  /**
   * 삭제
   * @param shopId
   * @return
   */
  @Override
  public CoffeeShop deleteCoffeeShop(Long shopId) {
    
    StringBuffer sql = new StringBuffer();
    sql.append("delete from coffeeshop ");
    sql.append("where shop_id = ? ");
  
    jdbcTemplate.update(sql.toString(),
        shopId);
    return null;
  }
  
  /**
   * 전체조회
   * @return
   */
  @Override
  public List<CoffeeShop> selectAll() {
  
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
  
    List<CoffeeShop> list = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(CoffeeShop.class));
    
    return list;
  }
  
  /**
   * 개별조회
   * @param shopId
   * @return
   */
  @Override
  public CoffeeShop selectOne(Long shopId) {
    
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
    sql.append("where shop_id = ? ");
    
    CoffeeShop coffeeShopItem = null;
  
    try {
      coffeeShopItem = jdbcTemplate.queryForObject(
          sql.toString(),
          new BeanPropertyRowMapper<>(CoffeeShop.class),
          shopId
      );
    } catch (DataAccessException e) {
      coffeeShopItem = null;
    }
    return coffeeShopItem;
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
    sql.append("where shop_Id = ? ");

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
    sql.append("where shop_Id = ? ");

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
