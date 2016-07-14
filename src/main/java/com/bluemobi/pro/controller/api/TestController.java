package com.bluemobi.pro.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bluemobi.pro.entity.TestEntity;

/**
 * Created by Administrator on 2016/7/1.
 */

@RequestMapping("/app/test")
@Controller
public class TestController {

//    @RequestLimit(count = 5)
    @RequestMapping(value = "/aes")
    public void aes(TestEntity testEntity) {
        System.out.println(testEntity.toString());
    }
}
