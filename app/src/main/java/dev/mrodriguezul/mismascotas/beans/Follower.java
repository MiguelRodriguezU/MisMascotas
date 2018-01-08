package dev.mrodriguezul.mismascotas.beans;

/**
 * Created by MIGUEL on 7/01/2018.
 */

public class Follower {
    private String id;
    private String username;
    private String fullName;
    private String urlFoto;

    public Follower(String id, String username, String fullName, String urlFoto) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.urlFoto = urlFoto;
    }

    public Follower() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }
}
