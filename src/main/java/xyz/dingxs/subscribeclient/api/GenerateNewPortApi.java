package xyz.dingxs.subscribeclient.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import xyz.dingxs.subscribeclient.common.config.SubscribeClientConfigProperties;

@Component
public class GenerateNewPortApi {
    @Autowired

    private SubscribeClientConfigProperties subscribeClientConfigProperties;

    @Autowired
    private RestTemplate restTemplate;

    public Integer generate() {
        String url = subscribeClientConfigProperties.getApisConfig().getApis().get("GenerateNewPortApi");

        HttpHeaders headers = new HttpHeaders();
        headers.add("token", subscribeClientConfigProperties.getAuthorized().getToken());
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<Integer> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Integer.class);
        if (HttpStatus.OK == responseEntity.getStatusCode()) {
            return responseEntity.getBody();
        } else {
            return null;
        }
    }
}
