package cesarkemper.com.myapplicationforgit.Retrofit.Api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServices {
    private static Retrofit retrofit;

    final static String SERVER = "https://jsonplaceholder.typicode.com/";

    public static Routes getApiInstance(){

        return startRetrofitInstance().create(Routes.class);
    }

    private static Retrofit startRetrofitInstance() {

        if (retrofit == null) {

            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(SERVER)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
