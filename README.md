Dependency Injection
------------------
#### 标准规范
- [JSR 330: Dependency Injection for Java](https://jcp.org/en/jsr/detail?id=330) - Final Release 2009-10-14
- [JSR 299: Contexts and Dependency Injection for the Java EE 1.0](https://jcp.org/en/jsr/detail?id=299) - Final Release 2009-12-10
- [JSR 346: Contexts and Dependency Injection for the Java EE 1.1](https://jcp.org/en/jsr/detail?id=346) - Final Release 2013-05-24
- [JSR 365: Contexts and Dependency Injection for the Java 2.0](https://jcp.org/en/jsr/detail?id=365) - Early Draft Review 2015-07-08
- [JSR 250: Common Annotations for the Java Platform](https://jcp.org/en/jsr/detail?id=250) - Final Release 2006-05-11
- [JSR 308: Annotations on Java Types](https://jcp.org/en/jsr/detail?id=308) - Final Release 2014-03-04
- [JSR 305: Annotations for Software Defect Detection](https://jcp.org/en/jsr/detail?id=305) - Expert Group Formation 2006-09-12

#### [API](http://www.cdi-spec.org)
- 2009-10-14 javax.inject:javax.inject:1
- 2013-04-27 javax.annotation:javax.annotation-api:1.2
- 2007-05-16 javax.annotation:jsr250-api:1.0
- 2015-06-30 javax.enterprise:cdi-api:2.0-EDR1
- 2014-04-11 javax.enterprise:cdi-api:1.2
- 2013-04-11 javax.enterprise:cdi-api:1.1
- 2010-12-17 javax.enterprise:cdi-api:1.0-SP4
- 2010-12-17 javax.enterprise:cdi-api:1.0-SP4

#### 实现
- [Weld 是 CDI 的参考实现](https://jersey.java.net) - 2015-12-08 org.jboss.weld:weld-core:2.3.2.Final
  Weld 是 JSR-299 的参考实现，同时也是 JBoss Seam 的核心组件。它不仅可以在 JBoss、Glassfish 等应用服务器上使用，也可以在 Tomcat、Jetty 等 Servlet 容器中使用，甚至普通 Java SE 程序也可以使用 Weld。在不同环境中使用 Weld，配置和使用的方法都不相同。应用服务器上的 CDI 功能最为完整，到了 Servlet 容器中就要打个折扣， Java SE 中的 Weld，功能最受限制。

#### 友情链接
- 2012-05-29 [Java EE CDI 依赖注入 (@Inject) 教程](http://www.oschina.net/translate/java-ee-cdi-dependency-injection-inject-tutorial)
- 2013-01-07 [IBM: 利用 CDI 和 JPA 开发 Servlet 应用](https://www.ibm.com/developerworks/cn/java/j-lo-cdijpa/)
- 2014-12-29 [Java CDI Dependency Injection Example](http://buraktas.com/java-cdi-dependency-injection-example/)
- 2015-03-02 [CDI Dependency Injection @PostConstruct and @PreDestroy Example](http://buraktas.com/cdi-dependency-injection-postconstruct-predestroy-example/)
- 2012-05-24 [Tutorial – Introduction to CDI – Contexts and Dependency Injection for Java EE (JSR 299)](https://jaxenter.com/tutorial-introduction-to-cdi-contexts-and-dependency-injection-for-java-ee-jsr-299-104536.html)