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
import com.quoteoftheday.raccoonapps.diploma.mode.pojo.*
import com.quoteoftheday.raccoonapps.diploma.utils.CacheDataRetriever
import com.quoteoftheday.raccoonapps.diploma.view.listener.OnMainListener
import com.rengwuxian.materialedittext.MaterialEditText
import java.util.*
import kotlinx.android.synthetic.main.item_user.*


class AdapterListMethod(var listMethod: ArrayList<Method>, var context: Context, val mode: Int) : RecyclerSwipeAdapter<AdapterListMethod.ViewHolder>() {

    val onMainListener: OnMainListener = context as AdminActivity

    override fun onCreateViewHolder(p0: ViewGroup?, p1: Int): ViewHolder? {
        return ViewHolder(LayoutInflater.from(p0?.context).inflate(R.layout.item_base, p0, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.userName?.text = listMethod[position].name

        holder?.bottomWrapper?.setOnClickListener {
            listMethod.removeAt(position)
            notifyItemRemoved(position)
            notifyDataSetChanged()

            if(mode == 0)
                CacheDataRetriever.saveToFileListUsers(context, listMethod, Constants.METHOD_KEO)
            else
                CacheDataRetriever.saveToFileListUsers(context, listMethod, Constants.METHOD_ADAPTER)
        }

        holder?.edit?.setOnClickListener {
            val dialog = AlertDialog.Builder(context).setView(R.layout.add_base).show()
            val buttonAddUser = dialog.findViewById(R.id.addBase) as Button
            val name = dialog.findViewById(R.id.name) as MaterialEditText
            buttonAddUser.text = "Save"
            buttonAddUser.setOnClickListener {
                listMethod[position].name = name.text.toString()
                notifyDataSetChanged()
                dialog.hide()
            }
        }

        holder?.info?.visibility = View.VISIBLE
        holder?.info?.setImageResource(R.drawable.ic_download)
        holder?.info?.setOnClickListener{
            Toast.makeText(context, "io", Toast.LENGTH_LONG).show()
        }


    }

    override fun getItemCount(): Int = listMethod.size

    override fun getSwipeLayoutResourceId(p0: Int): Int = R.id.swipe

    fun addItem(method: Method) {
        listMethod.add(method)
        notifyItemInserted(listMethod.size - 1)
        notifyDataSetChanged()

        if(mode == 0)
            CacheDataRetriever.saveToFileListUsers(context, listMethod, Constants.METHOD_KEO)
        else
            CacheDataRetriever.saveToFileListUsers(context, listMethod, Constants.METHOD_ADAPTER)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userName: TextView = view.findViewById(R.id.userName) as TextView
        val bottomWrapper: LinearLayout = view.findViewById(R.id.bottom_wrapper) as LinearLayout
        val item: LinearLayout = view.findViewById(R.id.item) as LinearLayout
        val info: ImageView  = view.findViewById(R.id.info) as ImageView
        val edit: ImageView  = view.findViewById(R.id.edit) as ImageView

    }
}