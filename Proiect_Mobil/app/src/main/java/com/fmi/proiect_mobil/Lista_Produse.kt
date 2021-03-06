package com.fmi.proiect_mobil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fmi.proiect.room.ProdusViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView



class Lista_Produse : AppCompatActivity() {


    private lateinit var nav: BottomNavigationView
    private lateinit var mProdusViewModel: ProdusViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista__produse)


        // navigatie


        nav = findViewById(R.id.navigatie)
        nav.selectedItemId = R.id.lista

        nav.setOnNavigationItemSelectedListener {
            val intent = Intent(this, Lista_Produse::class.java)
            val intent2 = Intent(this, MainActivity::class.java)
            when (it.itemId) {


                R.id.lista -> {
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                }
                R.id.acasa -> {
                    startActivity(intent2)
                    overridePendingTransition(0, 0)
                }

            }
            true

        }

        // sf navigatie



        // recycler view
        val adapter = ListAdapter()
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // view model produs

        mProdusViewModel = ViewModelProvider(this).get(ProdusViewModel::class.java)
        mProdusViewModel.readAllData.observe(this, Observer  {

            adapter.setData(it)

        })


    }


    //meniu cautare

    override fun onCreateOptionsMenu(menu: Menu): Boolean {//adaug meniu

        menuInflater.inflate(R.menu.sterge_cauta, menu)

        val cauta = menu.findItem(R.id.buton_cauta)
        val searchview = cauta.actionView as SearchView
        searchview.isSubmitButtonEnabled = true
        searchview.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    getItemsFromDb(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    getItemsFromDb(newText)
                }
                return true
            }

            private fun getItemsFromDb(textcauta: String) {
                var textcauta = textcauta
                textcauta = "%$textcauta%"

                mProdusViewModel.cauta(textcauta).observe(this@Lista_Produse, Observer { list -> //display in log-uri
                        list.let {

                            Log.e("Gasite = ", list.toString())

                        }

                    })

            }
        })

        return true
    }

    //meniu stergere

    override fun onOptionsItemSelected(item: MenuItem): Boolean { // daca este apasat icon sterge -> sterge prod()

        if (item.itemId == R.id.buton_sterge) {

            stergelista()

        }

        return super.onOptionsItemSelected(item)
    }

    private fun stergelista() { // functie de stergere lista

        val builder = AlertDialog.Builder(this) // alerta stergere
        builder.setPositiveButton("Da"){ _,_ ->

            mProdusViewModel.stergeLista()
            Toast.makeText(this, "Lista a fost stearsa", Toast.LENGTH_SHORT).show()

        }
        builder.setNegativeButton("Nu"){_,_ ->
        }

        builder.setTitle("Sterge lista")
        builder.setMessage("Sunteti sigur ca doriti sa stergeti intreaga lista?")
        builder.create().show()


    }

}


















