package com.quoteoftheday.raccoonapps.diploma.view.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.quoteoftheday.raccoonapps.diploma.AdminActivity
import com.quoteoftheday.raccoonapps.diploma.R
import com.quoteoftheday.raccoonapps.diploma.mode.pojo.Constants
import com.quoteoftheday.raccoonapps.diploma.mode.pojo.User
import com.quoteoftheday.raccoonapps.diploma.utils.CacheDataRetriever
import com.quoteoftheday.raccoonapps.diploma.view.adapters.AdapterListUser
import com.rengwuxian.materialedittext.MaterialEditText
import kotlinx.android.synthetic.main.fragment_list.*
import java.util.*

class ListUserFragment : Fragment() {

    var users = ArrayList<User>()
    lateinit  var adapterListUser: AdapterListUser

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_list, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        label.text = activity.resources.getString(R.string.people) + ":"

        users = CacheDataRetriever.getFromFileListUsers(activity, Constants.USERS, "User")

        adapterListUser = AdapterListUser(users, activity)
        recyclerView.adapter = adapterListUser


        addUser.setOnClickListener {
           addNewUser()
        }
    }

    override fun onPause() {
        super.onPause()
       // CacheDataRetriever.saveToFileListUsers(activity, users, Constants.USERS)
    }

    fun addNewUser() {
        val dialog = AlertDialog.Builder(activity).setView(R.layout.add_user).show()

        val buttonAddUser = dialog.findViewById(R.id.addUser) as Button
        val firstName = dialog.findViewById(R.id.firstName) as MaterialEditText
        val lastName = dialog.findViewById(R.id.lastName) as MaterialEditText
        val login = dialog.findViewById(R.id.login) as MaterialEditText

        buttonAddUser.setOnClickListener {
            if(firstName.text.toString().equals("") || lastName.text.toString().equals("") || login.text.toString().equals("")) {
                Toast.makeText(activity, getString(R.string.fillInAllTheFields), Toast.LENGTH_LONG).show()
            } else {
                adapterListUser.addItem(User(firstName.text.toString(), lastName.text.toString(), login.text.toString(), ""))
                dialog.hide()
            }
        }
    }
}