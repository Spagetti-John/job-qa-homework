package org.janis.qa.homework.model.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPayload {
    private String name;
    private String gender;
    private String email;
    private String status;
}
