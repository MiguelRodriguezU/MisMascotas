package dev.mrodriguezul.mismascotas.restApi.model;

/**
 * Created by MIGUEL on 21/01/2018.
 */

public class LikeResponse {
    private String id;
    private String idFoto;
    private String usuario;

    public LikeResponse(String id, String idFoto, String usuario) {
        this.id = id;
        this.idFoto = idFoto;
        this.usuario = usuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(String idFoto) {
        this.idFoto = idFoto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
