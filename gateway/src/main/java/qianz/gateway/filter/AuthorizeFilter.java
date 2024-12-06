package qianz.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import qianz.cloudapicommon.util.JwtUtil;
import reactor.core.publisher.Mono;
/**
 * 权限校验过滤器
 * */
@Slf4j
@Order(-1)
@Component
public class AuthorizeFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 0.检查是否为注册或登入请求
        ServerHttpRequest request=exchange.getRequest();
        String requestPath = request.getPath().value();
        if (requestPath.equals("/user/register") || requestPath.equals("/user/login")) {
            return chain.filter(exchange);
        }
        // 1.获取请求参数
        MultiValueMap<String,String> params = request.getQueryParams();
        // 2.获取参数中的 authorization 参数
        String auth=params.getFirst("authorization");
        // TODO 3.判断参数值是否正确
        if(JwtUtil.verifyToken(auth)){
            // 4.是，放行
            return chain.filter(exchange);
        }
        // 5.否，拦截
        //5.1.设置状态码
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        //5.2.拦截请求
        log.info("拦截401请求：auth={}", auth);
        return exchange.getResponse().setComplete(); // 表示响应直接完结
    }
}
