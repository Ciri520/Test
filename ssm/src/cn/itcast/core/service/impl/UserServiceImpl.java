package cn.itcast.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.core.dao.UserDao;
import cn.itcast.core.pojo.User;
import cn.itcast.core.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Override
	public void save(User user) {
		userDao.save(user);
	}
	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

}
