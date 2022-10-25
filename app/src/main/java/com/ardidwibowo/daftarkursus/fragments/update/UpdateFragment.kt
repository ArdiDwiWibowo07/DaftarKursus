package com.ardidwibowo.daftarkursus.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import  com.ardidwibowo.daftarkursus.R
import com.ardidwibowo.daftarkursus.model.Kursus
import com.ardidwibowo.daftarkursus.viewmodel.KursusViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

//TODO 15 : Buat update fragment
class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mKursusViewModel: KursusViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mKursusViewModel = ViewModelProvider(this).get(KursusViewModel::class.java)

        view.updateKursus_et.setText(args.currentKursus.kursus)
        view.updateLembaga_et.setText(args.currentKursus.lembaga)
        view.updateHarga_et.setText(args.currentKursus.harga.toString())

        view.update_btn.setOnClickListener {
            updateItem()
        }

        // Add menu
        setHasOptionsMenu(true)

        return view
    }

    //Melakukan udapte dari input yang dimasukan
    private fun updateItem() {
        val kursus = updateKursus_et.text.toString()
        val lembaga = updateLembaga_et.text.toString()
        val harga =updateHarga_et.text


        if (inputCheck(kursus, lembaga, harga)) {

            val updatedKursus = Kursus(args.currentKursus.id, kursus, lembaga, Integer.parseInt(harga.toString()))

            mKursusViewModel.updateKursus(updatedKursus)
            Toast.makeText(requireContext(), "Update berhasil!", Toast.LENGTH_SHORT).show()
            // Navigasi kembali
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Isi semua field.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    //validasi input
    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //mencari item id yang akan dihapus
        if (item.itemId == R.id.menu_delete) {
            deleteKursus()
        }
        return super.onOptionsItemSelected(item)
    }

    //Tombol hapus dari dialog yang muncul untuk menghapus item beradasarkan kursus yang sedang dipilih
    private fun deleteKursus() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Ya") { _, _ ->
            mKursusViewModel.deleteKursus(args.currentKursus)
            Toast.makeText(
                requireContext(),
                "Berhasil dihapus : ${args.currentKursus.kursus}",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("Tidak") { _, _ -> }
        builder.setTitle("Hapus ${args.currentKursus.kursus}?")
        builder.setMessage("Jadi dihapus ${args.currentKursus.kursus}?")
        builder.create().show()
    }
}