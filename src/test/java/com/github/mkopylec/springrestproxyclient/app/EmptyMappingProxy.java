package com.github.mkopylec.springrestproxyclient.app;

import org.springframework.web.bind.annotation.RequestMapping;

public class EmptyMappingProxy {

    public interface NoMappingResource {

        String getResource();
    }

    @RequestMapping
    public interface ClassEmptyMappingResource {

        String getResource();
    }

    public interface MethodEmptyMappingResource {

        @RequestMapping
        String getResource();
    }

    @RequestMapping
    public interface EmptyMappingResource {

        @RequestMapping
        String getResource();
    }
}
