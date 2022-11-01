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
    private lateinit var textView: TextView

    private lateinit var queue: RequestQueue
    //private lateinit var request: StringRequest
    private var url = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=IBM&apikey=<R97TTGEBZXRBP60R>"

    @SuppressLint("MissingInflatedId", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // create request queue
        // define request
        queue = Volley.newRequestQueue(this)

        val request = StringRequest(
            Request.Method.GET, url,
            { response ->

                var strResp = response.toString()
                val jsonObj = JSONObject(strResp)
                val jsonArray: JSONArray = jsonObj.getJSONArray("items")
                //var str_stock: String = "" + jsonArray[0]

                // textView
                // TODO error is here i think
                textView = findViewById(R.id.textView)
                textView.text = strResp
            },
            {
                // Error
            }
        )

        // add call to request queue
        queue.add(request)


    }
}