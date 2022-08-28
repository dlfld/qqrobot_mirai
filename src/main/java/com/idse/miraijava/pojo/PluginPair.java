package com.idse.miraijava.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.lang.reflect.Method;

@Data
@Accessors(chain = true)
public class PluginPair {
    Method method;
    Class<?> clazz;
}
