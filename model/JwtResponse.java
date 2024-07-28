package com.leucine.assignment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private String role;
}
