Java RESTful Web Services Tutorial for Beginner with Jersey and Tomcat
---
ref: https://www.codejava.net/java-ee/web-services/java-restful-web-services-tutorial-for-beginner-with-jersey-and-tomcat

Steps:
* Create Project & Specify Jersey Dependency
* Code Hello world RESTful Web Services
* Configure Jersey Servler Container
* TestL Chrome, curl, Postman
* Use JSON for RESTful Web Services
* Code Client program

### Create Project & Specify Jersey Dependency
- Installed Eclipse IDE - Java Enterprise version
- File > Dynamic Web Project > Project name: HelloREST > Next 
	- > tick Generate web.xml deployment descriptor > directory: `src/main/webapp` > Finish
- Right-Click HelloRest > Configure > Convert to Maven Project > Maven POM's Group Id: net.codejava.ws > Finish
- pom.xml > added in dependency glassfish.jersey...
``` xml
	<dependencies>
		<dependency>
			<groupId>org.glassfish.jersey.containers</groupId>
			<artifactId>jersey-container-servlet</artifactId>
			<version>2.29.1</version>
		</dependency>
		<dependency>
			<groupId>org.glassfish.jersey.inject</groupId>
			<artifactId>jersey-hk2</artifactId>
			<version>2.29.1</version>
		</dependency>
	</dependencies>
```

### Code Hello world RESTful Web Services
- src > New > Package > `et.codejava.ws`
- new > java class > `HelloResource`

``` java
@Path("/bonjour")
public class HelloResource {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String direBonjour() {
		return "Bonjour, tout le monde!";
	}
}
```

### Configure Jersey Servler Container
- WebContent > WEB-INF > web.xml > add
``` xml
<servlet>
    <servlet-name>Jersey REST Service</servlet-name>
    <servlet-class>org.glassfish.jersey.servlet.ServletContainer</servlet-class>
    <init-param>
        <param-name>jersey.config.server.provider.packages</param-name>
        <param-value>net.codejava.ws</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
    <servlet-name>Jersey REST Service</servlet-name>
    <url-pattern>/rest/*</url-pattern>
</servlet-mapping>
```
- Deploy on server > click "No servers are available. Click this link to create a new server..."
- `Tomcat v10.0 Server` > Next > Select `HelloREST(...` > Add > Finish
- start the server `> Tomcat v10.0 Server at localhost` > `Tomcat v10.0...` > `Start`
- can see at the console the server up and running 

### Test Chrome, curl, Postman
- Web browser, Chrome > `http://localhost:8080/HelloREST/rest/bonjour`

- HelloResouce.java added:
``` java
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHTMLHello() {
		return "<html><title>Hello</title><body><h1>Bonjour, tout le monde!</h1><body></html>";
	}
```
- curl
``` console
C:\Users\AlvinNg>curl -v http://localhost:8080/HelloREST/rest/bonjour
*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080 (#0)
> GET /HelloREST/rest/bonjour HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.83.1
> Accept: */*
>
* Mark bundle as not supporting multiuse
< HTTP/1.1 200
< Content-Type: text/html
< Content-Length: 77
< Date: Thu, 03 Nov 2022 06:58:05 GMT
<
<html><title>Hello</title><body><h1>Bonjour, tout le monde!</h1><body></html>* Connection #0 to host localhost left intact
C:\Users\AlvinNg>curl -H "Accept: text/html" http://localhost:8080/HelloREST/rest/bonjour
<html><title>Hello</title><body><h1>Bonjour, tout le monde!</h1><body></html>
C:\Users\AlvinNg>curl -H "Accept: text/plain" http://localhost:8080/HelloREST/rest/bonjour
Bonjour, tout le monde!
```
- postman
	- New Collection > New Request > Get : `http://localhost:8080/HelloREST/rest/bonjour`
	- Headers > `Accept` > `text/html`

### Use JSON for RESTful Web Services
- pom.xml, add in
``` xml
		<dependency>
			<groupId>org.glassfish.jersey.media</groupId>
			<artifactId>jersey-media-json-jackson</artifactId>
			<version>2.29.1</version>
		</dependency>
```
- create new class, `Student.java`
``` java
public class Student {
	private int id;
	private String name;
	private String country;

	public Student(int id, String name, String country) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
	}
	...
```
- HelloResource.java, add in
``` java
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Student sayJsonHello() {
		return new Student(123, "Samir Kumar", "India");
	}
```
- curl
``` console
C:\Users\AlvinNg>curl -H "Accept: application/json" http://localhost:8080/HelloREST/rest/bonjour
{"id":123,"name":"Samir Kumar","country":"India"}
```
- postman
	- Headers > KEY : `Accept` > VALUE : `application/json`
``` json
{
    "id": 123,
    "name": "Samir Kumar",
    "country": "India"
}
```

### Code Client program
- Create New Maven project > tick `Create a simple project` > Next >
- Group Id: `net.codejava.ws` > Artifact Id: `RESTClient` > Finish
- RESTClient > JRE System Lubrary [...] > right-click Properties > 
	- Execution environment: `JavaSE-11 (jdk...)` > Apply and Close
- pom.xml add
``` xml
	<dependencies>
		<dependency>
			<groupId>org.glassfish.jersey.core</groupId>
			<artifactId>jersey-client</artifactId>
			<version>2.29.1</version>
		</dependency>
		<dependency>
			<groupId>org.glassfish.jersey.inject</groupId>
			<artifactId>jersey-hk2</artifactId>
			<version>2.29.1</version>
		</dependency>
```
- src/main.java > new package > `net.codejava.ws`
- new class > `HelloClient` > tick `public static void main` > Finish
``` java
public class HelloClient {

	public static void main(String[] args) {
		String uri = "http://localhost:8080/HelloREST/rest/bonjour";
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(uri);

		String response = target.request().accept(MediaType.TEXT_HTML)
								  .get(String.class);
		System.out.println(response);
	}
}
```
- Run As > Java Application
``` console
<html><title>Hello</title><body><h1>Bonjour, tout le monde!</h1><body></html>
```
- if change to `TEXT_PLAIN`> Run > HelloClient
``` console
Bonjour, tout le monde!
```
- if change to `APPLICATION_JSON` > Run > HelloClient
``` console
{"id":123,"name":"Samir Kumar","country":"India"}
```
---
---

ref: https://www.codejava.net/servers/tomcat/how-to-add-tomcat-server-in-eclipse-ide
- Add server > Window > Preferences > Server > Runtime Environment > Add > `Apache Tomcat v9.0`

---
#### HelloResource.java
``` java
package net.codejava.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/bonjour")
public class HelloResource {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String direBonjour() {
		return "Bonjour, tout le monde!";
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Student sayJsonHello() {
		return new Student(123, "Samir Kumar", "India");
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHTMLHello() {
		return "<html><title>Hello</title><body><h1>Bonjour, tout le monde!</h1><body></html>";
	}
}
```
#### HelloREST/pom.xml
``` xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>net.codejava.ws</groupId>
	<artifactId>HelloREST</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>war</packaging>
	<dependencies>
		<dependency>
			<groupId>org.glassfish.jersey.containers</groupId>
			<artifactId>jersey-container-servlet</artifactId>
			<version>2.29.1</version>
		</dependency>
		<dependency>
			<groupId>org.glassfish.jersey.inject</groupId>
			<artifactId>jersey-hk2</artifactId>
			<version>2.29.1</version>
		</dependency>
		<dependency>
			<groupId>org.glassfish.jersey.media</groupId>
			<artifactId>jersey-media-json-jackson</artifactId>
			<version>2.29.1</version>
		</dependency>
	</dependencies>
	<build>
		<plugins>
			<plugin>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>3.8.1</version>
				<configuration>
					<release>11</release>
				</configuration>
			</plugin>
			<plugin>
				<artifactId>maven-war-plugin</artifactId>
				<version>3.2.3</version>
			</plugin>
		</plugins>
	</build>
</project>
```
#### HelloClient.java
``` java
package net.codejava.ws;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;

public class HelloClient {

	public static void main(String[] args) {
		String uri = "http://localhost:8080/HelloREST/rest/bonjour";
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(uri);

		String response = target.request()
		  .accept(MediaType.APPLICATION_JSON)
		  .get(String.class);
		System.out.println(response);
	}
}
```
#### RESTClient/pom.xml
``` xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>net.codejava.ws</groupId>
	<artifactId>RESTClient</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<dependencies>
		<dependency>
			<groupId>org.glassfish.jersey.core</groupId>
			<artifactId>jersey-client</artifactId>
			<version>2.29.1</version>
		</dependency>
		<dependency>
			<groupId>org.glassfish.jersey.inject</groupId>
			<artifactId>jersey-hk2</artifactId>
			<version>2.29.1</version>
		</dependency>
	</dependencies>
</project>
```
#### Student.java
``` java
package net.codejava.ws;

public class Student {
	private int id;
	private String name;
	private String country;

	public Student(int id, String name, String country) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
```

---
