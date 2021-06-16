package com.fdhasna21.multimedia

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_3.*

class Activity3 : AppCompatActivity() {
    companion object{
        private val permission_code = 321
        private val get_image_code = 123
    }

    private fun getImageFromGallery() {
        val destination = Intent(Intent.ACTION_PICK)
        destination.type = "image/*"
        startActivityForResult(destination, get_image_code)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Select an Gallery Image"

        activity3_button.setOnClickListener {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                    val permisson = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permisson, permission_code)
                }
                else{
                    getImageFromGallery()
                }
            }
            else{
                getImageFromGallery()
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
                    getImageFromGallery()
                }
                else{
                    Toast.makeText(this, "Permission denied,", Toast.LENGTH_SHORT).show()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK && requestCode == get_image_code){
            activity3_image.setImageURI(data?.data)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}