package com.itcast;

import com.alibaba.csp.sentinel.cluster.server.ClusterTokenServer;
import com.alibaba.csp.sentinel.cluster.server.SentinelDefaultTokenServer;
import com.alibaba.csp.sentinel.cluster.server.config.ClusterServerConfigManager;
import com.alibaba.csp.sentinel.cluster.server.config.ServerTransportConfig;

import java.util.Collections;

/**
 * Created by Administrator on 2020/1/8.
 */
public class ClusterServer {
    public static void main(String[] args) throws Exception {
        ClusterTokenServer tokenServer=new SentinelDefaultTokenServer();
        ClusterServerConfigManager.loadGlobalTransportConfig(new
                ServerTransportConfig().
                setIdleSeconds(600).setPort(9999));
        ClusterServerConfigManager.loadServerNamespaceSet(Collections.singleton("App-Mic"));
        tokenServer.start();
    }
}
