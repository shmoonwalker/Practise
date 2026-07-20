package com.example.practise;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record GreetingRequest(
        @NotBlank @Size(max = 255) String message
) {
}
