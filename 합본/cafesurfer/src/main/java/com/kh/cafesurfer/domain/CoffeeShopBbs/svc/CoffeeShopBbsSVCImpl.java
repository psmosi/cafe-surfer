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
  public Long saveOrigin(CoffeeShopBbs coffeeShopBbs, List<MultipartFile> files) {

    //1)원글 저장
    Long shopId = saveOrigin(coffeeShopBbs);

    //2)첨부 저장
    uploadFileSVC.addFile(shopId,files);

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
    String bcategory = String.valueOf(coffeeShopBbsDAO.findByBbsId(shopId));
    uploadFileSVC.deleteFileByCodeWithRid(shopId);

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
  public int updateByBbsId(Long shopId, CoffeeShopBbs coffeeShopBbs, List<MultipartFile> files) {

    //1)수정
    int affectedRow = updateByBbsId(shopId,coffeeShopBbs);

    //2)첨부 저장
    uploadFileSVC.addFile(shopId,files);

    return affectedRow;
  }

  @Override
  public List<CoffeeShopBbs> findAll(Long shopId, int startRec, int endRec) {
    return null;
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
