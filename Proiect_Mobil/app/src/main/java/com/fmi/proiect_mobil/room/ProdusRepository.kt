package com.fmi.proiect.room

import androidx.lifecycle.LiveData

class ProdusRepository(private val  produsDao: ProdusDao) {

    val readAllData : LiveData<List<Produs>> = produsDao.readAllProduse()

    suspend fun addProdus (produs: Produs){

        produsDao.addProdus(produs)

    }

    suspend fun stergeLista(){

        produsDao.stergeLista()

    }

    fun cauta(desc : String) : LiveData<List<Produs>>{

        return produsDao.rezultatcautare(desc)

    }



}