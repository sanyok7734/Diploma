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
import com.quoteoftheday.raccoonapps.diploma.mode.pojo.User
import com.quoteoftheday.raccoonapps.diploma.utils.CacheDataRetriever
import com.quoteoftheday.raccoonapps.diploma.view.listener.OnMainListener
import kotlinx.android.synthetic.main.fragment_list.*

import java.util.*


class AdapterListUser(var listUser: ArrayList<User>, var context: Context) : RecyclerSwipeAdapter<AdapterListUser.ViewHolder>() {

    val onMainListener: OnMainListener = context as AdminActivity


    override fun onCreateViewHolder(p0: ViewGroup?, p1: Int): ViewHolder? {
        return ViewHolder(LayoutInflater.from(p0?.context).inflate(R.layout.item_user, p0, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (holder?.swipeLayout?.openStatus == SwipeLayout.Status.Open) {
            holder?.swipeLayout?.close()
        }
        holder?.userName?.text = listUser[position].firstName + " " + listUser[position].lastName
        setOnline(holder)

        val charOne = listUser[position].firstName.toCharArray()[0]
        val charTwo = listUser[position].lastName.toCharArray()[0]
        val drawable = TextDrawable.builder().buildRound("" + charOne + charTwo, listUser[position].color)
        holder?.icon?.setImageDrawable(drawable)

        holder?.bottomWrapper?.setOnClickListener {
            listUser.removeAt(position)
            notifyItemRemoved(position)
            notifyDataSetChanged()
            CacheDataRetriever.saveToFileListUsers(context, listUser, Constants.USERS)
        }

        holder?.item?.setOnClickListener {
           // onMainListener.openAboutUser(listUser[position].login)
            onMainListener.startAnimationCircleRipe(listUser[position].login)
        }

        holder?.swipeLayout?.addSwipeListener(object : SwipeListener {
            override fun onOpen(layout: SwipeLayout?) {
                YoYo.with(Techniques.Tada).duration(1000).delay(200).playOn(layout?.findViewById(R.id.trash));
            }

            override fun onStartOpen(layout: SwipeLayout?) {
            }

            override fun onClose(layout: SwipeLayout?) {
            }

            override fun onUpdate(layout: SwipeLayout?, leftOffset: Int, topOffset: Int) {
            }

            override fun onStartClose(layout: SwipeLayout?) {
            }

            override fun onHandRelease(layout: SwipeLayout?, xvel: Float, yvel: Float) {
            }
        })


    }

    private fun setOnline(holder: ViewHolder?) {
        val gradientDrawable: GradientDrawable? = holder?.onlineIndicator?.background as GradientDrawable
        if (Random().nextInt(2) == 0) {
            gradientDrawable!!.setColor(Color.parseColor("#4CAF50"))
        } else {
            gradientDrawable!!.setColor(Color.parseColor("#bfbfbf"))
        }
    }

    override fun getItemCount(): Int = listUser.size

    override fun getSwipeLayoutResourceId(p0: Int): Int = R.id.swipe

    fun addItem(user: User) {
        listUser.add(user)
        notifyItemInserted(listUser.size - 1)
        notifyDataSetChanged()

        CacheDataRetriever.saveToFileListUsers(context, listUser, Constants.USERS)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userName: TextView = view.findViewById(R.id.userName) as TextView
        val icon: ImageView = view.findViewById(R.id.avatar) as ImageView
        val onlineIndicator: View = view.findViewById(R.id.onlineIndicator)
        val bottomWrapper: LinearLayout = view.findViewById(R.id.bottom_wrapper) as LinearLayout
        val item: FrameLayout = view.findViewById(R.id.edit) as FrameLayout
        val swipeLayout: SwipeLayout = view.findViewById(R.id.swipe) as SwipeLayout
    }
}

