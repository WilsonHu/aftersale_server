package com.eservice.api.service.impl;

import com.eservice.api.dao.WechatUserInfoMapper;
import com.eservice.api.model.wechat_user_info.WechatUserInfo;
import com.eservice.api.service.WechatUserInfoService;
import com.eservice.api.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* Class Description: xxx
* @author Wilson Hu
* @date 2018/10/15.
*/
@Service
@Transactional
public class WechatUserInfoServiceImpl extends AbstractService<WechatUserInfo> implements WechatUserInfoService {
    @Resource
    private WechatUserInfoMapper wechatUserInfoMapper;

    public WechatUserInfo getWechatUserInfoByUnionId(String unionId) {
        return wechatUserInfoMapper.getWechatUserInfoByUnionId(unionId);
    }

}
