package cn.gotojava.springboot.util;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * YamlPropertySourceFactory
 *  用于重写PropertySourceFactory接口，实现@PropertySource注解读取自定义yaml文件
 */
public class YamlPropertySourceFactory implements PropertySourceFactory {

    @Override
    public PropertySource<?> createPropertySource(String sourceName, EncodedResource resource) throws IOException {
        Properties propertiesFromYaml = loadYaml(resource);
        if(StringUtils.isBlank(sourceName)){
            sourceName = resource.getResource().getFilename();;
        }
        return new PropertiesPropertySource(sourceName, propertiesFromYaml);
    }

    private Properties loadYaml(EncodedResource resource) throws FileNotFoundException {
        try {
            YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
            factory.setResources(resource.getResource());
            factory.afterPropertiesSet();
            return factory.getObject();
        } catch (IllegalStateException e) {
            // for ignoreResourceNotFound
            Throwable cause = e.getCause();
            if (cause instanceof FileNotFoundException)
                throw (FileNotFoundException) e.getCause();
            throw e;
        }
    }
}
