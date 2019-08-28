package ru.skillbranch.devintensive.ui.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.ui.custom.CircleImageView

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.SingleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: SingleViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    class SingleViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private var cvImageView: CircleImageView
        private var svIndicator: View
        private var tvTitleSingle: TextView
        private var tvMessageSingle: TextView
        private var tvDateSingle: TextView
        private var tvCounterSingle: TextView

        init {
            cvImageView = view.findViewById(R.id.iv_avatar_single)
            svIndicator = view.findViewById(R.id.sv_indicator)
            tvTitleSingle = view.findViewById(R.id.tv_title_single)
            tvMessageSingle = view.findViewById(R.id.tv_message_single)
            tvDateSingle = view.findViewById(R.id.tv_date_single)
            tvCounterSingle = view.findViewById(R.id.tv_counter_single)
        }

        fun bind() {
        }
    }
}