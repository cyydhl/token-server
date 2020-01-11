package com.itcast;

import com.alibaba.csp.sentinel.cluster.flow.rule.ClusterFlowRuleManager;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;

/**
 * Created by Administrator on 2020/1/8.
 */
public class DataSourceInitFunc implements InitFunc {
    private final String remoteAddress = "192.168.0.111"; //nacos远程服务host
    private final String groupId = "SENTINEL_GROUP"; //nacos groupId
    private static final String FLOW_POSTFIX = "-flow-rules"; //namespace不同，限流规则也不同
    @Override
    public void init() throws Exception {
        ClusterFlowRuleManager.setPropertySupplier(namespace -> {
            ReadableDataSource<String, List<FlowRule>> rds =
                    new NacosDataSource<>(remoteAddress, groupId,
                            namespace + FLOW_POSTFIX,
                            source -> JSON.parseObject(source, new
                                    TypeReference<List<FlowRule>>() {
                                    }));
            return rds.getProperty();
        });
    }
}
