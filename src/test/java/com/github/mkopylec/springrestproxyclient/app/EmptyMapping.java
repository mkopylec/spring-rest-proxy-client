package com.github.mkopylec.springrestproxyclient.app;

import org.springframework.web.bind.annotation.RestController;

import static com.github.mkopylec.springrestproxyclient.utils.ResourceSamples.STRING_RESOURCE;

public class EmptyMapping {

    @RestController
    public static class NoMappingResource implements EmptyMappingProxy.NoMappingResource {

        @Override
        public String getResource() {
            return STRING_RESOURCE;
        }
    }

    @RestController
    public static class EmptyMappingResource implements EmptyMappingProxy.EmptyMappingResource {

        @Override
        public String getResource() {
            return STRING_RESOURCE;
        }
    }

    @RestController
    public static class ClassEmptyMappingResource implements EmptyMappingProxy.ClassEmptyMappingResource {

        @Override
        public String getResource() {
            return STRING_RESOURCE;
        }
    }

    @RestController
    public static class MethodEmptyMappingResource implements EmptyMappingProxy.MethodEmptyMappingResource {

        @Override
        public String getResource() {
            return STRING_RESOURCE;
        }
    }
}
