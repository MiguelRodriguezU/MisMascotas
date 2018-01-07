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

import dev.mrodriguezul.mismascotas.beans.UsuarioIns;
import dev.mrodriguezul.mismascotas.restApi.JsonKeys;
import dev.mrodriguezul.mismascotas.restApi.model.UsuarioInsResponse;

public class UsuarioInsDeserializador implements JsonDeserializer<UsuarioInsResponse> {


    @Override
    public UsuarioInsResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        UsuarioInsResponse usuarioInsResponse = gson.fromJson(json, UsuarioInsResponse.class);
        JsonArray usuarioInsResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.USERINS_SERCH_RESPONSE_ARRAY);

        usuarioInsResponse.setUsuariosInstagram(deserializarUsuarioInstagramFromJSON(usuarioInsResponseData));

        return usuarioInsResponse;
    }

    private ArrayList<UsuarioIns> deserializarUsuarioInstagramFromJSON(JsonArray usuarioInsResponseData){
        ArrayList<UsuarioIns> usuariosIns = new ArrayList<>();
        for(int i=0 ; i< usuarioInsResponseData.size(); i++){
            JsonObject usuarioInsResposeDataObject = usuarioInsResponseData.get(i).getAsJsonObject();

            String id = usuarioInsResposeDataObject.get(JsonKeys.USERINS_ID).getAsString();
            String userName = usuarioInsResposeDataObject.get(JsonKeys.USERINS_USERNAME).getAsString();
            String urlFoto = usuarioInsResposeDataObject.get(JsonKeys.USERINS_URL).getAsString();
            String fullName = usuarioInsResposeDataObject.get(JsonKeys.USERINS_FULLNAME).getAsString();

            UsuarioIns usuarioIns = new UsuarioIns(id, userName, urlFoto, fullName);

            usuariosIns.add(usuarioIns);
        }
        return usuariosIns;
    }
}
