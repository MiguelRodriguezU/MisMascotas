package dev.mrodriguezul.mismascotas.restApi.model;

/**
 * Created by MIGUEL on 17/01/2018.
 */

public class CredencialResponse {
    private String id;
    private String token;

    public CredencialResponse(String id, String token) {
        this.id = id;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
