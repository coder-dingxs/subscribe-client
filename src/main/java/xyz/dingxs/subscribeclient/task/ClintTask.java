package xyz.dingxs.subscribeclient.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import xyz.dingxs.subscribeclient.api.GetSniffResApi;
import xyz.dingxs.subscribeclient.api.UpdatePortApi;
import xyz.dingxs.subscribeclient.service.V2rayService;

/**
 * 定时任务
 *
 * @author dingxs
 */
@Component
@EnableScheduling
public class ClintTask {

    private final Logger logger = LoggerFactory.getLogger(ClintTask.class);

    @Autowired
    private V2rayService v2rayService;

    @Autowired
    private GetSniffResApi getSniffResApi;

    @Autowired
    private UpdatePortApi updatePortApi;

    @Scheduled(cron = "${clint-ask.cron}")
    public void run() {

        // 获取嗅探结果
        Boolean res = getSniffResApi.get();
        // true 不操作
        if (!res) {
            // 生成端口
            int port = 1;
            // false 修改端口
            v2rayService.changePort(port);

            // 重启服务
            v2rayService.restart();

            // 修改订阅链接中的端口
            updatePortApi.update(port);
        }
    }
}
