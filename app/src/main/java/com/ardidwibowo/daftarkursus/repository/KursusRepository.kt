package com.ardidwibowo.daftarkursus.repository

import androidx.lifecycle.LiveData
import com.ardidwibowo.daftarkursus.data.KursusDao
import com.ardidwibowo.daftarkursus.model.Kursus

//TODO 5 : Buat repository
class KursusRepository(private val kursusDao: KursusDao) {

    val readAllData: LiveData<List<Kursus>> = kursusDao.readAllData()

    suspend fun addKursus(kursus: Kursus){
        kursusDao.addKursus(kursus)
    }

    suspend fun updateKursus(kursus: Kursus){
        kursusDao.updateKursus(kursus)
    }

    suspend fun deleteKursus(kursus: Kursus){
        kursusDao.deleteKursus(kursus)
    }

    suspend fun deleteAllKursus(){
        kursusDao.deleteAllKursus()
    }

}