package com.quoteoftheday.raccoonapps.diploma.view.fragments


import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.quoteoftheday.raccoonapps.diploma.R
import com.quoteoftheday.raccoonapps.diploma.mode.pojo.Fact
import com.quoteoftheday.raccoonapps.diploma.mode.pojo.FactClass
import com.quoteoftheday.raccoonapps.diploma.utils.ItemClickSupport
import com.quoteoftheday.raccoonapps.diploma.view.adapters.AdapterListFactClass
import com.quoteoftheday.raccoonapps.diploma.view.adapters.AdapterListFacts
import kotlinx.android.synthetic.main.fragment_selection_keo.*
import java.util.*


class FragmentSelectionKEO : Fragment(){

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_selection_keo, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        KEOfab.hide()

        rvFactClass.layoutManager = LinearLayoutManager(activity)
        rvFacts.layoutManager = LinearLayoutManager(activity)

        //gavno-------------------------------------------
        val listFactClass = ArrayList<FactClass>()
        listFactClass.add(FactClass("TSP", "множина ідентифікаторів класів МАІ"))
        listFactClass.add(FactClass("TH", "множина ідентифікаторів типів ієрархій"))
        listFactClass.add(FactClass("MVP", "OLLOLOLOLOLO"))
        listFactClass.add(FactClass("LLO", "OLLOLOLOLOLO"))
        listFactClass.add(FactClass("TH", "OLLOLOLOLOLO"))
        listFactClass.add(FactClass("OOP", "OLLOLOLOLOLO"))
        listFactClass.add(FactClass("MVP", "OLLOLOLOLOLO"))
        listFactClass.add(FactClass("LLO", "OLLOLOLOLOLO"))
        listFactClass.add(FactClass("TH", "OLLOLOLOLOLO"))
        listFactClass.add(FactClass("OOP", "OLLOLOLOLOLO"))
        listFactClass.add(FactClass("MVP", "OLLOLOLOLOLO"))

        val listFacts1 = ArrayList<Fact>()
        listFacts1.add(Fact("tsp1", "методи прийняття індивідуальних рішень","1"))
        listFacts1.add(Fact("tsp2", "методи прийняття колективних рішень","2"))

        val listFacts2 = ArrayList<Fact>()
        listFacts2.add(Fact("th1", "перший тип","3"))
        listFacts2.add(Fact("th2", "другий тип","4"))
        listFacts2.add(Fact("th3", "третій тип","5"))

        //end gavno-------------------------------------------


        val adapterFactClass = AdapterListFactClass(listFactClass, activity)
        rvFactClass.adapter = adapterFactClass
        //defaultValue
        adapterFactClass.setSelected(0)
        rvFacts.adapter = AdapterListFacts(listFacts1, activity)

        with(ItemClickSupport.addTo(rvFactClass)) {
            setOnItemClickListener { recyclerView, position, v ->
               // Toast.makeText(activity, listFactClass[position].key, Toast.LENGTH_LONG).show();
                adapterFactClass.setSelected(position)
                if(position == 0) {
                    rvFacts.adapter = AdapterListFacts(listFacts1, activity)
                } else if (position == 1) {
                    rvFacts.adapter = AdapterListFacts(listFacts2, activity)
                }
                //TODO list rvFACTS
            }
        }

        with(ItemClickSupport.addTo(rvFacts)) {
            setOnItemClickListener { recyclerView, position, v ->

                   //TODO list expression
                if(expression.text.split("^").size == listFactClass.size) {
                    KEOfab.show()
                } else {
                    if (expression.text.toString().equals("")) {
                        expression.text = "${(rvFacts.adapter as AdapterListFacts).listFacts[position].value}"
                    } else {
                        expression.text = "${expression.text} ^ ${(rvFacts.adapter as AdapterListFacts).listFacts[position].value}"
                    }
                }
            }
        }

        KEOfab.setOnClickListener {
            if(true) {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Tакакая КЕО ЕСТЬ")
                builder.setMessage("Вы молодец")
                builder.show()
            } else {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Tакакая КЕО not")
                builder.setMessage("Вы можете создать ее")
                builder.setPositiveButton("CREATE", DialogInterface.OnClickListener { dialogInterface, i -> })
                builder.setNegativeButton("CANCEL", null)
                builder.show()
            }
        }
    }
}