package api.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class AuthErrorDto {

    int code;
    String details;
    String message;
    String timestamp;
}
