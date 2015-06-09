package com.github.mkopylec.springrestproxyclient

import com.github.mkopylec.springrestproxyclient.exceptions.RestProxyClientException

import static com.github.mkopylec.springrestproxyclient.app.EmptyMappingProxy.EmptyMappingResource
import static com.github.mkopylec.springrestproxyclient.app.EmptyMappingProxy.NoMappingResource
import static com.github.mkopylec.springrestproxyclient.utils.ResourceSamples.STRING_RESOURCE

class EmptyRequestMappingSpec extends BasicSpec {

    def "Should fail to get resource from root server path when no @RequestMapping is present"() {
        when:
        getProxy(NoMappingResource).getResource()

        then:
        thrown RestProxyClientException
    }

    def "Should get resource from root server path when empty @RequestMapping's are present"() {
        when:
        def resource = getProxy(EmptyMappingResource).getResource()

        then:
        resource == STRING_RESOURCE
    }
}