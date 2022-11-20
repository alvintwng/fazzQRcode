## Deploying Spring code

- IDE: Spring Tools Suites 4
- File > New > Spring Start Project > eg postReq1
  - packaing: War >  Java Ver: 08 > other info > Next
  - Spring Web > Finished
- Files initiale created:
  - PostReq1Application.java
  - ServletInitializer.java

PostReq1Application.java edit added
``` java
@RestController
...
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
```

### Create War file
- Run as > Maven Build > Goals: package
- War files will find in directory > [projectname] > target > [filename].war
- attached into server directory, tomcat/webapps 
  - server will extract this war

server
``` console
C:\Users\xxx\myWebProject\tomcat\bin> startup
...
C:\Users\xxx\myWebProject\tomcat\bin> shutdown
```
# Failed to deploy!

---
