package com.tektonlabs.odc_android.models;

import com.google.gson.JsonObject;

/**
 * Created by rubymobile on 5/27/15.
 */
public class Album {
    private int collectionId;
    private String artistName;
    private String collectionName;
    private String artistViewUrl;
    private String collectionViewUrl;
    private String artworkUrl100;
    private Double collectionPrice;
    private int trackCount;
    private String country;
    private String currency;
    private String releaseDate;
    private String primaryGenreName;

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

    public String getCollectionViewUrl() {
        return collectionViewUrl;
    }

    public void setCollectionViewUrl(String collectionViewUrl) {
        this.collectionViewUrl = collectionViewUrl;
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

    public String getArtistViewUrl() {
        return artistViewUrl;
    }

    public void setArtistViewUrl(String artistViewUrl) {
        this.artistViewUrl = artistViewUrl;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public void setPrimaryGenreName(String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
    }

    public static Album parseAlbum(JsonObject albumJson){
        return new Album.Builder(albumJson.get("collectionId").getAsInt()).
                    artistName(albumJson.get("artistName").getAsString()).
                    collectionName(albumJson.get("collectionName").getAsString()).
                    collectionViewUrl(albumJson.get("collectionViewUrl").getAsString()).
                    artworkUrl100(albumJson.get("artworkUrl100").getAsString()).
                    collectionPrice(albumJson.get("collectionPrice").getAsDouble()).
                    trackCount(albumJson.get("trackCount").getAsInt()).
                    currency(albumJson.get("currency").getAsString()).
                    build();
    }

    public static class Builder {
        private int mCollectionId;
        private String mArtistName;
        private String mCollectionName;
        private String mArtistViewUrl;
        private String mCollectionViewUrl;
        private String mArtworkUrl100;
        private Double mCollectionPrice;
        private int mTrackCount;
        private String mCountry;
        private String mCurrency;
        private String mReleaseDate;
        private String mPrimaryGenreName;

        public Builder(int id) {
            mCollectionId = id;
        }

        public Builder artistName(String artistName) {
            mArtistName = artistName;
            return this;
        }

        public Builder collectionName(String collectionName){
            mCollectionName = collectionName;
            return this;
        }

        public Builder artistViewUrl(String artistViewUrl){
            mArtistViewUrl = artistViewUrl;
            return this;
        }

        public Builder collectionViewUrl(String collectionViewUrl){
            mCollectionViewUrl = collectionViewUrl;
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

        public Builder country(String country){
            mCountry = country;
            return this;
        }

        public Builder releaseDate(String releaseDate){
            mReleaseDate = releaseDate;
            return this;
        }

        public Builder primaryGenreName(String primaryGenreName){
            mPrimaryGenreName = primaryGenreName;
            return this;
        }

        public Builder currency(String currency){
            mCurrency = currency;
            return this;
        }

        public Album build() {
            Album album = new Album();
            album.setCollectionId(mCollectionId);
            album.setArtistName(mArtistName);
            album.setCollectionName(mCollectionName);
            album.setArtistViewUrl(mArtistViewUrl);
            album.setCollectionViewUrl(mCollectionViewUrl);
            album.setArtworkUrl100(mArtworkUrl100);
            album.setCollectionPrice(mCollectionPrice);
            album.setTrackCount(mTrackCount);
            album.setCountry(mCountry);
            album.setCurrency(mCurrency);
            album.setReleaseDate(mReleaseDate);
            album.setPrimaryGenreName(mPrimaryGenreName);
            return album;

        }

    }


}
