package cc.ziyi.pojo;

/**
 * Unify response results
 * utilizes generics and method overloading for success()
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result<T> {
    private Integer code; // Business status code: 0-Success, 1-Failure
    private String message; // Prompt message
    private T data;  // Response data - Generics ensure data-field to hold any type of object

    // return a successful response result (with response data)
    // Static methods cannot use the class-level T, but must declare a generic parameter E themselves.
    public static <E> Result<E> success(E data) {
        return new Result<>(0, "Operation successful", data);
    }

    // return a successful response result-- f.eks add article or user
    public static Result success() {
        return new Result(0, "Operation successful", null);
    }

    // Return an error response result with a custom message
    public static Result error(String message) {
        return new Result(1, message, null);
    }
}
