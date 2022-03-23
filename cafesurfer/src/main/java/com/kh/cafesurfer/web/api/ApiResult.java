package com.kh.cafesurfer.web.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResult<T> {
  private String rtcd;
  private String rtmsg;
  private T data;
}
