package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.management.MalformedObjectNameException;
import java.util.List;

@Controller
@Slf4j  //logger 생성
public class HomeController {


    @RequestMapping("/")
    public String home(){
        log.info("home controller");
        return "home";
    }


}
