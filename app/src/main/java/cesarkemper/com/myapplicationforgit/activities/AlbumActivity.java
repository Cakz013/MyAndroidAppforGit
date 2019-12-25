package cesarkemper.com.myapplicationforgit.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cesarkemper.com.myapplicationforgit.R;
import cesarkemper.com.myapplicationforgit.Retrofit.Adapters.RecyclerAdapter;
import cesarkemper.com.myapplicationforgit.Retrofit.Api.ApiServices;
import cesarkemper.com.myapplicationforgit.Retrofit.Api.Routes;
import cesarkemper.com.myapplicationforgit.Retrofit.Entities.Album;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumActivity extends AppCompatActivity {

    @BindView( R.id.recycler)
    RecyclerView rv;
    @BindView( R.id.toolbar)
    Toolbar toolbar;
    LinearLayoutManager llm;
    RecyclerAdapter mAdapter;
    String  mPhotoUrl ;
    List<Album> listaData= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("RecyclerView");
        toolbar.setTitleTextColor(Color.WHITE);


        loadData();

        rv.setItemAnimator(new DefaultItemAnimator());

        rv.setHasFixedSize(true);
        llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);
        rv.setLayoutManager(new GridLayoutManager(this, 2));//en caso de ser grid
        mAdapter = new RecyclerAdapter(AlbumActivity.this, listaData);
        rv.setAdapter(mAdapter);


    }


    private void loadData(){

        final ProgressDialog myPd_ring2 = ProgressDialog.show(AlbumActivity.this,
                "Please wait", "Loading...", true);
        Call<JsonArray> call;
        Routes service_api = ApiServices.getApiInstance();

        call = service_api.getAlbum(1);
        call.enqueue(new Callback<JsonArray>() {

            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                try {
                    Log.d("RESPONSE CODE",  response.code()+"");
                    JsonArray result =  response.body();

                    if (myPd_ring2 != null) {
                        myPd_ring2.dismiss();
                    }
                    if (result !=null && response.isSuccessful() && result.size()>0) {
                        Toast.makeText(getApplicationContext(), "Loaded Data",
                                Toast.LENGTH_SHORT).show();
                        for (int i=0;i<result.size();i++){
                            JsonObject j=   (JsonObject)result.get(i);
                            Album album = new Album();
                            album.setTitle(j.get("title").getAsString());
                            album.setThumbnailUrl(j.get("thumbnailUrl").getAsString());
                            album.setUrl(j.get("url").getAsString());
                            listaData.add(album);
                            mAdapter.notifyDataSetChanged();
                        }
                    } else {
                        Log.d("ERROR", "ERROR RESPONSE " + response.code());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                if(myPd_ring2!=null){
                    myPd_ring2.dismiss();
                }
                Log.d("ERROR", "ERROR RESPONSE "+ t.getMessage());
            }
        });


    }

}
