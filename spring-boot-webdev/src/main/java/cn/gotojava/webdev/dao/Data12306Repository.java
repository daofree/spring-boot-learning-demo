package cn.gotojava.webdev.dao;

import cn.gotojava.webdev.model.Data12306;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface Data12306Repository extends JpaRepository<Data12306,Long> {

    /**
     * 查询所有记录
     * @return Data12306
     */
    @Override
    List<Data12306> findAll();

    /**
     * 添加/修改记录
     * @param s
     * @param <S>
     * @return
     */
    @Override
    <S extends Data12306> S save(S s);

    /**
     * 根据Id删除记录
     * @param aLong
     */
    @Override
    void deleteById(Long aLong);

    /**
     * 根据id查询一条记录
     * @param aLong
     * @return
     */
    @Override
    Data12306 getOne(Long aLong);
}
