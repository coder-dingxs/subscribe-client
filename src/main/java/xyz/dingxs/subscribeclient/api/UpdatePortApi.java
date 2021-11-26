package xyz.dingxs.subscribeclient.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import xyz.dingxs.subscribeclient.api.req.UpdatePortReq;
import xyz.dingxs.subscribeclient.common.config.SubscribeClientConfigProperties;

/**
 * 设置订阅链接
 *
 * @author dingxs
 */
@Component
public class UpdatePortApi {

    @Autowired
    private SubscribeClientConfigProperties subscribeClientConfigProperties;

    @Autowired
    private RestTemplate restTemplate;

    public Boolean update(Integer port) {
        String url = subscribeClientConfigProperties.getApisConfig().getApis().get("UpdatePortApi");
        String token = subscribeClientConfigProperties.getAuthorized().getToken();

        UpdatePortReq req = new UpdatePortReq();
        req.setPort(port);

        HttpHeaders headers = new HttpHeaders();
        headers.add("token", token);
        HttpEntity<UpdatePortReq> entity = new HttpEntity<>(req, headers);

        ResponseEntity<Boolean> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, Boolean.class);
        if (HttpStatus.OK == responseEntity.getStatusCode()) {
            return responseEntity.getBody();
        } else {
            return false;
        }
    }
}
