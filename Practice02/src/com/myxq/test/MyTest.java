package com.myxq.test;

import com.myxq.domain.Customer;
import com.myxq.domain.Linkman;
import com.myxq.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import javax.persistence.Table;

public class MyTest {


    //   一对多插入
    @Test
    public void test1(){
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();

        Customer customer1 = new Customer();
        customer1.setCust_name("customer1");

        Customer customer2 = new Customer();
        customer2.setCust_name("customer2");

        Customer customer3 = new Customer();
        customer3.setCust_name("customer3");


        Linkman linkman1 = new Linkman();
        linkman1.setLink_name("linkman1");

        Linkman linkman2 = new Linkman();
        linkman2.setLink_name("linkman2");

        Linkman linkman3 = new Linkman();
        linkman3.setLink_name("linkman3");


        /*  配置关系
        *
        *    双向维护
        *
        * */

        // 配置一边关系
        customer1.getLinkmen().add(linkman1);
        customer1.getLinkmen().add(linkman2);
        customer2.getLinkmen().add(linkman3);
        //  配置另一边关系
        linkman1.setCustomer(customer1);
        linkman2.setCustomer(customer1);
        linkman3.setCustomer(customer2);


        ///   保存   凡是创建的对象都要保存
        session.save(customer1);
        session.save(customer2);

        /*
        *
        * 在配置文件中  开起了级联保存  那么就可以只保存一边  他的关联对象会自动保存
        * 保存的是哪个类的对象  就在哪个类的属性文件中配置
        * 例如 ，保存的是customer对象   关联linkman   就在customer.hbm.xml中配置  级联保存
        *
        *    <set name="linkmen" cascade="save-update">   cascade默认是关闭的
         *
        *
        * */

       /* session.save(linkman1);
        session.save(linkman2);
        session.save(linkman3);*/

        transaction.commit();
    }



    //  一对多查询
    @Test
    public void test2(){

        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();

        Linkman linkman = session.get(Linkman.class, 1L);

        transaction.commit();

        System.out.println(linkman.getCustomer().getCust_name());

    }
}
