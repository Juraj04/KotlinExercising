package com.example.janik.kotlinexercising

import android.accessibilityservice.GestureDescription
import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import kotlinx.android.synthetic.main.add_item_dialog.*
import kotlinx.android.synthetic.main.add_item_dialog.view.*

/**
 * Created by janik on 05.11.2017.
 */

class AddItemDialog : DialogFragment(){

    interface AddItemDialogListener {
        fun onDialogPositiveClick(name: String, count: Int, price: Double)
    }

    var listener: AddItemDialogListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        //try {
            listener = context as? AddItemDialogListener
        //}catch (e: ClassCastException){
           // throw ClassCastException("something went wrong")
       // }
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        val dialogView = inflater.inflate(R.layout.add_item_dialog,null)

        builder.setView(dialogView)
                .setTitle(getString(R.string.add_name))
                .setPositiveButton(getString(R.string.yes), { _, _ ->
                    val name = dialogView.editText.text
                    val count = dialogView.editText2.text
                    val price = dialogView.editText3.text

                    listener?.onDialogPositiveClick(name.toString().toLowerCase(),count.toString().toInt(),price.toString().toDouble())
                })
                .setNegativeButton(getString(R.string.cancel), { _, _ ->
                    Toast.makeText(activity,getString(R.string.cancel),Toast.LENGTH_SHORT).show()
                })
        return builder.create()


    }
}