package com.softeem.test;

import com.softeem.bean.User;
import com.softeem.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserTest {
    SqlSession sqlSession = null ;

    @Before
    public void init() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //创建SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
       // sqlSession = sqlSessionFactory.openSession();
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        sqlSession = sqlSessionFactory.openSession(true);
    }

    @Test
    public void insertUserTest(){
        //调用UserMapper接口中的方法，就可以根据UserMapper的全类名匹配元素文件，通过调用的方法名匹配
        //映射文件中的SQL标签，并执行标签中的SQL语句
        UserMapper usermapper = sqlSession.getMapper(UserMapper.class);
        int i = usermapper.insertUser();
        System.out.println("影响行 = " + i);
        //sqlSession.commit();//提交事务
        //sqlSession.rollback();//回滚
        //sqlSession.close();//关闭SqlSession
    }

    @Test
    public void deleteUserTest(){
        UserMapper usermapper = sqlSession.getMapper(UserMapper.class);
        int i = usermapper.deleteUser();
        System.out.println("影响行 = " + i);
    }

    @Test
    public void updateUserTest(){
        UserMapper usermapper = sqlSession.getMapper(UserMapper.class);
        int i = usermapper.updateUser();
        System.out.println("影响行 = " + i);
    }

    @Test
    public void getUserByIdTest(){
        UserMapper usermapper = sqlSession.getMapper(UserMapper.class);
        User user = usermapper.getUserById();
        System.out.println(user);
    }

    @Test
    public void getUserListTest(){
        UserMapper usermapper = sqlSession.getMapper(UserMapper.class);
        System.out.println("usermapper对象的内存地址:" + usermapper);
        List<User> userList = usermapper.getUserList();
        //这样打印出User对象的全部属性
        userList.forEach(System.out::println);
        System.out.println("---------------------------------------------------------");
        for (User user : userList) {
            System.out.println(user);
        }
    }

}
