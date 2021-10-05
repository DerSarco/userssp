package com.sarco.userssp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.sarco.userssp.databinding.ActivityMainBinding
/*la actividad que contiene el main activity en este caso, es donde se aloja la lista, por ende
* debemos extender la actividad al OnClickListener definido por nosotros.*/
class MainActivity : AppCompatActivity(), OnClickListener {
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

        //con getPreferences obtenemos los datos almacenados dentro de la aplicación,
        //estas no se puede compartir con las demas aplicaciones, no asi como las
        // getSharedPreferences
        val preferences = getPreferences(Context.MODE_PRIVATE)
        //se inicializa el valor con un valor por defecto, en este caso true y su nombre se define
        //en un recurso string
        val isFirstTime = preferences.getBoolean(getString(R.string.sp_first_time), true)
        Log.i("SP: ", "${getString(R.string.sp_first_time)} = $isFirstTime")

        //con esta función de preferences podemos insertar un dato dentro de shared preferences
        //la función commit realiza el insert del dato de manera sincrona.

        if(isFirstTime){
            /*inflamos el formulario de registro para poder incrustarlo dentro de nuestro
            * Dialog.*/
            val dialogView = layoutInflater.inflate(R.layout.dialog_register, null)
            /*Creamos un Dialog para poder cambiar el valor del dato en Shared Preferences*/
            MaterialAlertDialogBuilder(this )
                .setTitle(R.string.dialog_title)
//                        con setView entregamos el layout que queremos usar dentro de nuestro
//                    AlertDialog
                .setView(dialogView)
//                    Cancelable evita que el dialog se quite al tocar fuera de la zona de dialogo
                .setCancelable(false)
                .setPositiveButton(R.string.dialog_confirm) { _, _ ->
                    val username = dialogView.findViewById<TextInputEditText>(R.id.etUserName)
                        .text.toString()
                    with(preferences.edit()){
                      putBoolean(getString(R.string.sp_first_time), false)
                        putString(getString(R.string.sp_username), username)
                            .apply()
                    }
                    Toast.makeText(this, R.string.register_success, Toast.LENGTH_SHORT).show()
                /*dentro del positive Button, se al presionarlo el valor del dato cambia a false*/

                }
                .setNeutralButton(getString(R.string.dialog_guest), null)
                .show()
            /*si el usuario ya existe dentro de las shared preferences le damos la bienvenida
            * podemos rescatar su nombre desde las shared preferences*/
        } else {
            val username = preferences.getString(getString(R.string.sp_username),
                getString(R.string.hint_username))

            Toast.makeText(this, "Bienvendo $username", Toast.LENGTH_SHORT).show()
        }

        //igualamos el adapter instanciado previamente, le entregamos un listado, el cual
        //es requerido por la clase adapter UserAdapter.
        //UPDATE: le entregamos datos de prueba con la función getUsers para poder visualizar
        //información en el recyclerView.
        /*en la instancia del adapter, se define un nuevo parametro que hace referencia a la
        * interfaz de OnClickListener, como la actividad extiende la misma interfaz, con el
        * la palabra reservada this podemos entregar la interfaz extendida*/
        userAdapter = UserAdapter(getUsers(), this)
        //para poder manejar el layout debemos igualar la variable linearLayoutManager a la clase
        //LinearLayoutMnager entregando el contexto, el cual hara referencia al contexto actual
        //osea this.
        linearLayoutManager = LinearLayoutManager(this)

        //dentro de la vista, tenemos el recyclerview generado, por ende accedemos a el y aplicamos
        //las siguientes variables.
        binding.recyclerView.apply {
            setHasFixedSize(true)
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

        val alain = User(1, "Alain", "Nicolás", "https://frogames.es/wp-content/uploads/2020/09/alain-1.jpg")
        val samanta = User(2, "Samanta", "Meza", "https://upload.wikimedia.org/wikipedia/commons/b/b2/Samanta_villar.jpg")
        val javier = User(3, "Javier", "Gómez", "https://live.staticflickr.com/974/42098804942_b9ce35b1c8_b.jpg")
        val emma = User(4, "Emma", "Cruz", "https://upload.wikimedia.org/wikipedia/commons/d/d9/Emma_Wortelboer_%282018%29.jpg")

        users.add(alain)
        users.add(samanta)
        users.add(javier)
        users.add(emma)
        users.add(alain)
        users.add(samanta)
        users.add(javier)
        users.add(emma)
        users.add(alain)
        users.add(samanta)
        users.add(javier)
        users.add(emma)
        users.add(alain)
        users.add(samanta)
        users.add(javier)
        users.add(emma)

        return users
    }

/*al extender la interfaz, debemos hacer un override de la funcion que esta definida en
* la interfaz.*/
    override fun onClick(user: User, position: Int) {
        Toast.makeText(this, "$position: ${user.getFullName()}" , Toast.LENGTH_SHORT).show()
    }
}