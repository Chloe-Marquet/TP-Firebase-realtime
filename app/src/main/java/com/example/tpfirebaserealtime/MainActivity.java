package com.example.tpfirebaserealtime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "SMIN";
    // reference to the root node of the database
    private DatabaseReference mDatabaseRef;
    private EditText identifierEditText;
    private EditText nameEditText;
    private TextView informationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing views
        identifierEditText = findViewById(R.id.identifier_input);
        nameEditText = findViewById(R.id.name_input);
        informationTextView = findViewById(R.id.person_info);

        // Creates a reference on the "root" node of the database
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        Log.d(TAG, "Message onCreate");
    }

    public void submitNewPerson(View view) {
        String newIdentifier = identifierEditText.getText().toString();
        String newName = nameEditText.getText().toString();
        Personne newPerson = new Personne (newIdentifier, newName);
        Log.d(TAG, newPerson.toString());
        mDatabaseRef.child("personnes").child(newPerson.getIdentifier()).setValue(newPerson);
        Log.d(TAG, "Message submitNewPerson");


        //mDatabaseRef.child("message").setValue("Hello World !");

        mDatabaseRef.child("message").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Personne updatedPerson = snapshot.getValue(Personne.class);
                    informationTextView.setText(updatedPerson.toString());
                    //Log.d(TAG, "message value is : " + snapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}