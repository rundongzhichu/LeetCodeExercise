package com.halform.mybatis.mybatisdemo;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

public class OperationDataByMybatisTests {

    @Test
    public void testGetStudent() {
        SqlSession session = null;
        try {
            // 读取mybatis-config.xml文件
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            // 初始化mybatis，创建SqlSessionFactory类的实例
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            // 创建Session实例
            session = sqlSessionFactory.openSession();
            StudentMapper mapper = session.getMapper(StudentMapper.class);
            Student student = mapper.getStudent("20171509");
            System.out.println("student.getName() = " + student.getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //System.out.println(session);
            if(session!=null)
                session.close();
        }
    }



}
