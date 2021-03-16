package com.yusril.latihanactionbar

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.yusril.latihanactionbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // data binding
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater=menuInflater
        inflater.inflate(R.menu.option_menu, menu)// masukan menu

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        //Untuk mengambil komponen searchview yang sebelumnya sudah di inflate, kita menggunakan fungsi berikut
        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView
        //berguna untuk memberikan hint pada pengguna tentang query search apa yang telah dimasukkan.
        searchView.queryHint = resources.getString(R.string.search_hint)// ini hint
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            // gunaakan function ini ketika search seleasi atau ok
            //Metode onQueryTextSubmit() akan dipanggil saat Submit ditekan.
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
                return true
            }
            // gunakan function ini untuk merespon tiap perubahan huruf pada searchView
            //onQueryTextChange() akan dipanggil setiap kali user memasukkan atau mengubah query yang ada pada inputan searchview.
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })


//        return super.onCreateOptionsMenu(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu1 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, MenuFragment())
                    .addToBackStack(null)
                    .commit()
                true
            }
            R.id.menu2 -> {
                val i = Intent(this, MenuActivity::class.java)
                startActivity(i)
                true
            }
            else -> true
        }
    }
}


