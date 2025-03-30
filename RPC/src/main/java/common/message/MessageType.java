package common.message;

import lombok.AllArgsConstructor;

/**
 * @author JoestarZQ
 * @version 3.14
 * @date 2025/3/29
 * @description ...
 */
@AllArgsConstructor
public enum MessageType {
    REQUEST(0),
    RESPONSE(1);
    private  int code;
    public int getCode() {
        return code;
    }
}