package xws.handlingReqservices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class RestTemplateMediaService {

    @Bean
    public RestTemplate restTemplate() {

        RestTemplate restTemplate = new RestTemplate();
//		restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://192.168.0.16:8021")); //e2-141
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://127.0.0.1:8080"));    //e2-143

        return restTemplate;
    }

}
