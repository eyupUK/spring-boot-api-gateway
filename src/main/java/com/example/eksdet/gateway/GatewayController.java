package com.example.eksdet.gateway;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@RestController
@RequestMapping("/gateway/apps")
public class GatewayController {

    private final RestTemplate rest = new RestTemplate();
    private final String NODE_BASE = "http://localhost:3000/apps";

    @Operation(summary = "List all apps")
    @GetMapping
    public ResponseEntity<Object> all() {
        return rest.getForEntity(NODE_BASE, Object.class);
    }

    @Operation(summary = "Find apps by app name")
    @GetMapping("/{name}")
    public Object byName(@PathVariable String name) {
        try {
            return rest.getForEntity(NODE_BASE + "/" + name, Object.class);
        } catch (org.springframework.web.client.HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND || e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(java.util.Collections.singletonMap("error", "No apps found for the given name"));
            }
            throw e;
        }
    }

    @Operation(summary = "Find apps by owner")
    @GetMapping(path = "/search", params = "owner")
    public ResponseEntity<Object> byOwner(@RequestParam String owner) {
        String url = NODE_BASE + "/search?owner=" + owner;
        try {
            return rest.getForEntity(url, Object.class);
        } catch (org.springframework.web.client.HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND || e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(java.util.Collections.singletonMap("error", "No apps found for the given owner"));
            }
            throw e;
        }
    }
}
