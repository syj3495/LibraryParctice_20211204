package com.neppplus.libraryparctice_20211204

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvent()
        setValues()

    }

    fun setupEvent(){

        imgProfile.setOnClickListener {

            val myIntent = Intent(this, ViewPhotoActivity::class.java)
            startActivity(myIntent)

        }

        btnCall.setOnClickListener {

//            전화연결 해도 되는지 권한 확인 -> OK일때만 전화연결


//            권한 여부에 따른 행동 방침 변수

            val pl = object : PermissionListener{
                override fun onPermissionGranted() {
//            010-5555-5555에 실제 전화 연결

                    val myUri = Uri.parse("tel:010-5555-5555")
                    val myIntnet = Intent(Intent.ACTION_CALL, myUri)
                    startActivity(myIntnet)



                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                    Toast.makeText(this@MainActivity, "권한이 없어 연결 불가합니다. ", Toast.LENGTH_SHORT).show()
                }


            }

                TedPermission.create()
                    .setPermissionListener(pl)
                    .setPermissions( Manifest.permission.CALL_PHONE )
                    .check()



        }


    }

    fun setValues(){

//        인터넷상에 있는 이미지를 -> 곧바로 이미지뷰에 적용.

//        이미지 주소를 변수에 저장
        val imageURL = "https://spnimage.edaily.co.kr/images/Photo/files/NP/S/2021/09/PS21090100069.jpg"


//        Glide 이용 => imageURL 주소의 그림을 => imgInternet에 반영.

        Glide.with(this).load(imageURL).into( imgInternet )

    }
}