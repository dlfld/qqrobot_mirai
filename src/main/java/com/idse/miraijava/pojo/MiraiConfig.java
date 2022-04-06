package com.idse.miraijava.pojo;

import lombok.Data;
import lombok.experimental.Accessors;
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
}
