package com.ardidwibowo.daftarkursus.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ardidwibowo.daftarkursus.R
import com.ardidwibowo.daftarkursus.model.Kursus
import com.ardidwibowo.daftarkursus.viewmodel.KursusViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

//TODO 13 : buat AddFragment
class AddFragment : Fragment() {

    private lateinit var mKursusViewModel: KursusViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mKursusViewModel = ViewModelProvider(this).get(KursusViewModel::class.java)

        view.add_btn.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    //masukan data dari inputan yang dimasukan
    private fun insertDataToDatabase() {
        val kursus = addKursus_et.text.toString()
        val lembaga = addLembaga_et.text.toString()
        val harga = addHarga_et.text

        if(inputCheck(kursus, lembaga, harga)){
            // Create Kursus Object
            val kursus = Kursus(
                0,
                kursus,
                lembaga,
                Integer.parseInt(harga.toString())
            )
            // Add Data to Database
            mKursusViewModel.addKursus(kursus)
            Toast.makeText(requireContext(), "Kursus berhasil ditambahakan!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Isi semua field", Toast.LENGTH_LONG).show()
        }
    }


    //validasi input
    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

}