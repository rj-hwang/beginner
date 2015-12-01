## Servlet 2.x Sample

1. [生成 maven web 模块结构] [GenerateMavenWebappProject]  
> $ mvn archetype:generate -B \  
  -DgroupId=com.beginner \  
  -DartifactId=beginner-servlet2 \  
  -DarchetypeArtifactId=maven-archetype-webapp \  
  -DarchetypeCatalog=internal

2. 启动
> $ mvn jetty:run 或 $ mvn tomcat7:run

3. 访问
> [http://localhost:8080/hello](http://localhost:8080/hello)


[GenerateMavenWebappProject]: http://maven.apache.org/archetypes/maven-archetype-webapp/