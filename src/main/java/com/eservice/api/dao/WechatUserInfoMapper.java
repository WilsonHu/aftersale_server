package com.eservice.api.dao;

import com.eservice.api.core.Mapper;
import com.eservice.api.model.wechat_user_info.WechatUserInfo;
import org.apache.ibatis.annotations.Param;

public interface WechatUserInfoMapper extends Mapper<WechatUserInfo> {

    WechatUserInfo getWechatUserInfoByUnionId(@Param("unionId") String unionId);
    WechatUserInfo getWechatUserInfoByOpenId(@Param("openId") String openId);

    WechatUserInfo getWechatUserInfoByAccount(@Param("account") String account);
}