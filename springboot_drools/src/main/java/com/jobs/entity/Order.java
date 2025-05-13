package com.jobs.entity;

import lombok.Data;

/**
 * 订单
 */
@Data
public class Order {
    private Double originalPrice;//订单原始价格，即优惠前价格
    private Double realPrice;//订单真实价格，即优惠后价格
    // getter  setter
}
