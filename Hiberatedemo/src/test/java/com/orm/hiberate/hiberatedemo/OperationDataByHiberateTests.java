package com.orm.hiberate.hiberatedemo;

import lombok.SneakyThrows;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OperationDataByHiberateTests {

    @SneakyThrows
    @Test
    public void testQueryUsers() {
        SessionFactory sessionFactory=null;
//        //Configuration类负责管理Hibernate的配置信息
//        Configuration configuration=new Configuration().configure();
//        //Hibernate4之后新增ServiceRegistry接口，所有基于Hibernate 的配置都必须统一向这个ServiceRegistry注册后才能生效
//        ServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder()
//                .applySettings(configuration.getProperties())
//                .build();

        Configuration configuration = new Configuration();
        //configuration.configure("hibernate.cfg.xml");
        // 创建工厂
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        // creating session object
        Session session = sessionFactory.openSession();
        User user= new User();
        user.setAge(41);
        user.setName("张全蛋");
        session.beginTransaction();
        session.save(user); //新增
        session.update(user);//修改
//        session.delete(user);//删除
        session.getTransaction().commit();
        String sql = "select id,sno,name,sex,age,dep_no from user";
        Query<User> query = session.createNativeQuery(sql,User.class);
        //session.get("name");
        // 3）获得结果
        List<User> list = query.list();
        System.out.println(list.size());
        for(User u : list){
            System.out.println(u.getName()+":"+u.getAge());
        }
    }

}
