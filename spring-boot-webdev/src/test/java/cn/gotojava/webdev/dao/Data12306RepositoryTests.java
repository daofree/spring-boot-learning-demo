package cn.gotojava.webdev.dao;

import cn.gotojava.webdev.model.Data12306;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Data12306RepositoryTests {

    private static final Logger logger = LoggerFactory.getLogger(Data12306RepositoryTests.class);

    // 注入Data12306实体对象
    @Autowired
    private Data12306Repository data12306Repository;

    // 记录总记录数
    Long count = 0L;

    /**
     * 查询全部记录
     */
    @Test
    public void findAll(){
        // 查询全部记录
        List<Data12306> findAll = data12306Repository.findAll();

        logger.info("============== 查询开始 ================");
        long startTime = System.currentTimeMillis();// 开始时间
        findAll.forEach((obj)->{
            logger.info("data12306 [{}]",obj.toString());
            count++;
        });
        long endTime = System.currentTimeMillis();// 结束时间
        logger.info("============== 查询结束 ================");
        logger.info("总记录数[{}],执行时间:[{}ms]",count,(endTime - startTime));
    }

    /**
     * 新增记录
     *  如果id不存在，则新增该记录
     *  如果id存在，则修改该记录
     */
    @Test
    public void save(){
        Data12306 data = new Data12306();
        data.setId(3L);
        data.setName("ISI");
        data.setUsername("lisi");
        data.setMobile("110");
        data.setPassword("lisiPasswd");
        data.setDataMail("lisi@qq.com");
        data.setEMail("zhangsan@qq.com");
        data.setIdCard("420321199909132334");
        Data12306 save = data12306Repository.save(data);
        logger.debug("新增成功：{}",save);
    }

    /**
     * 根据id删除记录
     */
    @Test
    public void deleteById(){
        try {
            data12306Repository.deleteById(1L);
            logger.info("删除成功");
        } catch (Exception e) {
            logger.error("删除失败，错误消息：{}",e.getMessage());
        }
    }

    /**
     * 根据Id查询一条记录
     */
    @Test
    @Transactional  // 添加事务控制
    public void getOne(){
        Data12306 one = data12306Repository.getOne(3L);
        logger.info(one.toString());
    }
}
