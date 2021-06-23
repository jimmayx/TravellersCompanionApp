package op.mobile.app.dev.simjd1.travelling.helpers

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.adapters.TextViewBindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import op.mobile.app.dev.simjd1.travelling.R
import op.mobile.app.dev.simjd1.travelling.models.Country


@BindingAdapter("listData")
fun setListData(recyclerView: RecyclerView, data: List<Country>?) {
    val adapter = recyclerView.adapter as RecyclerViewAdapter
    adapter.submitList(data)
}

@BindingAdapter("apiServiceStatus")
fun setApiServiceStatus(tvStatus: TextView, status: ServiceStatus?) {
    when (status) {
        ServiceStatus.LOADING -> {
            tvStatus.visibility = View.VISIBLE
            tvStatus.text = tvStatus.context.getString(R.string.loading)
        }
        ServiceStatus.ERROR -> {
            tvStatus.visibility = View.VISIBLE
            tvStatus.text = tvStatus.context.getString(R.string.connection_error)
        }
        ServiceStatus.COMPLETE -> tvStatus.visibility = View.GONE
    }
}

@BindingAdapter("flagImage")
fun setFlagImage(imageView: ImageView, imageUrl: String) {
    Glide.with(imageView.context)
        .load(imageUrl).apply(RequestOptions().circleCrop())
        .into(imageView)
}

@BindingAdapter("new_response")
fun setNewResponse(tv: TextView, status: ServiceStatus?) {
    if (status == ServiceStatus.LOADING) {
        tv.visibility = View.GONE
    } else {
        tv.visibility = View.VISIBLE
    }
}

@BindingAdapter("error_text")
fun setErrorText(et: EditText?, message: String?) {
    et?.error = message
}

@BindingAdapter("android:afterTextChanged")
fun setListener(et: EditText?, after: TextViewBindingAdapter.AfterTextChanged?) {
    setListener(et, after)
}