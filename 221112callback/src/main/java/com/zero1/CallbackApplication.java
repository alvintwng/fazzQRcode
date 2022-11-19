package com.zero1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CallbackApplication {

	public static void main(String[] args) {
		SpringApplication.run(CallbackApplication.class, args);
	}

	@Autowired
	CallString callString;

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "Zero1") String name) {
		return String.format("<h1>Welcome %s!</h1>", name);
	}

	@GetMapping("/view")
	public String view() {
		return String.format(callString.printAll());
	}
}

// Sample on command prompt:
// curl -X POST -H "Content-type: application/json" -d "Orchard Road" "http://localhost:8080/response/postbody"

// browser: http://localhost:8080/view