package api.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class ContactDto {
    private int id;
    private String name;
    private String lastName;
    private String address;
    private String description;
    private String email;
    private String phone;

}
