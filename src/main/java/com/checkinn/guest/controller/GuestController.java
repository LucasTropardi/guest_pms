package com.checkinn.guest.controller;

import com.checkinn.guest.dto.GuestRequest;
import com.checkinn.guest.dto.GuestResponse;
import com.checkinn.guest.service.GuestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

@RestController
@RequestMapping("/api/guests")
@RequiredArgsConstructor
public class GuestController {

    private final GuestService guestService;

    @PostMapping
    public ResponseEntity<GuestResponse> create(@RequestHeader("Authorization") String token,
                                                @Valid @RequestBody GuestRequest request) {
        return ResponseEntity.ok(new GuestResponse(guestService.save(request, token)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuestResponse> update(@PathVariable Long id,
                                                @Valid @RequestBody GuestRequest request,
                                                @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(new GuestResponse(guestService.update(id, request, token)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id,
                                       @RequestHeader("Authorization") String token) {
        guestService.delete(id, token);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<GuestResponse>> findAll() {
        return ResponseEntity.ok(
                guestService.findAll().stream().map(GuestResponse::new).toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuestResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new GuestResponse(guestService.findById(id)));
    }
}