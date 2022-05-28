package com.example.RestService.responses;

import java.util.Objects;

public class Response {
    private final Object result;

    public Response(Object result) {
        this.result = result;
    }

    public Object getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return Objects.equals(result, response.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result);
    }
}
