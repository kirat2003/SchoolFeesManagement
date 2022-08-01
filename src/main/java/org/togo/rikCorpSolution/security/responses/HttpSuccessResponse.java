package org.togo.rikCorpSolution.security.responses;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@Data
@RequiredArgsConstructor
public class HttpSuccessResponse {

    private HttpStatus status;

    private int statusCode;

    private String message;

    private Object data;
}
