package designPatterns.paginationImplementation;

import java.util.Map;

public class Response {
    private int statusCode;
    private Map<String, Object> responseBody;

    public Response(int statusCode, Map<String, Object> responseBody) {
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }
}
