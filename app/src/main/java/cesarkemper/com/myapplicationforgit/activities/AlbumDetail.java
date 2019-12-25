package cesarkemper.com.myapplicationforgit.activities;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import cesarkemper.com.myapplicationforgit.R;

public class AlbumDetail extends AppCompatActivity {

    @BindView(R.id.id_img_portada)
    ImageView id_img_portada;
    @BindView(R.id.profile_toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appBar;
    @BindView(R.id.des)
    TextView description;
    @BindView(R.id.btn_floating)
    FloatingActionButton btn_floating;


    String title;
    String url;

    CollapsingToolbarLayout collapsing_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);
        ButterKnife.bind(this, AlbumDetail.this);

        Bundle b = getIntent().getExtras();
        if(b!=null) {
            url = b.getString("url");
            title = b.getString("title");
        }


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        HideStatusBar();

        collapsing_toolbar = findViewById(R.id.collapsing_toolbar);
        collapsing_toolbar.setTitle(title);

        description.setText(title);


        Glide.with(getApplicationContext())
                .load(url)
                .into(id_img_portada);//IMAGE VIEW

        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (verticalOffset > -5) {
                } else if (id_img_portada.getDrawable()!=null) {

                    Bitmap bitmapPallet = ((BitmapDrawable) id_img_portada.getDrawable()).getBitmap();

                    Palette.from(bitmapPallet).generate(new Palette.PaletteAsyncListener() {
                        @Override
                        public void onGenerated(Palette palette) {
                            collapsing_toolbar.setContentScrimColor(palette.getMutedColor(getResources().getColor(R.color.colorPrimary)));
                            collapsing_toolbar.setStatusBarScrimColor(palette.getDarkMutedColor(getResources().getColor(
                                    R.color.colorPrimaryDark)));

                        }
                    });
                }

            }
        });

    }
    public void HideStatusBar(){
        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.
                    LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
