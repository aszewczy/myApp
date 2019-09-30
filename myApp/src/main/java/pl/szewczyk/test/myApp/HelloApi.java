package pl.szewczyk.test.myApp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloApi {

	
	@GetMapping("/api/hello")
	public String sayHello() {
		return "HELLO!";
	}
	
}
