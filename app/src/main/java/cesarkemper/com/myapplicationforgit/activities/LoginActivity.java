package cesarkemper.com.myapplicationforgit.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import cesarkemper.com.myapplicationforgit.R;

public class LoginActivity extends AppCompatActivity {
    public EditText userId, password;
    Button btnSignIn;
    TextView tvsignUp;
    FirebaseAuth mFireBaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFireBaseAuth = FirebaseAuth.getInstance();
        userId = findViewById(R.id.UserText);
        password = findViewById(R.id.PasswordText);
        btnSignIn = findViewById(R.id.buttonSignUp);
        tvsignUp = findViewById(R.id.SignInHere);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {


            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFireBaseAuth.getCurrentUser();
                if(mFirebaseUser != null){
                    Toast.makeText(LoginActivity.this,"You are logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(LoginActivity.this,"Please Login", Toast.LENGTH_SHORT).show();
                }
            }
        };
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = userId.getText().toString();
                String pwd = password.getText().toString();
                if (user.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Los Campos estan vacios!", Toast.LENGTH_LONG).show();
                }
                else if (user.isEmpty()) {
                    userId.setError("Ingrese su usuario");
                    userId.requestFocus();
                }
                else if (pwd.isEmpty()){
                    password.setError("Ingrese su contraseña");
                    password.requestFocus();
                }else if (!(user.isEmpty() && pwd.isEmpty())){
                    mFireBaseAuth.signInWithEmailAndPassword(user, pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"Error al ingresar intentelo de vuelta!", Toast.LENGTH_LONG).show();
                        }else{
                            Intent intToHome = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intToHome);
                        }
                        }
                    });
                }else{
                    Toast.makeText(LoginActivity.this,"Ocurrio un error!", Toast.LENGTH_LONG).show();
                }
            }
        });

        tvsignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intSignUp = new Intent (LoginActivity.this, MainActivity.class);
                startActivity(intSignUp);
            }
        });
    }
    protected void onStart(){
        super.onStart();
        mFireBaseAuth.addAuthStateListener(mAuthStateListener);
    }
}
