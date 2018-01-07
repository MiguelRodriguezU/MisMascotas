package dev.mrodriguezul.mismascotas.beans;

public class UsuarioIns {
    private String id;
    private String username;
    private String urlFoto;
    private String fullName;

    public UsuarioIns(String id, String username, String urlFoto, String fullName) {
        this.id = id;
        this.username = username;
        this.urlFoto = urlFoto;
        this.fullName = fullName;
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

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
