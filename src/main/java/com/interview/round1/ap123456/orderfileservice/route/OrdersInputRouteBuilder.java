package com.interview.round1.ap123456.orderfileservice.route;

import com.interview.round1.ap123456.orderfileservice.model.Order;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.spi.DataFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OrdersInputRouteBuilder extends RouteBuilder {

    private final String orderInputEndpoint;
    private final String routeId;
    private final String publishEndpoint;
    private final String topicKey;

    public OrdersInputRouteBuilder(
            @Value("${orders.input-folder}") String orderInputEndpoint,
            @Value("${route-id}") String routeId,
            @Value("${publish-endpoint}") String publishEndpoint,
            @Value("${topicKey}") String topicKey
    ) {
        this.orderInputEndpoint = orderInputEndpoint;
        this.routeId = routeId;
        this.publishEndpoint = publishEndpoint;
        this.topicKey = topicKey;
    }

    @Override
    public void configure() {
        DataFormat format = new BindyCsvDataFormat(Order.class);
        from(orderInputEndpoint)
                .routeId(routeId)
                .unmarshal(format)
                .split().body()
                .setHeader(KafkaConstants.KEY, simple(topicKey))
                .to(publishEndpoint);
    }
}
