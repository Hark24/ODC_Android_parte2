package com.tektonlabs.odc_android.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tektonlabs.odc_android.R;
import com.tektonlabs.odc_android.models.Album;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rubymobile on 5/27/15.
 */
public class AlbumAdapter extends BaseAdapter {

    private Activity activity;
    private List<Album> albums;

    /* Constructor */
    public AlbumAdapter(Activity activity, List<Album> albums) {
        this.activity = activity;
        this.albums = albums;
    }

    /* Metodos del BaseAdapter */

    @Override
    public int getCount() {
        return albums.size();
    }

    @Override
    public Object getItem(int position) {
        return albums.get(position);
    }

    @Override
    public long getItemId(int position) {
        return albums.get(position).getCollectionId();
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

        if(albums.size() >= 0){
            Album album = albums.get(position);
            viewHolder.tv_artist.setText(album.getArtistName());
            viewHolder.tv_collection.setText(album.getCollectionName());
            viewHolder.tv_track.setText("Tracks: " + album.getTrackCount());
            viewHolder.tv_price.setText(album.getCurrency()+ " "+ album.getCollectionPrice());
        }

        return v;
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
