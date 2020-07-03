# AOP切面编程

### 依赖引入

`<dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-aop</artifactId>
 </dependency>
 `

注意：在完成了引入AOP依赖包后，不需要去做其他配置。AOP的默认配置属性中，spring.aop.auto属性默认是开启的，也就是说只要引入了AOP依赖后，默认已经增加了@EnableAspectJAutoProxy，不需要在程序主类中增加@EnableAspectJAutoProxy来启用。

### 各注解含义
* @Aspect(切面): 通常是一个类，里面可以定义切入点和通知
* @Pointcut(切入点): 带有通知的连接点，在程序中主要体现为书写切入点表达式和签名。表达式就是Pointcut后面括号里的内容，签名就是Pointcut下面的方法名。
* @Before: 标识一个前置增强方法，相当于BeforeAdvice的功能
* @After: final增强，不管是抛出异常或者正常退出都会执行
* @Around: 环绕增强，相当于MethodInterceptor
* @AfterReturning: 后置增强，似于AfterReturningAdvice, 方法正常退出时执行
* @AfterThrowing: 异常抛出增强，相当于ThrowsAdvice
* JointPoint(连接点): 程序执行过程中明确的点，一般是方法的调用
* ProceedingJoinPoint：用在@Around中，是JointPoint的子类，其proceed（）方法，相当于切入点的那个方法执行
* Advice(增强通知): AOP在特定的切入点上执行的增强处理
* AOP Proxy：AOP框架创建的对象，代理就是目标对象的加强。Spring中的AOP代理可 以使JDK动态代理，也可以是CGLIB代理，前者基于接口，后者基于子类

### 不同advice 的拦截顺序:
* 无异常：@Around（proceed())之前的部分 → @Before → 方法执行 → @Around（proceed())之后的部分 → @After → @AfterReturning

* 有异常：@Around（proceed())之前的部分 → @Before → 扔异常ing → @After → @AfterThrowing    （大概是因为方法没有跑完抛了异常，没有正确返回所有@Around的proceed()之后的部分和@AfterReturning两个注解的加强没有能够织入）

