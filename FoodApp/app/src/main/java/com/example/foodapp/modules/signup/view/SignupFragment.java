package com.example.foodapp.modules.signup.view;

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
import com.example.foodapp.modules.login.view.LoginFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignupFragment extends Fragment {
    EditText email;
    EditText pass;
    Button signUp;
    FirebaseAuth mAuth;
    TextView login;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview= inflater.inflate(R.layout.fragment_signup, container, false);
        email=rootview.findViewById(R.id.signup_email);
        pass=rootview.findViewById(R.id.signup_password);
        login=rootview.findViewById(R.id.loginRedirectText);
        signUp=rootview.findViewById(R.id.signup_button);
        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(rootview).navigate(R.id.action_signupFragment_to_loginFragment2);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailtext = String.valueOf(email.getText());
                String passtext = String.valueOf(email.getText());

                if (!TextUtils.isEmpty(emailtext)) {
                    if (!TextUtils.isEmpty(passtext)) {
                        mAuth.createUserWithEmailAndPassword(emailtext, passtext)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(getContext(), "Account Created",
                                                    Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(getContext(), "Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }else {
                        Toast.makeText(getContext(), "Enter password", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(getContext(), "Enter Email", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return rootview;
    }
}