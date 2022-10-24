package com.ardidwibowo.daftarkursus.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import  com.ardidwibowo.daftarkursus.R
import com.ardidwibowo.daftarkursus.model.Kursus
import  com.ardidwibowo.daftarkursus.viewmodel.KursusViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    //TODO 9  : Buat listFragment
    private lateinit var mKursusViewModel: KursusViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // Recyclerview
        val adapter = ListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // UserViewModel
        mKursusViewModel = ViewModelProvider(this).get(KursusViewModel::class.java)
        mKursusViewModel.readAllData.observe(viewLifecycleOwner, Observer { kursus ->
            adapter.setData(kursus)
        })

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        // Add menu
        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteAllKursus()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllKursus() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mKursusViewModel.deleteAllKursus()
            Toast.makeText(
                requireContext(),
                "Berhasil hapus semua kursus",
                Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Hapus semua ?")
        builder.setMessage("ingin hapus semua?")
        builder.create().show()
    }
}