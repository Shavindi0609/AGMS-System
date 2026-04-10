package lk.ijse.api_gateway.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    public static class Config {
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {

                // --- මෙතනට තමයි මුලින්ම sout එක දාන්න ඕනේ ---
                System.out.println("Gateway Filter Triggered for: " + exchange.getRequest().getURI());
                // Header එකේ token එක තියෙනවාද බලන්න
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("Missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }

                // --- Token එක කැපුවට පස්සේ ඒකත් බලමු ---
                System.out.println("Processing Token: " + authHeader);
                try {
                    jwtUtil.validateToken(authHeader);
                } catch (Exception e) {
                    System.out.println("JWT Validation Error: " + e.getMessage()); // මේ line එක දාන්න
                    throw new RuntimeException("Un-authorized access to application");
                }
            }
            return chain.filter(exchange);
        });
    }
}
