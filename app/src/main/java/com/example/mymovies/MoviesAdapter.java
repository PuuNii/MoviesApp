package com.example.mymovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.mymovies.MoviesNew.Result;
import com.example.mymovies.Retrofit.ApiClient;
import com.example.mymovies.MoviesNew.Result;
import com.example.mymovies.Retrofit.ApiClient;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ItemHolder>
{

    public List<Result> adsList;
    Context context;

    public MoviesAdapter(List<Result> adsList , Context context)
    {
        this.adsList = adsList;
        this.context = context;

    }
    @NonNull
    @Override
    public MoviesAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_ads, viewGroup, false);
        final MoviesAdapter.ItemHolder itemHolder = new MoviesAdapter.ItemHolder(itemView);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int postion)
    {
        // get data from list of response of ads
        Result itemData = adsList.get(postion);

        // set data of items of recycle view
        itemHolder.titleTv.setText(itemData.getTitle());
        itemHolder.dateTv.setText(itemData.getReleaseDate());
        itemHolder.rateTv.setText(itemData.getVoteAverage()+"");


        // download image from path and show it
        if (itemData.getPosterPath() != null && itemData.getPosterPath() != "")
            {
                Glide.with(context)
                        // url of image
                        .load(ApiClient.IMAGE_PATH+itemData.getPosterPath())
//                        .error(R.drawable.arrow_back)
                        .listener(new RequestListener<String, GlideDrawable>()
                        {
                            @Override
                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource)
                            {
                                Log.e("Glide erorr**", "failed to load image");
                                // Toast.makeText(getApplicationContext() , getResources().getString(R.string.load_photo_error) , Toast.LENGTH_LONG).show();
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                return false;
                            }

                        })
                        .error(context.getResources().getDrawable(R.drawable.ic_popcorn))
//                    .diskCacheStrategy(DiskCacheStrategy.NONE)
//                    .skipMemoryCache(true)
                        .into(itemHolder.adIv);
            }
//

    }


    @Override
    public int getItemCount() {
        return adsList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder
    {
        ImageView adIv ;
        TextView titleTv , rateTv , dateTv;
        ConstraintLayout layout;

        public ItemHolder(View itemView)
        {
            super(itemView);
            layout  = itemView.findViewById(R.id.container);
            adIv  = itemView.findViewById(R.id.imageView4);
            titleTv  = itemView.findViewById(R.id.textView13);
            rateTv  = itemView.findViewById(R.id.textView15);
            dateTv  = itemView.findViewById(R.id.textView14);
        }

    }
}
