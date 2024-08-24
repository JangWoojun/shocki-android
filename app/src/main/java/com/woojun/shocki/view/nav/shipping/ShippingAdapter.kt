package com.woojun.shocki.view.nav.shipping

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.woojun.shocki.R
import com.woojun.shocki.databinding.ShippingItemBinding

class ShippingAdapter (private val shippingList: List<String>):
    RecyclerView.Adapter<ShippingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShippingAdapter.ViewHolder {
        val binding = ShippingItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding).also { handler ->
            binding.reportButton.setOnClickListener {
                reportListDialog(binding.root.context)
            }
        }
    }

    private fun reportListDialog(context: Context) {
        val customDialog = Dialog(context)
        customDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        customDialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        customDialog.window?.setGravity(Gravity.BOTTOM)

        customDialog.setContentView(R.layout.dialog_report_list)
        customDialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        customDialog.findViewById<CardView>(R.id.different_button).setOnClickListener {
            reportDialog(context)
            customDialog.cancel()
        }

        customDialog.findViewById<CardView>(R.id.none_button).setOnClickListener {
            reportDialog(context)
            customDialog.cancel()
        }

        customDialog.findViewById<CardView>(R.id.etc_button).setOnClickListener {
            reportDialog(context)
            customDialog.cancel()
        }

        customDialog.show()
    }

    private fun reportDialog(context: Context) {
        val customDialog = Dialog(context)
        customDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        customDialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        customDialog.window?.setGravity(Gravity.BOTTOM)

        customDialog.setContentView(R.layout.dialog_report)
        customDialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        customDialog.findViewById<CardView>(R.id.main_button).setOnClickListener {
            Toast.makeText(context, "신고가 완료되었어요", Toast.LENGTH_SHORT).show()
            customDialog.cancel()
        }

        customDialog.findViewById<MaterialCardView>(R.id.cancel_button).setOnClickListener {
            customDialog.cancel()
        }

        customDialog.show()
    }

    override fun onBindViewHolder(holder: ShippingAdapter.ViewHolder, position: Int) {
        holder.bind(shippingList[position])
    }

    override fun getItemCount(): Int = shippingList.size

    inner class ViewHolder(private val binding : ShippingItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(title: String){
            binding.imageView.setImageResource(R.drawable.banner1)
            binding.titleText.text = title
        }
    }
}