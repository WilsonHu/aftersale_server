package com.eservice.api.dao;

import com.eservice.api.core.Mapper;
import com.eservice.api.model.parts_info.PartsAllInfo;
import com.eservice.api.model.parts_info.PartsInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PartsInfoMapper extends Mapper<PartsInfo> {
    List<PartsAllInfo> getPartsInfoByRepairRecordId(@Param("repairRecordId") Integer repairRecordId);

    List<PartsAllInfo> getPartsInfoList(@Param("nameplate")String nameplate,
                                        @Param("partsName")String partsName,
                                        @Param("customerNameInMachine")String customerNameInMachine,
                                        @Param("repairChargePersonName")String repairChargePersonName,
                                        @Param("queryStartSendbackConfirmedTime")String queryStartSendbackConfirmedTime,
                                        @Param("queryFinishSendbackConfirmedTime")String queryFinishSendbackConfirmedTime,
                                        @Param("supplier")String supplier,
                                        @Param("partsStatus")String partsStatus,
                                        @Param("sendbackTrackingNumber")String sendbackTrackingNumber,
                                        @Param("sendbackConfirmedPerson")String sendbackConfirmedPerson,
                                        @Param("isFuzzy")boolean isFuzzy);
}