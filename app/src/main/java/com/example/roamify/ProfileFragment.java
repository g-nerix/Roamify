package com.example.roamify;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileFragment extends Fragment {

    FirebaseAuth auth;
    FirebaseUser user;

    DatabaseReference mDatabase;
    TextView name, email, age;

    Button logout, delete;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);

        auth= FirebaseAuth.getInstance();
        user= auth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        logout= view.findViewById(R.id.logoutBtn);
        name= view.findViewById(R.id.Name);
        age= view.findViewById(R.id.Age);
        email= view.findViewById(R.id.emailContainer);
        //delete= view.findViewById(R.id.Delete);

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

//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                builder.setTitle("Delete Account")
//                        .setMessage("Are you sure you want to delete your account?")
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                // Delete the user's account
//                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                                user.delete()
//                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                            @Override
//                                            public void onComplete(@NonNull Task<Void> task) {
//                                                if (task.isSuccessful()) {
//                                                    // Delete the user's data from the Realtime Database
//                                                    mDatabase.child("users").child(user.getUid()).removeValue();
//                                                    // Sign out and return to the login screen
//                                                    FirebaseAuth.getInstance().signOut();
//                                                    Intent intent = new Intent(getActivity(), Login.class);
//                                                    startActivity(intent);
//                                                    getActivity().finish();
//                                                } else {
//                                                    Toast.makeText(getContext(), "Failed to delete account", Toast.LENGTH_SHORT).show();
//                                                }
//                                            }
//                                        });
//                            }
//                        })
//                        .setNegativeButton("No", null)
//                        .show();
//            }
//        });


        return view;
    }
}