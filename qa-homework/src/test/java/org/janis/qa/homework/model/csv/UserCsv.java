package org.janis.qa.homework.model.csv;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.janis.qa.homework.constants.CsvColumnNames;
import org.janis.qa.homework.model.payload.UserPayload;
import org.janis.qa.homework.model.response.UserResponse;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
        return new UserPayload(this.name, this.gender, this.email, this.status);
    }

    public UserResponse convertToResponse() {
        return new UserResponse(0, this.name, this.email, this.gender, this.status);
    }
}
