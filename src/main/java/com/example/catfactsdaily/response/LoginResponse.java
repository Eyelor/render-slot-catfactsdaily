package com.example.catfactsdaily.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginResponse {

    private String name;
    private String token;
    private Long expiresIn;
}
