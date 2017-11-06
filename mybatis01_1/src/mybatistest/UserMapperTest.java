package mybatistest;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import domain.User;
import junit.framework.TestCase;
import mapper.UserMapper;

public class UserMapperTest extends TestCase{
	//会话工厂
	
	private  SqlSessionFactory sqlSessionFactory;
	/*@Before
	public void init() throws Exception{
		//配置文件(核心配置文件)
		String resource = "SqlMapConfig.xml";
		//读取配置文件
		InputStream resourceAsStream = Resources.getResourceAsStream(resource);
		//使用SqlSessionFactoryBuilder从xml配置文件中创建SqlSessionFactory
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
	}*/

	@Before
	protected void setUp() throws Exception {
		//mybatis配置文件
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//使用SqlSessionFactoryBuilder创建sessionFactory
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	
	@Test
	 public void aFindUserById() throws Exception {
//		 SqlSession sqlSession = sqlSessionFactory.openSession();
//		 UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//		 User user = mapper.findUserById(10);
//		 System.out.println(user);
//		 sqlSession.close();
	
		//获取session
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//获取mapper接口的代理对象
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		//调用代理对象方法
		User user = mapper.findUserById(10);
		System.out.println(user);
		//关闭session
		sqlSession.close();
		}
	 
	@Test
	public void testFindUserById() throws Exception {
		 SqlSession sqlSession = sqlSessionFactory.openSession();
		 UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		 User user = mapper.findUserById(10);
		 System.out.println(user);
		 sqlSession.close();
	}
}

