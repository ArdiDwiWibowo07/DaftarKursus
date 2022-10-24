package com.ardidwibowo.daftarkursus.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

//TODO 2 : Membuat model tabel
@Parcelize
@Entity(tableName = "tabel_kursus")
data class Kursus(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val kursus: String,
    val lembaga : String,
    val harga : Int
): Parcelable