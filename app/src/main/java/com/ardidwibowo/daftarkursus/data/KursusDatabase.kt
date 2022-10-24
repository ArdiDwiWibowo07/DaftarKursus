package com.ardidwibowo.daftarkursus.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ardidwibowo.daftarkursus.model.Kursus

//TODO 4 : Masukan model kedalam database
@Database(entities = [Kursus::class], version = 1, exportSchema = false)
abstract class KursusDatabase : RoomDatabase() {

    abstract fun kursusDao(): KursusDao

    companion object {
        @Volatile
        private var INSTANCE: KursusDatabase? = null

        fun getDatabase(context: Context): KursusDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KursusDatabase::class.java,
                    "kursus_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}