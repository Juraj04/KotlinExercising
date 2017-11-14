package com.example.janik.kotlinexercising

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity(), AddItemDialog.AddItemDialogListener {


    var items = mutableListOf<Item>(
            Item("ham", 2, 2.4),
            Item("egg", 4, 5.5),
            Item("milk", 69, 69.0),
            Item("avocado", 85, 8.5)
    )
    val LOW: Int = 1
    val HIGH: Int = 2
    var priceIndex = LOW


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        listView1?.adapter = ListAdapter(this, items)

        listView1?.onItemLongClickListener = AdapterView.OnItemLongClickListener { _, _, position, _ ->
            items.removeAt(position)
            listView1?.adapter = ListAdapter(this, items)
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        R.id.action_sortAsc -> {
            items = items.sortedBy { it.name } as MutableList<Item>
            listView1?.adapter = ListAdapter(this, items)
            true
        }
        R.id.action_sortDesc -> {
            items = items.sortedByDescending { it.name } as MutableList<Item>
            listView1?.adapter = ListAdapter(this, items)
            true
        }
        R.id.action_sortPrice -> {
            when (priceIndex) {
                LOW -> {
                    items = items.sortedBy { it.price } as MutableList<Item>
                    priceIndex = HIGH
                }
                HIGH -> {
                    items = items.sortedByDescending { it.price } as MutableList<Item>
                    priceIndex = LOW
                }
            }
            listView1?.adapter = ListAdapter(this, items)
            true
        }
        R.id.action_add -> {
            val dialog = AddItemDialog()
            dialog.show(fragmentManager, getString(R.string.text_dialog))
            true
        }
        else -> false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.list_menu, menu)
        return true
    }

    override fun onDialogPositiveClick(name: String, count: Int, price: Double) {
        val item = Item(name, count, price)
        items.add(item)
    }
}
