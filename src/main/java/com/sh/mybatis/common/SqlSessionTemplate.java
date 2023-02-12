package com.sh.mybatis.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionTemplate {

    /**
     * 1. FactoryBuilder
     * 2. Factory (설정파일필요)
     * 3. SqlSession
     * 
     */
    public static SqlSession getSqlSession() {
        SqlSession session = null;
        String resource = "/mybatis-config.xml";
        
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        
        try {
            InputStream is = Resources.getResourceAsStream(resource);
            SqlSessionFactory factory = builder.build(is);
            session = factory.openSession(false); // auto-commit : false
            
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        
        return session;
    }

}
