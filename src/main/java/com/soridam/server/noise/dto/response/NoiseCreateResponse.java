package com.soridam.server.noise.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoiseCreateResponse {
    private int status;
    private String message;
    private Long noiseId;
}
