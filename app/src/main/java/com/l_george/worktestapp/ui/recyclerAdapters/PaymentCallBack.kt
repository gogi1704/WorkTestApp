package com.l_george.worktestapp.ui.recyclerAdapters

import androidx.recyclerview.widget.DiffUtil
import com.l_george.worktestapp.data.models.PaymentModel

class PaymentCallBack:DiffUtil.ItemCallback<PaymentModel>() {
    override fun areItemsTheSame(oldItem: PaymentModel, newItem: PaymentModel): Boolean  = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: PaymentModel, newItem: PaymentModel): Boolean = oldItem == newItem
}