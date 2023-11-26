package com.sha.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthTokenFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        System.out.println("Itt ellenőrizhető az Authentication-Header "+ exchange.getRequest());
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            System.out.println("Post processing ha szükséges "+ exchange.getResponse());
        }));
    }
}
