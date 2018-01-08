package dev.mrodriguezul.mismascotas.restApi.model;

import java.util.ArrayList;

import dev.mrodriguezul.mismascotas.beans.Follower;

/**
 * Created by MIGUEL on 7/01/2018.
 */

public class FollowerResponse {
    private ArrayList<Follower> followers;

    public ArrayList<Follower> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<Follower> followers) {
        this.followers = followers;
    }
}
