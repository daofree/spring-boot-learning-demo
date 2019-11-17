package cn.gotojava.dao;

import cn.gotojava.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 功能：对User的增、删、改、查
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    
}
