package com.eservice.api.model.maintain_customer_feedback;

import javax.persistence.*;

@Table(name = "maintain_customer_feedback")
public class MaintainCustomerFeedback {
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
}