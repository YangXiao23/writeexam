package com.example.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: yangxiao
 * @DATE 2023/10/25 0:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse {
    private Integer code;
    private String message;
    private Object obj;
}
