package com.myxq.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.util.HashSet;
import java.util.Set;

@Setter@Getter
public class Customer {

    private Long cust_id;
    private String cust_name;
    private String cust_source;
    private String cust_industry;
    private String cust_level;
    private String cust_phone;
    private String cust_mobile;

    //  一个客户多个联系人
    private Set<Linkman> linkmen = new HashSet<>();


}
