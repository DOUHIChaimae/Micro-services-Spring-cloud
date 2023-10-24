package ma.enset.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
//@CrossOrigin("origins = http://localhost:4200")
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    //Conf JAVA ==> static conf
    //@Bean
    RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                //.route("r1", r -> r.path("/customers/**").uri("http://localhost:8085/"))
                .route("r1", r -> r.path("/customers/**").uri("lb://CUSTOMER-SERVICE"))
                .route("r2", r -> r.path("/products/**").uri("lb://PRODUCT-SERVICE"))
                .build();
    }


    //dynamic conf
    @Bean
    DiscoveryClientRouteDefinitionLocator definitionLocator(
            ReactiveDiscoveryClient discoveryClient,
            DiscoveryLocatorProperties properties) {
        return new DiscoveryClientRouteDefinitionLocator(discoveryClient, properties);
    }

}
