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
	public String postBody(@RequestBody String post) {

		callString.add(post);

		return ">>> " + post;
	}
}