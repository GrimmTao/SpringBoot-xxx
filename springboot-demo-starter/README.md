# SpringBoot 自动装配

在我看来，自动装配可以简单理解为：通过注解或者一些简单的配置就能在 Spring Boot 的帮助下实现某块功能。

SpringBoot实现自动装配的原理
核心注解@SpringBootApplication,可以分为三部分

![image.png](https://upload-images.jianshu.io/upload_images/25046096-a11a6e8eb8cdba5c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


**1. @EnableAutoConfiguration**：启用 SpringBoot 的自动配置机制，是实现自动装配的核心注解;

![image.png](https://upload-images.jianshu.io/upload_images/25046096-bb52b82f47e1ccba.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

自动装配的核心功能实际通过AutoConfigurationImportSelector类实现，该类实现了 ImportSelector接口，也就实现了这个接口中的 selectImports方法，该方法主要用于获取所有符合条件的类的全限定类名，这些类需要被加载到 IoC 容器中。

![image.png](https://upload-images.jianshu.io/upload_images/25046096-73d9d227606367d7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

getAutoConfigurationEntry()方法，主要负责加载自动配置。该方法调用链接如下：

![image.png](https://upload-images.jianshu.io/upload_images/25046096-6b1fb2a9d3b1cd1b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

剖析getAutoConfigurationEntry()方法

![image.png](https://upload-images.jianshu.io/upload_images/25046096-a30e7f32cc621190.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

第1步：判断自动装配开关是否打开。默认spring.boot.enableautoconfiguration=true，可在 application.properties 或 application.yml 中设置；

第2部：获取EnableAutoConfiguration注解中的 exclude 和 excludeName；

第3部：获取需要自动装配的所有配置类，读取META-INF/spring.factories

第4部：spring.factories中这么多配置，每次启动都要全部加载么？答案是否定的，因为，这一步有经历了一遍筛选，@ConditionalOnXXX 中的所有条件都满足，该类才会生效。

**2. @SpringBootConfiguration-@Configuration**：允许在上下文中注册额外的 bean 或导入其他配置类;

**3. @ComponentScan**：扫描被@Component (@Service,@Controller)注解的 bean，注解默认会扫描启动类所在的包下所有的类 ，可以自定义不扫描某些 bean。如下图所示，容器中将排除TypeExcludeFilter 和 AutoConfigurationExcludeFilter。

![image.png](https://upload-images.jianshu.io/upload_images/25046096-a11a6e8eb8cdba5c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

那么如何实现一个自定义的starter呢？步骤如下：

### step1
创建自己的springboot-starter工程

### step2
引入springboot starter相关依赖
```java
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
    <version>2.4.2</version>
</dependency>
```

### step3
创建自定义的Configuration，如本例中的CustomConfiguration.java 和 ThreadPoolAutoConfiguration.java

### step4
在工程的 resources 包下创建META-INF/spring.factories文件，并在里面需要自动装配的Configuration
```
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
com.alex.demo.springboot.starter.config.ThreadPoolAutoConfiguration,\
com.alex.demo.springboot.starter.config.CustomConfiguration
```

这是因为：SpringBoot 定义了一套接口规范：SpringBoot 在启动时会扫描外部引用 jar 包中的META-INF/spring.factories文件，将文件中配置的类型信息加载到 Spring 容器中。并执行类中定义的各种操作。对于外部 jar 来说，只需要按照 SpringBoot 定义的标准，就能将自己的功能装置进 SpringBoot。

### step5
在需要用到的工程中，maven依赖刚才新建的sprintboot-starter工程，然后注入即可

## SpringBoot  条件注解

```java
@ConditionalOnBean：当容器里有指定 Bean 的条件下
@ConditionalOnMissingBean：当容器里没有指定 Bean 的情况下
@ConditionalOnSingleCandidate：当指定 Bean 在容器中只有一个，或者虽然有多个但是指定首选 Bean
@ConditionalOnClass：当类路径下有指定类的条件下
@ConditionalOnMissingClass：当类路径下没有指定类的条件下
@ConditionalOnProperty：指定的属性是否有指定的值
@ConditionalOnResource：类路径是否有指定的值
@ConditionalOnExpression：基于 SpEL 表达式作为判断条件
@ConditionalOnJava：基于 Java 版本作为判断条件
@ConditionalOnJndi：在 JNDI 存在的条件下差在指定的位置
@ConditionalOnNotWebApplication：当前项目不是 Web 项目的条件下
@ConditionalOnWebApplication：当前项目是 Web 项 目的条件下
```