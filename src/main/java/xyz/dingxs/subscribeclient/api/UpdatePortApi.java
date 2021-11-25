package xyz.dingxs.subscribeclient.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import xyz.dingxs.subscribeclient.api.req.UpdatePortReq;
import xyz.dingxs.subscribeclient.common.config.ApisConfig;

/**
 * 设置订阅链接
 *
 * @author dingxs
 */
@Component
public class UpdatePortApi {

    @Autowired
    private ApisConfig apisConfig;

    @Autowired
    private RestTemplate restTemplate;

    public Boolean update(Integer port) {
        String uri = apisConfig.getApis().get("UpdatePortApi");
        UpdatePortReq req = new UpdatePortReq();
        req.setPort(port);
        ResponseEntity<Boolean> responseEntity = restTemplate.postForEntity(uri, req, Boolean.class);
        if (HttpStatus.OK == responseEntity.getStatusCode()) {
            return responseEntity.getBody();
        } else {
            return false;
        }
    }
}
