package com.fmi.proiect.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "Tabel_Produse")
data class Produs (

    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val denumire_produs: String ,
    val cantitate_produs: String

)