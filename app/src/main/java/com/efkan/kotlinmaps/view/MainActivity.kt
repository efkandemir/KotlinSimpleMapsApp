package com.efkan.kotlinmaps.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.efkan.kotlinmaps.R
import com.efkan.kotlinmaps.adapter.placeAdapter
import com.efkan.kotlinmaps.databinding.ActivityMainBinding
import com.efkan.kotlinmaps.model.place
import com.efkan.kotlinmaps.roomdb.PlaceDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

private lateinit var binding:ActivityMainBinding
private val compositeDisposable=CompositeDisposable()
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        val db=Room.databaseBuilder(applicationContext,PlaceDatabase.PlaceDatabase::class.java,"Places").build()
        val placeDao=db.placeDao()
        compositeDisposable.add(
            placeDao.getAll().
            subscribeOn(Schedulers.io()).
            observeOn(AndroidSchedulers.mainThread()).
            subscribe(this::handleResponse)   //burada aldığımız veriler bize aşağıdaki handlerResponse fonksiyonunda verilecek
        )
    }
    private fun handleResponse(placeList:List<place>){   //bu metodun bana List<place> vermesi lazım.  burada verilere eriştikten sonra bunu recylerviewde göstericem
    binding.recyclerView.layoutManager=LinearLayoutManager(this)
        val adapter=placeAdapter(placeList)
        binding.recyclerView.adapter=adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuinflater=menuInflater
        menuinflater.inflate(R.menu.place_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId== R.id.add_place){
            val intent=Intent(this@MainActivity, MapsActivity::class.java)
            intent.putExtra("info","new")
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}