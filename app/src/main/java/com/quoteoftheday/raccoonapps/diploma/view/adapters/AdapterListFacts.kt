package com.quoteoftheday.raccoonapps.diploma.view.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
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
import com.quoteoftheday.raccoonapps.diploma.mode.pojo.Fact
import com.quoteoftheday.raccoonapps.diploma.mode.pojo.FactClass
import com.quoteoftheday.raccoonapps.diploma.mode.pojo.User
import com.quoteoftheday.raccoonapps.diploma.utils.CacheDataRetriever
import com.quoteoftheday.raccoonapps.diploma.view.listener.OnMainListener
import kotlinx.android.synthetic.main.fragment_list.*

import java.util.*


class AdapterListFacts(var listFacts: ArrayList<Fact>, var context: Context) : RecyclerSwipeAdapter<AdapterListFacts.ViewHolder>() {



    override fun onCreateViewHolder(p0: ViewGroup?, p1: Int): ViewHolder? {
        return ViewHolder(LayoutInflater.from(p0?.context).inflate(R.layout.item_fact, p0, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.value?.text = listFacts[position].value
        holder?.description?.text = listFacts[position].description
    }



    override fun getItemCount(): Int = listFacts.size

    override fun getSwipeLayoutResourceId(p0: Int): Int = R.id.swipe


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val value: TextView = view.findViewById(R.id.value) as TextView
        val description: TextView = view.findViewById(R.id.description) as TextView
    }
}

