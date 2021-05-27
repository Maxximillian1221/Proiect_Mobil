package com.fmi.proiect_mobil

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import com.fmi.proiect.room.Produs
import com.fmi.proiect.room.ProdusViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var nav : BottomNavigationView
    private lateinit var mProdusViewModel : ProdusViewModel
    private lateinit var buton_share :ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mProdusViewModel = ViewModelProvider(this).get(ProdusViewModel::class.java)


        buton_imagine.setOnClickListener{ // buton poza

            var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i , 123)

        }


        button_adauga.setOnClickListener{ // buton salveaza apelez functia

            insertProdus()


        }


        //navigatie

        nav= findViewById(R.id.navigatie)
        nav.selectedItemId = R.id.acasa

        nav.setOnNavigationItemSelectedListener {

            val intent = Intent(this , Lista_Produse::class.java)
            val intent2 = Intent(this , MainActivity::class.java)
            when(it.itemId) {

                R.id.lista -> {startActivity(intent)
                    overridePendingTransition(0 , 0)}

                R.id.acasa -> {startActivity(intent2)
                    overridePendingTransition(0 , 0);}

            }
            true

        }

        // sf navigatie



        //share


        buton_share=findViewById(R.id.buton_share)
        buton_share.setOnClickListener {

            val s = denumire_produs_et.text.toString()
            val a = cantitate_produs_et.text.toString()

            if (a.isNotBlank() && s.isNotEmpty())
            {
            val share_intent = Intent ()
            share_intent.action = Intent.ACTION_SEND
            share_intent.type = "text/plain"
            share_intent.putExtra(Intent.EXTRA_TEXT , "Cumpara: "+ a +" "+ s )
            startActivity(Intent.createChooser(share_intent, "ok"))


        }else{

                Toast.makeText(this, "Completati campurile!", Toast.LENGTH_SHORT).show()

        }

            }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) { // functie poza + insert in imageview
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode===123){


            var bmp = data?.extras?.get("data") as Bitmap
            imagine.setImageBitmap(bmp)


        }
    }


    private fun insertProdus() { /// functie insert produs

        //asignez valoarea din inputurile din layout

        val denumire_prod = denumire_produs_et.text.toString()
        val cantitate_prod = cantitate_produs_et.text.toString()

        try {

            if (denumire_prod.isNotEmpty() && cantitate_prod.isNotEmpty()) {

                // creez produs

                val produs = Produs(0, denumire_prod, cantitate_prod )

                // inserez produsul in baza

                mProdusViewModel.addProdus(produs)
                Toast.makeText(this, "Produs Salvat!", Toast.LENGTH_SHORT).show()

                denumire_produs_et.text.clear() // sterg textul din input
                cantitate_produs_et.text.clear()


            } else {

                Toast.makeText(this, "Completati campurile!", Toast.LENGTH_SHORT).show()

            }

        }catch (e:Exception){

            Toast.makeText(this, "Eroare! $e", Toast.LENGTH_SHORT).show()

        }
    }


}