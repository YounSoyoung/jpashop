package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        //키 - data, 값 - hello!!!
        model.addAttribute("data", "hello!!!");
        return "hello";  //"hello" - 화면 이름, hello 뒤의 html은 생략 가능
    }
}
