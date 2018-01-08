package dev.mrodriguezul.mismascotas.beans;

public class MediaIns {
    private String nombre;
    private String url;
    private int likes;

    public MediaIns(String nombre, String url, int likes) {
        this.nombre = nombre;
        this.url = url;
        this.likes = likes;
    }

    public MediaIns() {
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
