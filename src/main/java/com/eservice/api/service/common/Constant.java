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
}
