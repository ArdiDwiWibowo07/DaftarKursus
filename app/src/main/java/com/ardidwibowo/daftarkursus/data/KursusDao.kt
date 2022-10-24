package com.ardidwibowo.daftarkursus.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ardidwibowo.daftarkursus.model.Kursus

//TODO 3 : Buat Dao
@Dao
interface KursusDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addKursus(kursus: Kursus)

    @Update
    suspend fun updateKursus(kursus: Kursus)

    @Delete
    suspend fun deleteKursus(kursus: Kursus)

    @Query("DELETE FROM tabel_kursus")
    suspend fun deleteAllKursus()

    @Query("SELECT * FROM tabel_kursus ORDER BY id ASC")
    fun readAllData(): LiveData<List<Kursus>>


}