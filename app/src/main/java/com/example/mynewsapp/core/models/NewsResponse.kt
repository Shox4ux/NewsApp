package com.example.mynewsapp.core.models


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.mynewsapp.core.db.typeconverter.NewsTypeConverter

@Entity(tableName = "news_app")
data class NewsResponse(
    @SerializedName("articles")
    @TypeConverters(value = [NewsTypeConverter::class])
    val articles: List<Article>?,
    @SerializedName("status")
    val status: String?, // ok
    @SerializedName("totalResults")
    val totalResults: Int // 38
) : Parcelable {

    @PrimaryKey(autoGenerate = false)
    var id: Long = 0

    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(Article),
        parcel.readString(),
        parcel.readInt()
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(articles)
        parcel.writeString(status)
        parcel.writeInt(totalResults)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NewsResponse> {
        override fun createFromParcel(parcel: Parcel): NewsResponse {
            return NewsResponse(parcel)
        }

        override fun newArray(size: Int): Array<NewsResponse?> {
            return arrayOfNulls(size)
        }
    }
}