package mybatistest;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import domain.User;

public class Mybatis_First {
	//会话工厂
	private SqlSessionFactory sqlSessionFactory;
	
	@Before
	public void init() throws Exception{
		//配置文件(核心配置文件)
		String resource = "SqlMapConfig.xml";
		//读取配置文件
		InputStream resourceAsStream = Resources.getResourceAsStream(resource);
		//使用SqlSessionFactoryBuilder从xml配置文件中创建SqlSessionFactory
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
	}
	
	// 根据 id查询用户信息
	@Test
	public void testFindUserById(){
		// 创建数据库会话实例sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 查询单个记录，根据用户id查询用户信息(当id唯一是 namespace可以省略)
		User user = sqlSession.selectOne("test.findUserById",10);
		//输出用户信息
		System.out.println(user);
		//关闭资源
		sqlSession.close();
	}
	
	//根据名称模糊查询
	@Test
	public void testFindUserByUsername() throws Exception{
		//创建会话实例
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//查询单个记录,根据名称模糊查询
		List<User> list = sqlSession.selectList("test.findUserByUsername","张");
		System.out.println(list);
		sqlSession.close();
	}
	
	//根据id删除用户
	@Test
	public void deleteUserById() throws Exception{
		//创建会话实例
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//查询单个记录,根据名称模糊查询
		sqlSession.selectList("test.deleteUserById",27);
		sqlSession.commit();
		sqlSession.close();
	}
	
	//添加用户
	@Test
	public void insertUser(){
		//数据库会话实例
		SqlSession sqlSession = null;
		try {
			//创建会话实例sqlsession
			sqlSession = sqlSessionFactory.openSession();
			//添加用户
			User user = new User();
			user.setUsername("张小明");
			user.setBirthday(new Date());
			user.setAddress("北京");
			user.setSex("1");
			System.out.println("1111111111111111111   :"+user.getId());
			sqlSession.insert("test.insertUser",user);
			System.out.println("21222222222222222222   :"+user.getId());
			//提交事务
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
	}
	
	//跟新用户
	@Test
	public void updateUser(){
		SqlSession sqlSession =null;
		try {
			//获取session
			sqlSession = sqlSessionFactory.openSession();
			User user = new User();
			user.setId(30);
			user.setUsername("张晓明");
			user.setBirthday(new Date());
			user.setAddress("上海");
			user.setSex("0");
			sqlSession.update("updateUser", user);
			//提交事务
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
	}
	
	@Test
	public void  testGetUserById() throws Exception{
		UserDao userDao = new UserDaoImpl(sqlSessionFactory);
		User user = userDao.getUserById(30);
		System.out.println(user);
	}
	
}
