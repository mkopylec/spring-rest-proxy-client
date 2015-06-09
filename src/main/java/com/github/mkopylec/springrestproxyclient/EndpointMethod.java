package com.github.mkopylec.springrestproxyclient;

import org.springframework.http.HttpMethod;

class EndpointMethod {

    private String path;
    private HttpMethod method;
    private String accept;
    private String contentType;

    public String getPath() {
        return path;
    }

    public EndpointMethod setPath(String path) {
        this.path = path;
        return this;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public EndpointMethod setMethod(HttpMethod method) {
        this.method = method;
        return this;
    }

    public String getAccept() {
        return accept;
    }

    public EndpointMethod setAccept(String accept) {
        this.accept = accept;
        return this;
    }

    public String getContentType() {
        return contentType;
    }

    public EndpointMethod setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }
}
