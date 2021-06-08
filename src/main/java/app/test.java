package app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {
	
	@GetMapping("/cu")
	public String home(){
		return "cu";
		
	}

}
