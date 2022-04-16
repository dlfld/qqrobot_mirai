```text
 ██ ███████    ████████ ████████ ████     ████       ██████     ███████   ██████████
░██░██░░░░██  ██░░░░░░ ░██░░░░░ ░██░██   ██░██      ░█░░░░██   ██░░░░░██ ░░░░░██░░░ 
░██░██    ░██░██       ░██      ░██░░██ ██ ░██      ░█   ░██  ██     ░░██    ░██    
░██░██    ░██░█████████░███████ ░██ ░░███  ░██ █████░██████  ░██      ░██    ░██    
░██░██    ░██░░░░░░░░██░██░░░░  ░██  ░░█   ░██░░░░░ ░█░░░░ ██░██      ░██    ░██    
░██░██    ██        ░██░██      ░██   ░    ░██      ░█    ░██░░██     ██     ░██    
░██░███████   ████████ ░████████░██        ░██      ░███████  ░░███████      ░██    
░░ ░░░░░░░   ░░░░░░░░  ░░░░░░░░ ░░         ░░       ░░░░░░░    ░░░░░░░       ░░     

```
## 基于Java Mirai的QQ机器人框架

### 主要功能
+ 基于mirai实现一键创建QQ机器人（只需要输入账号密码等基本配置即可）
+ 采用插件式的开发方式，模拟Spring Mvc开发逻辑，只需要在类上加@Plugin插件就代表当前类是一个插件类，在插件类方法中加入@Command(command = "测试")注解就能实现一条QQ机器人命令
+ 
~~进出校园打卡~~
~~九价疫苗秒杀提醒~~



### TODO
+ 对mirai发消息逻辑的再一层封装
+ 实现插件的热插拔

### 开发
1. 引入maven依赖
```xml
        <dependency>
            <groupId>io.github.dailinfeng66</groupId>
            <artifactId>miraijava-spring-boot-starter</artifactId>
            <version>0.0.30</version>
        </dependency>
```
2. 下载项目的work_dir 
   
2. 在启动类中加入机器人启动代码
   
   ```java
   @SpringBootApplication
   public class MiraiTestApplication {
       public static void main(String[] args) throws Exception {
           new StartBot().run(new MiraiConfig()
                   .setQq("QQ")
                   .setPassword("QQ密码")
                   .setPluginsDir("com.idse.miraijava.plugins") //插件包的路径，插件包需要自己创建 类似于Spring Mvc中的controller包
                   .setWorkDir("src/main/resources/work_dir")); //work_dir的路径
           SpringApplication.run(MiraiTestApplication.class, args);
       }
   
   }
   ```

4. 在项目中创建插件所在的包，并进行插件添加。
5. 启动项目✅

<a href="https://gitee.com/dlfdd/mirai-java-demo.git">demo项目</a>

里面包含了work_dir工作目录，是一个基本的机器人。
```java
//单个插件只需要这样编写即可
@Plugin
public class TestPlugin {
    @Resource
    BigStudyService bigStudyService;

    @Command(command = "测试")
    public void ceshi(MessageEvent event, String msg) throws IOException {
        bigStudyService.getPaper("母猪的产后护理");
        System.out.println("进来了 收到了消息 测试");
    }
}

```
#### 思想

     一个插件对应的是一个实体类，一个实体类里面可以实现多个命令，每个命令是一个单独的方法
#### 开发方法
+ 所有的插件都写在plugins目录下
+ 在插件类上加入<code>@Plugin</code>注解，表示声明这是一个插件
+ 在命令方法上添加<code>@Command(command = "测试")</code>注解，表示这是一个命令处理方法，command表示的是一个命令内容