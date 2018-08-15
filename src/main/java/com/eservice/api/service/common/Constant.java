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
    public static final String FILE_TYPE_REPAIR_REQUEST_VOICE = "REPAIR_REQUEST_VOICE";
    //报修的图片：
    public static final String FILE_TYPE_REPAIR_REQUEST_IMAGE = "REPAIR_REQUEST_IMAGE";
    //维修的图片：
    public static final String FILE_TYPE_REPAIR_ACTUAL_IMAGE = "REPAIR_ACTUAL_IMAGE";
    //配件寄回快递图片
    public static final String FILE_TYPE_PARTS_SENDBACK_IMAGE = "PARTS_SENDBACK_IMAGE";
    //报修时提供的铭牌号图片（老机器）
    public static final String FILE_TYPE_REPAIR_REQUEST_NAMEPLATE_IMAGE = "REPAIR_REQUEST_NAMEPLATE_IMAGE";


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

    public enum IsBaseLibEnum {
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
     * 2：已接受（进行中，可以再派），
     * 3：失败，(可以 再派，代理商可以 转派)
     * 4：已再派，
     * 5：已转派，
     * 6：已完成(客户未确认)，
     * 7. 客户确认（维修成功）。转派后，前面的维修记录要保留，但是客户只需要看到成功的最后那次记录。
     */
    public static final String REPAIR_STATUS_UNSIGNED_TO_REPAIRER = "0";
    public static final String REPAIR_STATUS_SIGNED_TO_REPAIRER = "1";
    public static final String REPAIR_STATUS_REPAIRER_ACCEPTED = "2";
    public static final String REPAIR_STATUS_REPAIR_NG = "3";
    public static final String REPAIR_STATUS_REPAIRER_REASSIGN = "4";
    public static final String REPAIR_STATUS_REPAIRER_FORWARD = "5";
    public static final String REPAIR_STATUS_REPAIR_OK = "6";
    public static final String REPAIR_STATUS_REPAIR_CUSTOMER_CONFIRMED = "7";

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

    /*
     保养状态
      0：待分配，
      1：已分配(但未接单）
      2：已接受任务，
      3：保养完成(客户未确认)，
      4：已确认保养结果
     */
    public static final String MAINTAIN_STATUS_NOT_ASSIGN = "0";
    public static final String MAINTAIN_STATUS_ASSIGNED = "1";
    public static final String MAINTAIN_STATUS_TASK_DOING = "2";
    public static final String MAINTAIN_STATUS_FINISHED = "3";
    public static final String MAINTAIN_STATUS_CONFIRMED= "4";

    /**
     * 安装状态，
     0：待分配，
     1：已分配(但未接单）
     2：已接受
     3：完成(客户未确认)，
     4：已确认
     */
    public static final String INSTALL_STATUS_NOT_ASSIGN = "0";
    public static final String INSTALL_STATUS_ASSIGNED = "1";
    public static final String INSTALL_STATUS_TASK_DOING = "2";
    public static final String INSTALL_STATUS_FINISHED = "3";
    public static final String INSTALL_STATUS_CONFIRMED= "4";

    /**
     * 配件寄回状态，
     * 1: 无需回寄，其他表示需要寄回
     * 2：未寄回，
     * 3：已寄回（待售后确认）
     * 4：售后已确认
     */
    public static final String PARTS_STATUS_NOT_NEED = "1";
    public static final String PARTS_STATUS_WAIT_FOR_SENDBACK = "2";
    public static final String PARTS_STATUS_ALREADY_SENDBACK = "3";
    public static final String PARTS_STATUS_AFTERSALE_CONFIRMED = "4";

}
