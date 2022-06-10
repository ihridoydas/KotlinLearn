package com.hridoydas.newpaging3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.hridoydas.newpaging3.Adapter.Item.ItemModel
import com.hridoydas.newpaging3.Adapter.Item.ItemSectionDecoration
import com.hridoydas.newpaging3.Adapter.Item.TestAdapter

class RecyclerViewSectionHeader : AppCompatActivity() {
    private val swipeRefreshLayout: SwipeRefreshLayout by lazy {
        findViewById(R.id.swipeRefreshlayout)
    }


    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.recyclerview)
    }


    private lateinit var adpater: TestAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var itemSectionDecoration: ItemSectionDecoration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_section_header)

        val actionBar = supportActionBar
        actionBar!!.title = "Recycler Section Header"
        actionBar.setDisplayHomeAsUpEnabled(true)

        initList()
        loadmore()
    }

    private fun initList() {
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            reload()
        }
        layoutManager = LinearLayoutManager(this)
        adpater = TestAdapter {
            loadmore()
        }

        itemSectionDecoration = ItemSectionDecoration(this) {
            adpater.list
        }

        recyclerView.addItemDecoration(itemSectionDecoration)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adpater

    }

    private fun reload() {
        val list = dummyData(0, 20)
        recyclerView.post {
            adpater.reload(list)

        }
    }

    private fun loadmore() {

        val list = dummyData(adpater.itemCount, 20)
        recyclerView.post {
            adpater.loadMore(list)

        }
    }

    // Group Item by Date

    private fun dummyData(offset: Int, limit: Int): MutableList<ItemModel> {

        val list = mutableListOf<ItemModel>()

        var itemModel: ItemModel

        for (i in offset until offset + limit) {
            itemModel = when (i) {
                in 0..15 -> {
                    ItemModel("title $i", getDummyDateString("01"))
                }
                in 16..30 -> {
                    ItemModel("title $i", getDummyDateString("02"))
                }
                else -> {
                    ItemModel("title $i", getDummyDateString("03"))
                }

            }
            list.add(itemModel)
        }

        return list

    }

    private fun getDummyDateString(day: String): String {
        return "2021-10-$day"
    }
}