package com.checkinn.guest.controller;

import com.checkinn.guest.dto.GuestRequest;
import com.checkinn.guest.dto.GuestResponse;
import com.checkinn.guest.service.GuestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guests")
@RequiredArgsConstructor
public class GuestController {

    private final GuestService guestService;

    @PostMapping
    public ResponseEntity<GuestResponse> create(@Valid @RequestBody GuestRequest request) {
        return ResponseEntity.ok(new GuestResponse(guestService.save(request)));
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

    @PutMapping("/{id}")
    public ResponseEntity<GuestResponse> update(@PathVariable Long id,
                                                @Valid @RequestBody GuestRequest request) {
        return ResponseEntity.ok(new GuestResponse(guestService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        guestService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
