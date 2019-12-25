package cesarkemper.com.myapplicationforgit.Retrofit.Api;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Routes {
    @GET("/album/{id}/photos")
    Call<JsonArray> getAlbum(@Path("id") int albumId);

}
