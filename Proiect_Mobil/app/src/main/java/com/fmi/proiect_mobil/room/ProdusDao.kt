package com.fmi.proiect.room

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ProdusDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProdus (produs: Produs)

    @Query("DELETE FROM Tabel_Produse")
    suspend fun stergeLista()

    @Query("SELECT * FROM Tabel_Produse ORDER BY id ASC")
    fun readAllProduse(): LiveData<List<Produs>>

    @Query("SELECT * FROM tabel_produse WHERE denumire_produs LIKE  :desc")
    fun rezultatcautare(desc : String) : LiveData<List<Produs>>


}