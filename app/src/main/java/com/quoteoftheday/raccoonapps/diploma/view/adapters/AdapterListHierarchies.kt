package com.quoteoftheday.raccoonapps.diploma.view.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter
import com.quoteoftheday.raccoonapps.diploma.AdminActivity
import com.quoteoftheday.raccoonapps.diploma.R
import com.quoteoftheday.raccoonapps.diploma.mode.pojo.BaseHierarchies
import com.quoteoftheday.raccoonapps.diploma.mode.pojo.Constants
import com.quoteoftheday.raccoonapps.diploma.mode.pojo.User
import com.quoteoftheday.raccoonapps.diploma.utils.CacheDataRetriever
import com.quoteoftheday.raccoonapps.diploma.view.listener.OnMainListener
import com.rengwuxian.materialedittext.MaterialEditText
import java.util.*
import kotlinx.android.synthetic.main.item_user.*


class AdapterListHierarchies(var listUser: ArrayList<BaseHierarchies>, var context: Context) : RecyclerSwipeAdapter<AdapterListHierarchies.ViewHolder>() {

    val onMainListener: OnMainListener = context as AdminActivity

    override fun onCreateViewHolder(p0: ViewGroup?, p1: Int): ViewHolder? {
        return ViewHolder(LayoutInflater.from(p0?.context).inflate(R.layout.item_base, p0, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.userName?.text = listUser[position].name

        holder?.bottomWrapper?.setOnClickListener {
            listUser.removeAt(position)
            notifyItemRemoved(position)
            notifyDataSetChanged()
            CacheDataRetriever.saveToFileListUsers(context, listUser, Constants.BASE_HIERARCHIES)
        }

        holder?.edit?.setOnClickListener {
            val dialog = AlertDialog.Builder(context).setView(R.layout.add_base).show()
            val buttonAddUser = dialog.findViewById(R.id.addBase) as Button
            val name = dialog.findViewById(R.id.name) as MaterialEditText
            buttonAddUser.text = "Save"
            buttonAddUser.setOnClickListener {
                listUser[position].name = name.text.toString()
                notifyDataSetChanged()
                dialog.hide()
            }
        }
    }

    override fun getItemCount(): Int = listUser.size

    override fun getSwipeLayoutResourceId(p0: Int): Int = R.id.swipe

    fun addItem(baseHierarchies: BaseHierarchies) {
        listUser.add(baseHierarchies)
        notifyItemInserted(listUser.size - 1)
        notifyDataSetChanged()

        CacheDataRetriever.saveToFileListUsers(context, listUser, Constants.BASE_HIERARCHIES)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userName: TextView = view.findViewById(R.id.userName) as TextView
        val bottomWrapper: LinearLayout = view.findViewById(R.id.bottom_wrapper) as LinearLayout
        val item: LinearLayout = view.findViewById(R.id.item) as LinearLayout
        val edit: ImageView  = view.findViewById(R.id.edit) as ImageView
    }
}