package dev.mrodriguezul.mismascotas.restApi.model;

import java.util.ArrayList;

import dev.mrodriguezul.mismascotas.beans.UsuarioIns;


public class UsuarioInsResponse {
    private ArrayList<UsuarioIns> usuariosInstagram;

    public ArrayList<UsuarioIns> getUsuariosInstagram() {
        return usuariosInstagram;
    }

    public void setUsuariosInstagram(ArrayList<UsuarioIns> usuariosInstagram) {
        this.usuariosInstagram = usuariosInstagram;
    }
}
