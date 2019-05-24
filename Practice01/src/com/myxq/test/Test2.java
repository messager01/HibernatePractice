package com.myxq.test;

import com.myxq.domain.Customer;
import com.myxq.utils.HibernateUtil;
import org.hibernate.CustomEntityDirtinessStrategy;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.usertype.CompositeUserType;
import org.junit.Test;
import sun.security.ec.SunEC;

import javax.crypto.CipherSpi;
import java.io.Serializable;

public class Test2 {
    @Test
    public void test1(){

        /*
        *
        *   测试一级缓存
        *
        * */
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();

        Customer customer = session.get(Customer.class, 3L);
        System.out.println(customer);

        Customer customer2 = session.get(Customer.class, 3L);
        System.out.println(customer2);

        System.out.println(customer == customer2);

        transaction.commit();
    }



    @Test
    public void test2(){

        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();

        Customer customer = new Customer();
        customer.setCust_name("adetokuobo");

        //   会把保存的对象（数据） 在 一级缓存中放一份
        Serializable id = session.save(customer);      //  保存的id

        //  查找插入的记录  并不会发送sql  直接在缓存中拿
        Customer customer1 = session.get(Customer.class, id);
        System.out.println(customer == customer1);
    }
}
