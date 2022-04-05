package com.kh.cafesurfer.domain.CoffeeShopBbs.svc;


import com.kh.cafesurfer.domain.CoffeeShopBbs.dao.CoffeeShopBbs;
import com.kh.cafesurfer.domain.CoffeeShopBbs.dao.CoffeeShopBbsDAO;
import com.kh.cafesurfer.domain.CoffeeShopBbs.dao.CoffeeShopBbsFirterCondition;
import com.kh.cafesurfer.domain.common.file.svc.UploadFileSVC;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CoffeeShopBbsSVCImpl implements CoffeeShopBbsSVC {

  private final CoffeeShopBbsDAO coffeeShopBbsDAO;
  private final UploadFileSVC uploadFileSVC;


  private String CODE = "F0101";

  //원글
  @Override
  public Long saveOrigin(CoffeeShopBbs coffeeShopBbs) {
    return coffeeShopBbsDAO.saveOrigin(coffeeShopBbs);
  }

  //원글-첨부파일
  @Override
  public Long saveOrigin(CoffeeShopBbs coffeeShopBbs, List<MultipartFile> files1,List<MultipartFile> files2,List<MultipartFile> files3,List<MultipartFile> files4) {

    //1)원글 저장
    Long shopId = saveOrigin(coffeeShopBbs);

    //2)첨부 저장
    uploadFileSVC.addFile(coffeeShopBbs.getBcategoryB0101(),shopId, files1);
    uploadFileSVC.addFile(coffeeShopBbs.getBcategoryB0102(),shopId, files2);
    uploadFileSVC.addFile(coffeeShopBbs.getBcategoryB0103(),shopId, files3);
    uploadFileSVC.addFile(coffeeShopBbs.getBcategoryB0104(),shopId, files4);

    return shopId;
  }

  //목록
  @Override
  public List<CoffeeShopBbs> findAll() {
    return coffeeShopBbsDAO.findAll();
  }

  @Override
  public List<CoffeeShopBbs> findAll(int startRec, int endRec) {
    return coffeeShopBbsDAO.findAll(startRec,endRec);
  }

  //검색
  @Override
  public List<CoffeeShopBbs> findAll(CoffeeShopBbsFirterCondition coffeeShopBbsFirterCondition) {
    return coffeeShopBbsDAO.findAll(coffeeShopBbsFirterCondition);
  }

  //상세조회
  @Override
  public CoffeeShopBbs findByBbsId(Long shopId) {
    CoffeeShopBbs findedItem = coffeeShopBbsDAO.findByBbsId(shopId);
    return findedItem;
  }

  //삭제
  @Override
  public int deleteBbsId(Long shopId) {
    //1)첨부파일 삭제
    String bcategoryB0101 = coffeeShopBbsDAO.findByBbsId(shopId).getBcategoryB0101();
    String bcategoryB0102 = coffeeShopBbsDAO.findByBbsId(shopId).getBcategoryB0102();
    String bcategoryB0103 = coffeeShopBbsDAO.findByBbsId(shopId).getBcategoryB0103();
    String bcategoryB0104 = coffeeShopBbsDAO.findByBbsId(shopId).getBcategoryB0104();
    uploadFileSVC.deleteFileByCodeWithRid(bcategoryB0101, shopId);
    uploadFileSVC.deleteFileByCodeWithRid(bcategoryB0102, shopId);
    uploadFileSVC.deleteFileByCodeWithRid(bcategoryB0103, shopId);
    uploadFileSVC.deleteFileByCodeWithRid(bcategoryB0104, shopId);

    //2)게시글 삭제
    int affectedRow = coffeeShopBbsDAO.deleteBbsId(shopId);

    return affectedRow;
  }

  //수정
  @Override
  public int updateByBbsId(Long shopId, CoffeeShopBbs coffeeShopBbs) {
    return coffeeShopBbsDAO.updateByBbsId(shopId, coffeeShopBbs);
  }

  //수정-첨부파일
  @Override
  public int updateByBbsId( CoffeeShopBbs coffeeShopBbs, Long rid, List<MultipartFile> files) {

    //1)수정
    int affectedRow = updateByBbsId(rid, coffeeShopBbs);

    //2)첨부 저장
    uploadFileSVC.addFile(coffeeShopBbs.getBcategoryB0101(),rid,files);
    uploadFileSVC.addFile(coffeeShopBbs.getBcategoryB0102(),rid,files);
    uploadFileSVC.addFile(coffeeShopBbs.getBcategoryB0103(),rid,files);
    uploadFileSVC.addFile(coffeeShopBbs.getBcategoryB0104(),rid,files);

    return affectedRow;
  }



  //전체건수
  @Override
  public int totalCount() {
    return coffeeShopBbsDAO.totalCount();
  }



  @Override
  public int totalCount(CoffeeShopBbsFirterCondition coffeeShopBbsFirterCondition) {
    return coffeeShopBbsDAO.totalCount(coffeeShopBbsFirterCondition);
  }


}
