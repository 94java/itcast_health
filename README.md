>本项目可在哔哩哔哩搜索传智健康查看黑马官方配套视频教程
>
>项目bug已修复，测试完美运行
>
>期待大家的start~~


## 项目概述

### 项目介绍

传智健康管理系统是一款应用于健康管理机构的业务系统，实现健康管理机构工作内容可视化、会员管理专业化、健康评估数字化、健康干预流程化、知识库集成化，从而提高健康管理师的工作效率，加强与会员间的互动，增强管理者对健康管理机构运营情况的了解。

### 原型展示

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/2276270350.png)

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/3614108217.png)

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/1620230402.png)

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/4240949202.png)

### 技术架构

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/4190408998.png)

### 功能架构

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/914807176.png)

### 软件开发流程

软件开发一般会经历如下几个阶段，整个过程是顺序展开，所以通常称为瀑布模型。

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/1622511015.png)

## 环境搭建

### 项目结构

本项目采用maven分模块开发方式，即对整个项目拆分为几个maven工程，每个maven工程存放特定的一类代码，具体如下：

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/4283792343.png)

**各模块职责定位**

- health_parent：父工程，打包方式为pom，统一锁定依赖的版本，同时聚合其他子模块便于统一执行maven命令
- health_common：通用模块，打包方式为jar，存放项目中使用到的一些工具类、实体类、返回结果和常量类
- health_interface：打包方式为jar，存放服务接口
- health_service_provider：Dubbo服务模块，打包方式为war，存放服务实现类、Dao接口、Mapper映射文件等，作为服务提供方，需要部署到tomcat运行
- health_backend：传智健康管理后台，打包方式为war，作为Dubbo服务消费方，存放Controller、HTML页面、js、css、spring配置文件等，需要部署到tomcat运行
- health_mobile：移动端前台，打包方式为war，作为Dubbo服务消费方，存放Controller、HTML页面、js、css、spring配置文件等，需要部署到tomcat运行

### maven项目搭建

通过前面的项目功能架构图可以知道本项目分为传智健康管理后台和传智健康前台（微信端）

#### health_parent

创建health_parent，父工程，打包方式为pom，用于统一管理依赖版本

**pom.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>top.hellocode</groupId>
    <artifactId>health_parent</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>pom</packaging>
    <!-- 集中定义依赖版本号 -->
    <properties>
        <junit.version>4.12</junit.version>
        <spring.version>5.0.5.RELEASE</spring.version>
        <pagehelper.version>4.1.4</pagehelper.version>
        <servlet-api.version>2.5</servlet-api.version>
        <dubbo.version>2.6.0</dubbo.version>
        <zookeeper.version>3.4.7</zookeeper.version>
        <zkclient.version>0.1</zkclient.version>
        <mybatis.version>3.4.5</mybatis.version>
        <mybatis.spring.version>1.3.1</mybatis.spring.version>
        <mybatis.paginator.version>1.2.15</mybatis.paginator.version>
        <mysql.version>5.1.32</mysql.version>
        <druid.version>1.0.9</druid.version>
        <commons-fileupload.version>1.3.1</commons-fileupload.version>
        <spring.security.version>5.0.5.RELEASE</spring.security.version>
        <poi.version>3.14</poi.version>
        <jedis.version>2.9.0</jedis.version>
        <quartz.version>2.2.1</quartz.version>
    </properties>
    <!-- 依赖管理标签  必须加 -->
    <dependencyManagement>
        <dependencies>
            <!-- Spring -->
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-context</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-beans</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-web</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-webmvc</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-jdbc</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-aspects</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-jms</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-context-support</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-test</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <!-- dubbo相关 -->
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>dubbo</artifactId>
                <version>${dubbo.version}</version>
            </dependency>
            <dependency>
                <groupId>org.apache.zookeeper</groupId>
                <artifactId>zookeeper</artifactId>
                <version>${zookeeper.version}</version>
            </dependency>
            <dependency>
                <groupId>com.github.sgroschupf</groupId>
                <artifactId>zkclient</artifactId>
                <version>${zkclient.version}</version>
            </dependency>
            <dependency>
                <groupId>junit</groupId>
                <artifactId>junit</artifactId>
                <version>4.12</version>
            </dependency>
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>fastjson</artifactId>
                <version>1.2.47</version>
            </dependency>
            <dependency>
                <groupId>javassist</groupId>
                <artifactId>javassist</artifactId>
                <version>3.12.1.GA</version>
            </dependency>
            <dependency>
                <groupId>commons-codec</groupId>
                <artifactId>commons-codec</artifactId>
                <version>1.10</version>
            </dependency>
            <dependency>
                <groupId>com.github.pagehelper</groupId>
                <artifactId>pagehelper</artifactId>
                <version>${pagehelper.version}</version>
            </dependency>
            <!-- Mybatis -->
            <dependency>
                <groupId>org.mybatis</groupId>
                <artifactId>mybatis</artifactId>
                <version>${mybatis.version}</version>
            </dependency>
            <dependency>
                <groupId>org.mybatis</groupId>
                <artifactId>mybatis-spring</artifactId>
                <version>${mybatis.spring.version}</version>
            </dependency>
            <dependency>
                <groupId>com.github.miemiedev</groupId>
                <artifactId>mybatis-paginator</artifactId>
                <version>${mybatis.paginator.version}</version>
            </dependency>
            <!-- MySql -->
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>${mysql.version}</version>
            </dependency>
            <!-- 连接池 -->
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>druid</artifactId>
                <version>${druid.version}</version>
            </dependency>
            <!-- 文件上传组件 -->
            <dependency>
                <groupId>commons-fileupload</groupId>
                <artifactId>commons-fileupload</artifactId>
                <version>${commons-fileupload.version}</version>
            </dependency>
            <dependency>
                <groupId>org.quartz-scheduler</groupId>
                <artifactId>quartz</artifactId>
                <version>${quartz.version}</version>
            </dependency>
            <dependency>
                <groupId>org.quartz-scheduler</groupId>
                <artifactId>quartz-jobs</artifactId>
                <version>${quartz.version}</version>
            </dependency>
            <dependency>
                <groupId>com.sun.jersey</groupId>
                <artifactId>jersey-client</artifactId>
                <version>1.18.1</version>
            </dependency>
            <dependency>
                <groupId>com.qiniu</groupId>
                <artifactId>qiniu-java-sdk</artifactId>
                <version>7.2.0</version>
            </dependency>
            <!--POI报表-->
            <dependency>
                <groupId>org.apache.poi</groupId>
                <artifactId>poi</artifactId>
                <version>${poi.version}</version>
            </dependency>
            <dependency>
                <groupId>org.apache.poi</groupId>
                <artifactId>poi-ooxml</artifactId>
                <version>${poi.version}</version>
            </dependency>
            <dependency>
                <groupId>redis.clients</groupId>
                <artifactId>jedis</artifactId>
                <version>${jedis.version}</version>
            </dependency>
            <!-- 安全框架 -->
            <dependency>
                <groupId>org.springframework.security</groupId>
                <artifactId>spring-security-web</artifactId>
                <version>${spring.security.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework.security</groupId>
                <artifactId>spring-security-config</artifactId>
                <version>${spring.security.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework.security</groupId>
                <artifactId>spring-security-taglibs</artifactId>
                <version>${spring.security.version}</version>
            </dependency>
            <dependency>
                <groupId>com.github.penggle</groupId>
                <artifactId>kaptcha</artifactId>
                <version>2.3.2</version>
                <exclusions>
                    <exclusion>
                        <groupId>javax.servlet</groupId>
                        <artifactId>javax.servlet-api</artifactId>
                    </exclusion>
                </exclusions>
            </dependency>
            <dependency>
                <groupId>dom4j</groupId>
                <artifactId>dom4j</artifactId>
                <version>1.6.1</version>
            </dependency>
            <dependency>
                <groupId>xml-apis</groupId>
                <artifactId>xml-apis</artifactId>
                <version>1.4.01</version>
            </dependency>
        </dependencies>
    </dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>${servlet-api.version}</version>
            <scope>provided</scope>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <!-- java编译插件 -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.2</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

#### health_common

创建health_common，子工程，打包方式为jar，存放通用组件，例如工具类、实体类等

**pom.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>health_parent</artifactId>
        <groupId>top.hellocode</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <artifactId>health_common</artifactId>
    <packaging>jar</packaging>
    <dependencies>
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper</artifactId>
        </dependency>
        <!-- Mybatis -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
        </dependency>
        <dependency>
            <groupId>com.github.miemiedev</groupId>
            <artifactId>mybatis-paginator</artifactId>
        </dependency>
        <!-- MySql -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <!-- 连接池 -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
        </dependency>
        <dependency>
            <groupId>commons-fileupload</groupId>
            <artifactId>commons-fileupload</artifactId>
        </dependency>
        <!-- Spring -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aspects</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jms</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context-support</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
        </dependency>
        <!-- dubbo相关 -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>dubbo</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.zookeeper</groupId>
            <artifactId>zookeeper</artifactId>
        </dependency>
        <dependency>
            <groupId>com.github.sgroschupf</groupId>
            <artifactId>zkclient</artifactId>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
        </dependency>
        <dependency>
            <groupId>javassist</groupId>
            <artifactId>javassist</artifactId>
        </dependency>
        <dependency>
            <groupId>commons-codec</groupId>
            <artifactId>commons-codec</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi</artifactId>
        </dependency>
        <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
        </dependency>
        <dependency>
            <groupId>com.qiniu</groupId>
            <artifactId>qiniu-java-sdk</artifactId>
        </dependency>
        <dependency>
            <groupId>com.sun.jersey</groupId>
            <artifactId>jersey-client</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi-ooxml</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-config</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-taglibs</artifactId>
        </dependency>
    </dependencies>
</project>
```

#### health_interface

创建health_interface，子工程，打包方式为jar，存放服务接口

**pom.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                             http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>health_parent</artifactId>
        <groupId>top.hellocode</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <artifactId>health_interface</artifactId>
    <packaging>jar</packaging>
    <dependencies>
        <dependency>
            <groupId>top.hellocode</groupId>
            <artifactId>health_common</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>
</project>
```

#### health_service_provider

创建health_service_provider，子工程，打包方式为war，作为服务单独部署，存放服务类、Dao接口和Mapper映射文件等

**pom.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                            http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <groupId>top.hellocode</groupId>
        <artifactId>health_parent</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <artifactId>health_service_provider</artifactId>
    <packaging>war</packaging>
    <dependencies>
        <dependency>
            <groupId>top.hellocode</groupId>
            <artifactId>health_interface</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.tomcat.maven</groupId>
                <artifactId>tomcat7-maven-plugin</artifactId>
                <configuration>
                    <!-- 指定端口 -->
                    <port>81</port>
                    <!-- 请求路径 -->
                    <path>/</path>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

log4j.properties

```properties
### direct log messages to stdout ###
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.Target=System.err
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d{ABSOLUTE} %5p %c{1}:%L - %m%n

### direct messages to file mylog.log ###
log4j.appender.file=org.apache.log4j.FileAppender
log4j.appender.file.File=c:\\mylog.log
log4j.appender.file.layout=org.apache.log4j.PatternLayout
log4j.appender.file.layout.ConversionPattern=%d{ABSOLUTE} %5p %c{1}:%L - %m%n

### set log levels - for more verbose logging change 'info' to 'debug' ###

log4j.rootLogger=debug, stdout
```

SqlMapConfig.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <plugins>
        <!-- com.github.pagehelper 为 PageHelper 类所在包名 -->
        <plugin interceptor="com.github.pagehelper.PageHelper">
            <!-- 设置数据库类型 Oracle,Mysql,MariaDB,SQLite,Hsqldb,PostgreSQL 六种数据库-->
            <property name="dialect" value="mysql"/>
        </plugin>
    </plugins>
</configuration>
```

spring-dao.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:context="http://www.springframework.org/schema/context"
    xmlns:p="http://www.springframework.org/schema/p"
    xmlns:aop="http://www.springframework.org/schema/aop"
    xmlns:tx="http://www.springframework.org/schema/tx"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
                            http://www.springframework.org/schema/beans/spring-beans-4.2.xsd
                            http://www.springframework.org/schema/context
                            http://www.springframework.org/schema/context/spring-context.xsd
                            http://www.springframework.org/schema/aop
                            http://www.springframework.org/schema/aop/spring-aop.xsd
                            http://www.springframework.org/schema/tx
                            http://www.springframework.org/schema/tx/spring-tx.xsd
                            http://www.springframework.org/schema/util
                            http://www.springframework.org/schema/util/spring-util.xsd">

    <!--数据源-->
    <bean id="dataSource" 
          class="com.alibaba.druid.pool.DruidDataSource" destroy-method="close">
        <property name="username" value="root" />
        <property name="password" value="root" />
        <property name="driverClassName" value="com.mysql.jdbc.Driver" />
        <property name="url" value="jdbc:mysql://localhost:3306/health" />
    </bean>
    <!--spring和mybatis整合的工厂bean-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource" />
        <property name="configLocation" value="classpath:SqlMapConfig.xml" />
    </bean>
    <!--批量扫描接口生成代理对象-->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <!--指定接口所在的包-->
        <property name="basePackage" value="top.hellocode.dao" />
    </bean>
</beans>
```

**spring-tx.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                        http://www.springframework.org/schema/beans/spring-beans.xsd
                        http://www.springframework.org/schema/mvc
                        http://www.springframework.org/schema/mvc/spring-mvc.xsd
                        http://www.springframework.org/schema/tx
                        http://www.springframework.org/schema/tx/spring-tx.xsd
                        http://www.springframework.org/schema/context
                        http://www.springframework.org/schema/context/spring-context.xsd">
    <!-- 事务管理器  -->
    <bean id="transactionManager" 
          class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>
    <!--
        开启事务控制的注解支持
        注意：此处必须加入proxy-target-class="true"，
              需要进行事务控制，会由Spring框架产生代理对象，
              Dubbo需要将Service发布为服务，要求必须使用cglib创建代理对象。
    -->
    <tx:annotation-driven transaction-manager="transactionManager" 
                          proxy-target-class="true"/>
</beans>
```

**spring-service.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                            http://www.springframework.org/schema/beans/spring-beans.xsd
                            http://www.springframework.org/schema/mvc
                            http://www.springframework.org/schema/mvc/spring-mvc.xsd
                            http://code.alibabatech.com/schema/dubbo
                            http://code.alibabatech.com/schema/dubbo/dubbo.xsd
                            http://www.springframework.org/schema/context
                            http://www.springframework.org/schema/context/spring-context.xsd">
    <!-- 指定应用名称 -->
    <dubbo:application name="health_service_provider"/>
    <!--指定暴露服务的端口，如果不指定默认为20880-->
    <dubbo:protocol name="dubbo" port="20887"/>
    <!--指定服务注册中心地址-->
    <dubbo:registry address="zookeeper://127.0.0.1:2181"/>
    <!--批量扫描，发布服务-->
    <dubbo:annotation package="top.hellocode.service"/>
</beans>
```

**web.xml**

```xml
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >
<web-app>
  <display-name>Archetype Created Web Application</display-name>
  <!-- 加载spring容器 -->
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath*:applicationContext*.xml</param-value>
  </context-param>
  <listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>
</web-app>
```

#### health_backend

创建health_backend，子工程，打包方式为war，单独部署，存放Controller、页面等

**pom.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                            http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>health_parent</artifactId>
        <groupId>top.hellocode</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <artifactId>health_backend</artifactId>
    <packaging>war</packaging>
    <dependencies>
        <dependency>
            <groupId>top.hellocode</groupId>
            <artifactId>health_interface</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.tomcat.maven</groupId>
                <artifactId>tomcat7-maven-plugin</artifactId>
                <configuration>
                    <!-- 指定端口 -->
                    <port>82</port>
                    <!-- 请求路径 -->
                    <path>/</path>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

**springmvc.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
    xmlns:context="http://www.springframework.org/schema/context"
    xmlns:dubbo="http://code.alibabatech.com/schema/dubbo" 
    xmlns:mvc="http://www.springframework.org/schema/mvc"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
                        http://www.springframework.org/schema/beans/spring-beans.xsd
                        http://www.springframework.org/schema/mvc
                        http://www.springframework.org/schema/mvc/spring-mvc.xsd
                        http://code.alibabatech.com/schema/dubbo
                        http://code.alibabatech.com/schema/dubbo/dubbo.xsd
                        http://www.springframework.org/schema/context
                        http://www.springframework.org/schema/context/spring-context.xsd">
    <mvc:annotation-driven>
      <mvc:message-converters register-defaults="true">
        <bean class="com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter">
          <property name="supportedMediaTypes" value="application/json"/>
          <property name="features">
            <list>
              <value>WriteMapNullValue</value>
              <value>WriteDateUseDateFormat</value>
            </list>
          </property>
        </bean>
      </mvc:message-converters>
    </mvc:annotation-driven>
    <!-- 指定应用名称 -->
    <dubbo:application name="health_backend" />
    <!--指定服务注册中心地址-->
    <dubbo:registry address="zookeeper://127.0.0.1:2181"/>
    <!--批量扫描-->
    <dubbo:annotation package="top.hellocode.controller" />
    <!--
        超时全局设置 10分钟
        check=false 不检查服务提供方，开发阶段建议设置为false
        check=true 启动时检查服务提供方，如果服务提供方没有启动则报错
    -->
    <dubbo:consumer timeout="600000" check="false"/>
    <!--文件上传组件-->
    <bean id="multipartResolver" 
          class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <property name="maxUploadSize" value="104857600" />
        <property name="maxInMemorySize" value="4096" />
        <property name="defaultEncoding" value="UTF-8"/>
    </bean>
</beans>
```

**web.xml**

```xml
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd">
<web-app>
  <display-name>Archetype Created Web Application</display-name>
  <!-- 解决post乱码 -->
  <filter>
    <filter-name>CharacterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
      <param-name>encoding</param-name>
      <param-value>utf-8</param-value>
    </init-param>
    <init-param>
      <param-name>forceEncoding</param-name>
      <param-value>true</param-value>
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>CharacterEncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
  <servlet>
    <servlet-name>springmvc</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <!-- 指定加载的配置文件 ，通过参数contextConfigLocation加载 -->
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:springmvc.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
  </servlet>
  <servlet-mapping>
    <servlet-name>springmvc</servlet-name>
    <url-pattern>*.do</url-pattern>
  </servlet-mapping>
</web-app>
```

### 导入项目所需公共资源

项目开发过程中一般会提供一些公共资源，供多个模块或者系统来使用。

我们导入的公共资源有：

（1）返回消息常量类MessageConstant，放到health_common工程中

```java
package top.hellocode.constant;

/**
 * 消息常量
 */
public class MessageConstant {
    public static final String DELETE_CHECKITEM_FAIL = "删除检查项失败";
    public static final String DELETE_CHECKITEM_SUCCESS = "删除检查项成功";
    public static final String ADD_CHECKITEM_SUCCESS = "新增检查项成功";
    public static final String ADD_CHECKITEM_FAIL = "新增检查项失败";
    public static final String EDIT_CHECKITEM_FAIL = "编辑检查项失败";
    public static final String EDIT_CHECKITEM_SUCCESS = "编辑检查项成功";
    public static final String QUERY_CHECKITEM_SUCCESS = "查询检查项成功";
    public static final String QUERY_CHECKITEM_FAIL = "查询检查项失败";
    public static final String UPLOAD_SUCCESS = "上传成功";
    public static final String ADD_CHECKGROUP_FAIL = "新增检查组失败";
    public static final String ADD_CHECKGROUP_SUCCESS = "新增检查组成功";
    public static final String DELETE_CHECKGROUP_FAIL = "删除检查组失败";
    public static final String DELETE_CHECKGROUP_SUCCESS = "删除检查组成功";
    public static final String QUERY_CHECKGROUP_SUCCESS = "查询检查组成功";
    public static final String QUERY_CHECKGROUP_FAIL = "查询检查组失败";
    public static final String EDIT_CHECKGROUP_FAIL = "编辑检查组失败";
    public static final String EDIT_CHECKGROUP_SUCCESS = "编辑检查组成功";
    public static final String PIC_UPLOAD_SUCCESS = "图片上传成功";
    public static final String PIC_UPLOAD_FAIL = "图片上传失败";
    public static final String ADD_SETMEAL_FAIL = "新增套餐失败";
    public static final String ADD_SETMEAL_SUCCESS = "新增套餐成功";
    public static final String IMPORT_ORDERSETTING_FAIL = "批量导入预约设置数据失败";
    public static final String IMPORT_ORDERSETTING_SUCCESS = "批量导入预约设置数据成功";
    public static final String GET_ORDERSETTING_SUCCESS = "获取预约设置数据成功";
    public static final String GET_ORDERSETTING_FAIL = "获取预约设置数据失败";
    public static final String ORDERSETTING_SUCCESS = "预约设置成功";
    public static final String ORDERSETTING_FAIL = "预约设置失败";
    public static final String ADD_MEMBER_FAIL = "新增会员失败";
    public static final String ADD_MEMBER_SUCCESS = "新增会员成功";
    public static final String DELETE_MEMBER_FAIL = "删除会员失败";
    public static final String DELETE_MEMBER_SUCCESS = "删除会员成功";
    public static final String EDIT_MEMBER_FAIL = "编辑会员失败";
    public static final String EDIT_MEMBER_SUCCESS = "编辑会员成功";
    public static final String TELEPHONE_VALIDATECODE_NOTNULL = "手机号和验证码都不能为空";
    public static final String LOGIN_SUCCESS = "登录成功";
    public static final String VALIDATECODE_ERROR = "验证码输入错误";
    public static final String QUERY_ORDER_SUCCESS = "查询预约信息成功";
    public static final String QUERY_ORDER_FAIL = "查询预约信息失败";
    public static final String QUERY_SETMEALLIST_SUCCESS = "查询套餐列表数据成功";
    public static final String QUERY_SETMEALLIST_FAIL = "查询套餐列表数据失败";
    public static final String QUERY_SETMEAL_SUCCESS = "查询套餐数据成功";
    public static final String QUERY_SETMEAL_FAIL = "查询套餐数据失败";
    public static final String SEND_VALIDATECODE_FAIL = "验证码发送失败";
    public static final String SEND_VALIDATECODE_SUCCESS = "验证码发送成功";
    public static final String SELECTED_DATE_CANNOT_ORDER = "所选日期不能进行体检预约";
    public static final String ORDER_FULL = "预约已满";
    public static final String HAS_ORDERED = "已经完成预约，不能重复预约";
    public static final String ORDER_SUCCESS = "预约成功";
    public static final String GET_USERNAME_SUCCESS = "获取当前登录用户名称成功";
    public static final String GET_USERNAME_FAIL = "获取当前登录用户名称失败";
    public static final String GET_MENU_SUCCESS = "获取当前登录用户菜单成功";
    public static final String GET_MENU_FAIL = "获取当前登录用户菜单失败";
    public static final String GET_MEMBER_NUMBER_REPORT_SUCCESS = "获取会员统计数据成功";
    public static final String GET_MEMBER_NUMBER_REPORT_FAIL = "获取会员统计数据失败";
    public static final String GET_SETMEAL_COUNT_REPORT_SUCCESS = "获取套餐统计数据成功";
    public static final String GET_SETMEAL_COUNT_REPORT_FAIL = "获取套餐统计数据失败";
    public static final String GET_BUSINESS_REPORT_SUCCESS = "获取运营统计数据成功";
    public static final String GET_BUSINESS_REPORT_FAIL = "获取运营统计数据失败";
    public static final String GET_SETMEAL_LIST_SUCCESS = "查询套餐列表数据成功";
    public static final String GET_SETMEAL_LIST_FAIL = "查询套餐列表数据失败";
}
```

（2）返回结果Result和PageResult类，放到health_common工程中

```java
package top.hellocode.entity;
import java.io.Serializable;
/**
 * 封装返回结果
 */
public class Result implements Serializable{
    private boolean flag;//执行结果，true为执行成功 false为执行失败
    private String message;//返回提示信息，主要用于页面提示信息
    private Object data;//返回数据
    public Result(boolean flag, String message) {
        super();
        this.flag = flag;
        this.message = message;
    }
    public Result(boolean flag, String message, Object data) {
        this.flag = flag;
        this.message = message;
        this.data = data;
    }
    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
}
package top.hellocode.entity;
import java.io.Serializable;
import java.util.List;
/**
 * 分页结果封装对象
 */
public class PageResult implements Serializable{
    private Long total;//总记录数
    private List rows;//当前页结果
    public PageResult(Long total, List rows) {
        super();
        this.total = total;
        this.rows = rows;
    }
    public Long getTotal() {
        return total;
    }
    public void setTotal(Long total) {
        this.total = total;
    }
    public List getRows() {
        return rows;
    }
    public void setRows(List rows) {
        this.rows = rows;
    }
}
```

（3）封装查询条件的QueryPageBean类，放到health_common工程中

```java
package top.hellocode.entity;
import java.io.Serializable;
/**
 * 封装查询条件
 */
public class QueryPageBean implements Serializable{
    private Integer currentPage;//页码
    private Integer pageSize;//每页记录数
    private String queryString;//查询条件
    public Integer getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public String getQueryString() {
        return queryString;
    }
    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }
}
```

（4）html、js、css、图片等静态资源，放到health_backend工程中

注意：后续随着项目开发还会陆续导入其他一些公共资源。

## 图片存储方案

### 介绍

在实际开发中，我们会有很多处理不同功能的服务器。例如：

应用服务器：负责部署我们的应用

数据库服务器：运行我们的数据库

文件服务器：负责存储用户上传文件的服务器

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/2071929894.png)

分服务器处理的目的是让服务器各司其职，从而提高我们项目的运行效率。

常见的图片存储方案：

方案一：使用nginx搭建图片服务器

方案二：使用开源的分布式文件存储系统，例如Fastdfs、HDFS等

方案三：使用云存储，例如阿里云、七牛云等

### 七牛云存储

七牛云（隶属于上海七牛信息技术有限公司）是国内领先的以视觉智能和数据智能为核心的企业级云计算服务商，同时也是国内知名智能视频云服务商，累计为 70 多万家企业提供服务，覆盖了国内80%网民。围绕富媒体场景推出了对象存储、融合 CDN 加速、容器云、大数据平台、深度学习平台等产品、并提供一站式智能视频云解决方案。为各行业及应用提供可持续发展的智能视频云生态，帮助企业快速上云，创造更广阔的商业价值。

官网：https://www.qiniu.com/

通过七牛云官网介绍我们可以知道其提供了多种服务，我们主要使用的是七牛云提供的对象存储服务来存储图片。

#### 1.2.1 新建存储空间

要进行图片存储，我们需要在七牛云管理控制台新建存储空间。点击管理控制台首页对象存储下的立即添加按钮，页面跳转到新建存储空间页面：

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/930718321.png)

可以创建多个存储空间，各个存储空间是相互独立的。

#### 1.2.2 查看存储空间信息

存储空间创建后，会在左侧的存储空间列表菜单中展示创建的存储空间名称，点击存储空间名称可以查看当前存储空间的相关信息

#### 1.2.3 开发者中心

可以通过七牛云提供的开发者中心学习如何操作七牛云服务，地址：https://developer.qiniu.com/

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/4111130528.png)

点击对象存储，跳转到对象存储开发页面，地址：https://developer.qiniu.com/kodo

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/3091271746.png)

七牛云提供了多种方式操作对象存储服务，本项目采用Java SDK方式，地址：https://developer.qiniu.com/kodo/sdk/1239/java

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/1987243840.png)

使用Java SDK操作七牛云需要导入如下maven坐标：

```xml
<dependency>
  <groupId>com.qiniu</groupId>
  <artifactId>qiniu-java-sdk</artifactId>
  <version>7.2.0</version>
</dependency>
```

#### 1.2.4 鉴权

Java SDK的所有的功能，都需要合法的授权。授权凭证的签算需要七牛账号下的一对有效的Access Key和Secret Key，这对密钥可以在七牛云管理控制台的个人中心（https://portal.qiniu.com/user/key）获得，如下图：

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/32337073.png)

#### 1.2.6 Java SDK操作七牛云

本章节我们就需要使用七牛云提供的Java SDK完成图片上传和删除，我们可以参考官方提供的例子。

```java
//构造一个带指定Zone对象的配置类
Configuration cfg = new Configuration(Zone.zone0());
//...其他参数参考类注释
UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传
String accessKey = "your access key";
String secretKey = "your secret key";
String bucket = "your bucket name";
//如果是Windows情况下，格式是 D:\\qiniu\\test.png
String localFilePath = "/home/qiniu/test.png";
//默认不指定key的情况下，以文件内容的hash值作为文件名
String key = null;
Auth auth = Auth.create(accessKey, secretKey);
String upToken = auth.uploadToken(bucket);
try {
    Response response = uploadManager.put(localFilePath, key, upToken);
    //解析上传成功的结果
    DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
    System.out.println(putRet.key);
    System.out.println(putRet.hash);
} catch (QiniuException ex) {
    Response r = ex.response;
    System.err.println(r.toString());
    try {
        System.err.println(r.bodyString());
    } catch (QiniuException ex2) {
        //ignore
    }
}
//构造一个带指定Zone对象的配置类
Configuration cfg = new Configuration(Zone.zone0());
//...其他参数参考类注释

String accessKey = "your access key";
String secretKey = "your secret key";

String bucket = "your bucket name";
String key = "your file key";

Auth auth = Auth.create(accessKey, secretKey);
BucketManager bucketManager = new BucketManager(auth, cfg);
try {
    bucketManager.delete(bucket, key);
} catch (QiniuException ex) {
    //如果遇到异常，说明删除失败
    System.err.println(ex.code());
    System.err.println(ex.response.toString());
}
```

#### 1.2.7 封装工具类

为了方便操作七牛云存储服务，我们可以将官方提供的案例简单改造成一个工具类，在我们的项目中直接使用此工具类来操作就可以：

```java
package top.hellocode.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 七牛云工具类
 */
public class QiniuUtils {
    public  static String accessKey = "dulF9Wze9bxujtuRvu3yyYb9JX1Sp23jzd3tO708";
    public  static String secretKey = "vZkhW7iot3uWwcWz9vXfbaP4JepdWADFDHVLMZOe";
    public  static String bucket = "qiniutest";

    public static void upload2Qiniu(String filePath,String fileName){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(filePath, fileName, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = 
              new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        } catch (QiniuException ex) {
            Response r = ex.response;
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }

    //上传文件
    public static void upload2Qiniu(byte[] bytes, String fileName){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = fileName;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(bytes, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = 
              new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }

    //删除文件
    public static void deleteFileFromQiniu(String fileName){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        String key = fileName;
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }
}

```

## 定时任务组件Quartz

### Quartz介绍

Quartz是Job scheduling（作业调度）领域的一个开源项目，Quartz既可以单独使用也可以跟spring框架整合使用，在实际开发中一般会使用后者。使用Quartz可以开发一个或者多个定时任务，每个定时任务可以单独指定执行的时间，例如每隔1小时执行一次、每个月第一天上午10点执行一次、每个月最后一天下午5点执行一次等。

官网：http://www.quartz-scheduler.org/

maven坐标：

```xml
<dependency>
  <groupId>org.quartz-scheduler</groupId>
  <artifactId>quartz</artifactId>
  <version>2.2.1</version>
</dependency>
<dependency>
  <groupId>org.quartz-scheduler</groupId>
  <artifactId>quartz-jobs</artifactId>
  <version>2.2.1</version>
</dependency>
```

### Quartz入门案例

本案例基于Quartz和spring整合的方式使用。具体步骤：

（1）创建maven工程quartzdemo，导入Quartz和spring相关坐标，pom.xml文件如下

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>top.hellocode</groupId>
    <artifactId>quartdemo</artifactId>
    <version>1.0-SNAPSHOT</version>
    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context-support</artifactId>
            <version>5.0.2.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
            <version>5.0.2.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.quartz-scheduler</groupId>
            <artifactId>quartz</artifactId>
            <version>2.2.1</version>
        </dependency>
        <dependency>
            <groupId>org.quartz-scheduler</groupId>
            <artifactId>quartz-jobs</artifactId>
            <version>2.2.1</version>
        </dependency>
    </dependencies>
</project>
```

（2）自定义一个Job

```java
package top.hellocode.jobs;
/**
 * 自定义Job
 */
public class JobDemo {
    public void run(){
        System.out.println("job execute...");
    }
}
```

（3）提供Spring配置文件spring-jobs.xml，配置自定义Job、任务描述、触发器、调度工厂等

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                        http://www.springframework.org/schema/beans/spring-beans.xsd
                        http://www.springframework.org/schema/mvc
                        http://www.springframework.org/schema/mvc/spring-mvc.xsd
                        http://code.alibabatech.com/schema/dubbo
                        http://code.alibabatech.com/schema/dubbo/dubbo.xsd
                        http://www.springframework.org/schema/context
                        http://www.springframework.org/schema/context/spring-context.xsd">
    <!-- 注册自定义Job -->
    <bean id="jobDemo" class="top.hellocode.jobs.JobDemo"></bean>
    <!-- 注册JobDetail,作用是负责通过反射调用指定的Job -->
    <bean id="jobDetail" 
          class="org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean">
        <!-- 注入目标对象 -->
        <property name="targetObject" ref="jobDemo"/>
        <!-- 注入目标方法 -->
        <property name="targetMethod" value="run"/>
    </bean>
    <!-- 注册一个触发器，指定任务触发的时间 -->
    <bean id="myTrigger" class="org.springframework.scheduling.quartz.CronTriggerFactoryBean">
        <!-- 注入JobDetail -->
        <property name="jobDetail" ref="jobDetail"/>
        <!-- 指定触发的时间，基于Cron表达式 -->
        <property name="cronExpression">
            <value>0/10 * * * * ?</value>
        </property>
    </bean>
    <!-- 注册一个统一的调度工厂，通过这个调度工厂调度任务 -->
    <bean id="scheduler" class="org.springframework.scheduling.quartz.SchedulerFactoryBean">
        <!-- 注入多个触发器 -->
        <property name="triggers">
            <list>
                <ref bean="myTrigger"/>
            </list>
        </property>
    </bean>
</beans>
```

（4）编写main方法进行测试

```java
package top.hellocode.jobs.top.hellocode.app;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("spring-jobs.xml");
    }
}
```

执行上面main方法观察控制台，可以发现每隔10秒会输出一次，说明每隔10秒自定义Job被调用一次。

### cron表达式

上面的入门案例中我们指定了一个表达式：0/10 * * * * ?

这种表达式称为cron表达式，通过cron表达式可以灵活的定义出符合要求的程序执行的时间。本小节我们就来学习一下cron表达式的使用方法。如下图：

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/1388884927.png)

cron表达式分为七个域，之间使用空格分隔。其中最后一个域（年）可以为空。每个域都有自己允许的值和一些特殊字符构成。使用这些特殊字符可以使我们定义的表达式更加灵活。

下面是对这些特殊字符的介绍：

- 逗号（,）：指定一个值列表，例如使用在月域上1,4,5,7表示1月、4月、5月和7月
- 横杠（-）：指定一个范围，例如在时域上3-6表示3点到6点（即3点、4点、5点、6点）
- 星号（*）：表示这个域上包含所有合法的值。例如，在月份域上使用星号意味着每个月都会触发
- 斜线（/）：表示递增，例如使用在秒域上0/15表示每15秒
- 问号（?）：只能用在日和周域上，但是不能在这两个域上同时使用。表示不指定
- 井号（#）：只能使用在周域上，用于指定月份中的第几周的哪一天，例如6#3，意思是某月的第三个周五 (6=星期五，3意味着月份中的第三周)
- L：某域上允许的最后一个值。只能使用在日和周域上。当用在日域上，表示的是在月域上指定的月份的最后一天。用于周域上时，表示周的最后一天，就是星期六
- W：W 字符代表着工作日 (星期一到星期五)，只能用在日域上，它用来指定离指定日的最近的一个工作日

### 4.4 cron表达式在线生成器

前面介绍了cron表达式，但是自己编写表达式还是有一些困难的，我们可以借助一些cron表达式在线生成器来根据我们的需求生成表达式即可。

http://cron.qqe2.com/

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/891754624.png)

## 定时清理垃圾图片

> 前面我们已经完成了体检套餐的管理，在新增套餐时套餐的基本信息和图片是分两次提交到后台进行操作的。也就是用户首先将图片上传到七牛云服务器，然后再提交新增窗口中录入的其他信息。如果用户只是上传了图片而没有提交录入的其他信息，此时的图片就变为了垃圾图片，因为在数据库中并没有记录它的存在。此时我们要如何处理这些垃圾图片呢？

解决方案就是通过定时任务组件定时清理这些垃圾图片。为了能够区分出来哪些图片是垃圾图片，我们在文件上传成功后将图片保存到了一个redis集合中，当套餐数据插入到数据库后我们又将图片名称保存到了另一个redis集合中，通过计算这两个集合的差值就可以获得所有垃圾图片的名称。

本章节我们就会基于Quartz定时任务，通过计算redis两个集合的差值找出所有的垃圾图片，就可以将垃圾图片清理掉。

**操作步骤**

（1）创建maven工程health_jobs，打包方式为war，导入Quartz等相关坐标

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" 
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
                             http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>health_parent</artifactId>
        <groupId>top.hellocode</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <artifactId>health_jobs</artifactId>
    <packaging>war</packaging>
    <name>health_jobs Maven Webapp</name>
    <url>http://www.example.com</url>
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
    </properties>
    <dependencies>
        <dependency>
            <groupId>top.hellocode</groupId>
            <artifactId>health_interface</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>org.quartz-scheduler</groupId>
            <artifactId>quartz</artifactId>
        </dependency>
        <dependency>
            <groupId>org.quartz-scheduler</groupId>
            <artifactId>quartz-jobs</artifactId>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.tomcat.maven</groupId>
                <artifactId>tomcat7-maven-plugin</artifactId>
                <configuration>
                    <!-- 指定端口 -->
                    <port>83</port>
                    <!-- 请求路径 -->
                    <path>/</path>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

（2）配置web.xml

```xml
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >
<web-app>
  <display-name>Archetype Created Web Application</display-name>
  <!-- 加载spring容器 -->
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath*:applicationContext*.xml</param-value>
  </context-param>
  <listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>
</web-app>
```

（3）配置log4j.properties

（4）配置applicationContext-redis.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
    xmlns:p="http://www.springframework.org/schema/p"
    xmlns:context="http://www.springframework.org/schema/context"
    xmlns:dubbo="http://code.alibabatech.com/schema/dubbo" 
    xmlns:mvc="http://www.springframework.org/schema/mvc"
    xsi:schemaLocation="http://www.springframework.org/schema/beans 
                         http://www.springframework.org/schema/beans/spring-beans.xsd
                        http://www.springframework.org/schema/mvc 
                          http://www.springframework.org/schema/mvc/spring-mvc.xsd
                        http://code.alibabatech.com/schema/dubbo 
                          http://code.alibabatech.com/schema/dubbo/dubbo.xsd
                        http://www.springframework.org/schema/context
                          http://www.springframework.org/schema/context/spring-context.xsd">

    <!--Jedis连接池的相关配置-->
    <bean id="jedisPoolConfig" class="redis.clients.jedis.JedisPoolConfig">
        <property name="maxTotal">
            <value>200</value>
        </property>
        <property name="maxIdle">
            <value>50</value>
        </property>
        <property name="testOnBorrow" value="true"/>
        <property name="testOnReturn" value="true"/>
    </bean>
    <bean id="jedisPool" class="redis.clients.jedis.JedisPool">
        <constructor-arg name="poolConfig" ref="jedisPoolConfig" />
        <constructor-arg name="host" value="127.0.0.1" />
        <constructor-arg name="port" value="6379" type="int" />
        <constructor-arg name="timeout" value="30000" type="int" />
    </bean>
</beans>
```

（5）配置applicationContext-jobs.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
    xmlns:context="http://www.springframework.org/schema/context"
    xmlns:dubbo="http://code.alibabatech.com/schema/dubbo" 
    xmlns:mvc="http://www.springframework.org/schema/mvc"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
                            http://www.springframework.org/schema/beans/spring-beans.xsd
                            http://www.springframework.org/schema/mvc
                            http://www.springframework.org/schema/mvc/spring-mvc.xsd
                            http://code.alibabatech.com/schema/dubbo
                            http://code.alibabatech.com/schema/dubbo/dubbo.xsd
                            http://www.springframework.org/schema/context
                            http://www.springframework.org/schema/context/spring-context.xsd">
    <context:annotation-config></context:annotation-config>
    <bean id="clearImgJob" class="top.hellocode.jobs.ClearImgJob"></bean>
    <bean id="jobDetail" 
          class="org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean">
        <!-- 注入目标对象 -->
        <property name="targetObject" ref="clearImgJob"/>
        <!-- 注入目标方法 -->
        <property name="targetMethod" value="clearImg"/>
    </bean>
    <!-- 注册一个触发器，指定任务触发的时间 -->
    <bean id="myTrigger" class="org.springframework.scheduling.quartz.CronTriggerFactoryBean">
        <!-- 注入JobDetail -->
        <property name="jobDetail" ref="jobDetail"/>
        <!-- 指定触发的时间，基于Cron表达式 -->
        <property name="cronExpression">
            <value>0 0 2 * * ?</value>
        </property>
    </bean>
    <!-- 注册一个统一的调度工厂，通过这个调度工厂调度任务 -->
    <bean id="scheduler" class="org.springframework.scheduling.quartz.SchedulerFactoryBean">
        <!-- 注入多个触发器 -->
        <property name="triggers">
            <list>
                <ref bean="myTrigger"/>
            </list>
        </property>
    </bean>
</beans>
```

（6）创建ClearImgJob定时任务类

```java
package top.hellocode.jobs;

import top.hellocode.constant.RedisConstant;
import top.hellocode.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;
import java.util.Set;

/**
 * 自定义Job，实现定时清理垃圾图片
 */
public class ClearImgJob {
    @Autowired
    private JedisPool jedisPool;
    public void clearImg(){
        //根据Redis中保存的两个set集合进行差值计算，获得垃圾图片名称集合
        Set<String> set = 
            jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, 
                                          RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        if(set != null){
            for (String picName : set) {
                //删除七牛云服务器上的图片
                QiniuUtils.deleteFileFromQiniu(picName);
                //从Redis集合中删除图片名称
                jedisPool.getResource().
                    srem(RedisConstant.SETMEAL_PIC_RESOURCES,picName);
            }
        }
    }
}
```

## Apache POI

### POI介绍

Apache POI是用Java编写的免费开源的跨平台的Java API，Apache POI提供API给Java程序对Microsoft Office格式档案读和写的功能，其中使用最多的就是使用POI操作Excel文件。

jxl：专门操作Excel

maven坐标：

```xml
<dependency>
  <groupId>org.apache.poi</groupId>
  <artifactId>poi</artifactId>
  <version>3.14</version>
</dependency>
<dependency>
  <groupId>org.apache.poi</groupId>
  <artifactId>poi-ooxml</artifactId>
  <version>3.14</version>
</dependency>
```

POI结构：

```
HSSF － 提供读写Microsoft Excel XLS格式档案的功能
XSSF － 提供读写Microsoft Excel OOXML XLSX格式档案的功能
HWPF － 提供读写Microsoft Word DOC格式档案的功能
HSLF － 提供读写Microsoft PowerPoint格式档案的功能
HDGF － 提供读Microsoft Visio格式档案的功能
HPBF － 提供读Microsoft Publisher格式档案的功能
HSMF － 提供读Microsoft Outlook格式档案的功能
```

### 入门案例

#### 从Excel文件读取数据

使用POI可以从一个已经存在的Excel文件中读取数据

```java
//创建工作簿
XSSFWorkbook workbook = new XSSFWorkbook("D:\\hello.xlsx");
//获取工作表，既可以根据工作表的顺序获取，也可以根据工作表的名称获取
XSSFSheet sheet = workbook.getSheetAt(0);
//遍历工作表获得行对象
for (Row row : sheet) {
  //遍历行对象获取单元格对象
  for (Cell cell : row) {
    //获得单元格中的值
    String value = cell.getStringCellValue();
    System.out.println(value);
  }
}
workbook.close();
```

通过上面的入门案例可以看到，POI操作Excel表格封装了几个核心对象：

```
XSSFWorkbook：工作簿
XSSFSheet：工作表
Row：行
Cell：单元格
```

上面案例是通过遍历工作表获得行，遍历行获得单元格，最终获取单元格中的值。

还有一种方式就是获取工作表最后一个行号，从而根据行号获得行对象，通过行获取最后一个单元格索引，从而根据单元格索引获取每行的一个单元格对象，代码如下：

```java
//创建工作簿
XSSFWorkbook workbook = new XSSFWorkbook("D:\\hello.xlsx");
//获取工作表，既可以根据工作表的顺序获取，也可以根据工作表的名称获取
XSSFSheet sheet = workbook.getSheetAt(0);
//获取当前工作表最后一行的行号，行号从0开始
int lastRowNum = sheet.getLastRowNum();
for(int i=0;i<=lastRowNum;i++){
  //根据行号获取行对象
  XSSFRow row = sheet.getRow(i);
  short lastCellNum = row.getLastCellNum();
  for(short j=0;j<lastCellNum;j++){
    String value = row.getCell(j).getStringCellValue();
    System.out.println(value);
  }
}
workbook.close();
```

#### 向Excel文件写入数据

使用POI可以在内存中创建一个Excel文件并将数据写入到这个文件，最后通过输出流将内存中的Excel文件下载到磁盘

```java
//在内存中创建一个Excel文件
XSSFWorkbook workbook = new XSSFWorkbook();
//创建工作表，指定工作表名称
XSSFSheet sheet = workbook.createSheet("传智播客");

//创建行，0表示第一行
XSSFRow row = sheet.createRow(0);
//创建单元格，0表示第一个单元格
row.createCell(0).setCellValue("编号");
row.createCell(1).setCellValue("名称");
row.createCell(2).setCellValue("年龄");

XSSFRow row1 = sheet.createRow(1);
row1.createCell(0).setCellValue("1");
row1.createCell(1).setCellValue("小明");
row1.createCell(2).setCellValue("10");

XSSFRow row2 = sheet.createRow(2);
row2.createCell(0).setCellValue("2");
row2.createCell(1).setCellValue("小王");
row2.createCell(2).setCellValue("20");

//通过输出流将workbook对象下载到磁盘
FileOutputStream out = new FileOutputStream("D:\\itcast.xlsx");
workbook.write(out);
out.flush();
out.close();
workbook.close();
```

## 搭建移动端工程

本项目是基于SOA架构进行开发，前面我们已经完成了后台系统的部分功能开发，在后台系统中都是通过Dubbo调用服务层发布的服务进行相关的操作。本章节我们开发移动端工程也是同样的模式，所以我们也需要在移动端工程中通过Dubbo调用服务层发布的服务，如下图：

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/1171314955.png)

#### 原型展示

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/3096973456.png)

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/4065160622.png)

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/4065160622.png)

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/2048155634.png)

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/1344068270.png)

#### 2.2.1 导入maven坐标

在health_common工程的pom.xml文件中导入阿里短信发送的maven坐标

```xml
<dependency>
  <groupId>com.aliyun</groupId>
  <artifactId>aliyun-java-sdk-core</artifactId>
  <version>3.3.1</version>
</dependency>
<dependency>
  <groupId>com.aliyun</groupId>
  <artifactId>aliyun-java-sdk-dysmsapi</artifactId>
  <version>1.0.0</version>
</dependency>
```

#### 2.2.2 导入通用组件

在health_common工程中导入如下通用组件

ValidateCodeUtils工具类：

```java
package top.hellocode.utils;

import java.util.Random;

/**
 * 随机生成验证码工具类
 */
public class ValidateCodeUtils {
    /**
     * 随机生成验证码
     * @param length 长度为4位或者6位
     * @return
     */
    public static Integer generateValidateCode(int length){
        Integer code =null;
        if(length == 4){
            code = new Random().nextInt(9999);//生成随机数，最大为9999
            if(code < 1000){
                code = code + 1000;//保证随机数为4位数字
            }
        }else if(length == 6){
            code = new Random().nextInt(999999);//生成随机数，最大为999999
            if(code < 100000){
                code = code + 100000;//保证随机数为6位数字
            }
        }else{
            throw new RuntimeException("只能生成4位或6位数字验证码");
        }
        return code;
    }

    /**
     * 随机生成指定长度字符串验证码
     * @param length 长度
     * @return
     */
    public static String generateValidateCode4String(int length){
        Random rdm = new Random();
        String hash1 = Integer.toHexString(rdm.nextInt());
        String capstr = hash1.substring(0, length);
        return capstr;
    }
}
```

RedisMessageConstant常量类：

```java
package top.hellocode.constant;

public class RedisMessageConstant {
    public static final String SENDTYPE_ORDER = "001";//用于缓存体检预约时发送的验证码
    public static final String SENDTYPE_LOGIN = "002";//用于缓存手机号快速登录时发送的验证码
    public static final String SENDTYPE_GETPWD = "003";//用于缓存找回密码时发送的验证码
}
```

#### 2.2.3 health_mobile

创建移动端工程health_mobile，打包方式为war，用于存放Controller，在Controller中通过Dubbo可以远程访问服务层相关服务，所以需要依赖health_interface接口工程。

pom.xml：

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" 
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
                             http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>health_parent</artifactId>
        <groupId>top.hellocode</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <artifactId>health_mobile</artifactId>
    <packaging>war</packaging>
    <name>healthmobile_web Maven Webapp</name>
    <url>http://www.example.com</url>
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
    </properties>
    <dependencies>
        <dependency>
            <groupId>top.hellocode</groupId>
            <artifactId>health_interface</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.tomcat.maven</groupId>
                <artifactId>tomcat7-maven-plugin</artifactId>
                <configuration>
                    <!-- 指定端口 -->
                    <port>80</port>
                    <!-- 请求路径 -->
                    <path>/</path>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

静态资源（CSS、html、img等，详见资料）：

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/3080786.png)

web.xml：

```xml
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >
<web-app>
  <display-name>Archetype Created Web Application</display-name>
  <!-- 解决post乱码 -->
  <filter>
    <filter-name>CharacterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
      <param-name>encoding</param-name>
      <param-value>utf-8</param-value>
    </init-param>
    <init-param>
      <param-name>forceEncoding</param-name>
      <param-value>true</param-value>
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>CharacterEncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
  <servlet>
    <servlet-name>springmvc</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <!-- 指定加载的配置文件 ，通过参数contextConfigLocation加载 -->
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:springmvc.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
  </servlet>
  <servlet-mapping>
    <servlet-name>springmvc</servlet-name>
    <url-pattern>*.do</url-pattern>
  </servlet-mapping>
  <welcome-file-list>
    <welcome-file>/pages/index.html</welcome-file>
  </welcome-file-list>
</web-app>
```

springmvc.xml：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
    xmlns:p="http://www.springframework.org/schema/p"
    xmlns:context="http://www.springframework.org/schema/context"
    xmlns:dubbo="http://code.alibabatech.com/schema/dubbo" 
    xmlns:mvc="http://www.springframework.org/schema/mvc"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
         http://www.springframework.org/schema/beans/spring-beans.xsd
          http://www.springframework.org/schema/mvc
          http://www.springframework.org/schema/mvc/spring-mvc.xsd
          http://code.alibabatech.com/schema/dubbo
          http://code.alibabatech.com/schema/dubbo/dubbo.xsd
          http://www.springframework.org/schema/context
          http://www.springframework.org/schema/context/spring-context.xsd">

    <mvc:annotation-driven>
      <mvc:message-converters register-defaults="true">
        <bean class="com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter">
          <property name="supportedMediaTypes" value="application/json"/>
          <property name="features">
            <list>
              <value>WriteMapNullValue</value>
              <value>WriteDateUseDateFormat</value>
            </list>
          </property>
        </bean>
      </mvc:message-converters>
    </mvc:annotation-driven>
    <!-- 指定应用名称 -->
    <dubbo:application name="health_mobile" />
    <!--指定服务注册中心地址-->
    <dubbo:registry address="zookeeper://127.0.0.1:2181"/>
    <!--批量扫描-->
    <dubbo:annotation package="top.hellocode.controller" />
    <!--
        超时全局设置 10分钟
        check=false 不检查服务提供方，开发阶段建议设置为false
        check=true 启动时检查服务提供方，如果服务提供方没有启动则报错
    -->
    <dubbo:consumer timeout="600000" check="false"/>
    <import resource="spring-redis.xml"></import>
</beans>
```

spring-redis.xml：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
    xmlns:p="http://www.springframework.org/schema/p"
    xmlns:context="http://www.springframework.org/schema/context"
    xmlns:dubbo="http://code.alibabatech.com/schema/dubbo" 
    xmlns:mvc="http://www.springframework.org/schema/mvc"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
         http://www.springframework.org/schema/beans/spring-beans.xsd
          http://www.springframework.org/schema/mvc
          http://www.springframework.org/schema/mvc/spring-mvc.xsd
          http://code.alibabatech.com/schema/dubbo
          http://code.alibabatech.com/schema/dubbo/dubbo.xsd
          http://www.springframework.org/schema/context
          http://www.springframework.org/schema/context/spring-context.xsd">
  
    <context:property-placeholder location="classpath:redis.properties" />

    <!--Jedis连接池的相关配置-->
    <bean id="jedisPoolConfig" class="redis.clients.jedis.JedisPoolConfig">
        <property name="maxTotal">
            <value>${redis.pool.maxActive}</value>
        </property>
        <property name="maxIdle">
            <value>${redis.pool.maxIdle}</value>
        </property>
        <property name="testOnBorrow" value="true"/>
        <property name="testOnReturn" value="true"/>
    </bean>

    <bean id="jedisPool" class="redis.clients.jedis.JedisPool">
        <constructor-arg name="poolConfig" ref="jedisPoolConfig" />
        <constructor-arg name="host" value="${redis.host}" />
        <constructor-arg name="port" value="${redis.port}" type="int" />
        <constructor-arg name="timeout" value="${redis.timeout}" type="int" />
    </bean>
</beans>
```

redis.properties：

```properties
#最大分配的对象数
redis.pool.maxActive=200
#最大能够保持idel状态的对象数
redis.pool.maxIdle=50
redis.pool.minIdle=10
redis.pool.maxWaitMillis=20000
#当池内没有返回对象时，最大等待时间
redis.pool.maxWait=300

#格式：redis://:[密码]@[服务器地址]:[端口]/[db index]
#redis.uri = redis://:12345@127.0.0.1:6379/0

redis.host = 127.0.0.1
redis.port = 6379
redis.timeout = 30000
```

## 短信发送

### 短信服务介绍

目前市面上有很多第三方提供的短信服务，这些第三方短信服务会和各个运营商（移动、联通、电信）对接，我们只需要注册成为会员并且按照提供的开发文档进行调用就可以发送短信。需要说明的是这些短信服务都是收费的服务。

本项目短信发送我们选择的是阿里云提供的短信服务。（*也可以使用腾讯云提供的短信服务，可免费发送100条*）

短信服务（Short Message Service）是阿里云为用户提供的一种通信服务的能力，支持快速发送短信验证码、短信通知等。 三网合一专属通道，与工信部携号转网平台实时互联。电信级运维保障，实时监控自动切换，到达率高达99%。短信服务API提供短信发送、发送状态查询、短信批量发送等能力，在短信服务控制台上添加签名、模板并通过审核之后，可以调用短信服务API完成短信发送等操作。

### 注册阿里云账号

阿里云官网：https://www.aliyun.com/

点击官网首页免费注册跳转到如下注册页面：

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/3918675046.png)

### 设置短信签名

注册成功后，点击登录按钮进行登录。登录后进入短信服务管理页面，选择国内消息菜单：

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/3884439984.png)

点击添加签名按钮：

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/1452865002.png)

目前个人用户只能申请适用场景为验证码的签名

### 设置短信模板

在国内消息菜单页面中，点击模板管理标签页：

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/4201340732.png)

点击添加模板按钮：

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/3388604953.png)

### 设置access keys

在发送短信时需要进行身份认证，只有认证通过才能发送短信。本小节就是要设置用于发送短信时进行身份认证的key和密钥。鼠标放在页面右上角当前用户头像上，会出现下拉菜单：

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/1710219249.png)

点击accesskeys：

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/3642896476.png)

点击开始使用子用户AccessKey按钮：

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/438434259.png)

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/1469160674.png)

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/3076083010.png)

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/862697323.png)

创建成功，其中AccessKeyID为访问短信服务时使用的ID，AccessKeySecret为密钥。

可以在用户详情页面下禁用刚刚创建的AccessKey：

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/1877713532.png)

可以设置每日和每月短信发送上限：

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/833746616.png)

### 发送短信

#### 导入maven坐标

```xml
<dependency>
  <groupId>com.aliyun</groupId>
  <artifactId>aliyun-java-sdk-core</artifactId>
  <version>3.3.1</version>
</dependency>
<dependency>
  <groupId>com.aliyun</groupId>
  <artifactId>aliyun-java-sdk-dysmsapi</artifactId>
  <version>1.0.0</version>
</dependency>
```

#### 封装工具类

```java
package top.hellocode.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * 短信发送工具类
 */
public class SMSUtils {
    public static final String VALIDATE_CODE = "SMS_159620392";//发送短信验证码
    public static final String ORDER_NOTICE = "SMS_159771588";//体检预约成功通知

    /**
     * 发送短信
     * @param phoneNumbers
     * @param param
     * @throws ClientException
     */
    public static void sendShortMessage(String templateCode,String phoneNumbers,String param) throws ClientException{
        // 设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        // 初始化ascClient需要的几个参数
        final String product = "Dysmsapi";// 短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";// 短信API产品域名（接口地址固定，无需修改）
        // 替换成你的AK
        final String accessKeyId = "accessKeyId";// 你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = "accessKeySecret";// 你的accessKeySecret，参考本文档步骤2
        // 初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        // 组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        // 使用post提交
        request.setMethod(MethodType.POST);
        // 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        request.setPhoneNumbers(phoneNumbers);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName("传智健康");
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        // 友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam("{\"code\":\""+param+"\"}");
        // 可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        // request.setSmsUpExtendCode("90997");
        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        // request.setOutId("yourOutId");
        // 请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            // 请求成功
            System.out.println("请求成功");
        }
    }
}
```

#### 测试短信发送

```java
public static void main(String[] args)throws Exception {
        SMSUtils.sendShortMessage("SMS_159620392","13812345678","1234");
}
```

## 页面静态化介绍

> 本章课程中我们已经实现了移动端套餐列表页面和套餐详情页面的动态展示。但是我们需要思考一个问题，就是对于这两个页面来说，每次用户访问这两个页面都需要查询数据库获取动态数据进行展示，而且这两个页面的访问量是比较大的，这就对数据库造成了很大的访问压力，并且数据库中的数据变化频率并不高。那我们需要通过什么方法为数据库减压并提高系统运行性能呢？答案就是页面静态化。

页面静态化其实就是将原来的动态网页(例如通过ajax请求动态获取数据库中的数据并展示的网页)改为通过静态化技术生成的静态网页，这样用户在访问网页时，服务器直接给用户响应静态html页面，没有了动态查询数据库的过程。

那么这些静态HTML页面还需要我们自己去编写吗？其实并不需要，我们可以通过专门的页面静态化技术帮我们生成所需的静态HTML页面，例如：Freemarker、thymeleaf等。

### Freemarker介绍

FreeMarker 是一个用 Java 语言编写的模板引擎，它基于模板来生成文本输出。FreeMarker与 Web 容器无关，即在 Web 运行时，它并不知道 Servlet 或 HTTP。它不仅可以用作表现层的实现技术，而且还可以用于生成 XML，JSP 或 Java 等。

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/3649752188.png)

### Freemarker入门案例

#### 环境搭建

创建maven工程并导入Freemarker的maven坐标

```xml
<dependency>
  <groupId>org.freemarker</groupId>
  <artifactId>freemarker</artifactId>
  <version>2.3.23</version>
</dependency>
```

#### 创建模板文件

模板文件中有四种元素：

1、文本，直接输出的部分 2、注释，即 `<#--...-->`格式不会输出 3、插值（Interpolation）：即 `${..}`部分,将使用数据模型中的部分替代输出 4、FTL指令：FreeMarker指令，和HTML标记类似，名字前加#予以区分，不会输出

Freemarker的模板文件后缀可以任意，一般建议为ftl。

在D盘创建ftl目录，在ftl目录中创建名称为test.ftl的模板文件，内容如下：

```html
<html>
<head>
    <meta charset="utf-8">
    <title>Freemarker入门</title>
</head>
<body>
    <#--我只是一个注释，我不会有任何输出  -->
    ${name}你好，${message}
</body>
</html>
```

#### 生成文件

使用步骤：

第一步：创建一个 Configuration 对象，直接 new 一个对象。构造方法的参数就是 freemarker的版本号。

第二步：设置模板文件所在的路径。

第三步：设置模板文件使用的字符集。一般就是 utf-8。

第四步：加载一个模板，创建一个模板对象。

第五步：创建一个模板使用的数据集，可以是 pojo 也可以是 map。一般是 Map。

第六步：创建一个 Writer 对象，一般创建 FileWriter 对象，指定生成的文件名。

第七步：调用模板对象的 process 方法输出文件。

第八步：关闭流。

```java
public static void main(String[] args) throws Exception{
    //1.创建配置类
    Configuration configuration=new Configuration(Configuration.getVersion());
    //2.设置模板所在的目录 
    configuration.setDirectoryForTemplateLoading(new File("D:\\ftl"));
    //3.设置字符集
    configuration.setDefaultEncoding("utf-8");
    //4.加载模板
    Template template = configuration.getTemplate("test.ftl");
    //5.创建数据模型
    Map map=new HashMap();
    map.put("name", "张三");
    map.put("message", "欢迎来到传智播客！");
    //6.创建Writer对象
    Writer out =new FileWriter(new File("d:\\test.html"));
    //7.输出
    template.process(map, out);
    //8.关闭Writer对象
    out.close();
}
```

上面的入门案例中Configuration配置对象是自己创建的，字符集和模板文件所在目录也是在Java代码中指定的。在项目中应用时可以将Configuration对象的创建交由Spring框架来完成，并通过依赖注入方式将字符集和模板所在目录注入进去。

### Freemarker指令

#### assign指令

assign指令用于在页面上定义一个变量

（1）定义简单类型

```html
<#assign linkman="周先生">
联系人：${linkman}
```

（2）定义对象类型

```html
<#assign info={"mobile":"13812345678",'address':'北京市昌平区'} >
电话：${info.mobile}  地址：${info.address}
```

#### include指令

include指令用于模板文件的嵌套

（1）创建模板文件head.ftl

```html
<h1>黑马程序员</h1>
```

（2）修改入门案例中的test.ftl，在test.ftl模板文件中使用include指令引入上面的模板文件

```html
<#include "head.ftl"/>
```

#### if指令

if指令用于判断

（1）在模板文件中使用if指令进行判断

```html
<#if success=true>
  你已通过实名认证
<#else>  
  你未通过实名认证
</#if>
```

（2）在java代码中为success变量赋值

```java
map.put("success", true);
```

在freemarker的判断中，可以使用= 也可以使用==

#### list指令

list指令用于遍历

（1）在模板文件中使用list指令进行遍历

```html
<#list goodsList as goods>
  商品名称： ${goods.name} 价格：${goods.price}<br>
</#list>
```

（2）在java代码中为goodsList赋值

```java
List goodsList=new ArrayList();

Map goods1=new HashMap();
goods1.put("name", "苹果");
goods1.put("price", 5.8);

Map goods2=new HashMap();
goods2.put("name", "香蕉");
goods2.put("price", 2.5);

Map goods3=new HashMap();
goods3.put("name", "橘子");
goods3.put("price", 3.2);

goodsList.add(goods1);
goodsList.add(goods2);
goodsList.add(goods3);

map.put("goodsList", goodsList);
```

## 权限控制

### 认证和授权概念

前面我们已经完成了传智健康后台管理系统的部分功能，例如检查项管理、检查组管理、套餐管理、预约设置等。接下来我们需要思考2个问题：

问题1：在生产环境下我们如果不登录后台系统就可以完成这些功能操作吗？

答案显然是否定的，要操作这些功能必须首先登录到系统才可以。

问题2：是不是所有用户，只要登录成功就都可以操作所有功能呢？

答案是否定的，并不是所有的用户都可以操作这些功能。不同的用户可能拥有不同的权限，这就需要进行授权了。

认证：系统提供的用于识别用户身份的功能，通常提供用户名和密码进行登录其实就是在进行认证，认证的目的是让系统知道你是谁。

授权：用户认证成功后，需要为用户授权，其实就是指定当前用户可以操作哪些功能。

本章节就是要对后台系统进行权限控制，其本质就是对用户进行认证和授权。

### 权限模块数据模型

前面已经分析了认证和授权的概念，要实现最终的权限控制，需要有一套表结构支撑：

用户表t_user、权限表t_permission、角色表t_role、菜单表t_menu、用户角色关系表t_user_role、角色权限关系表t_role_permission、角色菜单关系表t_role_menu。

表之间关系如下图：

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/282010002.png)

通过上图可以看到，权限模块共涉及到7张表。在这7张表中，角色表起到了至关重要的作用，其处于核心位置，因为用户、权限、菜单都和角色是多对多关系。

接下来我们可以分析一下在认证和授权过程中分别会使用到哪些表：

认证过程：只需要用户表就可以了，在用户登录时可以查询用户表t_user进行校验，判断用户输入的用户名和密码是否正确。

授权过程：用户必须完成认证之后才可以进行授权，首先可以根据用户查询其角色，再根据角色查询对应的菜单，这样就确定了用户能够看到哪些菜单。然后再根据用户的角色查询对应的权限，这样就确定了用户拥有哪些权限。所以授权过程会用到上面7张表。

### Spring Security简介

Spring Security是 Spring提供的安全认证服务的框架。 使用Spring Security可以帮助我们来简化认证和授权的过程。官网：https://spring.io/projects/spring-security

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/3599645920.png)

对应的maven坐标：

```xml
<dependency>
  <groupId>org.springframework.security</groupId>
  <artifactId>spring-security-web</artifactId>
  <version>5.0.5.RELEASE</version>
</dependency>
<dependency>
  <groupId>org.springframework.security</groupId>
  <artifactId>spring-security-config</artifactId>
  <version>5.0.5.RELEASE</version>
</dependency>
```

常用的权限框架除了Spring Security，还有Apache的shiro框架。

### Spring Security入门案例

#### 工程搭建

创建maven工程，打包方式为war，为了方便起见我们可以让入门案例工程依赖health_interface，这样相关的依赖都继承过来了。

pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                             http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>top.hellocode</groupId>
  <artifactId>springsecuritydemo</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>war</packaging>

  <name>springsecuritydemo Maven Webapp</name>
  <url>http://www.example.com</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
  </properties>

  <dependencies>
    <dependency>
      <groupId>top.hellocode</groupId>
      <artifactId>health_interface</artifactId>
      <version>1.0-SNAPSHOT</version>
    </dependency>
  </dependencies>
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.tomcat.maven</groupId>
        <artifactId>tomcat7-maven-plugin</artifactId>
        <configuration>
          <!-- 指定端口 -->
          <port>85</port>
          <!-- 请求路径 -->
          <path>/</path>
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>
```

提供index.html页面，内容为hello Spring Security!!

#### 配置web.xml

在web.xml中主要配置SpringMVC的DispatcherServlet和用于整合第三方框架的DelegatingFilterProxy，用于整合Spring Security。

```xml
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app>
  <display-name>Archetype Created Web Application</display-name>
  <filter>
    <!--
      DelegatingFilterProxy用于整合第三方框架
      整合Spring Security时过滤器的名称必须为springSecurityFilterChain，
      否则会抛出NoSuchBeanDefinitionException异常
    -->
    <filter-name>springSecurityFilterChain</filter-name>
    <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
  </filter>
  <filter-mapping>
    <filter-name>springSecurityFilterChain</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
  <servlet>
    <servlet-name>springmvc</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <!-- 指定加载的配置文件 ，通过参数contextConfigLocation加载 -->
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:spring-security.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
  </servlet>
  <servlet-mapping>
    <servlet-name>springmvc</servlet-name>
    <url-pattern>*.do</url-pattern>
  </servlet-mapping>

</web-app>
```

#### 配置spring-security.xml

在spring-security.xml中主要配置Spring Security的拦截规则和认证管理器。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:security="http://www.springframework.org/schema/security"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                        http://www.springframework.org/schema/beans/spring-beans.xsd
                        http://www.springframework.org/schema/mvc
                        http://www.springframework.org/schema/mvc/spring-mvc.xsd
                        http://code.alibabatech.com/schema/dubbo
                        http://code.alibabatech.com/schema/dubbo/dubbo.xsd
                        http://www.springframework.org/schema/context
                        http://www.springframework.org/schema/context/spring-context.xsd
                          http://www.springframework.org/schema/security
                          http://www.springframework.org/schema/security/spring-security.xsd">

    <!--
        http：用于定义相关权限控制
        auto-config：是否自动配置
                        设置为true时框架会提供默认的一些配置，例如提供默认的登录页面、登出处理等
                        设置为false时需要显示提供登录表单配置，否则会报错
        use-expressions：用于指定intercept-url中的access属性是否使用表达式
    -->
    <security:http auto-config="true" use-expressions="true">
        <!--
            intercept-url：定义一个拦截规则
            pattern：对哪些url进行权限控制
            access：在请求对应的URL时需要什么权限，默认配置时它应该是一个以逗号分隔的角色列表，
                  请求的用户只需拥有其中的一个角色就能成功访问对应的URL
        -->
        <security:intercept-url pattern="/**"  access="hasRole('ROLE_ADMIN')" />
    </security:http>

    <!--
        authentication-manager：认证管理器，用于处理认证操作
    -->
    <security:authentication-manager>
        <!--
            authentication-provider：认证提供者，执行具体的认证逻辑
        -->
        <security:authentication-provider>
            <!--
                user-service：用于获取用户信息，提供给authentication-provider进行认证
            -->
            <security:user-service>
                <!--
                    user：定义用户信息，可以指定用户名、密码、角色，后期可以改为从数据库查询用户信息
                  {noop}：表示当前使用的密码为明文
                -->
                <security:user name="admin" 
                               password="{noop}admin" 
                               authorities="ROLE_ADMIN">
                </security:user>
            </security:user-service>
        </security:authentication-provider>
    </security:authentication-manager>
</beans>
```

### 对入门案例改进

前面我们已经完成了Spring Security的入门案例，通过入门案例我们可以看到，Spring Security将我们项目中的所有资源都保护了起来，要访问这些资源必须要完成认证而且需要具有ROLE_ADMIN角色。

但是入门案例中的使用方法离我们真实生产环境还差很远，还存在如下一些问题：

1、项目中我们将所有的资源（所有请求URL）都保护起来，实际环境下往往有一些资源不需要认证也可以访问，也就是可以匿名访问。

2、登录页面是由框架生成的，而我们的项目往往会使用自己的登录页面。

3、直接将用户名和密码配置在了配置文件中，而真实生产环境下的用户名和密码往往保存在数据库中。

4、在配置文件中配置的密码使用明文，这非常不安全，而真实生产环境下密码需要进行加密。

本章节需要对这些问题进行改进。

#### 配置可匿名访问的资源

第一步：在项目中创建pages目录，在pages目录中创建a.html和b.html

第二步：在spring-security.xml文件中配置，指定哪些资源可以匿名访问

```xml
<!--
  http：用于定义相关权限控制
  指定哪些资源不需要进行权限校验，可以使用通配符
-->
<security:http security="none" pattern="/pages/a.html" />
<security:http security="none" pattern="/paegs/b.html" />
<security:http security="none" pattern="/pages/**"></security:http>
```

通过上面的配置可以发现，pages目录下的文件可以在没有认证的情况下任意访问。

#### 使用指定的登录页面

第一步：提供login.html作为项目的登录页面

```html
<html>
<head>
    <title>登录</title>
</head>
<body>
    <form action="/login.do" method="post">
        username：<input type="text" name="username"><br>
        password：<input type="password" name="password"><br>
        <input type="submit" value="submit">
    </form>
</body>
</html>
```

第二步：修改spring-security.xml文件，指定login.html页面可以匿名访问

```xml
<security:http security="none" pattern="/login.html" />
```

第三步：修改spring-security.xml文件，加入表单登录信息的配置

```xml
<!--
  form-login：定义表单登录信息
-->
<security:form-login login-page="/login.html"
                     username-parameter="username"
                     password-parameter="password"
                     login-processing-url="/login.do"
                     default-target-url="/index.html"
                     authentication-failure-url="/login.html"
                     />
```

第四步：修改spring-security.xml文件，关闭CsrfFilter过滤器

```xml
<!--
  csrf：对应CsrfFilter过滤器
  disabled：是否启用CsrfFilter过滤器，如果使用自定义登录页面需要关闭此项，否则登录操作会被禁用（403）
-->
<security:csrf disabled="true"></security:csrf>
```

#### 从数据库查询用户信息

如果我们要从数据库动态查询用户信息，就必须按照spring security框架的要求提供一个实现UserDetailsService接口的实现类，并按照框架的要求进行配置即可。框架会自动调用实现类中的方法并自动进行密码校验。

实现类代码：

```java
package top.hellocode.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService implements UserDetailsService {
    //模拟数据库中的用户数据
    public  static  Map<String, top.hellocode.pojo.User> map = new HashMap<>();
    static {
        top.hellocode.pojo.User user1 = new top.hellocode.pojo.User();
        user1.setUsername("admin");
        user1.setPassword("admin");

        top.hellocode.pojo.User user2 = new top.hellocode.pojo.User();
        user2.setUsername("xiaoming");
        user2.setPassword("1234");

        map.put(user1.getUsername(),user1);
        map.put(user2.getUsername(),user2);
    }
    /**
     * 根据用户名加载用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username:" + username);
        top.hellocode.pojo.User userInDb = map.get(username);//模拟根据用户名查询数据库
        if(userInDb == null){
            //根据用户名没有查询到用户
            return null;
        }

        //模拟数据库中的密码，后期需要查询数据库
        String passwordInDb = "{noop}" + userInDb.getPassword();

        List<GrantedAuthority> list = new ArrayList<>();
        //授权，后期需要改为查询数据库动态获得用户拥有的权限和角色
        list.add(new SimpleGrantedAuthority("add"));
        list.add(new SimpleGrantedAuthority("delete"));
        list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
  
        UserDetails user = new User(username,passwordInDb,list);
        return user;
    }
}
```

spring-security.xml：

```xml
<!--
  authentication-manager：认证管理器，用于处理认证操作
-->
<security:authentication-manager>
  <!--
    authentication-provider：认证提供者，执行具体的认证逻辑
  -->
  <security:authentication-provider user-service-ref="userService">
  </security:authentication-provider>
</security:authentication-manager>

<bean id="userService" class="top.hellocode.security.UserService"></bean>
```

本章节我们提供了UserService实现类，并且按照框架的要求实现了UserDetailsService接口。在spring配置文件中注册UserService，指定其作为认证过程中根据用户名查询用户信息的处理类。当我们进行登录操作时，spring security框架会调用UserService的loadUserByUsername方法查询用户信息，并根据此方法中提供的密码和用户页面输入的密码进行比对来实现认证操作。

#### 对密码进行加密

前面我们使用的密码都是明文的，这是非常不安全的。一般情况下用户的密码需要进行加密后再保存到数据库中。

常见的密码加密方式有：

3DES、AES、DES：使用对称加密算法，可以通过解密来还原出原始密码

MD5、SHA1：使用单向HASH算法，无法通过计算还原出原始密码，但是可以建立彩虹表进行查表破解

bcrypt：将salt随机并混入最终加密后的密码，验证时也无需单独提供之前的salt，从而无需单独处理salt问题

加密后的格式一般为：

```
$2a$10$/bTVvqqlH9UiE0ZJZ7N2Me3RIgUCdgMheyTgV0B4cMCSokPa.6oCa
```

加密后字符串的长度为固定的60位。其中：$是分割符，无意义；2a是bcrypt加密版本号；10是cost的值；而后的前22位是salt值；再然后的字符串就是密码的密文了。

**实现步骤**

第一步：在spring-security.xml文件中指定密码加密对象

```xml
<!--配置密码加密对象-->
<bean id="passwordEncoder" 
      class="org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder" />

<!--认证管理器，用于处理认证操作-->
<security:authentication-manager>
  <!--认证提供者，执行具体的认证逻辑-->
  <security:authentication-provider user-service-ref="userService">
    <!--指定密码加密策略-->
    <security:password-encoder ref="passwordEncoder" />
  </security:authentication-provider>
</security:authentication-manager>
<!--开启spring注解使用-->
<context:annotation-config></context:annotation-config>
```

第二步：修改UserService实现类

```java
package top.hellocode.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService implements UserDetailsService {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public  Map<String, top.hellocode.pojo.User> map = new HashMap<>();//模拟数据库中的用户数据

    public void initData(){
        top.hellocode.pojo.User user1 = new top.hellocode.pojo.User();
        user1.setUsername("admin");
        user1.setPassword(passwordEncoder.encode("admin"));

        top.hellocode.pojo.User user2 = new top.hellocode.pojo.User();
        user2.setUsername("xiaoming");
        user2.setPassword(passwordEncoder.encode("1234"));

        map.put(user1.getUsername(),user1);
        map.put(user2.getUsername(),user2);
    }
    /**
     * 根据用户名加载用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        initData();
        System.out.println("username:" + username);
        top.hellocode.pojo.User userInDb = map.get(username);//模拟根据用户名查询数据库
        if(userInDb == null){
            //根据用户名没有查询到用户
            return null;
        }

        String passwordInDb = userInDb.getPassword();//模拟数据库中的密码，后期需要查询数据库

        List<GrantedAuthority> list = new ArrayList<>();
        //授权，后期需要改为查询数据库动态获得用户拥有的权限和角色
        list.add(new SimpleGrantedAuthority("add"));
        list.add(new SimpleGrantedAuthority("delete"));
        list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        UserDetails user = new User(username,passwordInDb,list);
        return user;
    }
}
```

#### 配置多种校验规则

为了测试方便，首先在项目中创建a.html、b.html、c.html、d.html几个页面

修改spring-security.xml文件：

```xml
<!--只要认证通过就可以访问-->
<security:intercept-url pattern="/index.jsp"  access="isAuthenticated()" />
<security:intercept-url pattern="/a.html"  access="isAuthenticated()" />

<!--拥有add权限就可以访问b.html页面-->
<security:intercept-url pattern="/b.html"  access="hasAuthority('add')" />

<!--拥有ROLE_ADMIN角色就可以访问c.html页面-->
<security:intercept-url pattern="/c.html"  access="hasRole('ROLE_ADMIN')" />

<!--拥有ROLE_ADMIN角色就可以访问d.html页面，
    注意：此处虽然写的是ADMIN角色，框架会自动加上前缀ROLE_-->
<security:intercept-url pattern="/d.html"  access="hasRole('ADMIN')" />
```

#### 注解方式权限控制

Spring Security除了可以在配置文件中配置权限校验规则，还可以使用注解方式控制类中方法的调用。例如Controller中的某个方法要求必须具有某个权限才可以访问，此时就可以使用Spring Security框架提供的注解方式进行控制。

**实现步骤**

第一步：在spring-security.xml文件中配置组件扫描，用于扫描Controller

```xml
<mvc:annotation-driven></mvc:annotation-driven>
<context:component-scan base-package="top.hellocode.controller"></context:component-scan>
```

第二步：在spring-security.xml文件中开启权限注解支持

```xml
<!--开启注解方式权限控制-->
<security:global-method-security pre-post-annotations="enabled" />
```

第三步：创建Controller类并在Controller的方法上加入注解进行权限控制

```java
package top.hellocode.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('add')")//表示用户必须拥有add权限才能调用当前方法
    public String add(){
        System.out.println("add...");
        return "success";
    }

    @RequestMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")//表示用户必须拥有ROLE_ADMIN角色才能调用当前方法
    public String delete(){
        System.out.println("delete...");
        return "success";
    }
}
```

#### 退出登录

用户完成登录后Spring Security框架会记录当前用户认证状态为已认证状态，即表示用户登录成功了。那用户如何退出登录呢？我们可以在spring-security.xml文件中进行如下配置：

```xml
<!--
  logout：退出登录
  logout-url：退出登录操作对应的请求路径
  logout-success-url：退出登录后的跳转页面
-->
<security:logout logout-url="/logout.do" 
                 logout-success-url="/login.html" invalidate-session="true"/>
   
```

通过上面的配置可以发现，如果用户要退出登录，只需要请求/logout.do这个URL地址就可以，同时会将当前session失效，最后页面会跳转到login.html页面。

#### 显示用户名

前面我们已经完成了认证和授权操作，如果用户认证成功后需要在页面展示当前用户的用户名。Spring Security在认证成功后会将用户信息保存到框架提供的上下文对象中，所以此处我们就可以调用Spring Security框架提供的API获取当前用户的username并展示到页面上。

实现步骤：

第一步：在main.html页面中修改，定义username模型数据基于VUE的数据绑定展示用户名，发送ajax请求获取username

```html
<script>
    new Vue({
        el: '#app',
        data:{
            username:null,//用户名
            menuList:[]
        },
        created(){
            //发送请求获取当前登录用户的用户名
            axios.get('/user/getUsername.do').then((response)=>{
                this.username = response.data.data;
            });
        }
    });
</script>
<div class="avatar-wrapper">
  <img src="../img/user2-160x160.jpg" class="user-avatar">
  <!--展示用户名-->
  {{username}}
</div>
```

第二步：创建UserController并提供getUsername方法

```java
package top.hellocode.controller;

import top.hellocode.constant.MessageConstant;
import top.hellocode.entity.Result;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    //获取当前登录用户的用户名
    @RequestMapping("/getUsername")
    public Result getUsername()throws Exception{
        try{
            org.springframework.security.core.userdetails.User user =
                    (org.springframework.security.core.userdetails.User)
                    SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return new Result(true, MessageConstant.GET_USERNAME_SUCCESS,user.getUsername());
        }catch (Exception e){
            return new Result(false, MessageConstant.GET_USERNAME_FAIL);
        }
    }
}
```

## 图形报表ECharts

### ECharts简介

ECharts缩写来自Enterprise Charts，商业级数据图表，是百度的一个开源的使用JavaScript实现的数据可视化工具，可以流畅的运行在 PC 和移动设备上，兼容当前绝大部分浏览器（IE8/9/10/11，Chrome，Firefox，Safari等），底层依赖轻量级的矢量图形库 [ZRender](https://github.com/ecomfe/zrender)，提供直观、交互丰富、可高度个性化定制的数据可视化图表。

- 官网：[https://echarts.apache.org/zh/index.html](https://echarts.apache.org/zh/index.html)
- 下载地址：[https://echarts.apache.org/zh/download.html](https://echarts.apache.org/zh/download.html)

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/1281911460.png)

解压下载的zip文件：

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/391754680.png)

我们只需要将dist目录下的echarts.js文件引入到页面上就可以使用了

### 2.2 5分钟上手ECharts

我们可以参考官方提供的5分钟上手ECharts文档感受一下ECharts的使用方式，地址如下：

[https://echarts.apache.org/handbook/zh/get-started/](https://echarts.apache.org/handbook/zh/get-started/)

第一步：创建html页面并引入echarts.js文件

```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <!-- 引入 ECharts 文件 -->
    <script src="echarts.js"></script>
</head>
</html>
```

第二步：在页面中准备一个具备宽高的DOM容器。

```html
<body>
    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="main" style="width: 600px;height:400px;"></div>
</body>
```

第三步：通过echarts.init方法初始化一个 echarts 实例并通过setOption方法生成一个简单的柱状图

```html
<script type="text/javascript">
  // 基于准备好的dom，初始化echarts实例
  var myChart = echarts.init(document.getElementById('main'));

  // 指定图表的配置项和数据
  var option = {
    title: {
      text: 'ECharts 入门示例'
    },
    tooltip: {},
    legend: {
      data:['销量']
    },
    xAxis: {
      data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
    },
    yAxis: {},
    series: [{
      name: '销量',
      type: 'bar',
      data: [5, 20, 36, 10, 10, 20]
    }]
};

// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option);
</script>
```

效果如下：

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/1827952881.png)

### 2.3 查看ECharts官方实例

ECharts提供了很多官方实例，我们可以通过这些官方实例来查看展示效果和使用方法。

官方实例地址：[https://echarts.apache.org/examples/zh/index.html](https://echarts.apache.org/examples/zh/index.html)

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/978509396.png)

可以点击具体的一个图形会跳转到编辑页面，编辑页面左侧展示源码（js部分源码），右侧展示图表效果，如下：

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/1059957558.png)

要查看完整代码可以点击右下角的Download按钮将完整页面下载到本地。

通过官方案例我们可以发现，使用ECharts展示图表效果，关键点在于确定此图表所需的数据格式，然后按照此数据格式提供数据就可以了，我们无须关注效果是如何渲染出来的。

在实际应用中，我们要展示的数据往往存储在数据库中，所以我们可以发送ajax请求获取数据库中的数据并转为图表所需的数据即可。

## 常见的PDF报表生成方式

### iText

iText是著名的开放源码的站点sourceforge一个项目，是用于生成PDF文档的一个java类库。通过iText不仅可以生成PDF或rtf的文档，而且可以将XML、Html文件转化为PDF文件。 iText的安装非常方便，下载iText.jar文件后，只需要在系统的CLASSPATH中加入iText.jar的路径，在程序中就可以使用iText类库了。

maven坐标：

```xml
<dependency>
  <groupId>com.lowagie</groupId>
  <artifactId>itext</artifactId>
  <version>2.1.7</version>
</dependency>
```

示例代码:

```java
package top.hellocode.app;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ItextDemo {
    public static void main(String[] args) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("D:\\test.pdf"));
            document.open();
            document.add(new Paragraph("hello itext"));
            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
```

### JasperReports

JasperReports是一个强大、灵活的报表生成工具，能够展示丰富的页面内容，并将之转换成PDF，HTML，或者XML格式。该库完全由Java写成，可以用于在各种Java应用程序，包括J2EE，Web应用程序中生成动态内容。一般情况下，JasperReports会结合Jaspersoft Studio(模板设计器)使用导出PDF报表。

maven坐标:

```xml
<dependency>
  <groupId>net.sf.jasperreports</groupId>
  <artifactId>jasperreports</artifactId>
  <version>6.8.0</version>
</dependency>
```

### JasperReports快速体验

本小节我们先通过一个快速体验来感受一下JasperReports的开发过程。

第一步：创建maven工程，导入JasperReports的maven坐标

```xml
<dependency>
  <groupId>net.sf.jasperreports</groupId>
  <artifactId>jasperreports</artifactId>
  <version>6.8.0</version>
</dependency>
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
</dependency>
```

第二步：将提前准备好的jrxml文件复制到maven工程中(后面会详细讲解如何创建jrxml文件)

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/289764666.png)

第三步：编写单元测试，输出PDF报表

```java
@Test
public void testJasperReports()throws Exception{
    String jrxmlPath = 
        "D:\\ideaProjects\\projects111\\jasperdemo\\src\\main\\resources\\demo.jrxml";
    String jasperPath = 
        "D:\\ideaProjects\\projects111\\jasperdemo\\src\\main\\resources\\demo.jasper";

    //编译模板
    JasperCompileManager.compileReportToFile(jrxmlPath,jasperPath);

    //构造数据
    Map paramters = new HashMap();
    paramters.put("reportDate","2019-10-10");
    paramters.put("company","itcast");
    List<Map> list = new ArrayList();
    Map map1 = new HashMap();
    map1.put("name","xiaoming");
    map1.put("address","beijing");
    map1.put("email","xiaoming@itcast.cn");
    Map map2 = new HashMap();
    map2.put("name","xiaoli");
    map2.put("address","nanjing");
    map2.put("email","xiaoli@itcast.cn");
    list.add(map1);
    list.add(map2);

    //填充数据
    JasperPrint jasperPrint = 
        JasperFillManager.fillReport(jasperPath, 
                                     paramters, 
                                     new JRBeanCollectionDataSource(list));

    //输出文件
    String pdfPath = "D:\\test.pdf";
    JasperExportManager.exportReportToPdfFile(jasperPrint,pdfPath);
}
```

### JasperReports原理

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/165516908.png)

- JRXML：报表填充模板，本质是一个xml文件
- Jasper：由JRXML模板编译成的二进制文件，用于代码填充数据
- Jrprint：当用数据填充完Jasper后生成的对象，用于输出报表
- Exporter：报表输出的管理类，可以指定要输出的报表为何种格式
- PDF/HTML/XML：报表形式

### 开发流程

使用JasperReports导出pdf报表，开发流程如下：

1. 制作报表模板
2. 模板编译
3. 构造数据
4. 填充数据
5. 输出文件

### 模板设计器Jaspersoft Studio

Jaspersoft Studio是一个图形化的报表设计工具，可以非常方便的设计出PDF报表模板文件(其实就是一个xml文件)，再结合JasperReports使用，就可以渲染出PDF文件。

下载地址:https://community.jaspersoft.com/community-download

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/3453559484.png)

下载完成后直接双击安装即可。

### Jaspersoft Studio面板介绍

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/3717668329.png)

### 创建工程和模板文件

可以看到创建处理的模板文件后缀为jrxml，从设计区面板可以看到如下效果：

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/2893913810.png)

可以看到整个文件是可视化的，分为几大区域（Title、Page Header、Column Header等），如果某些区域不需要也可以删除。

在面板左下角可以看到有三种视图方式：Design（设计模式）、Source（源码模式）、Preview（预览模式）:

- 通过Design视图可以看到模板的直观结构和样式
- 通过Source视图可以看到文件xml源码
- 通过Preview视图可以预览PDF文件输出后的效果

通过右侧Palette窗口可以看到常用的元素：

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/3402909117.png)

### 3.3 设计模板文件

#### 增减Band

可以根据情况删除或者增加模板文件中的区域（称为Band），例如在Page Header区域上点击右键，选择删除菜单：

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/544407004.png)

其中Detail区域可以添加多个，其他区域只能有一个。

#### 将元素应用到模板中

##### 3.3.2.1 Image元素

从右侧Palette面板中选择Image元素（图片元素），拖动到Title区域：

弹出如下对话框，有多种创建模式，选择URL模式，并在下面输入框中输入一个网络图片的连接地址：

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/3863335737.png)

可以选中图片元素，鼠标拖动调整位置，也可以通过鼠标调整图片的大小。

调整完成后，可以点击Preview进入预览视图，查看PDF输出效果：

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/1290706619.png)

点击Source进入源码视图，查看xml文件内容：

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/2581496444.png)

其实我们上面创建的demo1.jrxml模板文件，本质上就是一个xml文件，只不过我们不需要自己编写xml文件的内容，而是通过Jaspersoft Studio这个设计器软件进行可视化设计即可。

##### 3.3.2.2 Static Text元素

Static Text元素就是静态文本元素，用于在PDF文件上展示静态文本信息：

双击Title面板中的Static Text元素，可以修改文本内容：

选中元素，也可以调整文本的字体和字号：

点击Preview进入预览视图，查看效果：

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/4025172475.png)

##### 3.3.2.3 Current Date元素

Current Date元素用于在报表中输出当前系统日期，将改元素拖动到Title区域：

预览输出效果：

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/652325389.png)

默认日期输出格式如上图所示，可以回到设计视图并选中元素，在Properties面板中的Text Field子标签中修改日期输出格式：

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/7131139.png)

#### 动态数据填充

上面我们在PDF文件中展示的都是一些静态数据，那么如果需要动态展示一些数据应该如何实现呢？我们可以使用Outline面板中的Parameters和Fields来实现。

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/971044182.png)

Parameters通常用来展示单个数据，Fields通常用来展示需要循环的列表数据。

##### 3.3.3.1 Parameters

在Parameters上点击右键，创建一个Parameter参数：

可以在右侧的Properties面板中修改刚才创建的参数名称：

将刚才创建的Parameter参数拖动到面板中：

进入预览视图，查看效果：

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/1623310681.png)

由于模板中我们使用了Parameter动态元素，所以在预览之前需要为其动态赋值：

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/3725935616.png)

注意：由于我们是在Jaspersoft Studio软件中进行预览，所以需要通过上面的输入框动态为Parameter赋值，在后期项目使用时，需要我们在Java程序中动态为Parameter赋值进行数据填充。

##### 3.3.3.2 Fields

使用Fields方式进行数据填充，既可以使用jdbc数据源方式也可以使用JavaBean数据源方式。

- jdbc数据源数据填充

第一步：在Repository Explorer面板中，在Data Adapters点击右键，创建一个数据适配器

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/2096239539.png)

第二步：选择Database JDBC Connection

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/2719606914.png)

第三步：选择mysql数据库，并完善jdbc连接信息

为了能够在Jaspersoft Studio中预览到数据库中的数据，需要加入MySQL的驱动包

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/1316607899.png)

第四步：在Outline视图中，右键点击工程名，选择Database and Query菜单

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/2043591977.png)

第五步：在弹出的对话框中选择刚刚创建的JDBC数据库连接选项

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/1678093451.png)

第六步：在弹出对话框中Language选择sql，在右侧区域输入SQL语句并点击Read Fields按钮

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/52794595.png)

可以看到通过点击上面的Read Fields按钮，已经读取到了t_setmeal表中的所有字段信息并展示在了下面，这些字段可以根据需要进行删除或者调整位置

第七步：在Outline视图中的Fields下可以看到t_setmeal表中相关字段信息，拖动某个字段到设计区的Detail区域并调整位置

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/417195627.png)

可以看到，在拖动Fields到设计区时，同时会产生两个元素，一个是静态文本，一个是动态元素。静态文本相当于表格的表头，可以根据需要修改文本内容。最终设计完的效果如下：

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/3426481135.png)

第八步：使用Preview预览视图进行预览

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/2911158399.png)

通过上图可以看到，虽然列表数据展示出来了，但是展示的还存在问题。在每条数据遍历时表头也跟着遍历了一遍。这是怎么回事呢？这是由于我们设计的表头和动态Fields都在Detail区域。为了能够解决上面的问题，需要将表头放在Column Header区域，将动态Fields放在Detail区域。

- JavaBean数据源数据填充

第一步：在Fields处点击右键创建新的Field

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/1140777853.png)

创建完成后在Properties属性面板中修改Field的名称

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/3203685800.png)

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/3131465396.png)

第二步：将创建的Fields拖动到Detail区域并调整好位置

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/1934226664.png)

注意：使用此种JavaBean数据源数据填充方式，无法正常进行预览，因为这些动态Fields需要在Java程序中动态进行数据填充。

### 结合JasperReports输出报表

前面我们已经使用Jaspersoft Studio设计了两个模板文件：demo1.jrxml和demo2.jrxml。其中demo1.jrxml的动态列表数据是基于JDBC数据源方式进行数据填充，demo2.jrxml的动态列表数据是基于JavaBean数据源方式进行数据填充。本小节我们就结合JasperReports的Java API来完成pdf报表输出。

#### JDBC数据源方式填充数据

第一步：创建maven工程，导入相关maven坐标

```xml
<dependency>
    <groupId>net.sf.jasperreports</groupId>
    <artifactId>jasperreports</artifactId>
    <version>6.8.0</version>
</dependency>
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.47</version>
</dependency>
```

第二步：将设计好的demo1.jrxml文件复制到当前工程的resources目录下

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/392466260.png)

第三步：编写单元测试

```java
@Test
public void testReport_JDBC() throws Exception{
    Class.forName("com.mysql.jdbc.Driver");
    Connection connection = 
        DriverManager.getConnection("jdbc:mysql://localhost:3306/health", 
                                    "root", 
                                    "root");

    String jrxmlPath = "D:\\ideaProjects\\projects111\\jasperreports_test\\src\\main\\resources\\demo1.jrxml";
    String jasperPath = "D:\\ideaProjects\\projects111\\jasperreports_test\\src\\main\\resources\\demo1.jasper";

    //编译模板
    JasperCompileManager.compileReportToFile(jrxmlPath,jasperPath);

    //构造数据
    Map paramters = new HashMap();
    paramters.put("company","传智播客");

    //填充数据---使用JDBC数据源方式填充
    JasperPrint jasperPrint = 
        JasperFillManager.fillReport(jasperPath, 
                                    paramters, 
                                    connection);
    //输出文件
    String pdfPath = "D:\\test.pdf";
    JasperExportManager.exportReportToPdfFile(jasperPrint,pdfPath);
}
```

通过上面的操作步骤可以输出pdf文件，但是中文的地方无法正常显示。这是因为JasperReports默认情况下对中文支持并不友好，需要我们自己进行修复。具体操作步骤如下：

1、在Jaspersoft Studio中打开demo1.jrxml文件，选中中文相关元素，统一将字体设置为“华文宋体”并将修改后的demo1.jrxml重新复制到maven工程中

2、将本章资源/解决中文无法显示问题目录下的文件复制到maven工程的resources目录中

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/1539285360.png)

按照上面步骤操作后重新执行单元测试导出PDF文件：

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/623541376.png)

#### JavaBean数据源方式填充数据

第一步：为了能够避免中文无法显示问题，首先需要将demo2.jrxml文件相关元素字体改为“华文宋体”并将demo2.jrxml文件复制到maven工程的resources目录下

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/1716945680.png)

第二步：编写单元测试方法输出PDF文件

```java
@Test
public void testReport_JavaBean() throws Exception{
    String jrxmlPath = "D:\\ideaProjects\\projects111\\jasperreports_test\\src\\main\\resources\\demo2.jrxml";
    String jasperPath = "D:\\ideaProjects\\projects111\\jasperreports_test\\src\\main\\resources\\demo2.jasper";

    //编译模板
    JasperCompileManager.compileReportToFile(jrxmlPath,jasperPath);

    //构造数据
    Map paramters = new HashMap();
    paramters.put("company","传智播客");

    List<Map> list = new ArrayList();
    Map map1 = new HashMap();
    map1.put("tName","入职体检套餐");
    map1.put("tCode","RZTJ");
    map1.put("tAge","18-60");
    map1.put("tPrice","500");

    Map map2 = new HashMap();
    map2.put("tName","阳光爸妈老年健康体检");
    map2.put("tCode","YGBM");
    map2.put("tAge","55-60");
    map2.put("tPrice","500");
    list.add(map1);
    list.add(map2);

    //填充数据---使用JavaBean数据源方式填充
    JasperPrint jasperPrint = 
        JasperFillManager.fillReport(jasperPath, 
                                     paramters, 
                                     new JRBeanCollectionDataSource(list));
    //输出文件
    String pdfPath = "D:\\test.pdf";
    JasperExportManager.exportReportToPdfFile(jasperPrint,pdfPath);
}
```

查看输出效果：

![请输入图片描述](https://www.hellocode.top/usr/uploads/2022/09/2444095922.png)

