package com.vungn.todoapp.ui.main.searchuser.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.vungn.todoapp.R
import com.vungn.todoapp.data.model.reponse.UserResponse

class SearchAutoCompletedTVAdapter(
    var context: Context,
    var lstUser: List<UserResponse>,
) : BaseAdapter(), Filterable {

    override fun getCount(): Int {
        return lstUser.size
    }

    override fun getItem(position: Int): Any {
        return lstUser.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertview: View?, p2: ViewGroup?): View {
        var view: View?
        var viewHolder: ViewHolder
        if (convertview == null) {
            var layoutInflater: LayoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.row_auto_completed_textview, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertview
            viewHolder = convertview.tag as ViewHolder
        }

        var user: UserResponse = getItem(position) as UserResponse
        viewHolder.tvName.text = user.name
        return view!!
    }

    class ViewHolder(row: View) {
        var tvName: TextView

        init {
            tvName = row.findViewById(R.id.row_ItemNameSearch) as TextView
        }
    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }
}

