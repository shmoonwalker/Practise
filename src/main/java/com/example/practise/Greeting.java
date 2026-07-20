package com.example.practise;

import java.time.Instant;

public record Greeting(Long id, String message, Instant createdAt) {
}
