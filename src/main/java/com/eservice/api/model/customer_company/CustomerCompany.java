package com.eservice.api.model.customer_company;

import javax.persistence.*;

@Table(name = "customer_company")
public class CustomerCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "customer_company_name")
    private String customerCompanyName;

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
     * @return customer_company_name
     */
    public String getCustomerCompanyName() {
        return customerCompanyName;
    }

    /**
     * @param customerCompanyName
     */
    public void setCustomerCompanyName(String customerCompanyName) {
        this.customerCompanyName = customerCompanyName;
    }
}