package ru.morev.project.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class HeaderController {

    @GetMapping("/headers")
    public String showHeaders(HttpServletRequest request, Model model) {
        Map<String, String> headers = new LinkedHashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            headers.put(name, request.getHeader(name));
        }

        model.addAttribute("headers", headers);
        return "headers";
    }
}
