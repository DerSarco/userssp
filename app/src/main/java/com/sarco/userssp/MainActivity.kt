package com.sarco.userssp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sarco.userssp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //creamos 3 variables, una para el userAdapter,
    //otra para el linearLayoutManager
    //otra para el binding, haciendo referencia a la vista
    private lateinit var userAdapter: UserAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    private lateinit var  binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //igualamos el binding instanciado a la actividad a inflar, para poder acceder a los
        //elementos de la vista
        binding = ActivityMainBinding.inflate(layoutInflater)
        //dentro de la vista, seteamos el contenido del binding anterior.
        setContentView(binding.root)

        //igualamos el adapter instanciado previamente, le entregamos un listado, el cual
        //es requerido por la clase adapter UserAdapter.
        //UPDATE: le entregamos datos de prueba con la función getUsers para poder visualizar
        //información en el recyclerView.
        userAdapter = UserAdapter(getUsers())
        //para poder manejar el layout debemos igualar la variable linearLayoutManager a la clase
        //LinearLayoutMnager entregando el contexto, el cual hara referencia al contexto actual
        //osea this.
        linearLayoutManager = LinearLayoutManager(this)

        //dentro de la vista, tenemos el recyclerview generado, por ende accedemos a el y aplicamos
        //las siguientes variables.
        binding.recyclerView.apply {
            //entregamos el layoutmanager que hara referencia a este contexto
            layoutManager = linearLayoutManager
            //entregamos el UserAdapter para que obtenga las funciones y viewholder creados en
            //nuestra clase adapter
            adapter = userAdapter
        }
    }


    //función que se encarga solo de crear una lista mockup para visualizar datos en el recycler view
    private fun getUsers(): MutableList<User>{
        val users = mutableListOf<User>()

        val alain = User(1, "Alain", "Nicolas", "urlGenerica")
        val carlos = User(2, "Carlos", "Muñoz", "urlGenerica")
        val pedro = User(3, "Pedro", "Castillo", "urlGenerica")

        users.add(alain)
        users.add(carlos)
        users.add(pedro)

        return users
    }
}