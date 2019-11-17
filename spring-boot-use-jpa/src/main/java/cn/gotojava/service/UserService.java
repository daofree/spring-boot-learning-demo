package cn.gotojava.service;

import cn.gotojava.entity.User;

import java.util.List;

/**
 * 用户业务层接口
 */
public interface UserService {

    /**
     * 新增用户信息
     * @return
     */
    User save(User user);

    /**
     * 根据id删除用户信息
     * @return
     */
    void deleteById(int userId);

    /**
     * 更新用户信息
     * @return
     */
    void updateUser(User user);

    /**
     * 查询所有用户信息
     * @return
     */
    List<User> findAll();

    /**
     * 判断用户是否存在
     * @return
     */
    boolean exists(Integer userId);

    /**
     * 根据用户ID查询用户信息
     * @param userId
     * @return
     */
    User getOne(Integer userId);
}
