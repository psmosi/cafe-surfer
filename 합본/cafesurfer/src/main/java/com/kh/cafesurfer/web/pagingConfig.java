package com.kh.cafesurfer.web;


import com.kh.cafesurfer.domain.common.paging.FindCriteria;
import com.kh.cafesurfer.domain.common.paging.PageCriteria;
import com.kh.cafesurfer.domain.common.paging.RecordCriteria;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class pagingConfig {

  private static final int REC_COUNT_10_PER_PAGE = 10;
  private static final int PAGE_COUNT_10_PER_PAGE = 10;

  private static final int REC_COUNT_5_PER_PAGE = 5;
  private static final int PAGE_COUNT_5_PER_PAGE = 5;

  @Bean
  public RecordCriteria rc10(){
    return new RecordCriteria(REC_COUNT_10_PER_PAGE);
  }

  @Bean
  public PageCriteria pc10(){
    return new PageCriteria(rc10(), PAGE_COUNT_10_PER_PAGE);
  }

  @Bean
  public RecordCriteria rc5(){
    return new RecordCriteria(REC_COUNT_5_PER_PAGE);
  }

  @Bean
  public PageCriteria pc5(){
    return new PageCriteria(rc10(), PAGE_COUNT_5_PER_PAGE);
  }

  @Bean
  public FindCriteria fc10() {
    return new FindCriteria(rc10(),PAGE_COUNT_10_PER_PAGE);
  }

  @Bean
  public FindCriteria fc5() {
    return  new FindCriteria(rc5(),PAGE_COUNT_5_PER_PAGE);
  }
}
