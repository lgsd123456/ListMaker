package cn.com.pax.lg.listmaker

import android.content.Context
import android.preference.PreferenceManager

class ListDataManager(private val context: Context) {


    fun saveList(list: TaskList) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context).edit()
        sharedPreferences.putStringSet(list.name, list.tasks.toHashSet())
        sharedPreferences.apply()
    }

    fun readLists(): ArrayList<TaskList> {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val sharedPreferencesContents = sharedPreferences.all
        val taskLists = ArrayList<TaskList>()

        for(tasklist in sharedPreferencesContents) {
            val itemsHashSet = ArrayList(tasklist.value as HashSet<String>)
            val list = TaskList(tasklist.key, itemsHashSet)
            taskLists.add(list)
        }
        return taskLists
    }
}