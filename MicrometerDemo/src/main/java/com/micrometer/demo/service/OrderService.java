package com.micrometer.demo.service;

import io.micrometer.core.instrument.*;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final MeterRegistry registry;
    private final Counter orderCounter;
    private final Timer orderProcessingTimer;
    private final DistributionSummary orderAmountSummary;

    public OrderService(MeterRegistry registry) {
        this.registry = registry;

        // 订单计数器（按状态）
        orderCounter = Counter.builder("orders.total")
                .description("Total orders created")
                .tags("status", "created")
                .register(registry);

        // 订单处理时间
        orderProcessingTimer = Timer.builder("orders.processing.time")
                .description("Order processing duration")
                .publishPercentiles(0.5, 0.95) // 50%和95%分位
                .register(registry);

        // 订单金额分布
        orderAmountSummary = DistributionSummary.builder("orders.amount")
                .description("Order amount distribution")
                .baseUnit("USD")
                .minimumExpectedValue(1.0)
                .maximumExpectedValue(10000.0)
                .register(registry);

        // 库存量仪表盘
        Gauge.builder("inventory.quantity", () -> 1)
                .description("Current inventory quantity")
                .strongReference(true)
                .register(registry);
    }


    public void createOrder() {
        // 记录处理时间
        orderProcessingTimer.record(() -> {
            System.out.println("createOrder主流程执行");
            // 更新指标
            System.out.println("更新创建order的指标！");
            orderCounter.increment();
            orderAmountSummary.record(1);
            // 自定义标签
            registry.counter("orders.by.payment",
                    "method", "monitoredMethodName",
                    "currency", "monitoredCurrency"
            ).increment();
        });
    }


}