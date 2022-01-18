package com.abdigunawan.muapartner.ui.onboarding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.abdigunawan.muapartner.R
import com.abdigunawan.muapartner.model.OnBoardingData

class OnBoardingAdapter(private var context: Context, private var onBoardingDataList: List<OnBoardingData>) : PagerAdapter() {
    override fun getCount(): Int {
        return onBoardingDataList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.onboarding_layout, null)
        val imageView : ImageView
        val title : TextView
        val desc : TextView

        imageView = view.findViewById(R.id.imageView2)
        title = view.findViewById(R.id.textView)
        desc = view.findViewById(R.id.textView2)


        imageView.setImageResource(onBoardingDataList[position].imgUrl)
        title.text = onBoardingDataList[position].title
        desc.text = onBoardingDataList[position].desc

        container.addView(view)
        return view
    }
}