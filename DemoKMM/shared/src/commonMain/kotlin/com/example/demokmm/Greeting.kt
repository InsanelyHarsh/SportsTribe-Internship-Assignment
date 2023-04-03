package com.example.demokmm

//import android.content.Context.MODE_PRIVATE
import com.example.demokmm.KMMContext
import com.example.demokmm.KMMPreference

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}

final class Validator(){

    private val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";

    fun isValidUserName(name: String): Boolean {
        return name.count() >= 5
    }

    fun isValidEmail(email: String): Boolean {
        return EMAIL_REGEX.toRegex().matches(email);
    }
}

//Saving using ** Shared Preferences **
final class DatabaseManager(){
//    private lateinit var sharedPref:SharedPreferences = getPreferences(MODE_PRIVATE);
//    var sharedPreferences: SharedPreferences? = getPreferences(android.content.Context.MODE_PRIVATE)
//    fun save(){
//        //save data
//    }
//
//    private fun saveTextData(text:String){
//    }
}


final class KMMPreference(private val context: KMMContext) {

    fun put(key: String, value: String) {
        context.putString(key, value)
    }


    fun getString(key: String) : String?
            =  context.getString(key)

}