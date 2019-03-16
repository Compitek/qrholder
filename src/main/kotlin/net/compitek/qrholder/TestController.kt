package net.compitek.qrholder

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class TestController {
    @GetMapping("/test")
    @ResponseBody fun blog():  String {
        return "{\"response_text\":\"it work's!\"}";
    }
}