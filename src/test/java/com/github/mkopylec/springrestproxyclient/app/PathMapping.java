package com.github.mkopylec.springrestproxyclient.app;

import org.springframework.web.bind.annotation.RestController;

import static com.github.mkopylec.springrestproxyclient.utils.ResourceSamples.STRING_RESOURCE;

public class PathMapping {

    @RestController
    public static class NoMappingResource implements PathMappingProxy.PathMappingResource {

        @Override
        public String getResource() {
            return STRING_RESOURCE;
        }
    }

    @RestController
    public static class ClassEmptyMappingResource1 implements PathMappingProxy.ClassPathMappingResource1 {

        @Override
        public String getResource() {
            return STRING_RESOURCE;
        }
    }

    @RestController
    public static class ClassEmptyMappingResource2 implements PathMappingProxy.ClassPathMappingResource2 {

        @Override
        public String getResource() {
            return STRING_RESOURCE;
        }
    }

    @RestController
    public static class MethodEmptyMappingResource1 implements PathMappingProxy.MethodPathMappingResource1 {

        @Override
        public String getResource() {
            return STRING_RESOURCE;
        }
    }

    @RestController
    public static class MethodEmptyMappingResource2 implements PathMappingProxy.MethodPathMappingResource2 {

        @Override
        public String getResource() {
            return STRING_RESOURCE;
        }
    }
}
