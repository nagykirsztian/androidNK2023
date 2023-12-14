package com.tasty.recipesapp.repository.recipe.entity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [RecipeEntity::class], version = 2, exportSchema =
    false
)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao

    companion object {
        // Create a singleton instance of the database
        @Volatile
        private var INSTANCE: RecipeDatabase? = null
        fun getDatabase(context: Context): RecipeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecipeDatabase::class.java,
                    "recipe_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Create a new table with the desired schema
                database.execSQL(
                    "CREATE TABLE IF NOT EXISTS `recipe_new` " +
                            "(`internalId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                            "`json` TEXT NOT NULL DEFAULT '{}', " +
                            "`name` TEXT, " +
                            "`title` TEXT, " +
                            "`description` TEXT, " +
                            "`pictureUrl` TEXT, " +
                            "`videoUrl` TEXT)"
                )

                // Copy data from the old table to the new table
                database.execSQL(
                    "INSERT INTO `recipe_new` (`internalId`, `name`, `title`, `description`, `pictureUrl`, `videoUrl`) " +
                            "SELECT `internalId`, `name`, `title`, `description`, `pictureUrl`, `videoUrl` FROM `recipe`"
                )

                // Remove the old table
                database.execSQL("DROP TABLE IF EXISTS `recipe`")

                // Change the table name to the correct one
                database.execSQL("ALTER TABLE `recipe_new` RENAME TO `recipe`")

            }
        }
    }
}
