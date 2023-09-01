# miniNews
微头条项目初始化
## 项目依赖Dependencies
- Lombok
- Spring Boot DevTools
- Spring Web
- Spring Configuration Processor
- MySQL Driver
- Apache StringUtils
- javaXb-api
## 数据库
MySQL 8.0
## 技术选型
- SpringBoot 3.1.2
- Spring 6.0.5
- SpringMVC
## 持久层框架
- Mybatis
- Mybatis-plus 3.5.3.1
## 加密方式
- JWT
- MD5
## 项目构建工具
- Gradle
## 日志
- Slf4j
## 异常处理
- 全局异常处理类

## Gradle项目依赖
```implementation 'org.springframework.boot:spring-boot-starter-web'
compileOnly 'org.projectlombok:lombok'
developmentOnly 'org.springframework.boot:spring-boot-devtools'
runtimeOnly 'com.mysql:mysql-connector-j'
annotationProcessor 'org.springframework.boot:spring-boot-configuration-processor'
annotationProcessor 'org.projectlombok:lombok'
testImplementation 'org.springframework.boot:spring-boot-starter-test'
implementation 'com.baomidou:mybatis-plus-boot-starter:3.5.3.1'
implementation 'com.alibaba:fastjson:1.2.47'
implementation 'io.jsonwebtoken:jjwt:0.6.0'
implementation 'org.apache.commons:commons-lang3:3.12.0'
implementation 'javax.xml.bind:jaxb-api:2.3.1'
```
