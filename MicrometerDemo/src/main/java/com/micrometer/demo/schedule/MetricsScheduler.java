package com.micrometer.demo.schedule;

import com.micrometer.demo.service.OrderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MetricsScheduler {

    private final OrderService orderService;

    public MetricsScheduler(OrderService orderService) {
        this.orderService = orderService;
    }

    // 每5秒创建一个订单
    @Scheduled(fixedRate = 5000)
    public void generateOrders() {
        orderService.createOrder();
    }

    // 每10秒更新内存指标
    @Scheduled(fixedRate = 10000)
    public void updateMemoryMetrics() {
        // 内存使用指标会自动收集，此处仅为示例
    }
}


