package dev.mrodriguezul.mismascotas.restApi.model;

import java.util.ArrayList;

import dev.mrodriguezul.mismascotas.beans.MediaIns;


public class MediaInsResponse {
    private ArrayList<MediaIns> mediasInstagram;

    public ArrayList<MediaIns> getMediasInstagram() {
        return mediasInstagram;
    }

    public void setMediasInstagram(ArrayList<MediaIns> mediasInstagram) {
        this.mediasInstagram = mediasInstagram;
    }
}
