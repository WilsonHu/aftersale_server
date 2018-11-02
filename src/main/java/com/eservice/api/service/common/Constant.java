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
    public static final String FILE_TYPE_REPAIR_REQUEST_VOICE = "FILE_TYPE_REPAIR_REQUEST_VOICE";
    //报修的图片：
    public static final String FILE_TYPE_REPAIR_REQUEST_IMAGE = "FILE_TYPE_REPAIR_REQUEST_IMAGE";
    //维修的图片：
    public static final String FILE_TYPE_REPAIR_ACTUAL_IMAGE = "FILE_TYPE_REPAIR_ACTUAL_IMAGE";
    //配件寄回快递图片
    public static final String FILE_TYPE_PARTS_SENDBACK_IMAGE = "FILE_TYPE_PARTS_SENDBACK_IMAGE";
    //报修时提供的铭牌号图片（老机器）
    public static final String FILE_TYPE_REPAIR_REQUEST_NAMEPLATE_IMAGE = "FILE_TYPE_REPAIR_REQUEST_NAMEPLATE_IMAGE";
    //维修完成时提交的照片
    public static final String FILE_TYPE_REPAIR_ACTUAL_INFO_IMAGE = "FILE_TYPE_REPAIR_ACTUAL_INFO_IMAGE";


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
     * 0: 报修进行中（报修过程中，文件上传没完成等情况）
     * 1：未派单，
     * 2：已派单（但未接单）,
     * 3：已接受（进行中，可以再派），
     * 4：失败，(可以 再派，代理商可以 转派)
     * 5：已再派，
     * 6：已转派，
     * 7：已完成(客户未确认)，
     * 8. 客户确认（维修成功）。转派后，前面的维修记录要保留，但是客户只需要看到成功的最后那次记录。
     */
    public static final String REPAIR_STATUS_IN_REQUESTING = "0";
    public static final String REPAIR_STATUS_UNSIGNED_TO_REPAIRER = "1";
    public static final String REPAIR_STATUS_SIGNED_TO_REPAIRER = "2";
    public static final String REPAIR_STATUS_REPAIRER_ACCEPTED = "3";
    public static final String REPAIR_STATUS_REPAIR_NG = "4";
    public static final String REPAIR_STATUS_REPAIRER_REASSIGN = "5";
    public static final String REPAIR_STATUS_REPAIRER_FORWARD = "6";
    public static final String REPAIR_STATUS_REPAIR_OK = "7";
    public static final String REPAIR_STATUS_REPAIR_CUSTOMER_CONFIRMED = "8";

    public static final int REPAIR_IS_FORWARD_NO = 0;
    public static final int REPAIR_IS_FORWARD_YES = 1;

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

    /**
     * 机器类型
     * 0：表示生产部数据库出来的机器
     * 1：表示客户报的老机器（生产部新系统之前生产的机器，不在生产部数据库）
     * 2：表示售后主动加入的机器，比如非信胜的机器。
     */
    public static final String MACHINE_TYPE_FROM_PRODUCTION_DEPARTMENT = "0";
    public static final String MACHINE_TYPE_OLD = "1";
    public static final String MACHINE_TYPE_AFTERSALES_ADD = "2";

    /**
     * 老机器审核是否通过，空表示非老机器，0:未通过，1：通过 3: 未做判定
     */
    public static final String OLD_MACHINE_CHECK_NO_PASS = "0";
    public static final String OLD_MACHINE_CHECK_PASS = "1";
    public static final String OLD_MACHINE_CHECK_UNKNOWN= "3";

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

    /**
     * 微信code2Session返回值
     * -1	系统繁忙，此时请开发者稍候再试
     * 0	请求成功
     * 40029	code 无效
     * 45011	频率限制，每个用户每分钟100次
     */
    public static final String WX_ERRORCODE_BUSY = "-1";
    public static final String WX_ERRORCODE_SUCCESS = "0";
    public static final String WX_ERRORCODE_INVALID_JS_CODE = "40029";
    public static final String WX_ERRORCODE_FREQ_LIMIT = "45011";

    /**
     * 消息号
     * 1: 绑定机器时 通知客户
     * 2：安装任务派单时 通知员工
     * 3：保养装任务派单时 通知员工
     * 4：维修任务派单时 通知员工
     * 5：安装任务接单时 通知客户
     * 6：保养任务接单时 通知客户
     * 7：维修任务接单时 通知客户
     * 8: 安装完成后待评价时 通知客户
     * 9: 保养完成后待评价时 通知客户
     * 10: 维修完成后待评价时 通知客户
     */
    public static final String WX_MSG_1_MACHINE_BIND_TO_CUSTOMER = "WX_TEMPLATE_NO1_MACHINE_BIND_TO_CUSTOMER";
    public static final String WX_MSG_2_INSTALL_TASK_TO_EMPLOYEE = "WX_TEMPLATE_NO2_INSTALL_TASK_TO_EMPLOYEE";
    public static final String WX_MSG_3_MAINTAIN_TASK_TO_EMPLOYEE = "WX_TEMPLATE_NO3_MAINTAIN_TASK_TO_EMPLOYEE";
    public static final String WX_MSG_4_REPAIR_TASK_TO_EMPLOYEE = "WX_TEMPLATE_NO4_REPAIR_TASK_TO_EMPLOYEE";
    public static final String WX_MSG_5_INSTALLER_ACCEPT_TO_CUSTOMER = "WX_TEMPLATE_NO5_INSTALLER_ACCEPT_TO_CUSTOMER";
    public static final String WX_MSG_6_MAINTAIN_ACCEPT_TO_CUSTOMER = "WX_TEMPLATE_NO6_MAINTAIN_ACCEPT_TO_CUSTOMER";
    public static final String WX_MSG_7_REPAIR_ACCEPT_TO_CUSTOMER = "WX_TEMPLATE_NO7_REPAIR_ACCEPT_TO_CUSTOMER";
    public static final String WX_MSG_8_INSTALL_DONE_TO_CUSTOMER = "WX_TEMPLATE_NO8_INSTALL_DONE_TO_CUSTOMER";
    public static final String WX_MSG_9_MAINTAIN_DONE_TO_CUSTOMER = "WX_TEMPLATE_NO9_MAINTAIN_DONE_TO_CUSTOMER";
    public static final String WX_MSG_10_REPAIR_DONE_TO_CUSTOMER = "WX_TEMPLATE_NO10_REPAIR_DONE_TO_CUSTOMER";

    /**
     * 模板号
     * 1：预定成功通知
     * 2：任务分派提醒
     * 3：订单受理成功通知
     * 4：任务已完成通知
     */
    public static final String WX_TEMPLATE_1_BOOK_SUCCESS = "fbg_nVLRWSmcq1Tp5G2-L9WaWrc-ggP-rIsC2Dir42s";
    public static final String WX_TEMPLATE_2_TASK_COMMING = "Zybl6os4A5T9FtToXSAGoN2DN57j8X-oHhexhfo8XU4";
    public static final String WX_TEMPLATE_3_TASK_ACCEPTED = "zhOeyr_BSquYLVhrmeLpHSibZdXpKrSP9ycUK6daEt8";
    public static final String WX_TEMPLATE_4_TASK_DONE = "shuxRtXLw_Jqhz6SIKeFg9A1CJ6eBspQFdYS5kUkUAQ";

    public static final String WX_MSG_SEND_SUCCESS = "模板发送成功！";
    public static final String WX_MSG_1 = "你订购的机器已经就绪，预定出厂日期 FactoryDate";

    /**
     * 员工端小程序只能用员工账号登陆，客户端小程序只能用客户账号登陆
     * 3：普通员工：安装，保养，维修（包括了信胜和客户的）
     * 5：客户
     * 6：客户联系人
     */
    public static final int ROLE_ID_EMPLOYEE = 3;
    public static final int ROLE_ID_CUSTOMER = 5;
    public static final int ROLE_ID_CUSTOMER_CONTACT = 6;

    /**
     * 0:表示员工端，1表示客户端，
     */
    public static final String CALLER_IS_YUANGONGDUAN = "0";
    public static final String CALLER_IS_KEHUDUAN = "1";

}
