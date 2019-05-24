package com.myxq.test;

import com.myxq.domain.Customer;
import com.myxq.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.List;


public class test1 {

    @Test
    public void test1(){

        Session session = HibernateUtil.openSession();

        Customer customer = new Customer();
        customer.setCust_name("myxq2");
        customer.setCust_level("3");
        session.save(customer);

        session.close();



    }

    @Test
    public void test2(){
        Session session = HibernateUtil.openSession();
        // 开启事务
        Transaction transaction = session.beginTransaction();
        Customer customer = session.get(Customer.class, 1L);
        System.out.println(customer);
        customer.setCust_mobile("15872404075");
        session.update(customer);
        // 提交事务
        transaction.commit();
        session.close();
    }

    @Test
    public void test3(){
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();

        Customer customer = session.get(Customer.class, 2L);

        session.delete(customer);
        transaction.commit();
        session.close();


    }

    /*
    *    saveOrUpdate
    * */



    @Test
    public void test4(){
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();

        //查询所有  HQL

        Query query = session.createQuery("from com.myxq.domain.Customer");
        List<Customer> list = query.list();
        for (Customer customer : list) {
            System.out.println(customer);
        }

        transaction.commit();
        session.close();


    }

}
