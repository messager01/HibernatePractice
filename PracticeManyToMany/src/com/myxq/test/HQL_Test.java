package com.myxq.test;

import com.myxq.domain.User;
import com.myxq.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class HQL_Test {

    /*
    *   简单查询   HQL
    * */
    @Test
    public void test(){

        Session session = HibernateUtil.openSession();

        Query query = session.createQuery("from User");
        List<User> list = query.list();
        for (User user : list) {
            System.out.println(user);
        }
    }



    /*
    *    排序操作
    * */
    @Test
    public void test1(){
        Session session = HibernateUtil.openSession();
        Query query = session.createQuery("from User order by user_id desc ");
        List<User> list = query.list();
        for (User user : list) {
            System.out.println(user);
        }
    }



    /*
    *  条件查询
    * */

    @Test
    public void test2(){
        Session session = HibernateUtil.openSession();
        Query query = session.createQuery("from User where user_name = :name");
        query.setParameter("name","user1");
        List<User> list = query.list();
        System.out.println(list);

    }


    /*
    *  投影查询   查询部分属性
    * */

    @Test
    public void test3(){
        Session session = HibernateUtil.openSession();
        Query query = session.createQuery("select c.user_name from User c ");
        List<Object> list = query.list();
        System.out.println(list);


        Query query1 = session.createQuery("select c.user_name ,c.user_code from User c ");
        List<Object[]> list1 = query1.list();
        for (Object[] objects : list1) {
            System.out.println(Arrays.toString(objects));    //  打印一个数组
        }

        // 把查询的对象封装成对象                     //  提供一个有参的构造器
        Query query2 = session.createQuery("select new User(c.user_code,c.user_name) from User c ");
        List<User> list2 = query2.list();
        for (User user : list2) {
            System.out.println(user);
        }

    }

}
