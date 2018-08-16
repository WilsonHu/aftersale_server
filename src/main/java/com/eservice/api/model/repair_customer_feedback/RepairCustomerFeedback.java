package com.eservice.api.model.repair_customer_feedback;

import javax.persistence.*;
import java.util.*;

@Table(name = "repair_customer_feedback")
public class RepairCustomerFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 客户给的评分
     */
    @Column(name = "customer_mark")
    private String customerMark;

    /**
     * 客户意见
     */
    @Column(name = "customer_suggestion")
    private String customerSuggestion;

    /**
     * 维修结果。不再由客户判定
     */
//    @Column(name = "customer_repair_result")
//    private String customerRepairResult;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取客户给的评分
     *
     * @return customer_mark - 客户给的评分
     */
    public String getCustomerMark() {
        return customerMark;
    }

    /**
     * 设置客户给的评分
     *
     * @param customerMark 客户给的评分
     */
    public void setCustomerMark(String customerMark) {
        this.customerMark = customerMark;
    }

    /**
     * 获取客户意见
     *
     * @return customer_suggestion - 客户意见
     */
    public String getCustomerSuggestion() {
        return customerSuggestion;
    }

    /**
     * 设置客户意见
     *
     * @param customerSuggestion 客户意见
     */
    public void setCustomerSuggestion(String customerSuggestion) {
        this.customerSuggestion = customerSuggestion;
    }

//    /**
//     * 获取维修结果。
//     *
//     * @return customer_repair_result - 维修结果。
//     */
//    public String getCustomerRepairResult() {
//        return customerRepairResult;
//    }
//
//    /**
//     * 设置维修结果。
//     *
//     * @param customerRepairResult 维修结果。
//     */
//    public void setCustomerRepairResult(String customerRepairResult) {
//        this.customerRepairResult = customerRepairResult;
//    }



    /**
     * 创建时间，也即用户完成评价的时间，维修最后完成时间
     */
    @Column(name = "create_time")
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}