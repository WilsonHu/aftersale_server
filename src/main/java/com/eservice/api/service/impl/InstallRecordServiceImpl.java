package com.eservice.api.service.impl;

import com.eservice.api.dao.InstallRecordMapper;
import com.eservice.api.model.install_record.InstallRecord;
import com.eservice.api.model.install_record.InstallRecordInfo;
import com.eservice.api.service.InstallRecordService;
import com.eservice.api.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
* Class Description: xxx
* @author Wilson Hu
* @date 2018/07/19.
*/
@Service
@Transactional
public class InstallRecordServiceImpl extends AbstractService<InstallRecord> implements InstallRecordService {
    @Resource
    private InstallRecordMapper installRecordMapper;

    public List<InstallRecord> selectWaitProcessForGuest(String contacter){
        return installRecordMapper.selectWaitProcessForGuest(contacter);
    }
    public  List<InstallRecord> selectByNameplate(String nameplate){
        return installRecordMapper.selectByNameplate(nameplate);
    }

    public List<InstallRecordInfo> getInstallDetail(String nameplate) {
        return installRecordMapper.getInstallDetail(nameplate);
    }
}
