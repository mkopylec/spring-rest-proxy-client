package com.github.mkopylec.springrestproxyclient;

import com.github.mkopylec.springrestproxyclient.exceptions.RestProxyClientException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

class RestInvocationHandler implements InvocationHandler {

    private final RestTemplate restTemplate;
    private final String baseUri;
    private final Map<Method, EndpointMethod> endpointMethods = new HashMap<>();

    public RestInvocationHandler(RestTemplate restTemplate, String baseUri) {
        this.restTemplate = restTemplate;
        this.baseUri = baseUri;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        EndpointMethod endpoint = endpointMethods.get(method);
        if (endpoint == null) {
            throw new RestProxyClientException("Method " + method + " does not represent a REST endpoint");
        }
        String url = fromHttpUrl(baseUri).path(endpoint.getPath()).build().toString();
        ResponseEntity<?> response = restTemplate.exchange(url, endpoint.getMethod(), null, method.getReturnType());

        return response.getBody();
    }

    public void addEndpointMethod(Method method, EndpointMethod endpointMethod) {
        endpointMethods.put(method, endpointMethod);
    }
}
