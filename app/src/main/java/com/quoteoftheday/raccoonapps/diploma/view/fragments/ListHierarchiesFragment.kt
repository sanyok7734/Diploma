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
import com.quoteoftheday.raccoonapps.diploma.mode.pojo.User
import com.quoteoftheday.raccoonapps.diploma.utils.CacheDataRetriever
import com.quoteoftheday.raccoonapps.diploma.view.adapters.AdapterListHierarchies
import com.quoteoftheday.raccoonapps.diploma.view.adapters.AdapterListUser
import com.rengwuxian.materialedittext.MaterialEditText
import kotlinx.android.synthetic.main.fragment_list.*
import java.util.*

class ListHierarchiesFragment : Fragment() {

    var baseHierarchies = ArrayList<BaseHierarchies>()
    lateinit  var adapterListUser: AdapterListHierarchies

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_list, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        label.text = activity.resources.getString(R.string.baseHierarchies) + ":"

        sortTwo.setImageDrawable(activity.resources.getDrawable(R.drawable.ic_about_vector))

        recyclerView.layoutManager = LinearLayoutManager(activity)

        baseHierarchies = CacheDataRetriever.getFromFileListUsers(activity, Constants.BASE_HIERARCHIES, "BaseHierarchies")

        adapterListUser = AdapterListHierarchies(baseHierarchies, activity)
        recyclerView.adapter = adapterListUser

        addUser.setOnClickListener {
           addNewUser()
        }
    }

    override fun onPause() {
        super.onPause()
        CacheDataRetriever.saveToFileListUsers(activity, baseHierarchies, Constants.BASE_HIERARCHIES)
    }

    fun addNewUser() {
        val dialog = AlertDialog.Builder(activity).setView(R.layout.add_base).show()

        val buttonAddUser = dialog.findViewById(R.id.addBase) as Button
        val name = dialog.findViewById(R.id.name) as MaterialEditText

        buttonAddUser.setOnClickListener {
            if(name.text.toString().equals("")) {
                Toast.makeText(activity, getString(R.string.fillInAllTheFields), Toast.LENGTH_LONG).show()
            } else {
                adapterListUser.addItem(BaseHierarchies(name.text.toString()))
                dialog.hide()
            }
        }
    }
}