package com.idse.miraijava.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author dailinfeng
 * @Description qq配置类
 * @Date 2022/3/24 19:48
 * @Version 1.0
 */
@Component
@ConfigurationProperties(prefix = "idse.mirai")
@Data
public class MiraiConfig {
    String qq;
    String password;
    String workDir;
    String pluginsDir;
}
