package com.fmi.proiect_mobil

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.fmi.proiect.room.Produs
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.layout_produs.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.ProdusViewHolder>() {

    private var listaproduse = emptyList<Produs>()

    class ProdusViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdusViewHolder {

        return ProdusViewHolder(LayoutInflater.from(parent.context).
        inflate(R.layout.layout_produs, parent,false))

    }

    override fun onBindViewHolder(holder: ProdusViewHolder, position: Int) {

        val produscurent = listaproduse[position]

        holder.itemView.produs_afisare.text = produscurent.denumire_produs.toString()
        holder.itemView.cantitate_afisare.text = produscurent.cantitate_produs.toString()
        holder.itemView.text_id.text=produscurent.id.toString()

    }

    override fun getItemCount(): Int {

        return listaproduse.size
    }

    fun setData (produs: List<Produs>){

        listaproduse = produs
        notifyDataSetChanged()


    }


}