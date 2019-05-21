package `in`.handmade.mycoroutine

import `in`.handmade.mycoroutine.R.layout.activity_main
import android.app.PendingIntent.getActivity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)


        tv_count.setOnClickListener {
            /**
             * Async Coroutine
             */
            GlobalScope.launch(Dispatchers.Main) {
                val userOne = async(Dispatchers.IO) { fetchAndShowUser() }
                val userTwo = async(Dispatchers.IO) { fetchAndShowUser() }
                showValue(userOne.await(), userTwo.await()) // back on UI thread
            }
        }


        tv_count2.setOnClickListener {
            /**
             * Sample Coroutine
             */
            GlobalScope.launch(Dispatchers.Main) {
                val name= fetchAndShowUser()
                name.apply {
                    showValue(name);
                }

            }
        }
    }

    /**
     * Resultant Func
     */
    private fun showValue(name: String ="", msg :String="") {
        Toast.makeText(this, name+msg, Toast.LENGTH_SHORT).show()
    }

    /**
     * Provider Func
     */
    private fun fetchAndShowUser():String = "Hello World"


}

