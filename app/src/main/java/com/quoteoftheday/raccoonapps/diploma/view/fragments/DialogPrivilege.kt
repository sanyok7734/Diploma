package com.quoteoftheday.raccoonapps.diploma.view.fragments

import android.os.Bundle
import android.support.v7.app.AppCompatDialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.quoteoftheday.raccoonapps.diploma.R
import com.quoteoftheday.raccoonapps.diploma.mode.pojo.Constants
import com.quoteoftheday.raccoonapps.diploma.view.adapters.AdapterStringList
import com.quoteoftheday.raccoonapps.diploma.view.listener.OnUserListener
import kotlinx.android.synthetic.main.dialog_privilege.*
import java.util.*


class DialogPrivilege : AppCompatDialogFragment() {

    lateinit var list: List<String>
    lateinit var listener: OnUserListener


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.dialog_privilege, container, false);
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        privilegeList.layoutManager = LinearLayoutManager(activity)

        val locale = resources.configuration.locale.toString();
        var listPrivilege: ArrayList<String> = ArrayList()

        for (s in Constants.getPrivilege(locale)) {
            if(!list.contains(s)) {
                listPrivilege.add(s)
            }
        }

        for (s in listPrivilege) {
            Log.d("PRIVILEGE", s)

        }
        val adapterStringList = AdapterStringList(listPrivilege, activity, listener)
        adapterStringList.dialog = this
        privilegeList.adapter = adapterStringList

        dialog.setTitle("Set privilege: ")

        cancel.setOnClickListener {
            dismiss()
        }

    }
}