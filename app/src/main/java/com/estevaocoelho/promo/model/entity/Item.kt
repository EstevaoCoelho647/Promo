package com.estevaocoelho.promo.model.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.database.ServerValue

/**
 * Created by estevaocoelho on 05/12/17.
 */
class Item() : Parcelable {

    var id: String? = null

    var views: Int = 0

    var price: Double = 0.0

    var description: String = ""

    var createDate: Long = 0

    var itemURL: String = ""

    var imageURL = ""

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        views = parcel.readInt()
        price = parcel.readDouble()
        description = parcel.readString()
        createDate = parcel.readLong()
        itemURL = parcel.readString()
        imageURL = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeInt(views)
        parcel.writeDouble(price)
        parcel.writeString(description)
        parcel.writeLong(createDate)
        parcel.writeString(itemURL)
        parcel.writeString(imageURL)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }

}