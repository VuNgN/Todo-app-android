package com.vungn.todoapp.ui.main.searchuser.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import com.vungn.todoapp.R
import com.vungn.todoapp.data.model.reponse.UserResponse

class SearchAutoCompletedTVAdapter(
    context: Context,
    private val users: List<UserResponse>,
) : ArrayAdapter<UserResponse>(context, R.layout.row_search_textview, users) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView
            ?: LayoutInflater.from(context)
                .inflate(R.layout.row_search_textview, parent, false)
        bind(view, position)
        return view
    }

    override fun getFilter() = filter

    private fun bind(view: View, position: Int) {
        val userName = users[position].name
        (view.findViewById(R.id.row_ItemNameSearch) as? TextView)?.text = userName
    }

    private var filter: Filter = object : Filter() {
        /* thực hiện tìm kiếm các kết quả matcher với search
        * vì đã tìm kiếm nên chỉ việc get tất cả list user ra*/
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val results = FilterResults()
            results.values = users
            results.count = users.size

            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults) {
            clear()
            addAll(results.values as List<UserResponse>)
            notifyDataSetChanged()
        }

        override fun convertResultToString(resultValue: Any?): CharSequence {
            return (resultValue as UserResponse).name
        }
    }
}
