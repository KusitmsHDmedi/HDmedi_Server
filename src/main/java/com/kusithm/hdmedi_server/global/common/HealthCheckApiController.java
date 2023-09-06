package com.kusithm.hdmedi_server.global.common;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckApiController {
    @RequestMapping("/")
    public String hdmedi() { return "HDmedi Kusithm 2조!"; }
}
