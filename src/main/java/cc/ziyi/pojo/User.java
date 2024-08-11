package cc.ziyi.pojo;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


import java.time.LocalDateTime;

@Data
public class User {

    @NotNull
    private Integer id; //PRIMARY KEY ID
    private String username;  // 5-16 characters

    @JsonIgnore  // password will not appear in the final json-string when converting from current object to json
    private String password;  // 5-16 characters

    @NotEmpty
    @Pattern( regexp = "^\\S{1,10}$")
    private String nickname;

    @NotEmpty
    @Email
    private String email;

    private String userPic;  //url of the user-avatar
    private LocalDateTime createTime; //create_time
    private LocalDateTime updateTime; //update_time
}
