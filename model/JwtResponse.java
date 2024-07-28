package com.leucine.assignment.model;

import lombok.*;

@Builder(toBuilder = true)
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JwtResponse {
    private String jwtToken;
    private String userName;
    private String role;
}
