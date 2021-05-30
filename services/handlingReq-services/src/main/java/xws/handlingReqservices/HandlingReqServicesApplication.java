package xws.handlingReqservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HandlingReqServicesApplication {

    @RequestMapping("/health")
    public String home() {
        return "Hello world";
    }

	public static void main(String[] args) {
		SpringApplication.run(HandlingReqServicesApplication.class, args);
	}

}
