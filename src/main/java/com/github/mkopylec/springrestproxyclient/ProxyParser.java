package com.github.mkopylec.springrestproxyclient;

import com.github.mkopylec.springrestproxyclient.exceptions.RestProxyClientException;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;

import static org.apache.commons.lang3.StringUtils.removeEnd;
import static org.apache.commons.lang3.StringUtils.removeStart;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.HEAD;
import static org.springframework.http.HttpMethod.OPTIONS;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

class ProxyParser {

    public HttpMethod getClassLevelMethod(Class<?> proxyInterface) {
        RequestMapping mapping = proxyInterface.getAnnotation(RequestMapping.class);
        return getHttpMethod(mapping, proxyInterface);
    }

    public HttpMethod getMethodLevelMethod(Method proxyMethod) {
        RequestMapping mapping = proxyMethod.getAnnotation(RequestMapping.class);
        return getHttpMethod(mapping, proxyMethod);
    }

    public String getClassLevelPath(Class<?> proxyInterface) {
        RequestMapping mapping = proxyInterface.getAnnotation(RequestMapping.class);
        return getEndpointPath(mapping, proxyInterface);
    }

    public String getMethodLevelPath(Method proxyMethod) {
        RequestMapping mapping = proxyMethod.getAnnotation(RequestMapping.class);
        return getEndpointPath(mapping, proxyMethod);
    }

    private HttpMethod getHttpMethod(RequestMapping mapping, Object annotatedElement) {
        HttpMethod httpMethod = null;
        if (mapping != null) {
            if (mapping.method().length > 1) {
                throw new RestProxyClientException("Multiple request mapping methods on " + annotatedElement);
            }
            if (mapping.method().length == 1) {
                switch (mapping.method()[0]) {
                    case GET:
                        httpMethod = GET;
                        break;
                    case POST:
                        httpMethod = POST;
                        break;
                    case DELETE:
                        httpMethod = DELETE;
                        break;
                    case HEAD:
                        httpMethod = HEAD;
                        break;
                    case OPTIONS:
                        httpMethod = OPTIONS;
                        break;
                    case PATCH:
                        httpMethod = PATCH;
                        break;
                    case PUT:
                        httpMethod = PUT;
                        break;
                    default:
                        break;
                }
            }
        }
        return httpMethod;
    }

    private String getEndpointPath(RequestMapping mapping, Object annotatedElement) {
        String path = "";
        if (mapping != null) {
            String[] paths = mapping.value();
            if (paths.length > 1) {
                throw new RestProxyClientException("Multiple request mapping values on " + annotatedElement);
            }
            if (paths.length == 1) {
                path = paths[0];
            }
        }
        String pathWithoutTrailingSlash = removeEnd(path.trim(), "/");
        return removeStart(pathWithoutTrailingSlash, "/");
    }
}
