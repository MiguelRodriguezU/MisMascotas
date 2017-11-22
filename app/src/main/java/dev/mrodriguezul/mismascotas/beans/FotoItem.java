package dev.mrodriguezul.mismascotas.beans;

public class FotoItem {
    private int foto;
    private int raiting;

    public FotoItem(int foto, int raiting) {
        this.foto = foto;
        this.raiting = raiting;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getRaiting() {
        return raiting;
    }

    public void setRaiting(int raiting) {
        this.raiting = raiting;
    }
}
