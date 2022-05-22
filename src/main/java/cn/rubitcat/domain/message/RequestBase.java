package cn.rubitcat.domain.message;

public class RequestBase {
    String  token;

    @Override
    public String toString() {
        return "RequestBase{" +
                "token='" + token + '\'' +
                '}';
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
