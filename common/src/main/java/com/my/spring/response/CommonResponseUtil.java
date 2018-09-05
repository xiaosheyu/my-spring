package com.my.spring.response;

public class CommonResponseUtil {
    public static CommonResponse ok(){
        return new CommonResponse(ResponseStatus.success, null, null);
    }
}
