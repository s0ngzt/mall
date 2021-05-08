package com.example.mall.service;

import com.example.mall.common.api.CommonResult;

/**
 * 会员管理
 */
public interface UmsMemberService {

  /**
   * 生成验证码
   */
  CommonResult<String> generateAuthCode(String telephone);

  /**
   * 判断验证码和手机号码是否匹配
   */
  CommonResult<Object> verifyAuthCode(String telephone, String authCode);

}
