package com.ardidwibowo.daftarkursus.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ardidwibowo.daftarkursus.R
import com.ardidwibowo.daftarkursus.model.Kursus
import kotlinx.android.synthetic.main.custom_row.view.*

//TODO 11  : Buat adapter untuk mnampilkan recyler view dari laout yang tadi dibuat
class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var kursusList = emptyList<Kursus>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
       return kursusList.size
    }

    //Tampilkan item yang ada
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = kursusList[position]
        holder.itemView.Kursus_txt.text = currentItem.kursus
        holder.itemView.Lembaga_txt.text = currentItem.lembaga
        holder.itemView.Harga_txt.text = currentItem.harga.toString()

        //ketika ditekan item maka masuk ke updatefragment
        holder.itemView.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(kursus: List<Kursus>){
        this.kursusList = kursus
        notifyDataSetChanged()
    }
}