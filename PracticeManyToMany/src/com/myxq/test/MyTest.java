package com.myxq.test;

import com.myxq.domain.Role;
import com.myxq.domain.User;
import com.myxq.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class MyTest {

    @Test
    public void test(){

        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();


        //  创建用户

        User user1 = new User();
        user1.setUser_name("user1");

        User user2 = new User();
        user2.setUser_name("user2");

        // 创建角色
        Role role1 = new Role();
        role1.setRole_name("role1");

        Role role2 = new Role();
        role2.setRole_name("role2");

        Role role3 = new Role();
        role3.setRole_name("role3");


        /************************************************************
        *                                                             *
        *           在多对多的情况下   如果要双向维护                  *
         *           则必须有一方放弃外键维护权                        *
         *           否则中间表的就会被插入两次  报错                  *
        *                                                             *
        *                                                             *
        * ***********************************************************/

        //  配置关系       单向维护
        user1.getRoles().add(role1);
        user1.getRoles().add(role2);

        user2.getRoles().add(role2);
        user2.getRoles().add(role3);

        //  保存
          //            没有设置  级联保存  就每个都得手动保存
        session.save(user1);
        session.save(user2);
        session.save(role1);
        session.save(role2);
        session.save(role3);


        transaction.commit();
    }



    /*
    *   关系操作   操作内部的集合
    * */
    @Test
    public void test1(){
        //  需求1： 给用户增加新的角色3

        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();


        User user1 = session.get(User.class, 11L);
        Role role3 = session.get(Role.class, 11L);
        user1.getRoles().add(role3);

        session.save(user1);
        session.save(role3);



        transaction.commit();

    }



    @Test
    public void test2(){
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();

        //需求  把用户2的角色3改成角色1

        User user2 = session.get(User.class, 12L);   //  拿出uer2
        Role role3 = session.get(Role.class, 11L);    //  拿出角色3
        Role role1 = session.get(Role.class, 9L);    //  拿出角色1

        user2.getRoles().remove(role3);  //  移除角色3
        user2.getRoles().add(role1);  // 添加角色1

        transaction.commit();
    }
}

