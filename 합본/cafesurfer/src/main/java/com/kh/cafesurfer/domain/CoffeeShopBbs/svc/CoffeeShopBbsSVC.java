package com.kh.cafesurfer.domain.CoffeeShopBbs.svc;


import com.kh.cafesurfer.domain.CoffeeShopBbs.dao.CoffeeShopBbs;
import com.kh.cafesurfer.domain.CoffeeShopBbs.dao.CoffeeShopBbsFirterCondition;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CoffeeShopBbsSVC {

    /**
     * 원글작성
     * @param coffeeShopBbs
     * @return 게시글 번호
     */
    Long saveOrigin(CoffeeShopBbs coffeeShopBbs);


    /**
     * 원글작성-첨부파일 있는경우
     * @param coffeeShopBbs
     * @param files 첨파일
     * @return 게시글 번호
     */
    Long saveOrigin(CoffeeShopBbs coffeeShopBbs, List<MultipartFile> files);


    /**
     * 목록
     * @return
     */
    List<CoffeeShopBbs> findAll();
    List<CoffeeShopBbs> findAll(int startRec, int endRec);
    List<CoffeeShopBbs> findAll(Long shopId, int startRec, int endRec);

    /**
     * 검색
     * @param coffeeShopBbsFirterCondition 분류, 시작레코드번호, 종료레코드번호, 검색유형, 검색어
     * @return
     */
    List<CoffeeShopBbs> findAll(CoffeeShopBbsFirterCondition coffeeShopBbsFirterCondition);

    /**
     * 상세조회
     * @param shopId 게시글번호
     * @return
     */
    CoffeeShopBbs findByBbsId(Long shopId);

    /**
     * 삭제
     * @param shopId 게시글번호
     * @return   삭제건수
     */
    int deleteBbsId(Long shopId);

    /**
     * 수정
     * @param shopId  게시글번호
     * @param coffeeShopBbs 수정내용
     * @return    수정건수
     */
    int updateByBbsId(Long shopId, CoffeeShopBbs coffeeShopBbs);

    /**
     * 수정-첨부
     * @param shopId 게시글 번호
     * @param coffeeShopBbs 수정내용
     * @param files 첨부파일
     * @return 수정건수
     */
    int updateByBbsId(Long shopId, CoffeeShopBbs coffeeShopBbs, List<MultipartFile> files);

    /**전체건수
     *
     * @return 게시글 전체건수
     */
    int totalCount();

    int totalCount(CoffeeShopBbsFirterCondition coffeeShopBbsFirterCondition);
  }
