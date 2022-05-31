package com.aurosaswatraj.foobie

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.aurosaswatraj.foobie.Adapters.RandomRecipeAdapter
import com.aurosaswatraj.foobie.Listeners.RandomRecipeResponseListener
import com.aurosaswatraj.foobie.Listeners.RecipeClickListener
import com.aurosaswatraj.foobie.Models.RandomRecipeApiResponse
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    var manager:RequestManager?=null
    var randomRecipeAdapter:RandomRecipeAdapter?=null
    var tags: ArrayList<String> = ArrayList()
    var progress:ProgressDialog? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        manager= RequestManager(this)
//        manager!!.getRandomRecipes(randomRecipeResponseListener)

        var arrayAdapter=ArrayAdapter.createFromResource(
            this,
            R.array.tags,
            R.layout.spinner_text
        )


        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text)
        spinner_tags.adapter=arrayAdapter
        spinner_tags.onItemSelectedListener=spinnerSelectedListener

        searchView_home.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
               tags.clear()
                tags.add(query)
                manager!!.getRandomRecipes(randomRecipeResponseListener,tags)
                progress?.show()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        progress= ProgressDialog(this)
        progress?.setTitle("Loading ");
        progress?.show()

    }

    private val randomRecipeResponseListener: RandomRecipeResponseListener = object : RandomRecipeResponseListener {
        override fun didfetch(response: RandomRecipeApiResponse?, message: String?) {
            progress?.dismiss()
            recycler_random.setHasFixedSize(true)
            recycler_random.layoutManager=StaggeredGridLayoutManager(1,LinearLayoutManager.VERTICAL)
            randomRecipeAdapter=RandomRecipeAdapter(this@MainActivity,response?.recipes,recipeClickListener)
            recycler_random.adapter=randomRecipeAdapter
            recycler_random.setVisibility(View.VISIBLE)
        }

        override fun error(Message: String?) {
           Toast.makeText(this@MainActivity,"$Message",Toast.LENGTH_SHORT).show()
        }

    }


    private val spinnerSelectedListener: AdapterView.OnItemSelectedListener =
        object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                tags.clear()
                tags.add(adapterView.selectedItem.toString().lowercase(Locale.getDefault()))
               manager?.getRandomRecipes(randomRecipeResponseListener,tags)
                recycler_random.setVisibility(View.GONE)
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }

    private val recipeClickListener:RecipeClickListener= RecipeClickListener {

    }
}