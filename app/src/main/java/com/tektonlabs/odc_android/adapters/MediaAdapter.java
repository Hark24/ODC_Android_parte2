package com.tektonlabs.odc_android.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tektonlabs.odc_android.activities.MainActivity;
import com.tektonlabs.odc_android.R;
import com.tektonlabs.odc_android.models.Media;

import java.util.List;

/**
 * Created by rubymobile on 5/27/15.
 */
public class MediaAdapter extends BaseAdapter implements View.OnClickListener{

    private Activity activity;
    private List<Media> medias;

    /* Constructor */
    public MediaAdapter(Activity activity, List<Media> medias) {
        this.activity = activity;
        this.medias = medias;
    }

    /* Metodos del BaseAdapter */

    @Override
    public int getCount() {
        return medias.size();
    }

    @Override
    public Object getItem(int position) {
        return medias.get(position);
    }

    @Override
    public long getItemId(int position) {
        return medias.get(position).getCollectionId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder viewHolder;

        if(convertView == null){
            v = activity.getLayoutInflater().inflate(R.layout.album_row, null);

            viewHolder = new ViewHolder();
            viewHolder.iv_cover = (ImageView) v.findViewById(R.id.iv_cover);
            viewHolder.tv_artist=(TextView)v.findViewById(R.id.tv_artist);
            viewHolder.tv_collection=(TextView)v.findViewById(R.id.tv_collection);
            viewHolder.tv_track=(TextView)v.findViewById(R.id.tv_track);
            viewHolder.tv_price=(TextView)v.findViewById(R.id.tv_price);

            v.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) v.getTag();
        }

        if(medias.size() >= 0){
            Media media = medias.get(position);
            viewHolder.tv_artist.setText(media.getArtistName());
            if (media.getWrapperType().equals("collection")){
                viewHolder.tv_collection.setText(media.getCollectionName());
            }
            else{
                viewHolder.tv_collection.setText(media.getTrackName());
            }
            viewHolder.tv_track.setText("Tracks: " + media.getTrackCount());
            viewHolder.tv_price.setText(media.getCurrency()+ " "+ media.getCollectionPrice());
            Picasso.with(activity).load(media.getArtworkUrl100()).into(viewHolder.iv_cover);
            v.setOnClickListener(new OnItemClickListener(position));
        }

        return v;
    }

    /* Métodos que reconocen el clic */

    @Override
    public void onClick(View v) {

    }

    /* Se llama cuando se hace clic a un elemento del ListView */
    private class OnItemClickListener  implements View.OnClickListener {
        private int mPosition;

        public OnItemClickListener(int position){
            mPosition = position;
        }

        @Override
        public void onClick(View arg0) {
            MainActivity main = (MainActivity)activity;
            main.onItemClick(mPosition);
        }
    }

    /* Holder que contiene los elementos a mostrar en la fila de la lista*/

    public static class ViewHolder{
        public ImageView iv_cover;
        public TextView tv_artist;
        public TextView tv_collection;
        public TextView tv_track;
        public TextView tv_price;
    }
}
