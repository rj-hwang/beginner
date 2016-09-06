# maven with multiple modules

## 设置全局 UTF-8 编码
设置属性 project.build.sourceEncoding=UTF-8，这将使：
- maven-resources-plugin 以 UTF-8 编码复制 resources
- maven-compiler-plugin 视源码为 UTF-8 编码进行编译
```xml
<properties>
  <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
</properties>
```

## 设置编译的 jdk 版本
就是 maven-compiler-plugin 插件的相关配置
```xml
<properties>
  <jdk.version>1.8</jdk.version>
  <maven.compiler.source>${jdk.version}</maven.compiler.source>
  <maven.compiler.target>${jdk.version}</maven.compiler.target>
  <maven.compiler.compilerVersion>${jdk.version}</maven.compiler.compilerVersion>
</properties>
```

## BOM 的使用
BOM 即 Bill Of Materials，用于管理依赖包的版本，项目通过在 dependencyManagement 中使用 import 方式导入，导入后模块间的依赖就可以不配置 version 的值
```xml
<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>com.beginner.maven</groupId>
      <artifactId>bom</artifactId>
      <version>${bom.version}</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
  </dependencies>
</dependencyManagement>
```
注：import 只能导入 bom 的 dependencyManagement，bom 中的其它配置是不能 import 进来的，如 bom 中定义的 properties；如要将 bom 中所有配置都搞进当前项目，只能使用 maven 的 parent 继承方式才能实现。

## maven-source-plugin 的使用
maven 默认打包时是不会同时将源码打包成 jar 的，控制打包时一并打包源码需如下配置：
```xml
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-source-plugin</artifactId>
  <version>2.4</version>
  <executions>
    <execution>
      <phase>package</phase>
      <goals>
        <goal>jar-no-fork</goal>
      </goals>
    </execution>
  </executions>
</plugin>
```
注：按上述配置后，如果打包时想临时取消打包源码，增加 `-Dsource.skip=true` 参数即可。

## properties-maven-plugin 的使用
- 用于读取属性文件  
与在 pom 文件中定义 properties 相当，但读取的属性不能用来配置 dependencyManagement 的 version 值
- 用于写入所有属性到文件
```xml
<plugin>
  <groupId>org.codehaus.mojo</groupId>
  <artifactId>properties-maven-plugin</artifactId>
  <version>1.0.0</version>
  <executions>
    <!-- 从属性文件中读取属性配置 -->
    <execution>
      <id>read</id>
      <phase>initialize</phase>
      <goals>
        <goal>read-project-properties</goal>
      </goals>
      <configuration>
        <files>
          <file>project.properties</file>
        </files>
        <quiet>true</quiet>
      </configuration>
    </execution>
    <!-- 将所有属性写入文件中 -->
    <execution>
      <id>write</id>
      <phase>generate-resources</phase>
      <goals>
        <goal>write-project-properties</goal>
      </goals>
      <configuration>
        <outputFile>${project.build.directory}/project.properties</outputFile>
      </configuration>
    </execution>
  </executions>
</plugin>
```