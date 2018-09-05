package com.my.spring.rest;

import com.my.spring.response.CommonResponse;
import com.my.spring.response.CommonResponseUtil;
import com.my.spring.rest.command.LoginCommand;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LoginController {

    @PostMapping("/login")
    public CommonResponse login(@Valid @RequestBody LoginCommand command) {
        return CommonResponseUtil.ok();
    }
}
