package xyz.dingxs.subscribeclient.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import xyz.dingxs.subscribeclient.api.req.UpdatePortReq;
import xyz.dingxs.subscribeclient.common.config.ApisConfig;
import xyz.dingxs.subscribeclient.common.config.AuthorizedConfig;

/**
 * 设置订阅链接
 *
 * @author dingxs
 */
@Component
public class UpdatePortApi {

    @Autowired
    private AuthorizedConfig authorizedConfig;

    @Autowired
    private ApisConfig apisConfig;

    @Autowired
    private RestTemplate restTemplate;

    public Boolean update(Integer port) {
        String url = apisConfig.getApis().get("UpdatePortApi");
        String token = authorizedConfig.getToken();

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
