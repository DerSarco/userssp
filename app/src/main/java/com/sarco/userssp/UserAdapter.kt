package com.sarco.userssp

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sarco.userssp.databinding.ItemUserBinding

//creamos la clase adapter para poder realizar el binding al recycler view que hemos creado,
//generamos la lista de users de tipo List con tipos internos User, que es la data class creada.
//posteriormente, la clase se debe extender al Adapter de RecyclerView entregando la clase interna
//que se creo para este binding
class UserAdapter(private val users:List<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
    //creamos una clase interna, que es el ViewHolder sobreescrito, para ello requiere de un
    //parametro, en este caso la vista donde estara el Recycler View, esta clase debe extender
    //el objeto RecyclerView al cual le entregaremos la vista donde se encontrara el elemento
    //dentro de esta clase, tenemos que realizar el binding correspondiente para poder acceder al
    //elemento que queremos bindear, en este caso ItemUserBinding, es el binding generado para la
    //vista de Item User, que es el elemento a renderizar dentro del listado.
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemUserBinding.bind(view);
    }


}