package com.example.alejandra_alvarez_20241205_herpm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alejandra_alvarez_20241205_herpm.R
import com.example.alejandra_alvarez_20241205_herpm.models.Pharmacy

class PharmacyAdapter : RecyclerView.Adapter<PharmacyAdapter.PharmacyViewHolder>() {

    private val pharmacies = mutableListOf<Pharmacy>()

    fun submitList(list: List<Pharmacy>) {
        pharmacies.clear()
        pharmacies.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PharmacyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pharmacy, parent, false)
        return PharmacyViewHolder(view)
    }

    override fun onBindViewHolder(holder: PharmacyViewHolder, position: Int) {
        holder.bind(pharmacies[position])
    }

    override fun getItemCount(): Int = pharmacies.size

    class PharmacyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textName: TextView = itemView.findViewById(R.id.textName)
        private val textAddress: TextView = itemView.findViewById(R.id.textAddress)
        private val textComuna: TextView = itemView.findViewById(R.id.textComuna)
        private val textHours: TextView = itemView.findViewById(R.id.textHours)
        private val textPhone: TextView = itemView.findViewById(R.id.textPhone)

        fun bind(pharmacy: Pharmacy) {
            textName.text = pharmacy.nombre
            textAddress.text = "Dirección: ${pharmacy.direccion}"
            textComuna.text = "Comuna: ${pharmacy.comuna}"
            textHours.text = "Horario: ${pharmacy.apertura} - ${pharmacy.cierre}"
            textPhone.text = "Teléfono: ${pharmacy.telefono ?: "No disponible"}"
        }
    }
}
