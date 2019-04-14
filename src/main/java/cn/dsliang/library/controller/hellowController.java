package cn.dsliang.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class hellowController {
    @RequestMapping("/hello")
    @ResponseBody
    String say() {
        return "hello.myLibrary";
    }

}
