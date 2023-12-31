package dev.harolds.reservy.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class GeneralResponse<T> {
    private String message;
    private T data;

    public GeneralResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public GeneralResponse(String message) {
        this.message = message;
        this.data = null;
    }

    public GeneralResponse(T data) {
        this.message = "";
        this.data = data;
    }

    public GeneralResponse() {}
}
