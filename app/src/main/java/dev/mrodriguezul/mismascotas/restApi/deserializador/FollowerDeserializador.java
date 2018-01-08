package dev.mrodriguezul.mismascotas.restApi.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

import dev.mrodriguezul.mismascotas.beans.Follower;
import dev.mrodriguezul.mismascotas.restApi.JsonKeys;
import dev.mrodriguezul.mismascotas.restApi.model.FollowerResponse;

/**
 * Created by MIGUEL on 7/01/2018.
 */

public class FollowerDeserializador implements JsonDeserializer<FollowerResponse> {

    @Override
    public FollowerResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        FollowerResponse followerResponse = gson.fromJson(json, FollowerResponse.class);
        JsonArray followerResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.FOLLOWER_RESPONSE_ARRAY);

        followerResponse.setFollowers(deserializarFollowerFromJSON(followerResponseData));

        return followerResponse;
    }

    private ArrayList<Follower> deserializarFollowerFromJSON(JsonArray followerResponseData){
        ArrayList<Follower> followers = new ArrayList<>();
        for(int i=0 ; i< followerResponseData.size(); i++){
            JsonObject followerResponseDataObject = followerResponseData.get(i).getAsJsonObject();

            String id = followerResponseDataObject.get(JsonKeys.FOLLOWER_ID).getAsString();
            String username = followerResponseDataObject.get(JsonKeys.FOLLOWER_USERNAME).getAsString();
            String fullName = followerResponseDataObject.get(JsonKeys.FOLLOWER_FULL_NAME).getAsString();
            String urlPic = followerResponseDataObject.get(JsonKeys.FOLLOWER_PROFILE_PIC).getAsString();

            Follower follower = new Follower();
            follower.setId(id);
            follower.setUsername(username);
            follower.setFullName(fullName);
            follower.setUrlFoto(urlPic);

            followers.add(follower);
        }
        return followers;
    }
}
