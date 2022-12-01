package com.eslamhamouda.autoimageslider.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eslamhamouda.autoimageslider.R
import com.eslamhamouda.autoimageslider.constants.ActionTypes
import com.eslamhamouda.autoimageslider.constants.ScaleTypes
import com.eslamhamouda.autoimageslider.interfaces.ItemClickListener
import com.eslamhamouda.autoimageslider.interfaces.TouchListener
import com.eslamhamouda.autoimageslider.models.SlideModel
import com.eslamhamouda.autoimageslider.transformation.RoundedTransformation
import com.squareup.picasso.Picasso

class ViewPagerAdapter2(
    context: Context?,
    imageList: List<SlideModel>,
    private var radius: Int,
    private var errorImage: Int,
    private var placeholder: Int,
    private var titleBackground: Int,
    private var scaleType: ScaleTypes?,
    private var textAlign: String
) : RecyclerView.Adapter<ViewPagerAdapter2.ViewHolder>() {

    constructor(
        context: Context,
        imageList: List<SlideModel>,
        radius: Int,
        errorImage: Int,
        placeholder: Int,
        titleBackground: Int,
        textAlign: String
    ) :
            this(
                context,
                imageList,
                radius,
                errorImage,
                placeholder,
                titleBackground,
                null,
                textAlign
            )

    private var imageList: List<SlideModel>? = imageList
    private var itemClickListener: ItemClickListener? = null
    private var touchListener: TouchListener? = null

    /**
     * Get layout gravity value from textAlign variable
     *
     * @param  textAlign  text align by user
     */
    fun getGravityFromAlign(textAlign: String): Int {
        return when (textAlign) {
            "RIGHT" -> {
                Gravity.RIGHT
            }
            "CENTER" -> {
                Gravity.CENTER
            }
            else -> {
                Gravity.LEFT
            }
        }
    }

    /**
     * Set item click listener
     *
     * @param  itemClickListener  callback by user
     */
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    /**
     * Set touch listener for listen to image touch
     *
     * @param  touchListener  interface callback
     */
    fun setTouchListener(touchListener: TouchListener) {
        this.touchListener = touchListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pager_row, parent, false)

        return ViewHolder(view)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = imageList?.get(position)

        if (item?.name != null) {
            holder.textView.text = item.name
            holder.linearLayout.setBackgroundResource(titleBackground)
            holder.textView.gravity = getGravityFromAlign(textAlign)
            holder.linearLayout.gravity = getGravityFromAlign(textAlign)
        } else {
            holder.linearLayout.visibility = View.INVISIBLE
        }

        // Image from url.
        val loader =
            Picasso.get().load(item?.imageURL)

        // set Picasso options.
        if ((scaleType != null && scaleType == ScaleTypes.CENTER_CROP) || item?.scaleType == ScaleTypes.CENTER_CROP) {
            loader.fit().centerCrop()
        } else if ((scaleType != null && scaleType == ScaleTypes.CENTER_INSIDE) || item?.scaleType == ScaleTypes.CENTER_INSIDE) {
            loader.fit().centerInside()
        } else if ((scaleType != null && scaleType == ScaleTypes.FIT) || item?.scaleType == ScaleTypes.FIT) {
            loader.fit()
        }

        loader.transform(RoundedTransformation(radius, 0))
            .placeholder(placeholder)
            .error(placeholder)
            .into(holder.imageView)

        holder.imageView.setOnClickListener { itemClickListener?.onItemSelected(position) }

        if (touchListener != null) {
            holder.imageView.setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_MOVE -> touchListener!!.onTouched(ActionTypes.MOVE)
                    MotionEvent.ACTION_DOWN -> touchListener!!.onTouched(ActionTypes.DOWN)
                    MotionEvent.ACTION_UP -> touchListener!!.onTouched(ActionTypes.UP)
                }
                false
            }
        }
    }

    override fun getItemCount(): Int {
        if (imageList!=null){
            return imageList!!.size
        }
        return 0
    }
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.image_view)
        val linearLayout = itemView.findViewById<LinearLayout>(R.id.linear_layout)
        val textView = itemView.findViewById<TextView>(R.id.text_view)
    }

}