package ch.zhaw.moba1_lab7_betterstock

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    //private lateinit var textView: TextView
    private lateinit var listView: ListView

    private lateinit var queue: RequestQueue
    //private lateinit var request: StringRequest
    private var url = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=AAPL&apikey=<R97TTGEBZXRBP60R>"

    @SuppressLint("MissingInflatedId", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.stocklist)

        // define request
        queue = Volley.newRequestQueue(this)

        val request = StringRequest(
            Request.Method.GET, url,
            { response ->

                var strResp = response.toString()
                // textView
                var lines = strResp.replace(",", "").replace("\"", "").replace("{", "").replace("}", "").lines()
                var outputStock = lines.subList(2, lines.lastIndex)

                val arrayAdapter: ArrayAdapter<*>
                arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, outputStock)
                listView.adapter = arrayAdapter
            },
            {
                 error ->

                    //textView!!.text = "That didn't work!"
                // Error
            }
        )
        // add call to request queue
        queue.add(request)
    }
}