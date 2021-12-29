package studio.thinkground.aroundhub.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    
    private final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping(value="/hello")
    public String hello(){
        return "Hello World";
    }
    
    @RequestMapping("log-test")
    public void logTest(){
        LOGGER.trace("trace");
        LOGGER.info("info");
        LOGGER.debug("debug");
        LOGGER.warn("warn");
        LOGGER.error("error");
    }
    

}
