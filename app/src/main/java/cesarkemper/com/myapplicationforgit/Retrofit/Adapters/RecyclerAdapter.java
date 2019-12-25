package cesarkemper.com.myapplicationforgit.Retrofit.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cesarkemper.com.myapplicationforgit.R;
import cesarkemper.com.myapplicationforgit.Retrofit.Entities.Album;
import cesarkemper.com.myapplicationforgit.activities.AlbumDetail;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    private static final String TAG = RecyclerAdapter.class.getSimpleName();
    private LayoutInflater mInflater;
    private List<Album> mData;
    private Activity mContext;

    public RecyclerAdapter(Activity context,List<Album>  myDataset) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mData=myDataset;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = mInflater.inflate(R.layout.recycler_items, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        return holder;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        final Album data = mData.get(position);

        holder.txt_name.setText(data.getTitle());

        Glide.with(mContext)
                .load(data.getThumbnailUrl())
                .into(holder.imgAlbum);//IMAGE VIEW
    }
    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }

        return 0;

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.txt_name)//REFERENCIA A LA VISTA
        public TextView txt_name;
        @BindView(R.id.card_view)
        public CardView cv;
        @BindView(R.id.img_album)
        public ImageView imgAlbum;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getLayoutPosition();

            Toast.makeText(mContext, mData.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(mContext, AlbumDetail.class);
            intent.putExtra("url",mData.get(position).getUrl());
            intent.putExtra("title",mData.get(position).getTitle());
            mContext.startActivity(intent);
            //EVENT
            Bundle params = new Bundle();
            params.putString("blog_selected", mData.get(position).getTitle());



            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1");
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, mData.get(position).getTitle());
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "text");

        }


    }

}
