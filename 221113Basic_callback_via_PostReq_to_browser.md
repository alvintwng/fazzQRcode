### Callback via PostReq to browser
* Operating Sys: Windows 10 Pro
* IDE used: **Spring Tool Suites4**
* Java: JDK17, but config to JDK1.8
* File > New > Spring Starter Project
* Fill info such as:  Name: [*postReq1*]; Java Version : `8`;  Package: `War`
* Next > select `Spring Web` > Finish
``` console
 Directory of C:\xxx\postReq1\src\main\java\com\zero1

[.]                        [..]                       CallString.java            HomeController.java
PostReq1Application.java   ServletInitializer.java    SpringConfig.java
```
note that `ServletInitializer.java` may not necessary just to run within Spring.

---
### create War/Jar

- Right click project then click `Run As`
- Maven Build
- And you should type **`package`** in input area then run.
- `/path/your/project/target/` there is your `.war` file with dependencies.
- execute by **`java -jar`** [jar/war file]

* CommandPromtA:
``` console
C:\xxx\postReq1\target>dir /w
...
 Directory of C:\xxxg\postReq1\target

[.]                          [..]                         [classes]                    [generated-sources]
[generated-test-sources]     [m2e-wtp]                    [maven-archiver]             [maven-status]
[postReq1-jdk8]              postReq1-jdk8.war            postReq1-jdk8.war.original   [surefire-reports]
[test-classes]
              ...

C:\xxx\postReq1\target>java -jar postReq1-jdk8.war

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.7.5)
 ...
 ... : Tomcat started on port(s): 8080 (http) with context path '' 
 ... 
```


 
* browser input: `http://localhost:8080/hello?name=John`
  * browser shown : `Hello John!`

* CommandPromptB input: 
  ``` console
  C:\xxx>ccurl -X POST -H "Content-type: application/json" -d "Orchard Road" "http://localhost:8080/response/postbody"
  ```
  * CommandPromptA shown: `>>>>>>>> Orchard Road`
* browser input: `http://localhost:8080/view`
  * browser shown :` Hello Orchard Road!`
  * CommandPromptA shown: `>>> Orchard Road
  
### shut down project running in Server
* Closed the command prompt to exit.

---
## code
#### CallString.java
This is standard java application.
``` java
package com.zero1;
public class CallString {
	private String obj;
	public String getObj() {
		return obj;
	}
	public void setObj(String obj) {
		this.obj = obj;
	}
	public static int index = 0;
}
```
#### HomeController.java
Http postmapping by `/response`
``` java
package com.zero1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/response")
public class HomeController {
	
	@Autowired
	CallString callString;

	@PostMapping("/postbody")
	public String postBody(@RequestBody String fullPost) {

		callString.setObj(fullPost);

		System.out.println(">>>>>>>> " + fullPost);

		return "Hello " + fullPost;
	}
}
```
#### PostReq1Application.java
This code initialed by SpringTool prior to edit.
``` java
package com.zero1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PostReq1Application {
	public static void main(String[] args) {
		SpringApplication.run(PostReq1Application.class, args);
	}

	@Autowired
	CallString callString;

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@GetMapping("/view")
	public String view(@RequestParam(value = "name", defaultValue = "World") String name) {
		System.out.println(">>> " + callString.getObj());
		return String.format("Hello %s!", callString.getObj());
	}
}
```
#### ServletInitializer.java
This code generated by SpringTool, may not necessary to use.
``` java
package com.zero1;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
public class ServletInitializer extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PostReq1Application.class);
	}
}
```
#### SpringConfig.java
create `java bean`
``` java
package com.zero1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class SpringConfig {
	@Bean
	public CallString callString() {
		CallString cs = new CallString();
		return cs;
	}
}
```
#### pom.xml
This code should generated by SpringTool
``` xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.7.5</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.zero1</groupId>
	<artifactId>postReq1</artifactId>
	<version>jdk8</version>
	<packaging>war</packaging>
	<name>postReq1</name>
	<description>Callback Spring Boot</description>
	<properties>
		<java.version>1.8</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-tomcat</artifactId>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>
```

---