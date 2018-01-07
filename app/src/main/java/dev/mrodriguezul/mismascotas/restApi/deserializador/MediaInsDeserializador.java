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

import dev.mrodriguezul.mismascotas.beans.MediaIns;
import dev.mrodriguezul.mismascotas.restApi.JsonKeys;
import dev.mrodriguezul.mismascotas.restApi.model.MediaInsResponse;

public class MediaInsDeserializador implements JsonDeserializer<MediaInsResponse> {

    @Override
    public MediaInsResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MediaInsResponse mediaInsResponse = gson.fromJson(json, MediaInsResponse.class);
        JsonArray mediaInsResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIAINS_RESPONSE_ARRAY);

        mediaInsResponse.setMediasInstagram(deserializarMediaInstagramFromJSON(mediaInsResponseData));

        return mediaInsResponse;
    }

    private ArrayList<MediaIns> deserializarMediaInstagramFromJSON(JsonArray mediaInsResponseData){
        ArrayList<MediaIns> mediaIns = new ArrayList<>();
        for(int i=0 ; i< mediaInsResponseData.size(); i++){
            JsonObject mediaResponseDataObject = mediaInsResponseData.get(i).getAsJsonObject();

            JsonObject imageJson = mediaResponseDataObject.getAsJsonObject(JsonKeys.MEDIAINS_IMAGES);
            JsonObject stdResolution = imageJson.getAsJsonObject(JsonKeys.MEDIAINS_STANDARD_RESOLUTION);

            String urlFoto = stdResolution.get(JsonKeys.MEDIAINS_URL).getAsString();

            JsonObject likesJson = mediaResponseDataObject.getAsJsonObject(JsonKeys.MEDIAINS_LIKES);
            int likes = likesJson.get(JsonKeys.MEDIAINS_LIKES_COUNT).getAsInt();

            MediaIns mediaActual = new MediaIns();
            mediaActual.setUrl(urlFoto);
            mediaActual.setLikes(likes);

            mediaIns.add(mediaActual);
        }
        return mediaIns;
    }
}