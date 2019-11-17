package cn.gotojava.service.impl;

import cn.gotojava.dao.UserRepository;
import cn.gotojava.entity.User;
import cn.gotojava.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户业务层实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * 新增/修改用户信息
     * @param user
     * @return
     * @throws RuntimeException
     */
    @Override
    public User save(User user) throws RuntimeException{
        User save = userRepository.save(user);
        if (save == null){
            // 用户信息修改失败
            logger.debug("用户新增失败:{}",user.toString());
            throw new RuntimeException("用户新增失败：UserInfo{"+user.toString()+"}");
        }
        return user;
    }

    /**
     * 根据id删除用户信息
     * @param userId
     * @return
     */
    @Override
    public void deleteById(int userId) {
        try {
            userRepository.delete(userId);
            logger.debug("用户删除成功，用户编号：{}",userId);
        } catch (Exception e) {
            logger.error("删除失败，删除ID：{}",userId);
        }
    }

    /**
     * 更新用户信息
     * @param user
     * @throws RuntimeException
     */
    @Override
    public void updateUser(User user) throws RuntimeException{
        User save = userRepository.save(user);
        if (save != null) throw new RuntimeException("用户更新失败：UserInfo{"+user.toString()+"}");
    }

    /**
     * 全部全部用户信息
     * @return
     */
    @Override
    public List<User> findAll() {
        List<User> findAll = userRepository.findAll();
        return findAll;
    }

    /**
     * 根据userId查询用户是否存在
     * @param userId
     * @return
     */
    @Override
    public boolean exists(Integer userId) {
        return userRepository.exists(userId);
    }

    /**
     * 根据用户id查询用户信息
     * @param userId
     * @return
     */
    @Override
    public User getOne(@NotNull Integer userId) {
        User user = userRepository.getOne(userId);
        return user;
    }
}
