package com.erickresende.lista.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    /*O postman acessa pelos metods http a api sem restrição, mas no caso de
    um front precisaria configurar o que pode ou nao ser acessado, sem esse
    metodo nao daria pra acessar, esse libera o acesso total a partir do /
    futuramente preciso enteder melhor isso
    * */
    public void addCorsMappings(CorsRegistry registry){

        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}
