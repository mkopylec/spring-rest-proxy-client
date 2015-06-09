package com.github.mkopylec.springrestproxyclient;

import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Method;

import static java.lang.reflect.Proxy.newProxyInstance;
import static org.springframework.http.HttpMethod.GET;

public class RestProxyFactory {

    private final ProxyParser proxyParser = new ProxyParser();

    @SuppressWarnings("unchecked")
    public <T> T newRestProxy(String baseUri, RestTemplate restTemplate, Class<T> proxyInterface) {
        RestInvocationHandler handler = new RestInvocationHandler(restTemplate, baseUri);

        RequestMapping classLevelMapping = proxyInterface.getAnnotation(RequestMapping.class);
        String classLevelPath = proxyParser.getClassLevelPath(proxyInterface);
        HttpMethod classLevelMethod = proxyParser.getClassLevelMethod(proxyInterface);

        for (Method method : proxyInterface.getMethods()) {
            RequestMapping methodLevelMapping = method.getAnnotation(RequestMapping.class);
            if (classLevelMapping == null && methodLevelMapping == null) {
                continue;
            }

            String path = classLevelPath + "/" + proxyParser.getMethodLevelPath(method);

            HttpMethod methodLevelMethod = proxyParser.getMethodLevelMethod(method);
            HttpMethod httpMethod = GET;
            if (methodLevelMethod != null) {
                httpMethod = methodLevelMethod;
            } else if (classLevelMethod != null) {
                httpMethod = classLevelMethod;
            }

            EndpointMethod endpoint = new EndpointMethod().setPath(path).setMethod(httpMethod);
            handler.addEndpointMethod(method, endpoint);
        }

        return (T) newProxyInstance(proxyInterface.getClassLoader(), new Class[]{proxyInterface}, handler);
    }
}
