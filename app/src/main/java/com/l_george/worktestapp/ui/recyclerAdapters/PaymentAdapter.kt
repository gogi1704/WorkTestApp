package com.l_george.worktestapp.ui.recyclerAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.l_george.worktestapp.data.models.PaymentModel
import com.l_george.worktestapp.databinding.PaymentItemLayoutBinding
import com.l_george.worktestapp.utils.DATE_PARSE_PATTERN
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PaymentAdapter :
    ListAdapter<PaymentModel, PaymentAdapter.PaymentViewHolder>(PaymentCallBack()) {

    class PaymentViewHolder(private val binding: PaymentItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PaymentModel) {
            with(binding) {
                textTitle.text = item.title
                textAmount.text = item.amount.toString()
                textDate.text = parseDate(item.created.toLong())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val binding =
            PaymentItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PaymentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}

private fun parseDate(time: Long): String {
    val date = Date(time * 1000)
    val dateFormat = SimpleDateFormat(DATE_PARSE_PATTERN, Locale.getDefault())
    return dateFormat.format(date)
}