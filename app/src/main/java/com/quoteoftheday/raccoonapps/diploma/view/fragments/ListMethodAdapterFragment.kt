package com.quoteoftheday.raccoonapps.diploma.view.fragments


import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.quoteoftheday.raccoonapps.diploma.R
import com.quoteoftheday.raccoonapps.diploma.mode.pojo.*
import com.quoteoftheday.raccoonapps.diploma.utils.CacheDataRetriever
import com.quoteoftheday.raccoonapps.diploma.view.adapters.AdapterListHierarchies
import com.quoteoftheday.raccoonapps.diploma.view.adapters.AdapterListMethod
import com.quoteoftheday.raccoonapps.diploma.view.adapters.AdapterListModel
import com.quoteoftheday.raccoonapps.diploma.view.adapters.AdapterListUser
import com.rengwuxian.materialedittext.MaterialEditText
import kotlinx.android.synthetic.main.fragment_list.*
import java.util.*

class ListMethodAdapterFragment : Fragment() {

    var method = ArrayList<Method>()
    lateinit  var adapterListMethod: AdapterListMethod

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_list, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        label.text = activity.resources.getString(R.string.method_adapters) + ":"

        sortTwo.setImageDrawable(activity.resources.getDrawable(R.drawable.ic_about_vector))

        recyclerView.layoutManager = LinearLayoutManager(activity)

        method = CacheDataRetriever.getFromFileListUsers(activity, Constants.METHOD_ADAPTER, "MethodAdapter")

        adapterListMethod = AdapterListMethod(method, activity, 1)
        recyclerView.adapter = adapterListMethod

        addUser.setImageResource(R.drawable.ic_upload)


        addUser.setOnClickListener {
           addModel()
        }
    }

    override fun onPause() {
        super.onPause()
        CacheDataRetriever.saveToFileListUsers(activity, method, Constants.METHOD_ADAPTER)
    }

    fun addModel() {
        val dialog = AlertDialog.Builder(activity).setView(R.layout.add_base).show()

        val buttonAddUser = dialog.findViewById(R.id.addBase) as Button
        val name = dialog.findViewById(R.id.name) as MaterialEditText

        buttonAddUser.setOnClickListener {
            if(name.text.toString().equals("")) {
                Toast.makeText(activity, getString(R.string.fillInAllTheFields), Toast.LENGTH_LONG).show()
            } else {
                adapterListMethod.addItem(Method(name.text.toString()))
                dialog.hide()
            }
        }
    }
}