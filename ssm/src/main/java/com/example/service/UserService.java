package com.example.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.example.pojo.User;
import com.example.dao.UserDao;

@Service
public class UserService{

    @Resource
    private UserDao userDao;

    public int insert(User pojo){
        return userDao.insert(pojo);
    }

    public int insertSelective(User pojo){
        return userDao.insertSelective(pojo);
    }

    public int insertList(List<User> pojos){
        return userDao.insertList(pojos);
    }

    public int update(User pojo){
        return userDao.update(pojo);
    }
}
