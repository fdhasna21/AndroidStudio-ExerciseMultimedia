package com.fdhasna21.multimedia

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_4.*

class Activity4 : AppCompatActivity() {
    companion object{
        private val permission_code = 321
        private val get_image_code = 123
        private var image : ArrayList<Uri>? = ArrayList()
        private var position = 0
    }

    private fun getImagesFromGallery() {
        val destination = Intent()
        destination.type = "image/*"
        destination.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        destination.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(destination, "Select images."), get_image_code)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_4)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Select Multiple Gallery Images"

// TODO: set image default in image switcher
        activity4_image.setFactory { ImageView(applicationContext) }

        activity4_select_btn.setOnClickListener {
            image = ArrayList()
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                    val permisson = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permisson, permission_code)
                }
                else{
                    getImagesFromGallery()
                }
            }
            else{
                getImagesFromGallery()
            }
        }

        activity4_prev_btn.setOnClickListener {
            if(position > 0 ){
                position--
                activity4_image.setImageURI(image!![position])
            }
            else{
                Toast.makeText(this, "Nothing to show.", Toast.LENGTH_SHORT).show()
            }
        }

        activity4_next_btn.setOnClickListener {
            if(position < image!!.size - 1){
                position++
                activity4_image.setImageURI(image!![position])
            }
            else{
                Toast.makeText(this, "Nothing to show.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            permission_code -> {
                if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    getImagesFromGallery()
                }
                else{
                    Toast.makeText(this, "Permission denied,", Toast.LENGTH_SHORT).show()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == get_image_code){
            if(data!!.clipData != null){
                val imagesCount = data.clipData!!.itemCount
                for(i in 0 until imagesCount){
                    val imageUri = data.clipData!!.getItemAt(i).uri
                    image!!.add(imageUri)
                }
                activity4_image.setImageURI(image!![0])
            }
            else{
                val imageUri = data.data
                activity4_image.setImageURI(imageUri)
            }
            position = 0
        }
    }
}