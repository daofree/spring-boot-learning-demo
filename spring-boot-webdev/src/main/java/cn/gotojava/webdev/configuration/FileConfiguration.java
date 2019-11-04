package cn.gotojava.webdev.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileConfiguration {
    @Value("${cn.gotojava.title}") @Getter @Setter private String title;    // 标题
    @Value("${cn.gotojava.descript}") @Getter @Setter private String description;   // 说明
}
