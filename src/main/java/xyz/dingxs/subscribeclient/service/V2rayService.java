package xyz.dingxs.subscribeclient.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.dingxs.subscribeclient.common.config.V2rayConfig;
import xyz.dingxs.subscribeclient.common.util.FileUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class V2rayService {

    private final Logger logger = LoggerFactory.getLogger(V2rayService.class);

    @Autowired
    private V2rayConfig v2rayConfig;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 修改端口
     *
     * @param port 端口
     * @return 端口
     */
    public void changePort(int port) {
        String fileName = v2rayConfig.getFileName();
        try {
            String read = FileUtil.read(fileName);
            logger.debug("old config: {}", read);
            Map<String, Object> map = objectMapper.readValue(read, new TypeReference<Map<String, Object>>() {
            });
            List<Map<String, Object>> inbounds = (List<Map<String, Object>>) map.get("inbounds");
            inbounds.get(0).put("port", port);
            String newConfig = objectMapper.writeValueAsString(map);
            FileUtil.write(fileName, newConfig);
            logger.debug("new config: {}", newConfig);
        } catch (Exception e) {
            logger.error("changePort Exception", e);
        }
    }

    /**
     * 重启服务
     */
    public void restart() {
        String restartShell = v2rayConfig.getRestartShell();
        try {
            Process ps = Runtime.getRuntime().exec(restartShell);
            ps.waitFor(5, TimeUnit.SECONDS);

            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            StringBuilder out = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                out.append(line).append("\n");
            }

            logger.debug("{}--->{}", restartShell, out);
        } catch (Exception e) {
            logger.error("restart Exception", e);
        }
    }

    /**
     * 查看服务状态
     *
     * @return 状态
     */
    public String status() {
        String statusShell = v2rayConfig.getStatusShell();
        try {

            Process ps = Runtime.getRuntime().exec(statusShell);
            ps.waitFor(5, TimeUnit.SECONDS);

            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            StringBuilder out = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                out.append(line).append("\n");
            }
            return out.toString();
        } catch (Exception e) {
            logger.error("", e);
            return null;
        }
    }
}
