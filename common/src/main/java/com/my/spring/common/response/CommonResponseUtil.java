package com.my.spring.common.response;

public class CommonResponseUtil {
    public static CommonResponse ok(){
        return new CommonResponse(ResponseStatus.success, null, null);
    }
}
