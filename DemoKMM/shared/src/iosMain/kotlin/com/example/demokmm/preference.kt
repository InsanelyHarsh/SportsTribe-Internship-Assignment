package com.example.demokmm
//package app.shmehdi.quote.preference

//import app.shmehdi.utils.KMMContext
import platform.Foundation.NSUserDefaults
import com.example.demokmm.KMMContext


actual fun KMMContext.putString(key: String, value: String) {
    NSUserDefaults.standardUserDefaults.setObject(value, key)
}

actual fun KMMContext.getString(key: String): String? {
    return NSUserDefaults.standardUserDefaults.stringForKey(key)
}