package com.github.mkopylec.springrestproxyclient

import com.github.mkopylec.springrestproxyclient.app.TestApplication
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.client.RestTemplate
import spock.lang.Shared
import spock.lang.Specification

@ContextConfiguration(classes = TestApplication, loader = SpringApplicationContextLoader)
@WebIntegrationTest('server.port=8080')
class BasicSpec extends Specification {

    @Shared
    private RestProxyFactory proxyFactory = new RestProxyFactory()
    @Shared
    private RestTemplate restTemplate = new RestTemplate()

    protected <T> T getProxy(Class<T> proxyInterface) {
        return proxyFactory.newRestProxy('http://localhost:8080', restTemplate, proxyInterface)
    }
}