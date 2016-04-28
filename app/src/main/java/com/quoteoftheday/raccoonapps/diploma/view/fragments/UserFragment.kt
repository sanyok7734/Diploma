package com.quoteoftheday.raccoonapps.diploma.view.fragments


import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amulyakhare.textdrawable.TextDrawable
import com.quoteoftheday.raccoonapps.diploma.R
import com.quoteoftheday.raccoonapps.diploma.mode.pojo.User
import com.quoteoftheday.raccoonapps.diploma.mode.pojo.Constants
import com.quoteoftheday.raccoonapps.diploma.utils.CacheDataRetriever
import com.quoteoftheday.raccoonapps.diploma.utils.toLog
import com.quoteoftheday.raccoonapps.diploma.view.adapters.AdapterListUser
import com.quoteoftheday.raccoonapps.diploma.view.adapters.AdapterStringList
import com.quoteoftheday.raccoonapps.diploma.view.listener.OnUserListener
import java.util.*
import kotlinx.android.synthetic.main.fragment_about_user.*
import kotlinx.android.synthetic.clearFindViewByIdCache
import kotlinx.android.synthetic.main.list.*

class UserFragment : Fragment(), OnUserListener{


    var position: Int = 0
    lateinit var user: User
    lateinit var adapterStringList: AdapterStringList
    lateinit var users: ArrayList<User>

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_about_user, container, false)
        val bundle = this.arguments;

        users = CacheDataRetriever.getFromFileListUsers(activity, Constants.USERS, "User")
        position = getPosition(bundle.getString("id"), users)

        user = users[position]

        return view
    }

    private fun setOnline(status: Int) {
        val gradientDrawable: GradientDrawable? = onlineIndicator?.background as GradientDrawable
        if (status == 0) {
            gradientDrawable!!.setColor(Color.parseColor("#4CAF50"))
        } else {
            gradientDrawable!!.setColor(Color.parseColor("#bfbfbf"))
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userName.text = "${user.firstName} ${user.lastName}"

        val charOne = user.firstName.toCharArray()[0]
        val charTwo = user.lastName.toCharArray()[0]
        val drawable = TextDrawable.builder().buildRound("" + charOne + charTwo, user.color)

        avatar.setImageDrawable(drawable)

        userLogin.text = user.login


        val locale = resources.configuration.locale.toString();
        if (user.privileges != null)
            adapterStringList = AdapterStringList(user.privileges, activity, null)
        else
            adapterStringList = AdapterStringList(ArrayList<String>(), activity, null)
        privilegeList.adapter = adapterStringList
        privilegeList.layoutManager = LinearLayoutManager(activity)


        aboutYourself.setText(user.yourself)

        addPrivilege.setOnClickListener {
            if(adapterStringList.itemCount != 4) {
                val dialogFragment = DialogPrivilege();
                dialogFragment.list = adapterStringList.listUser
                dialogFragment.listener = this
                dialogFragment.show(activity.supportFragmentManager, "reviewDialog");
            }
        }

        editTextYourself.setOnClickListener {
            if (aboutYourself.isEnabled) {
                aboutYourself.isEnabled = false
                editTextYourself.setImageResource(R.drawable.ic_edit_vector)
                user.yourself = aboutYourself.text.toString()
                users[position] = user
                CacheDataRetriever.saveToFileListUsers(activity, users, Constants.USERS)
            } else {
                aboutYourself.isEnabled = true
                aboutYourself.isFocusable = true
                editTextYourself.setImageResource(R.drawable.ic_clear_vecto_grey)
            }
        }

        onlineIndicator.setOnClickListener {
            val dialog = AlertDialog.Builder(activity)
                    .setTitle(resources.getString(R.string.session_termination))
                    .setPositiveButton(resources.getString(R.string.Set_Online), DialogInterface.OnClickListener { dialogInterface, i ->
                        setOnline(0)
                    })
                    .setNegativeButton(resources.getString(R.string.Set_Offline), DialogInterface.OnClickListener { dialogInterface, i ->

                        setOnline(1)
                    }).show()
        }
    }

    private fun getPosition(bundle: String, users: ArrayList<User>): Int {
        for (x in 0..users.size) {
            if (users[x].login == bundle)
                return x
        }
        return 0
    }

    override fun addPrivilege(s: String) {
        user.addPrivileges(s)
        users[position] = user
        for (user1 in users) {
            Log.d("SANOOOO", user1.toString())
        }
        CacheDataRetriever.saveToFileListUsers(activity, users, Constants.USERS)
        adapterStringList.addItem(s)
    }
}
