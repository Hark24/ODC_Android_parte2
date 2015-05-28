package com.tektonlabs.odc_android.models;

import android.util.Log;

import com.google.gson.JsonObject;

/**
 * Created by rubymobile on 5/27/15.
 */
public class Media {
    private int collectionId;
    private String wrapperType;
    private String artistName;
    private String collectionName;
    private String trackName;
    private String artworkUrl100;
    private Double collectionPrice;
    private int trackCount;
    private String currency;
    private String releaseDate;
    private String previewUrl;

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }

    public Double getCollectionPrice() {
        return collectionPrice;
    }

    public void setCollectionPrice(Double collectionPrice) {
        this.collectionPrice = collectionPrice;
    }

    public int getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(int trackCount) {
        this.trackCount = trackCount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getWrapperType() {
        return wrapperType;
    }

    public void setWrapperType(String wrapperType) {
        this.wrapperType = wrapperType;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public static Media parseMedia(JsonObject mediaJson){
        Log.e("Collection ID", mediaJson.get("collectionId").equals(null) ? "error null" : mediaJson.get("collectionId").getAsInt() + "");
        Media.Builder mediaBuilder = new Media.Builder().
                                        collectionId(mediaJson.get("collectionId").getAsInt()).
                                        wrapperType(mediaJson.get("wrapperType").getAsString()).
                                        artistName(mediaJson.get("artistName").getAsString()).
                                        collectionName(mediaJson.get("collectionName").getAsString()).
                                        artworkUrl100(mediaJson.get("artworkUrl100").getAsString()).
                                        collectionPrice(mediaJson.get("collectionPrice").getAsDouble()).
                                        trackCount(mediaJson.get("trackCount").getAsInt()).
                                        currency(mediaJson.get("currency").getAsString()).
                                        releaseDate(mediaJson.get("releaseDate").getAsString());

        if(mediaJson.has("previewUrl")){
            mediaBuilder.previewUrl(mediaJson.get("previewUrl").getAsString());
        }

        if(mediaJson.has("trackName")){
            mediaBuilder.trackName(mediaJson.get("trackName").getAsString());
        }

        return mediaBuilder.build();
    }

    public static class Builder {
        private int mCollectionId;
        private String mWrapperType;
        private String mArtistName;
        private String mCollectionName;
        private String mTrackName;
        private String mArtworkUrl100;
        private Double mCollectionPrice;
        private int mTrackCount;
        private String mCurrency;
        private String mReleaseDate;
        private String mPreviewUrl;

        public Builder() {
        }

        public Builder collectionId(int collectionId){
            mCollectionId = collectionId;
            return this;
        }

        public Builder wrapperType(String wrapperType){
            mWrapperType = wrapperType;
            return this;
        }

        public Builder artistName(String artistName) {
            mArtistName = artistName;
            return this;
        }

        public Builder collectionName(String collectionName){
            mCollectionName = collectionName;
            return this;
        }

        public Builder trackName(String trackName){
            mTrackName = trackName;
            return this;
        }

        public Builder artworkUrl100(String artworkUrl100){
            mArtworkUrl100 = artworkUrl100;
            return this;
        }

        public Builder collectionPrice(Double collectionPrice){
            mCollectionPrice = collectionPrice;
            return this;
        }

        public Builder trackCount(int trackCount){
            mTrackCount = trackCount;
            return this;
        }

        public Builder releaseDate(String releaseDate){
            mReleaseDate = releaseDate;
            return this;
        }

        public Builder currency(String currency){
            mCurrency = currency;
            return this;
        }

        public Builder previewUrl(String previewUrl){
            mPreviewUrl = previewUrl;
            return this;
        }

        public Media build() {
            Media media = new Media();
            media.setCollectionId(mCollectionId);
            media.setWrapperType(mWrapperType);
            media.setArtistName(mArtistName);
            media.setCollectionName(mCollectionName);
            media.setTrackName(mTrackName);
            media.setArtworkUrl100(mArtworkUrl100);
            media.setCollectionPrice(mCollectionPrice);
            media.setTrackCount(mTrackCount);
            media.setCurrency(mCurrency);
            media.setReleaseDate(mReleaseDate);
            media.setPreviewUrl(mPreviewUrl);
            return media;
        }

    }


}
