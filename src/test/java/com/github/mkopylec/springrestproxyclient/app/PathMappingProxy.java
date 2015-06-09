package com.github.mkopylec.springrestproxyclient.app;

import org.springframework.web.bind.annotation.RequestMapping;

public class PathMappingProxy {

    @RequestMapping("resource1")
    public interface ClassPathMappingResource1 {

        String getResource();
    }

    @RequestMapping("resource2")
    public interface ClassPathMappingResource2 {

        @RequestMapping
        String getResource();
    }

    public interface MethodPathMappingResource1 {

        @RequestMapping("resource3")
        String getResource();
    }

    @RequestMapping
    public interface MethodPathMappingResource2 {

        @RequestMapping("resource4")
        String getResource();
    }

    @RequestMapping("resource5")
    public interface PathMappingResource {

        @RequestMapping("resource6")
        String getResource();
    }
}
