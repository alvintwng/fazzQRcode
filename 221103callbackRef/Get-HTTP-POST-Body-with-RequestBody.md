### Get HTTP POST Body in Spring Boot with @RequestBody
Ref: https://stackabuse.com/get-http-post-body-in-spring/


``` java
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/response")
public class HomeController {

	@PostMapping("/postbody")
	public String postBody(@RequestBody String fullName) {
		return "Hello " + fullName;
	}
}
```

``` console
C:\Users\AlvinNg>curl -X POST -H "Content-type: application/json" -d "John Smith" "http://localhost:8080/response/postbody"
Hello John Smith
```
