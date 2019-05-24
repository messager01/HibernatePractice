package com.myxq.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil  {

    public static final SessionFactory sessionFactory;

    // 值加载一次
    static {
        // 加载配置文件
        Configuration configure = new Configuration().configure();
        //  创建session工厂
         sessionFactory = configure.buildSessionFactory();
    }

    public static Session openSession(){
        return sessionFactory.openSession();
    }
}
