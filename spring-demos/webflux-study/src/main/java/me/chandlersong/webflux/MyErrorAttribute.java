package me.chandlersong.webflux;

import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

public class MyErrorAttribute <T extends Throwable> extends DefaultErrorAttributes {



    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request,
                                                  boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(request, includeStackTrace);
        addErrorDetails(errorAttributes, request);
        return errorAttributes;
    }
    protected void addErrorDetails(
            Map<String, Object> errorAttributes, ServerRequest request) {
        Throwable ex = getError(request);
        errorAttributes.put("exception", ex.getClass().getSimpleName());
        errorAttributes.put("hello world", "chandler");
    }
}
