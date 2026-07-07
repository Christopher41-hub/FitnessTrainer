package de.htw_berlin.FitnessTrainer.dto;

public record AuthResponse(String token, Long userId, String email, String name) {
}