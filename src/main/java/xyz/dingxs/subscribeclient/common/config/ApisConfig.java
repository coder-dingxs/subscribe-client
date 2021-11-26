package xyz.dingxs.subscribeclient.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.Map;

@Data
public class ApisConfig {

    @NestedConfigurationProperty
    private Map<String, String> apis;

}
