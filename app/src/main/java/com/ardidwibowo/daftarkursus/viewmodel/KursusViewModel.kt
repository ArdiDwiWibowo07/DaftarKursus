package com.ardidwibowo.daftarkursus.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ardidwibowo.daftarkursus.data.KursusDatabase
import com.ardidwibowo.daftarkursus.model.Kursus
import com.ardidwibowo.daftarkursus.repository.KursusRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//TODO 6 : Buat view model
class KursusViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Kursus>>
    private val repository: KursusRepository

    init {
        val kursusDao = KursusDatabase.getDatabase(
            application
        ).kursusDao()
        repository = KursusRepository(kursusDao)
        readAllData = repository.readAllData
    }

    fun addKursus(kursus: Kursus){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addKursus(kursus)
        }
    }

    fun updateKursus(kursus: Kursus){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateKursus(kursus)
        }
    }

    fun deleteKursus(kursus: Kursus){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteKursus(kursus)
        }
    }

    fun deleteAllKursus(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllKursus()
        }
    }


}