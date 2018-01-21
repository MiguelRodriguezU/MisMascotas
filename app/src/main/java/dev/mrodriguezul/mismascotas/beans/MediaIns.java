package dev.mrodriguezul.mismascotas.beans;

public class MediaIns {
    private String id;
    private String username;
    private String nombre;
    private String url;
    private int likes;

    public MediaIns(String id, String username, String nombre, String url, int likes) {
        this.id = id;
        this.username = username;
        this.nombre = nombre;
        this.url = url;
        this.likes = likes;
    }

    public MediaIns() {
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
