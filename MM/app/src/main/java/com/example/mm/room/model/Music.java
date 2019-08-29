package com.example.mm.room.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "music")
public class Music implements Parcelable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "music_name")
    private String name;
    @ColumnInfo(name = "singer")
    private String singer;
    @ColumnInfo(name = "music_composer")
    private String composer;
    @ColumnInfo(name = "music_path")
    private String path;
    @ColumnInfo(name = "position_default")
    private int positionDefault;
    @ColumnInfo(name = "image")
    private String image;

    public Music() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPositionDefault() {
        return positionDefault;
    }

    public void setPositionDefault(int positionDefault) {
        this.positionDefault = positionDefault;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.singer);
        parcel.writeString(this.composer);
        parcel.writeString(this.path);
        parcel.writeString(this.image);
        parcel.writeInt(this.positionDefault);
    }

    public static final Creator<Music> CREATOR = new Creator<Music>() {
        @Override
        public Music createFromParcel(Parcel parcel) {
            return new Music(parcel);
        }

        @Override
        public Music[] newArray(int i) {
            return new Music[i];
        }
    };
    protected Music(Parcel parcel) {
        this.id = parcel.readInt();
        this.name = parcel.readString();
        this.singer = parcel.readString();
        this.composer = parcel.readString();
        this.path = parcel.readString();
        this.image = parcel.readString();
        this.positionDefault = parcel.readInt();
    }

    public static class Builder {
        private int id, position;
        private String name, singer, composer,path, image;

        public Builder(String name) {
            this.name = name;
        }
        public Builder id(int id) {
            this.id = id;
            return this;
        }
        public Builder singer(String singer) {
            this.singer = singer;
            return this;
        }
        public Builder position(int position) {
            this.position = position;
            return this;
        }
        public Builder composer(String composer) {
            this.composer = composer;
            return this;
        }
        public Builder path(String path) {
            this.path = path;
            return this;
        }
        public Builder image(String image) {
            this.image = image;
            return this;
        }
        public Music build() {
            return new Music(this);
        }
    }
    private Music(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.positionDefault = builder.position;
        this.singer = builder.singer;
        this.composer = builder.composer;
        this.path = builder.path;
        this.image = builder.image;
    }

}
