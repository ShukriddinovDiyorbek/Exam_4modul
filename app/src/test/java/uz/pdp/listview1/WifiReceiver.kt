package uz.pdp.listview1

import android.R
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import java.lang.StringBuilder
import java.util.ArrayList


open class WifiReceiver(var wifiManager: WifiManager, var wifiDeviceList: ListView) :
    BroadcastReceiver() {
    var sb: StringBuilder? = null
    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION == action) {
            sb = StringBuilder()
            val wifiList = wifiManager.scanResults
            val deviceList = ArrayList<String>()
            for (scanResult in wifiList) {
                sb!!.append("\n").append(scanResult.SSID).append(" - ")
                    .append(scanResult.capabilities)
                deviceList.add(scanResult.SSID + " - " + scanResult.capabilities)
            }
            Toast.makeText(context, sb, Toast.LENGTH_SHORT).show()
            val arrayAdapter: ArrayAdapter<*> =
                ArrayAdapter<Any?>(context, R.layout.simple_list_item_1, deviceList.toTypedArray())
            wifiDeviceList.adapter = arrayAdapter
        }
    }
}