package com.example.mynewsapp.core.models


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.mynewsapp.core.db.typeconverter.NewsTypeConverter

@Keep
@Entity(tableName = "articles")
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    @SerializedName("author")
    val author: String?, // Amy Cheng, Eugene Scott
    @SerializedName("content")
    val content: String?, // Placeholder while article actions loadSen. Rand Paul (R-Ky.) objected Thursday to a Senate vote on assistance for Ukraine, delaying passage of the bill till next week and dampening a bipartisan pus… [+2957 chars]
    @SerializedName("description")
    val description: String?, // The $39.8 billion bill will pass despite the Kentucky senator's lone objection, which dampened a bipartisan push.
    @SerializedName("publishedAt")
    val publishedAt: String?, // 2022-05-13T10:30:00Z
    @SerializedName("source")
    @TypeConverters(value = [NewsTypeConverter::class])
    val source: Source?,
    @SerializedName("title")
    val title: String?, // Rand Paul blocks Senate vote to advance Ukraine war aid bill - The Washington Post
    @SerializedName("url")
    val url: String?, // https://www.washingtonpost.com/world/2022/05/13/rand-paul-ukraine-aid-senate-vote/
    @SerializedName("urlToImage")
    val urlToImage: String? // https://www.washingtonpost.com/wp-apps/imrs.php?src=https://arc-anglerfish-washpost-prod-washpost.s3.amazonaws.com/public/FDRWPZGKJEI6ZN7OOTYJ3AT4UY.jpg&w=1440
) :Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Source::class.java.classLoader),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(author)
        parcel.writeString(content)
        parcel.writeString(description)
        parcel.writeString(publishedAt)
        parcel.writeParcelable(source, flags)
        parcel.writeString(title)
        parcel.writeString(url)
        parcel.writeString(urlToImage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Article> {
        override fun createFromParcel(parcel: Parcel): Article {
            return Article(parcel)
        }

        override fun newArray(size: Int): Array<Article?> {
            return arrayOfNulls(size)
        }
    }
}
