package models.lombok;

import lombok.Data;

@Data
public class DataUserModel {
    private Integer id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;
}
