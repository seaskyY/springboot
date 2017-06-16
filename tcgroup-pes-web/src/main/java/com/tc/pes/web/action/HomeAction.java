package com.tc.pes.web.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhangshaojiao on 2017/6/15.
 */
@Controller
public class HomeAction {

    @RequestMapping("/index/{name}")
    @ResponseBody
    public String index(@PathVariable String name){
        return "您好"+name+"，欢迎使用牙周检查系统！";
    }

    @RequestMapping("/welcome")
    public String welcome(){
        return "archive/webcome";
    }
}
