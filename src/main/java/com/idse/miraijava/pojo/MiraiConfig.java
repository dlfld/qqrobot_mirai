package com.idse.miraijava.pojo;

import lombok.Data;
import lombok.experimental.Accessors;
import net.mamoe.mirai.utils.BotConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


//@Component
@Accessors(chain = true)
@Data
public class MiraiConfig {
    String qq;
    String password;
    String workDir;
    String pluginsDir;
    // QQ登陆的协议
    BotConfiguration.MiraiProtocol protocol;
}
