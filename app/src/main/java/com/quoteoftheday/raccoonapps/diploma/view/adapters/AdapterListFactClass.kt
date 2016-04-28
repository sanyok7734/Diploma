package com.quoteoftheday.raccoonapps.diploma.view.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.amulyakhare.textdrawable.TextDrawable
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.daimajia.swipe.SwipeLayout
import com.daimajia.swipe.SwipeLayout.SwipeListener
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter
import com.quoteoftheday.raccoonapps.diploma.AdminActivity
import com.quoteoftheday.raccoonapps.diploma.R
import com.quoteoftheday.raccoonapps.diploma.mode.pojo.Constants
import com.quoteoftheday.raccoonapps.diploma.mode.pojo.FactClass
import com.quoteoftheday.raccoonapps.diploma.mode.pojo.User
import com.quoteoftheday.raccoonapps.diploma.utils.CacheDataRetriever
import com.quoteoftheday.raccoonapps.diploma.view.listener.OnMainListener
import kotlinx.android.synthetic.main.fragment_list.*

import java.util.*


class AdapterListFactClass(var listFactClass: ArrayList<FactClass>, var context: Context) : RecyclerSwipeAdapter<AdapterListFactClass.ViewHolder>() {



    override fun onCreateViewHolder(p0: ViewGroup?, p1: Int): ViewHolder? {
        return ViewHolder(LayoutInflater.from(p0?.context).inflate(R.layout.item_fact_class, p0, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        holder?.text?.text = listFactClass[position].key

        holder?.about?.setOnClickListener {
            //Toast.makeText(context, "ololo", Toast.LENGTH_LONG).show()
            val builder = AlertDialog.Builder(context)
            builder.setTitle(listFactClass[position].key)
            builder.setMessage(listFactClass[position].description)
            builder.show()
        }


        if (listFactClass[position].isSelected) {
            holder?.selected?.visibility = View.VISIBLE
        } else {
            holder?.selected?.visibility = View.GONE
        }

    }

    fun setSelected(position: Int) {
        for (factClass in listFactClass) {
            factClass.isSelected = false
        }
        listFactClass[position].isSelected = true
        notifyDataSetChanged()
    }



    override fun getItemCount(): Int = listFactClass.size

    override fun getSwipeLayoutResourceId(p0: Int): Int = R.id.swipe



    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(R.id.text) as TextView
        val about: ImageView = view.findViewById(R.id.about) as ImageView
        val selected: ImageView = view.findViewById(R.id.selected) as ImageView
        val item: LinearLayout = view.findViewById(R.id.item) as LinearLayout
    }
}

