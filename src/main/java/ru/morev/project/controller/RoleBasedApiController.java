package ru.morev.project.controller;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class RoleBasedApiController {

    @GetMapping("/public")
    public String publicApi() {
        return "🌍 Публичный API доступен всем";
    }

    @GetMapping("/admin")
    @RolesAllowed("ADMIN")
    public Map<String, String> adminApi(Authentication auth) {
        return Map.of("user", auth.getName(), "role", "ADMIN");
    }

    @GetMapping("/support")
    @RolesAllowed("SUPPORT")
    public Map<String, String> supportApi(Authentication auth) {
        return Map.of("user", auth.getName(), "role", "SUPPORT");
    }
}
