package core.models;

//import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class LoginModel {
    @CsvBindByPosition(position = 0)
    private String id;

    @CsvBindByPosition(position = 1)
    private String description;

    @CsvBindByPosition(position = 2)
    private String username;

    @CsvBindByPosition(position = 3)
    private String password;

    @CsvBindByPosition(position = 4)
    private String expected;

    @CsvBindByPosition(position = 5)
    private String result;
}
