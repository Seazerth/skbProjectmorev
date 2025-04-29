package ru.morev.project.controller;

import org.springframework.web.bind.annotation.*;
import ru.morev.project.model.EchoRequest;
import ru.morev.project.model.EchoResponse;

@RestController
@RequestMapping("/api/json")
public class JsonEchoController {

    @PostMapping
    public EchoResponse echoJson(@RequestBody EchoRequest request) {
        EchoResponse.Info info = new EchoResponse.Info();
        info.setId(123L);
        info.setDate(request.getInfo().getDate());

        EchoResponse response = new EchoResponse();
        response.setPrice(request.getPrice());
        response.setInfo(info);

        return response;
    }
}
