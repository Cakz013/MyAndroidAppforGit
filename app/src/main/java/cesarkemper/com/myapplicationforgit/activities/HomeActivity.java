package cesarkemper.com.myapplicationforgit.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import cesarkemper.com.myapplicationforgit.R;

public class HomeActivity extends AppCompatActivity {
    Button btnLogOut, btnDagger;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnLogOut = findViewById(R.id.LogOut);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intToMain);


            }
        });
        btnDagger = findViewById(R.id.DaggerBtn);

        btnDagger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inttoDagger = new Intent(HomeActivity.this, DaggerActivity.class);
                startActivity(inttoDagger);
            }
        });
    }
}
