package com.TropicalFlavor.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.InputStream;

public class MybatisUtil
{
    private  static SqlSessionFactory sqlSessionFactory;
    static
    {
        try
        {
            String resource = "myBatis.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    //通过方法获得sqlsession对象
    //默认方法
    public  static SqlSession getSqlSession()
    {
        return sqlSessionFactory.openSession();
    }
    //自动提交
    public  static SqlSession getSqlSession(boolean autocommit)
    {
        return sqlSessionFactory.openSession(autocommit);
    }

}
