package org.janis.qa.homework.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.janis.qa.homework.model.payload.UserPayload;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private int id;
    private String name;
    private String email;
    private String gender;
    private String status;

    public UserPayload convertToPayload() {
        return UserPayload.builder().
                name(this.name).
                email(this.email).
                gender(this.gender).
                status(this.status).
                build();
    }
}
