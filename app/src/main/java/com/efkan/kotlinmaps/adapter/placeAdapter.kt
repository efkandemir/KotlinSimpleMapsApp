package com.efkan.kotlinmaps.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.efkan.kotlinmaps.databinding.RecyclerRowBinding
import com.efkan.kotlinmaps.model.place
import com.efkan.kotlinmaps.view.MapsActivity

class placeAdapter(val placeList:List<place>) :RecyclerView.Adapter<placeAdapter.placeHolder>(){
    class placeHolder(val recyclerRowBinding: RecyclerRowBinding):RecyclerView.ViewHolder(recyclerRowBinding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): placeHolder {
        val recyclerRowBinding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return placeHolder(recyclerRowBinding)
    }

    override fun getItemCount(): Int {
        return placeList.size
    }

    override fun onBindViewHolder(holder: placeHolder, position: Int) {
        holder.recyclerRowBinding.recyclerViewTextView.text=placeList.get(position).name
        holder.itemView.setOnClickListener{
            val intent= Intent(holder.itemView.context,MapsActivity::class.java)
            intent.putExtra("selectedPlace",placeList.get(position))  //Serializable yapmak gerekiyor  . bunuda place sınıfından serializable yaptım
            intent.putExtra("info","old")
            holder.itemView.context.startActivity(intent)
        }
    }
}