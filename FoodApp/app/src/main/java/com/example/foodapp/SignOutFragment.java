package com.example.foodapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodapp.modules.login.view.LoginFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignOutFragment extends Fragment {

    Button signout;
    ImageView imageView;
    TextView textView;
    FirebaseUser user;
    FirebaseAuth auth;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_sign_out2, container, false);
        signout=rootview.findViewById(R.id.btnSignOut);
        imageView=rootview.findViewById(R.id.imagSignOut);
        textView=rootview.findViewById(R.id.textView4);

        auth=FirebaseAuth.getInstance();
        user= auth.getCurrentUser();

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Navigation.findNavController(rootview).navigate(R.id.action_signoutFragment2_to_loginFragment2);
            }
        });

        return rootview;
    }
}