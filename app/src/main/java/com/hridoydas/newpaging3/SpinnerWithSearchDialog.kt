package com.hridoydas.newpaging3

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.hridoydas.newpaging3.databinding.ActivitySpinnerWithSearchDialogBinding


class SpinnerWithSearchDialog : AppCompatActivity() {
    private lateinit var binding: ActivitySpinnerWithSearchDialogBinding

    val ArrayList:ArrayList<String> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpinnerWithSearchDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar!!.title ="Spinner With search"
        actionBar.setDisplayHomeAsUpEnabled(true)

        ArrayList.add("One")
        ArrayList.add("Two")
        ArrayList.add("Three")
        ArrayList.add("Four")
        ArrayList.add("Five")
        ArrayList.add("Six")
        ArrayList.add("Seven")
        ArrayList.add("Eight")
        ArrayList.add("Nine")
        ArrayList.add("Ten")

        binding.textView.setOnClickListener(View.OnClickListener {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_searchable_spinner)
            dialog.window?.setLayout(950,1200)
            dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            //dialog.setTitle("Enter your new email")
            dialog.show()


            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ArrayList)

            val editText:EditText = dialog.findViewById(R.id.edit_text)
            val listView:ListView = dialog.findViewById(R.id.list_view)
           // val textView:TextView = dialog.findViewById(R.id.textViewSpinner)

            listView.adapter = adapter

            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    adapter.filter.filter(s)
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })

            listView.setOnItemClickListener { parent, view, position, id ->

                binding.textView.setText(adapter.getItem(position))
                editText.setText(adapter.getItem(position))
                dialog.dismiss()
            }

        })




    }
}