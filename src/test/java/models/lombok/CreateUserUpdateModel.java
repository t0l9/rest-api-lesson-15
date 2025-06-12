package models.lombok;

import lombok.Data;

@Data
public class CreateUserUpdateModel {
    private String name;
    private String job;
    private String updatedAt;
}
