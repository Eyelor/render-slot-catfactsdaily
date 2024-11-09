package com.example.catfactsdaily.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CatFactResponse {

    private UUID id;
    private String fact;
    private LocalDateTime added;
    private LocalDateTime modified;
}
