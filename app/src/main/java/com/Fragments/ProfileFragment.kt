package com.example.bakeboutique

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.bakeboutique.databinding.FragmentProfileBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.bumptech.glide.Glide
import kotlin.collections.hashMapOf as hashMapOf1

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var bottomSheetDialog: BottomSheetDialog
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var storage: FirebaseStorage

    companion object {
        const val REQUEST_IMAGE_CAPTURE = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        storage = FirebaseStorage.getInstance()

        binding.profileImage.setOnClickListener { dispatchSelectImageIntent() }
        binding.bellIcon.setOnClickListener { showBottomSheet() }
        binding.btnCustomerSupport.setOnClickListener { navigateToCustomerSupport() }
        binding.btnPrivacyPolicy.setOnClickListener { navigateToPrivacyPolicy() }
        binding.btnLanguage.setOnClickListener { navigateToLanguageSelection() }
        binding.btnAboutUs.setOnClickListener { navigateToAboutUs() }
        binding.btnFaq.setOnClickListener { navigateToFAQ() }

        loadUserProfile()

        return view
    }

    private fun dispatchSelectImageIntent() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageUri = data?.data
            binding.profileImage.setImageURI(imageUri)
            uploadImageToFirebase(imageUri)
        }
    }

    private fun uploadImageToFirebase(imageUri: Uri?) {
        val user = auth.currentUser ?: return
        val storageRef = storage.reference.child("profile_images/${user.uid}.jpg")
        imageUri?.let {
            storageRef.putFile(it).addOnSuccessListener {
                storageRef.downloadUrl.addOnSuccessListener { uri ->
                    val profileUpdates = hashMapOf1("imageUrl" to uri.toString())
                    firestore.collection("user").document(user.uid).update(profileUpdates as Map<String, Any>)
                }
            }
        }
    }

    private fun loadUserProfile() {
        val user = auth.currentUser ?: return
        val docRef = firestore.collection("users").document(user.uid)
        docRef.get().addOnSuccessListener { document ->
            if (document != null && document.exists()) {
                val name = document.getString("name") ?: ""
                val imageUrl = document.getString("imageUrl") ?: ""
                binding.profileName.setText(name)

                Glide.with(this).load(imageUrl).into(binding.profileImage)
            }
        }
    }

    private fun showBottomSheet() {
        val view = layoutInflater.inflate(R.layout.bottom_sheet, null)
        bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(view)

        view.findViewById<TextView>(R.id.order_dispatched).text = "Order is dispatched"
        view.findViewById<TextView>(R.id.order_on_way).text = "Order is on the way"
        view.findViewById<TextView>(R.id.order_delivered).text = "Order is delivered"

        bottomSheetDialog.show()
    }

    private fun navigateToCustomerSupport() {
        startActivity(Intent(activity, Customer_Support::class.java))
    }

    private fun navigateToPrivacyPolicy() {
        startActivity(Intent(activity, Order_Policies::class.java))
    }

    private fun navigateToLanguageSelection() {
        startActivity(Intent(activity,language_select::class.java))
    }

    private fun navigateToAboutUs() {
        startActivity(Intent(activity, About_us::class.java))
    }

    private fun navigateToFAQ() {
        startActivity(Intent(activity, Faqs_Screen::class.java))
    }
}
