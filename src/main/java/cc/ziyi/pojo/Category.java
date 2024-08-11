package cc.ziyi.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Category {
    @NotNull(groups = Update.class) // ensure id is present and not null before Update
    private Integer id;  // PK ID
    @NotEmpty (groups = {Update.class, Add.class})
    private String categoryName;
    @NotEmpty (groups = {Update.class, Add.class})
    private String categoryAlias;
    private Integer createUser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    public interface Add{
    }

    public interface Update{
    }
}
