package xyz.dingxs.subscribeclient.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "api-config")
public class ApisConfig {

    private Map<String, String> apis;

}
