package org.janis.qa.homework.model.csv;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.janis.qa.homework.constants.CsvColumnNames;
import org.janis.qa.homework.model.payload.UserPayload;
import org.janis.qa.homework.model.response.UserResponse;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCsv {
    @CsvBindByName(column = CsvColumnNames.NAME)
    private String name;
    @CsvBindByName(column = CsvColumnNames.GENDER)
    private String gender;
    @CsvBindByName(column = CsvColumnNames.EMAIL)
    private String email;
    @CsvBindByName(column = CsvColumnNames.STATUS)
    private String status;

    public UserPayload convertToPayload() {
        return UserPayload.builder().
                name(this.name).
                gender(this.gender).
                email(this.email).
                status(this.status).build();
    }

    public UserResponse convertToResponse() {
        return UserResponse.builder().
                id(0).
                status(this.status).
                email(this.email).
                gender(this.gender).
                name(this.name).
                build();
    }
}
