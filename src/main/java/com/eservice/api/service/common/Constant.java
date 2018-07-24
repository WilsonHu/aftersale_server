package com.eservice.api.service.common;

/**
 * Class Description:常量定义类
 *
 * @author Wilson Hu
 * @date 22/12/2017
 */
public class Constant {
    /**
     * 保存文件的类型
     * 为了区分同一次上传下的不同类型（比如报修时的铭牌号照片和报修问题照片）
     * 需要文件中包含下列名称中一个。
     */
    //报修的声音：
    public static final String REPAIR_REQUEST_VOICE = "REPAIR_REQUEST_VOICE";
    //报修的图片：
    public static final String REPAIR_REQUEST_IMAGE = "REPAIR_REQUEST_IMAGE";
    //维修的图片：
    public static final String REPAIR_ACTUAL_IMAGE = "REPAIR_ACTUAL_IMAGE";
    //配件寄回快递图片
    public static final String PARTS_SENDBACK_IMAGE = "PARTS_SENDBACK_IMAGE";
    //报修时提供的铭牌号图片（老机器）
    public static final String REPAIR_REQUEST_NAMEPLATE_IMAGE = "REPAIR_REQUEST_NAMEPLATE_IMAGE";


    /*
    生产部的机器状态对应
    */
    public static final int MACHINE_INITIAL = 0;
    public static final int MACHINE_CONFIGURED = 1;
    public static final int MACHINE_PLANING = 2;
    public static final int MACHINE_INSTALLING = 3;
    public static final int MACHINE_INSTALLED = 4;


    public enum ValidEnum {
        INVALID(0), VALID(1);
        private Integer value;

        private ValidEnum(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return this.value;
        }
    }

    public enum IsBaseLibEnum{
        BASE_LIB_ENUM(0), NOT_BASE_LIB_ENUM(1);
        private Integer value;

        private IsBaseLibEnum(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return this.value;
        }
    }

    /**
     * 维修状态
     * 0：未派单，
     * 1：已派单（但未接单）,
     * 2： 已接受任务，
     * 3：维修成功(客户未确认)，
     * 4：无法维修，维修被转派（不需要客户确认），
     * 5.客户已确认（维修成功）。转派后，前面的维修记录要保留，但是客户只需要看到成功的最后那次记录。
     */
    public static final String REPAIR_STATUS_UNSIGNED_TO_REPAIRER = "0";
    public static final String REPAIR_STATUS_SIGNED_TO_REPAIRER = "1";
    public static final String REPAIR_STATUS_REPAIRER_ACCEPTED = "2";
    public static final String REPAIR_STATUS_REPAIR_OK = "3";
    public static final String REPAIR_STATUS_REPAIR_NG = "4";
    public static final String REPAIR_STATUS_REPAIR_CUSTOMER_CONFIRMED = "5";

    /**
     * 机器状态
     * 0：未绑定，
     * 1：已绑定，
     * 2：待安装，
     * 3：正常工作状态（已安装、已保养，已维修），
     * 4：待保养，
     * 5：待修理，
     * 6：待审核
     */
    public static final String MACHINE_STATUS_UNBIND_TO_CUSTOMER = "0";
    public static final String MACHINE_STATUS_BOUND_TO_CUSTOMER = "1";
    public static final String MACHINE_STATUS_WAIT_FOR_INSTALL = "2";
    public static final String MACHINE_STATUS_IN_NORMAL = "3";
    public static final String MACHINE_STATUS_WAIT_FOR_MAINTAIN = "4";
    public static final String MACHINE_STATUS_WAIT_FOR_REPAIR = "5";
    public static final String MACHINE_STATUS_WAIT_FOR_CHECK = "6";
}
