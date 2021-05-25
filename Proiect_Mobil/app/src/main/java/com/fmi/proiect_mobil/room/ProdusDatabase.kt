package com.fmi.proiect_mobil.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fmi.proiect.room.Produs
import com.fmi.proiect.room.ProdusDao

@Database(entities = [Produs::class] , version = 1 , exportSchema = false)
abstract class ProdusDatabase: RoomDatabase() {

    abstract fun produsDao() : ProdusDao


    companion object{

        @Volatile
        private var INSTANCE : ProdusDatabase? = null

        fun getDatabase ( context: Context) : ProdusDatabase{

            val tempInstance = INSTANCE
            if(tempInstance != null){

                return tempInstance
            }
            synchronized(this){

                val instance= Room.databaseBuilder(
                context.applicationContext,
                ProdusDatabase::class.java,
                "produs_database"
                ).build()
                INSTANCE = instance
                return instance

            }
        }
    }
}