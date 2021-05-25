package com.fmi.proiect.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.fmi.proiect_mobil.room.ProdusDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProdusViewModel(application: Application): AndroidViewModel(application){


    val readAllData: LiveData<List<Produs>>
    private val repository: ProdusRepository

    init {
        val produsDao = ProdusDatabase.getDatabase(application).produsDao()
        repository = ProdusRepository(produsDao)
        readAllData = repository.readAllData
    }


    fun addProdus(produs: Produs){


        viewModelScope.launch(Dispatchers.IO){

            repository.addProdus(produs)

        }


    }

    fun stergeLista(){

        viewModelScope.launch(Dispatchers.IO) {

            repository.stergeLista()

        }

    }
    fun cauta(desc: String) : LiveData<List<Produs>> {
        return repository.cauta(desc)
    }






}