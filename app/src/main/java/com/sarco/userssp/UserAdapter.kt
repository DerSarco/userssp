package com.sarco.userssp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sarco.userssp.databinding.ItemUserBinding

//creamos la clase adapter para poder realizar el binding al recycler view que hemos creado,
//generamos la lista de users de tipo List con tipos internos User, que es la data class creada.
//posteriormente, la clase se debe extender al Adapter de RecyclerView entregando la clase interna
//que se creo para este binding
class UserAdapter(private val users:List<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    //se indica que la variable se inicializara despues.

    private lateinit var context: Context


    //con el atajo CTRL + i podemos acceder a la generación de las funciones del ViewHolder
    //que deben ser sobreescritas
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        //esta funcion sirve para inflar la vista.
        val view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)

        //retornamos el ViewHolder que creamos abajo, clase interna, y entregamos la vista inflada
        //como return.
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //este metodo se encarga de bindear la información al RecyclerView.

        //obtenemos la posicion del arreglo con el parametro generado por la función,
        //este actua similar a un foreach.
        val user = users[position]

        //holder referencia a ViewHolder, el que generamos y setea dentro de la vista de detalle
        //el valor para cada elemento.
        with(holder){
            binding.tvOrder.text = (position + 1).toString()
            binding.tvName.text = user.name
            //cargamos la imagen que llega desde la instancia de la clase en el main.
            //con Glide seteamos esta imagen en el ImageView que agregamos en la vista
            Glide.with(context).load(user.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
//                    con circle crop podemos hacer que las imagenes se recorten circularmente.
                .circleCrop()
                .into(binding.imgPhoto)

        }
    }
    //indica cuantos elementos hay en el adapter.
    override fun getItemCount(): Int = users.size

    //creamos una clase interna, que es el ViewHolder sobreescrito, para ello requiere de un
    //parametro, en este caso la vista donde estara el Recycler View, esta clase debe extender
    //el objeto RecyclerView al cual le entregaremos la vista donde se encontrara el elemento
    //dentro de esta clase, tenemos que realizar el binding correspondiente para poder acceder al
    //elemento que queremos bindear, en este caso ItemUserBinding, es el binding generado para la
    //vista de Item User, que es el elemento a renderizar dentro del listado.
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemUserBinding.bind(view)
    }


}