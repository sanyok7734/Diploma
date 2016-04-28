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
import com.quoteoftheday.raccoonapps.diploma.mode.pojo.BaseHierarchies
import com.quoteoftheday.raccoonapps.diploma.mode.pojo.Constants
import com.quoteoftheday.raccoonapps.diploma.mode.pojo.ModelKEO
import com.quoteoftheday.raccoonapps.diploma.mode.pojo.User
import com.quoteoftheday.raccoonapps.diploma.utils.CacheDataRetriever
import com.quoteoftheday.raccoonapps.diploma.view.adapters.AdapterListHierarchies
import com.quoteoftheday.raccoonapps.diploma.view.adapters.AdapterListModel
import com.quoteoftheday.raccoonapps.diploma.view.adapters.AdapterListUser
import com.rengwuxian.materialedittext.MaterialEditText
import kotlinx.android.synthetic.main.fragment_list.*
import java.util.*

class ListModelKEOFragment : Fragment() {

    var modelKEO = ArrayList<ModelKEO>()
    lateinit  var adapterListModel: AdapterListModel

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_list, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        label.text = activity.resources.getString(R.string.modelKeo) + ":"

        sortTwo.setImageDrawable(activity.resources.getDrawable(R.drawable.ic_about_vector))

        recyclerView.layoutManager = LinearLayoutManager(activity)

        modelKEO = CacheDataRetriever.getFromFileListUsers(activity, Constants.MODEL_KEO, "ModelKEO")

        adapterListModel = AdapterListModel(modelKEO, activity)
        recyclerView.adapter = adapterListModel

        addUser.setOnClickListener {
           addModel()
        }
    }

    override fun onPause() {
        super.onPause()
        CacheDataRetriever.saveToFileListUsers(activity, modelKEO, Constants.MODEL_KEO)
    }

    fun addModel() {
        val dialog = AlertDialog.Builder(activity).setView(R.layout.add_base).show()

        val buttonAddUser = dialog.findViewById(R.id.addBase) as Button
        val name = dialog.findViewById(R.id.name) as MaterialEditText

        buttonAddUser.setOnClickListener {
            if(name.text.toString().equals("")) {
                Toast.makeText(activity, getString(R.string.fillInAllTheFields), Toast.LENGTH_LONG).show()
            } else {
                adapterListModel.addItem(ModelKEO(name.text.toString()))
                dialog.hide()
            }
        }
    }
}