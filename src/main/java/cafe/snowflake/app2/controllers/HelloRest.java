package cafe.snowflake.app2.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRest {

    @GetMapping("/rest")
    public String rest() {
        return "Hello Rest";
    }

    @GetMapping("/user")
    public String getUser(@AuthenticationPrincipal OidcUser oidcUser) {
        return "oidcUser";
    }

}
