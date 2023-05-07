package com.example.roamify;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFragment extends Fragment {

    FirebaseAuth auth;
    FirebaseUser user;

    TextView name, email, age;

    Button logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);

        auth= FirebaseAuth.getInstance();
        user= auth.getCurrentUser();

        logout= view.findViewById(R.id.logoutBtn);
        name= view.findViewById(R.id.Name);
        age= view.findViewById(R.id.Age);
        email= view.findViewById(R.id.emailContainer);

        if( user==null){
//                    profile.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
            //startActivity(new Intent(getApplicationContext(), Login.class));
//                        }
//                    });

            Intent intent= new Intent(getActivity(), Login.class);
            startActivity(intent);
            getActivity().finish();
        }

        if( user.getEmail()==null){
            Intent intent= new Intent(getActivity(), Login.class);
            startActivity(intent);
            getActivity().finish();
        }
        email.setText("Email: "+user.getEmail());

        String[] x= user.getDisplayName().split("#");
        name.setText("Name: "+x[0]);
        age.setText("Age: "+x[1]);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();

                // changing the fragments
                Intent intent= new Intent(getActivity(), Login.class);
                startActivity(intent);
                getActivity().finish();
            }
        });


        return view;
    }
}