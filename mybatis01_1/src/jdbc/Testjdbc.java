package jdbc;

import java.security.acl.Permission;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Testjdbc {
	public static void main(String[] args) throws Exception {
			//1_加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			//2_通过驱动管理类获取数据库链接
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8", "root", "root");
			//3_定义sql语句 ?表示占位符
			String sql = "select * from user where id = ?";
			//4_获取预处理的statement
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			//5_设置参数，第一个参数为sql语句中参数的序号（从1开始），第二个参数为设置的参数值
			prepareStatement.setInt(1, 10);
			//6_执行sql,得到结果
			ResultSet resultSet = prepareStatement.executeQuery();
			//7_遍历查询结果
			while(resultSet.next()){
				System.out.println(resultSet.getString("id")+" "+resultSet.getString("username"));
			}	
			System.out.println(resultSet);
			//8_释放资源
			resultSet.close();
			prepareStatement.close();
			connection.close();
			
	}
}
