package ttmm.utils;

import lombok.Data;

@Data
public class Response {
    private String message;
    private Object data;
    private int status;
    private boolean success=true;

    public Response(String message, Object data, int status, boolean success) {
        this.message = message;
        this.data = data;
        this.status = status;
        this.success = success;
    }
}
