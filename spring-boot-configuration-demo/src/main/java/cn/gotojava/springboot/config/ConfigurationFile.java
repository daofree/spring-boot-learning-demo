package cn.gotojava.springboot.config;

import cn.gotojava.springboot.service.BeanService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 声明该类是一个配置类，用于替代Spring配置文件
 */
@Configuration
public class ConfigurationFile {

    /**
     * 将该方法的返回值添加到容器中：容器中这个组件默认id就是方法名
     * @return BeanService
     */
    @Bean
    public BeanService beanService(){
        System.out.println("==========BeanService组件已添加到容器中==========");
        return new BeanService();
    }
}
