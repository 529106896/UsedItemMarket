package com.TropicalFlavor.util;

import com.TropicalFlavor.dao.UtilsDao;
import org.apache.ibatis.session.SqlSession;

public class SQLUtils
{
    public static String getGID()
    {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UtilsDao utilsDao=sqlSession.getMapper(UtilsDao.class);
        return String.format("%d",utilsDao.selectMaxGID()+1);
    }

    public static String getPID()
    {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UtilsDao utilsDao=sqlSession.getMapper(UtilsDao.class);
        return String.format("%d",utilsDao.selectMaxPID()+1);
    }
}
