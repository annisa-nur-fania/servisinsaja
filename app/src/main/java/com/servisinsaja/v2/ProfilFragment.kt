package com.servisinsaja.v2

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profil.*
import java.io.ByteArrayOutputStream


class ProfilFragment : Fragment() {

    companion object {
        const val REQUEST_CAMERA = 100
    }

    private lateinit var imageUri: Uri
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        if (user != null){
            if (user.photoUrl !=null){
                Picasso.get().load(user.photoUrl).into(ivProfile)
            }else(
                    Picasso.get().load("https://picsum.photos/id/1/200/300").into(ivProfile)

            )

                etName.setText(user.displayName)
                etEmail.setText(user.email)

            if (user.isEmailVerified){
                icVerived.visibility = View.VISIBLE
            }else{
                icunVerived.visibility = View.VISIBLE
            }

            if (user.phoneNumber.isNullOrEmpty()){
                etPhone.setText("masukan nomor telephone")
            }else{
                etPhone.setText(user.phoneNumber)
            }


        }
        ivProfile.setOnClickListener {
            goToCamera()
        }
        btnUpdate.setOnClickListener {
           val image = when{
               ::imageUri.isInitialized -> imageUri
               user?.photoUrl == null -> Uri.parse("https://picsum.photos/id/1/200/300")
                else -> user.photoUrl
           }
            val name = etName.text.toString().trim()
            if (name.isEmpty()){
                etName.error = "nama harus disini"
                etName.requestFocus()
                return@setOnClickListener
            }

            UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .setPhotoUri(image)
                .build().also {
                    user?.updateProfile(it)?.addOnCompleteListener() {
                        if (it.isSuccessful) {
                            Toast.makeText(activity, "upluad profile", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(activity, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        }
    }

    private fun goToCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
            activity?.packageManager?.let {
                intent?.resolveActivity(it).also {
                    startActivityForResult(intent, REQUEST_CAMERA)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            val imgBitmap = data?.extras?.get("data") as Bitmap
            uploadImage(imgBitmap)
        }
    }

    private fun uploadImage(imgBitmap: Bitmap) {
        val baos = ByteArrayOutputStream()
        val ref =
            FirebaseStorage.getInstance().reference.child("img/${FirebaseAuth.getInstance().currentUser?.uid}")
        imgBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val image = baos.toByteArray()

        ref.putBytes(image)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    ref.downloadUrl.addOnCompleteListener {
                        it.result.let {
                            imageUri = it
                            ivProfile.setImageBitmap(imgBitmap)
                        }
                    }
                }
            }
    }
}