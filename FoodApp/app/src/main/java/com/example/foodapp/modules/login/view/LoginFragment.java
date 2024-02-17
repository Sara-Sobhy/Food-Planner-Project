package com.example.foodapp.modules.login.view;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.modules.home.view.HomeFragment;
import com.example.foodapp.modules.signup.view.SignupFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginFragment extends Fragment {
    EditText email;
    EditText pass;
    Button btnLogin;
    TextView signUp;
    TextView guest;
    FirebaseAuth mAuth;
    SharedPreferences sp;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentuser=mAuth.getCurrentUser();
        if(currentuser!=null){
            Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment2_to_homeFragment);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview= inflater.inflate(R.layout.fragment_login, container, false);
        email=rootview.findViewById(R.id.login_email);
        pass=rootview.findViewById(R.id.login_password);
        btnLogin = rootview.findViewById(R.id.login_button);
        signUp=rootview.findViewById(R.id.signUpRedirectText);
        guest=rootview.findViewById(R.id.asGuestText);
        mAuth=FirebaseAuth.getInstance();

        sp= getContext().getSharedPreferences("email", MODE_PRIVATE);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(rootview).navigate(R.id.action_loginFragment2_to_signupFragment);
            }
        });
        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(rootview).navigate(R.id.action_loginFragment2_to_homeFragment);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailtext= String.valueOf(email.getText());
                String passtext=String.valueOf(email.getText());

                if(!TextUtils.isEmpty(emailtext)){
                    if(!TextUtils.isEmpty(passtext)){
                        mAuth.signInWithEmailAndPassword(emailtext, passtext)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {

                                            String currentUserEmail = mAuth.getCurrentUser().getEmail();
                                            SharedPreferences.Editor editor=sp.edit();
                                            editor.putString("Email",currentUserEmail);
                                            editor.apply();

                                            Toast.makeText(getContext(), "Success",
                                                    Toast.LENGTH_SHORT).show();
                                            Navigation.findNavController(rootview).navigate(R.id.action_loginFragment2_to_homeFragment);
                                        } else {

                                            Toast.makeText(getContext(), "Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                    }else {
                        Toast.makeText(getContext(),"Enter password",Toast.LENGTH_SHORT).show();

                    }


                }else {
                    Toast.makeText(getContext(),"Enter Email",Toast.LENGTH_SHORT).show();
                }


            }
        });



        return rootview;
    }
}