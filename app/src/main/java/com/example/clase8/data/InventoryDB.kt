package com.example.clase8.data
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.clase8.model.Inventory
import com.example.clase8.utils.Constants.NAME_BD

@Database(entities = [Inventory::class], version = 1)
abstract class InventoryDB : RoomDatabase() {

    abstract fun inventoryDao(): InventoryDao

    companion object{
        fun getDatabase(context: Context): InventoryDB {
            return Room.databaseBuilder(
                context.applicationContext,
                InventoryDB::class.java,
                NAME_BD
            ).build()
        }
    }
}