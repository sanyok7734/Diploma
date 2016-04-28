package com.quoteoftheday.raccoonapps.diploma.view.adapters

import android.content.Context
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.daimajia.swipe.SwipeLayout
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter
import com.quoteoftheday.raccoonapps.diploma.AdminActivity
import com.quoteoftheday.raccoonapps.diploma.R
import com.quoteoftheday.raccoonapps.diploma.mode.pojo.BaseHierarchies
import com.quoteoftheday.raccoonapps.diploma.mode.pojo.Constants
import com.quoteoftheday.raccoonapps.diploma.utils.CacheDataRetriever
import com.quoteoftheday.raccoonapps.diploma.view.fragments.DialogPrivilege
import com.quoteoftheday.raccoonapps.diploma.view.listener.OnMainListener
import com.quoteoftheday.raccoonapps.diploma.view.listener.OnUserListener
import com.rengwuxian.materialedittext.MaterialEditText
import java.util.*


class AdapterStringList(var list: List<String>, var context: Context, val listener: OnUserListener?) : RecyclerSwipeAdapter<AdapterStringList.ViewHolder>() {

    val onMainListener: OnMainListener = context as AdminActivity
    val listUser = ArrayList(list)
    lateinit var  dialog: DialogPrivilege


    override fun onCreateViewHolder(p0: ViewGroup?, p1: Int): ViewHolder? {
        return ViewHolder(LayoutInflater.from(p0?.context).inflate(R.layout.item_privilege, p0, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.userName?.text = listUser[position]

        holder?.bottomWrapper?.isSwipeEnabled = false

        if (listener !=null)  {
            holder?.item?.visibility = View.GONE
        }
        holder?.item?.setOnClickListener {
            listUser.removeAt(position)
            notifyItemRemoved(position)
            notifyDataSetChanged()
        }

        if (listener != null) {
            holder?.itemView?.setOnClickListener {
                listener.addPrivilege(holder.userName.text.toString())
                dialog.dismiss()
            }
        }
    }



    override fun getItemCount(): Int = listUser.size

    override fun getSwipeLayoutResourceId(p0: Int): Int = R.id.swipe

    fun addItem(string: String) {
        listUser.add(string)
        notifyItemInserted(listUser.size - 1)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userName: TextView = view.findViewById(R.id.userName) as TextView
        val bottomWrapper: SwipeLayout = view.findViewById(R.id.swipe) as SwipeLayout
        val item: ImageView = view.findViewById(R.id.edit) as ImageView

    }
}