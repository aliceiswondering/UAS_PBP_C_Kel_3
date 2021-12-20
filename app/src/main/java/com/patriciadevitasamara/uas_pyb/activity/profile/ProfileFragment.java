package com.patriciadevitasamara.uas_pyb.activity.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.patriciadevitasamara.uas_pyb.R;

import com.patriciadevitasamara.uas_pyb.activity.login.LoginActivity;
import com.patriciadevitasamara.uas_pyb.utils.UserPreference;
import com.patriciadevitasamara.uas_pyb.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private FragmentProfileBinding binding;
    public static  final String TAG = "TAG";
    private static final int CAMERA_PERMISSION_CODE = 101;
    private static final int CAMERA_REQ_CODE = 102;
    private static final int GALLERY_REQ_CODE =103;
    EditText name, email, username, password, address, phoneNumber;
    FloatingActionButton cameraButton;

    ImageView profileImageView;
    Button btnSave;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        binding.buttonLogout.setOnClickListener(v -> logout());
    }

    private void logout(){
        UserPreference pref = new UserPreference(requireContext());
        pref.deleteUserLogin();

        startActivity(new Intent(requireContext(), LoginActivity.class));
        requireActivity().finishAffinity();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}