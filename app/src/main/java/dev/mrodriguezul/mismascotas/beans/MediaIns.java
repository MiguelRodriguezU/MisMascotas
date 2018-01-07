package dev.mrodriguezul.mismascotas.beans;

public class MediaIns {
    private String url;
    private int likes;

    public MediaIns(String url, int likes) {
        this.url = url;
        this.likes = likes;
    }

    public MediaIns() {
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
