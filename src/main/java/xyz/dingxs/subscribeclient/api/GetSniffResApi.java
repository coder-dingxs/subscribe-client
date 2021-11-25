package xyz.dingxs.subscribeclient.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import xyz.dingxs.subscribeclient.common.config.ApisConfig;
import xyz.dingxs.subscribeclient.common.config.AuthorizedConfig;

/**
 * 获取嗅探结果api
 *
 * @author dingxs
 */
@Component
public class GetSniffResApi {

    @Autowired
    private AuthorizedConfig authorizedConfig;

    @Autowired
    private ApisConfig apisConfig;

    @Autowired
    private RestTemplate restTemplate;

    public Boolean get() {
        String url = apisConfig.getApis().get("GetSniffResApi");
        String token = authorizedConfig.getToken();

        HttpHeaders headers = new HttpHeaders();
        headers.add("token", token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Boolean> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Boolean.class);
        if (HttpStatus.OK == responseEntity.getStatusCode()) {
            return responseEntity.getBody();
        } else {
            return true;
        }
    }
}
