package xyz.dingxs.subscribeclient.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import xyz.dingxs.subscribeclient.common.config.ApisConfig;
import xyz.dingxs.subscribeclient.common.config.AuthorizedConfig;

@Component
public class GenerateNewPortApi {
    @Autowired
    private AuthorizedConfig authorizedConfig;

    @Autowired
    private ApisConfig apisConfig;

    @Autowired
    private RestTemplate restTemplate;

    public Integer generate() {
        String url = apisConfig.getApis().get("GenerateNewPortApi");

        HttpHeaders headers = new HttpHeaders();
        headers.add("token", authorizedConfig.getToken());
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<Integer> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Integer.class);
        if (HttpStatus.OK == responseEntity.getStatusCode()) {
            return responseEntity.getBody();
        } else {
            return null;
        }
    }
}
