# AutoImageSlider
https://user-images.githubusercontent.com/69871727/205153173-99832e4e-4512-47be-853f-a941767d48d3.mp4
# How to use:
XML:<br/>
  <code><com.EslamHamouda.autoimageslider.ImageSlider</code><br/>
                    <code>android:id="@+id/image_slider"</code><br/>
                    <code>android:layout_width="match_parent"</code><br/>
                    <code>android:layout_height="@dimen/dimen_200dp"</code><br/>
                    <code>app:iss_auto_cycle="true"</code><br/>
                    <code>app:iss_corner_radius="20"</code><br/>
                    <code>app:iss_delay="0"</code><br/>
                    <code>app:iss_error_image="@drawable/ic_logo"</code><br/>
                    <code>app:iss_period="4000"</code><br/>
                    <code>app:iss_placeholder="@drawable/ic_logo"</code><br/>
                    <code>app:iss_selected_dot="@drawable/default_selected_dot"</code><br/>
                    <code>app:iss_title_background="@android:color/transparent"</code><br/>
                    <code>app:iss_unselected_dot="@drawable/default_unselected_dot"</code><br/>
                    <code>app:layout_constraintEnd_toEndOf="parent"</code><br/>
                    <code>app:layout_constraintStart_toStartOf="parent"</code><br/>
                    <code>app:layout_constraintTop_toTopOf="parent" /></code><br/>
# Code:
<code>private fun bindAds(list: ArrayList<AdsModel>) {</code><br/>
        <code>val imageList = ArrayList<SlideModel>()</code><br/>
        <code>for (item in list) {</code><br/>
            <code>imageList.add(</code><br/>
                <code>SlideModel(item.id,"Image name",URL))}</code><br/>
        <code>binding.imageSlider.setImageList(imageList)</code><br/>
        <code>binding.imageSlider.setItemClickListener(object : ItemClickListener {</code><br/>
            <code>override fun onItemSelected(position: Int) {</code><br/>
                <code>(imageList[position].adsURL)?.toUri()?.let {</code><br/>
                    <code>requireActivity().startActivity(</code><br/>
                        <code>Intent(Intent.ACTION_VIEW, it))}}})</code><br/>
        </code>
##  gradle:
Step 1. Add the JitPack repository to your build file
---
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  ---
  Step 2. Add the dependency
---
	dependencies {
	        implementation 'com.github.EslamHamouda:AutoImageSlider:Tag'
	}
  ---
 
  
  
