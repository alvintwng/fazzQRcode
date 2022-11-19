### Tomcat
ref: https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaServlets.html

to install Tomcat

ref: https://www3.ntu.edu.sg/home/ehchua/programming/howto/Tomcat_HowTo.html

http://tomcat.apache.org
* Download and unzip to new dir \myWebProject
*	control panel > System > Advance system setting > Advance > System Variables > add
*	set JAVA_HOME
  *		JAVA_HOME=C:\Program Files\Java\jdk-17.0.4.1
*	Configure the Tomcat Server; `conf\server.xml`; `conf\web.xm`l; `conf\context.xml`
*	Start Tomcat Server > cd \myWebProject\tomcat\bin **startup**
* 	Shutdown Server > cd \myWebProject\tomcat\bin  **shutdown**

Develop and Deploy a "Hello-world" WebApp
*	create C:\Users\AlvinNg\myWebProject\tomcat\webapps\hello\WEB-INF\classes*
	http://localhost:9999/hello

Step 3(a) "conf\server.xml" - Set the TCP Port Number
```
<!-- A "Connector" represents an endpoint by which requests are received
      and responses are returned. Documentation at :
      Java HTTP Connector: /docs/config/http.html
      Java AJP  Connector: /docs/config/ajp.html
      APR (HTTP/AJP) Connector: /docs/apr.html
      Define a non-SSL HTTP/1.1 Connector on port 8080
-->
<Connector port="9999" protocol="HTTP/1.1"
           connectionTimeout="20000"
           redirectPort="8443" />
```
Step 3(b) "conf\web.xml" - Enable Directory Listing
```
<servlet>
  <servlet-name>default</servlet-name>
  <servlet-class>org.apache.catalina.servlets.DefaultServlet</servlet-class>
  <init-param>
    <param-name>debug</param-name>
    <param-value>0</param-value>
  </init-param>
  <init-param>
    <param-name>listings</param-name>
    <param-value>true</param-value>
  </init-param>
  <load-on-startup>1</load-on-startup>
</servlet>
```
Step 3(c) "conf\context.xml" - Enabling Automatic Reload
```
<Context reloadable="true" crossContext="true" parallelAnnotationScanning="true">
   ......
   ......
</Context>
```

##### compile
C:\Users\AlvinNg\myWebProject\tomcat\webapps\hello\WEB-INF\classes>javac -cp .;C:\Users\AlvinNg\myWebProject\tomcat\lib\servlet-api.jar HelloServlet.java
http://localhost:9999/hello/sayhello > initial dont work!

Tomcat server:  user: xx  pw: xxx


---
Demo copy file out of server directory. need to setup as such:

create `helloAbc.xml`, and to place in `C:\Users\AlvinNg\myWebProject\tomcat\conf\Catalina\localhost>`
```
<?xml version="1.0" encoding="UTF-8"?>
<Context docBase="C:\Users\AlvinNg\xfersWk05\helloAbc" path="/helloAbc"/>
```
browser shown in:
* `http://localhost:9999/helloAbc/`
* `http://localhost:9999/helloAbc/sayhello`


---
