package xyz.dingxs.subscribeclient.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "v2ray")
public class V2rayConfig {

    private String fileName;

    private String restartShell;

    private String statusShell;

}
