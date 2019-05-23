package com.myxq.test;

import com.myxq.domain.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;


public class test1 {

    @Test
    public void test1(){
        // 加载配置文件
        Configuration configure = new Configuration().configure();
        //  创建session工厂
        SessionFactory sessionFactory = configure.buildSessionFactory();
        //  获取session
        Session session = sessionFactory.openSession();

        Customer customer = new Customer();
        customer.setCust_name("myxq");
        customer.setCust_level("2");
        session.save(customer);

        session.close();
        sessionFactory.close();


    }
}
