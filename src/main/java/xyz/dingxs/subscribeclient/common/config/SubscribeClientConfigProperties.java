package xyz.dingxs.subscribeclient.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "subscribe-client-config")
public class SubscribeClientConfigProperties {

    @NestedConfigurationProperty
    private ApisConfig apisConfig;

    @NestedConfigurationProperty
    private AuthorizedConfig authorized;

    @NestedConfigurationProperty
    private V2rayConfig v2ray;

    private String cron;

}
