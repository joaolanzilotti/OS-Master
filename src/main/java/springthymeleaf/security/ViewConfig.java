package springthymeleaf.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class ViewConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        //registry.addRedirectViewController("/js/jquery.mask.js", "/inicio");
        //registry.addRedirectViewController("/js/cadastro.js", "/inicio");
    }
    
}
