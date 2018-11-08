package com.my.spring.admin.rest;

import com.my.spring.common.response.CommonResponse;
import com.my.spring.common.response.CommonResponseUtil;
import com.my.spring.common.response.MockCache;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user/info")
    public CommonResponse userInfoIndex(@RequestParam("token") String token) {
        if (null == token)
            return CommonResponseUtil.ok();
        return CommonResponseUtil.ok(MockCache.get(token));
    }
}
