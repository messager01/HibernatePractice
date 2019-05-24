package com.myxq.test;

import com.myxq.domain.Customer;
import com.myxq.domain.Linkman;
import com.myxq.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;


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



    //  一对多查询     级联保存更新
    @Test
    public void test2(){

        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();

        Linkman linkman = session.get(Linkman.class, 1L);

        transaction.commit();

        System.out.println(linkman.getCustomer().getCust_name());

    }


    //  级联删除
    @Test
    public void test3(){


        //  默认删除    先去 打断两个表之间的关系    然后再删除记录    默认的并没有级联的删除

        /*
        *   如果要级联的删除   删除哪个就在哪个里面配置
        * */
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();

        Customer customer = session.get(Customer.class, 1L);
        session.delete(customer);

        transaction.commit();

    }



    //  更新操作
    @Test
    public void test4(){
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();

        //  更新
        //  将linkman1 的  客户  改为  customer2
        Linkman linkman1 = session.get(Linkman.class, 1L);
        Customer customer2 = session.get(Customer.class, 2L);

        //  建立关系  多边维护  和 单边维护都行  这里选择双边维护  一般让一方放弃外键的维护权
        linkman1.setCustomer(customer2);
        //  让   customer   放弃外键的维护  那么就得在customer的配置文件中进行配置
        customer2.getLinkmen().add(linkman1);  //    写的是双向维护 但是真正的是单边维护  只有linkman在维护


        //   保存   因为对customer设置了单边保存   保存customer1  即可
        session.save(customer2);
        transaction.commit();



    }
}
