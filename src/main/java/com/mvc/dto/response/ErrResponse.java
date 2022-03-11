package com.mvc.dto.response;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrResponse {

    //1-Thuôc tính là 1 Map => {các thuộc tính khác của response ,meta ={}}
    //2-Dùng nested class
//    private Map<String, String> meta;

    //    public BaseResponse(Map<String, String> meta) {
//        this.meta = meta;
//    }

    private Meta meta;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Meta {
        private Integer code;
        private Object message;

    }

    public static ErrResponse error(final Integer code, final Object message) {
        ErrResponse baseResponse = new ErrResponse();

        baseResponse.setMeta(new Meta(code, message));
        return baseResponse;
    }

}
