package cesarkemper.com.myapplicationforgit;

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
import com.google.firebase.auth.FirebaseAuthException;

public class MainActivity extends AppCompatActivity {
    public EditText userId, password;
    Button btnSignUp;
    TextView tvsignIn;
    FirebaseAuth mFireBaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFireBaseAuth = FirebaseAuth.getInstance();
        userId = findViewById(R.id.UserText);
        password = findViewById(R.id.PasswordText);
        btnSignUp = findViewById(R.id.buttonSignUp);
        tvsignIn = findViewById(R.id.SignInHere);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = userId.getText().toString();
                String pwd = password.getText().toString();
                if (user.isEmpty()){
                    userId.setError("Please enter user id");
                    userId.requestFocus();
                }
                if (user.isEmpty()){
                    userId.setError("Please enter user id");
                    userId.requestFocus();
                }
                else if (pwd.isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();
                }else if (user.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(MainActivity.this,"Fields are Empty!", Toast.LENGTH_LONG).show();
                }else if (!(user.isEmpty() && pwd.isEmpty())){
                    mFireBaseAuth.createUserWithEmailAndPassword(user,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"Sign Up Unsuccessful, Please Try Again", Toast.LENGTH_LONG).show();
                            }else
                                {
                                startActivity(new Intent(MainActivity.this,HomeActivity.class));
                            }
                        }
                    });

                }else{
                    Toast.makeText(MainActivity.this,"Error Occurred!", Toast.LENGTH_LONG).show();
                }
            }
        });

        tvsignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
