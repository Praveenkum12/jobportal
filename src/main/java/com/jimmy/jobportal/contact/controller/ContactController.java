package com.jimmy.jobportal.contact.controller;


import com.jimmy.jobportal.contact.service.impl.ContactService;
import com.jimmy.jobportal.dto.ContactRequestDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contacts")
@Tag(name = "Contact API", description = "Operations related to contacts")
public class ContactController {

    private final ContactService contactService;

    @PostMapping(path = "/public", version = "1.0")
    public ResponseEntity<Map<String, Object>> createContact(@Valid @RequestBody ContactRequestDto contactRequestDto) {
        Map<String, Object> map = new HashMap<>();

        if(contactService.createContact(contactRequestDto)) {
            map.put("statusCode", HttpStatus.CREATED.value());
            map.put("message","Contact created successfully" );
            return  ResponseEntity.status(HttpStatus.CREATED).body(map) ;
        } else {
            map.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            map.put("message","Issue while processing the request" );
        }
        return  ResponseEntity.internalServerError().body(map);
    }

    @GetMapping(version = "1.0")
    public ResponseEntity<String> getNewContacts(@Validated @NotBlank(message = "status can't be empty") @RequestParam(name = "status") String status) {
        return ResponseEntity.ok().body("good");
    }

}
