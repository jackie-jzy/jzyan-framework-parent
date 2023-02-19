package org.jzyan.framework.registry.endpoint;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.registry.NacosRegistration;
import com.alibaba.cloud.nacos.registry.NacosServiceRegistry;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 从nacos中下线副本的API
 * </p>
 *
 * @author jzyan
 * @since 2023-02-17
 */
@Log4j2
@Component
@Endpoint(id = "deregister")
public class NacosServiceDeregisterEndpoint {
    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;
    @Autowired
    private NacosRegistration nacosRegistration;
    @Autowired
    private NacosServiceRegistry nacosServiceRegistry;

    /**
     * 从 nacos 中主动下线应用
     *
     * @return
     */
    @ReadOperation
    public String endpoint() {
        String serviceName = nacosDiscoveryProperties.getService();
        String groupName = nacosDiscoveryProperties.getGroup();
        String clusterName = nacosDiscoveryProperties.getClusterName();
        String ip = nacosDiscoveryProperties.getIp();
        int port = nacosDiscoveryProperties.getPort();
        log.info("deregister from nacos, serviceName:{}, groupName:{}, clusterName:{}, ip:{}, port:{}", serviceName, groupName, clusterName, ip, port);
        // 设置服务下线
        nacosServiceRegistry.setStatus(nacosRegistration, "DOWN");
        return "success";
    }

}
