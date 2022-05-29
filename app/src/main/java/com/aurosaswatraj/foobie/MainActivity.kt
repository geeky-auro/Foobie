package com.aurosaswatraj.foobie

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.aurosaswatraj.foobie.Adapters.RandomRecipeAdapter
import com.aurosaswatraj.foobie.Listeners.RandomRecipeResponseListener
import com.aurosaswatraj.foobie.Models.RandomRecipeApiResponse
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var manager:RequestManager?=null
    var randomRecipeAdapter:RandomRecipeAdapter?=null

    var progress:ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        manager= RequestManager(this)
        manager!!.getRandomRecipes(randomRecipeResponseListener)

        var arrayAdapter=ArrayAdapter.createFromResource(
            this,
            R.array.tags,
            R.layout.spinner_text
        )


        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text)
        spinner_tags.adapter=arrayAdapter

        progress= ProgressDialog(this)
        progress?.setTitle("Loading ");
        progress?.show()

    }

    private val randomRecipeResponseListener: RandomRecipeResponseListener = object : RandomRecipeResponseListener {
        override fun didfetch(response: RandomRecipeApiResponse?, message: String?) {
            progress?.dismiss()
            recycler_random.setHasFixedSize(true)
            recycler_random.layoutManager=GridLayoutManager(this@MainActivity,1)
            randomRecipeAdapter=RandomRecipeAdapter(this@MainActivity,response?.recipes)
            recycler_random.adapter=randomRecipeAdapter
        }

        override fun error(Message: String?) {
           Toast.makeText(this@MainActivity,"$Message",Toast.LENGTH_SHORT).show()
        }

    }

    private val  spinnerSelectedListener:AdapterView.OnItemSelectedListener =object :AdapterView.OnItemClickListener,
        AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            TODO("Not yet implemented")
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
            TODO("Not yet implemented")
        }

        override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            TODO("Not yet implemented")
        }

    }
}