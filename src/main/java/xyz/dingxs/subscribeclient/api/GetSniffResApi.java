package xyz.dingxs.subscribeclient.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import xyz.dingxs.subscribeclient.common.config.ApisConfig;

/**
 * 获取嗅探结果api
 *
 * @author dingxs
 */
@Component
public class GetSniffResApi {

    @Autowired
    private ApisConfig apisConfig;

    @Autowired
    private RestTemplate restTemplate;

    public Boolean get() {
        String url = apisConfig.getApis().get("GetSniffResApi");
        ResponseEntity<Boolean> responseEntity = restTemplate.getForEntity(url, Boolean.class);
        if (HttpStatus.OK == responseEntity.getStatusCode()) {
            return responseEntity.getBody();
        } else {
            return true;
        }
    }
}
