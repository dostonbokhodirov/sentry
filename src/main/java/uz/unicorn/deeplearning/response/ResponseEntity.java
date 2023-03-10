package uz.unicorn.deeplearning.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * @author Doston Bokhodirov on 04 March 2023 at 11:20 PM
 */

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResponseEntity<T> {
    private T data;
    private ResponseError error;
    private int status;
    private LocalDateTime date; // may change to time with milliseconds

    public ResponseEntity(T data, Integer status) {
        this(status);
        this.data = data;
    }

    public ResponseEntity(ResponseError error, Integer status) {
        this(status);
        this.error = error;
    }

    private ResponseEntity(Integer status) {
        this.status = status;
        this.date = LocalDateTime.now();
    }

    public static <T> ResponseEntity<T> ok(T data) {
        return ok(data, HttpStatus.OK);
    }

    public static <T> ResponseEntity<T> ok(T data, HttpStatus status) {
        return new ResponseEntity<>(data, status.value());
    }

    public static <T> ResponseEntity<T> error(ResponseError error, HttpStatus status) {
        return new ResponseEntity<>(error, status.value());
    }

}
