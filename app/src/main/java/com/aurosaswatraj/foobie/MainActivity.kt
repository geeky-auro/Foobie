package com.aurosaswatraj.foobie

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.aurosaswatraj.foobie.Adapters.RandomRecipeAdapter
import com.aurosaswatraj.foobie.Listeners.RandomRecipeResponseListener
import com.aurosaswatraj.foobie.Models.RandomRecipeApiResponse
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var manager:RequestManager?=null
    var randomRecipeAdapter:RandomRecipeAdapter?=null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        manager= RequestManager(this)
        manager!!.getRandomRecipes(randomRecipeResponseListener)



    }

    private val randomRecipeResponseListener: RandomRecipeResponseListener = object : RandomRecipeResponseListener {
        override fun didfetch(response: RandomRecipeApiResponse?, message: String?) {

            recycler_random.setHasFixedSize(true)
            recycler_random.layoutManager=GridLayoutManager(this@MainActivity,1)
            randomRecipeAdapter=RandomRecipeAdapter(this@MainActivity,response?.recipes)
            recycler_random.adapter=randomRecipeAdapter
        }

        override fun error(Message: String?) {
           Toast.makeText(this@MainActivity,"$Message",Toast.LENGTH_SHORT).show()
        }

    }
}