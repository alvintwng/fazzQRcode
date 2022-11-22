## Testing of callback link, basic
Parameter:
* `http://localhost:8080/hello` - just for testing if server working
* `http://localhost:8080/view` - to display String object stored
* `http://localhost:8080/response/postbody` - To get input of String object

---
Geeral summary:

IDE used: **Spring Tool Suites4**

#### To execute :
- Right-click the project name, `Run As`, `Spring Boot App`. *Or* 
- If *jar*/*war* available,  will execute as `java -jar callback-221113.war` in command prompt.
``` console
C:\xxx\callback\target>java -jar callback-221113.war

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

#### Post from external
If post string to **`http://localhost:8080/response/postbody`**. For instance
``` console
C:\Users\xxx>curl -X POST -H "Content-type: application/json" -d "Orchard Road" "http://localhost:8080/response/postbody"
>>> Orchard Road
```

In browser  `http://localhost:8080/view`
``` console
Response (latest 10): 
Orchard Road
```
To indicate that this code has captured the post.

---
