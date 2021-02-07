# AOP切面编程

### 依赖引入

````xml
<dependency>
     <groupId>org.springframework.boot</groupId>  
     <artifactId>spring-boot-starter-aop</artifactId>  
 </dependency>
 ````

注意：在完成了引入AOP依赖包后，不需要去做其他配置。AOP的默认配置属性中，spring.aop.auto属性默认是开启的，也就是说只要引入了AOP依赖后，默认已经增加了@EnableAspectJAutoProxy，不需要在程序主类中增加@EnableAspectJAutoProxy来启用。

### 各注解含义
* @Aspect(切面): 通常是一个类，里面可以定义切入点（Pointcut）和增强通知（Advice）
* @Pointcut(切入点): 带有通知的连接点，在程序中主要体现为书写切入点表达式和签名。

  **表达式**：@Pointcut后面括号里的内容；

  **签名**：@Pointcut下面的方法名。

* Advice(增强通知): AOP在特定的切入点上执行的增强处理
* @Before: 标识一个前置增强方法，相当于BeforeAdvice的功能，可添加JoinPoint做参数，在切入的方法执行之前，执行Befor定义的方法。
* @After: final增强，不管是抛出异常或者正常退出都会执行。可添加JoinPoint做参数，在切入点方法运行之后，执行After定义的方法。
* @AfterReturning: 后置增强，似于AfterReturningAdvice, 方法正常退出时执行，可添加JoinPoint做参数。
* @AfterThrowing: 异常抛出增强，相当于ThrowsAdvice，可添加JoinPoint做参数。
* JointPoint(连接点): 程序执行过程中明确的点，一般是方法的调用

* @Around: 环绕增强，相当于MethodInterceptor。其方法参数一定要是ProceedingJoinPoint，是JoinPoint的子类。
* ProceedingJoinPoint：用在@Around中，是JointPoint的子类，其proceed（）方法，相当于切入点的那个方法执行

* AOP Proxy：AOP框架创建的对象，代理就是目标对象的加强。Spring中的AOP代理可 以使JDK动态代理，也可以是CGLIB代理，前者基于接口，后者基于子类

### 不同 advice 的拦截顺序:
1. 同一个@Aspect，不同Advice
* 无异常：@Around（proceed())之前的部分 → @Before → 方法执行 → @Around（proceed())之后的部分 → @After → @AfterReturning

* 有异常：@Around（proceed())之前的部分 → @Before → 扔异常ing → @After → @AfterThrowing    （大概是因为方法没有跑完抛了异常，没有正确返回所有@Around的proceed()之后的部分和@AfterReturning两个注解的加强没有能够执行）

其执行流程图如下图所示：

![image.png](https://upload-images.jianshu.io/upload_images/25046096-38a894dc962d23a6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

2. 不同@Aspect、Advice
不同的Aspect里面的advice的执行顺序是不一定，像是线程一样，没有谁先谁后，除非你给他们分配优先级，同样也可以为Aspect分配优先级。实现优先级的方式有两种，不管是哪种，都是order的值越小越先执行：
* 实现org.springframework.core.Ordered接口，实现它的getOrder()方法
* 给aspect添加@Order注解，该注解全称为：org.springframework.core.annotation.Order

其中，数值越低，表明优先级越高，@Order默认为最低优先级，即最大数值：
```java
    /**
	 * Useful constant for the lowest precedence value.
	 * @see java.lang.Integer#MAX_VALUE
	 */
	int LOWEST_PRECEDENCE = Integer.MAX_VALUE;
```
其执行顺序为：
* ①入操作（Around（接入点执行前）、Before），优先级越高，越先执行；
* ②一个切面的入操作执行完，才轮到下一切面，所有切面入操作执行完，才开始执行接入点；
* ③出操作（Around（接入点执行后）、After、AfterReturning、AfterThrowing），优先级越低，越先执行；
* ④一个切面的出操作执行完，才轮到下一切面，直到返回到调用点。

如下图所示：

![image.png](https://upload-images.jianshu.io/upload_images/25046096-5ada1ec7ee06d3d2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

3. 同一个@Aspect，相同Advice
同一aspect，相同advice的执行顺序并不能直接确定，而且 @Order 在advice方法上也无效，但是有如下两种变通方式：

* 将两个 advice 合并为一个 advice，那么执行顺序就可以通过代码控制了；
* 将两个 advice 分别抽离到各自的 aspect 内，然后为 aspect 指定执行顺序。

  不建议在同一个@Aspect中有相同的Advice

