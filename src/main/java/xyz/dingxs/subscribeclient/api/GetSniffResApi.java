package xyz.dingxs.subscribeclient.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import xyz.dingxs.subscribeclient.common.config.SubscribeClientConfigProperties;

/**
 * 获取嗅探结果api
 *
 * @author dingxs
 */
@Component
public class GetSniffResApi {

    @Autowired
    private SubscribeClientConfigProperties subscribeClientConfigProperties;

    @Autowired
    private RestTemplate restTemplate;

    public Boolean get() {
        String url = subscribeClientConfigProperties.getApisConfig().getApis().get("GetSniffResApi");
        String token = subscribeClientConfigProperties.getAuthorized().getToken();

        HttpHeaders headers = new HttpHeaders();
        headers.add("token", token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<Boolean> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Boolean.class);
        if (HttpStatus.OK == responseEntity.getStatusCode()) {
            return responseEntity.getBody();
        } else {
            return true;
        }
    }
}
