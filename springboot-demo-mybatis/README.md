# 关于MyBatis处理枚举类的相关点

### 需求

有时一个对象的属是枚举类，而我们又不会直接将枚举类存储到数据库中，所以一般是将枚举类的某个属性作为信息存储到数据库中，此时就需要利用MyBatis的 EnumTypeHandler来实现这个功能。

### 1
需要定义一个统一的枚举类接口，详见本例中的 BaseEnum


### 2
继承 BaseTypeHandler ,实现自己的 TypeHandler,详见本例中的 BaseEnumTypeHandler

### 3
为每一个需要映射的枚举类，创建一个自身的 TypeHandler,继承BaseEnumTypeHandler，详见本例中的: RoleHandler
注意子RoleHandler上也要写上注解 
```java

@MappedTypes({ Role.class })//转换后的数据类型
@MappedJdbcTypes({ JdbcType.INTEGER })//数据库中的数据类型
```


### 4
在application.properties中配置自定义Handler的包：
```java
mybatis.type-handlers-package=com.alex.demo.mybatis.handler
```
