package java.android.quanlybanhang.login;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.android.quanlybanhang.Activity.ShopActivity;
import java.android.quanlybanhang.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    Button sigupNow, login;
    TextView logoText, sloganText, forgetPass;
    ImageView imageView;
    TextInputEditText username, password;
    FloatingActionButton facebook, google;
    LoginButton login_button;

    private GoogleSignInClient mGoogleSignInClient;
    private CallbackManager mCallbackManager;

    private static final String TAG1 = "FacebookLogin";
    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 123;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        sigupNow = findViewById(R.id.btn_signup_now);
        login = findViewById(R.id.btn_login);
        logoText = findViewById(R.id.logoText);
        sloganText = findViewById(R.id.sloganText);
        username = findViewById(R.id.edt_email_username);
        password = findViewById(R.id.edt_password);
        imageView = findViewById(R.id.imageView);
        forgetPass = findViewById(R.id.lbl_forget_pass);
        facebook =  findViewById(R.id.btn_facebook);
        google = findViewById(R.id.btn_google);
        login_button = findViewById(R.id.login_button);

        Intent intent = getIntent();
        String emailIntent = intent.getStringExtra("email");

        if(emailIntent != null){
            username.setText(emailIntent);
            sloganText.setText("Sign up succes! Sign in to continue...");
        }

        createRequest();
        mAuth = FirebaseAuth.getInstance();
        mCallbackManager = CallbackManager.Factory.create();

        //setOnclick
        facebook.setOnClickListener(this);
        sigupNow.setOnClickListener(this);
        google.setOnClickListener(this);
        facebook.setOnClickListener(this);
        login.setOnClickListener(this);
        forgetPass.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == facebook){
            Intent intent = new Intent(LoginActivity.this, FacebookAuthActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }else if(v == sigupNow){
            callSignup();
        }else if(v == google){
            signIn();
        }else if(v == login){
            login();
        }else if(v == forgetPass){
            callForgetPassword();
        }
    }

    private void createRequest(){
        // [START config_signin]
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void callSignup(){
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);

        Pair[] pairs = new Pair[10];

        pairs[0] = new Pair<View, String>(imageView, "logo_image");
        pairs[1] = new Pair<View, String>(logoText, "logo_welcome");
        pairs[2] = new Pair<View, String>(sloganText, "logo_signin");
        pairs[3] = new Pair<View, String>(username, "edt_username");
        pairs[4] = new Pair<View, String>(password, "edt_password");
        pairs[5] = new Pair<View, String>(forgetPass, "lbl_forget");
        pairs[6] = new Pair<View, String>(login, "button_sign");
        pairs[7] = new Pair<View, String>(sigupNow, "btn_signup_now");
        pairs[8] = new Pair<View, String>(facebook, "button_facebook");
        pairs[9] = new Pair<View, String>(google, "button_google");

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
            startActivity(intent, options.toBundle());
        }
    }

    private void callForgetPassword(){
        Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);

        Pair[] pairs = new Pair[5];

        pairs[0] = new Pair<View, String>(imageView, "logo_image");
        pairs[1] = new Pair<View, String>(logoText, "logo_welcome");
        pairs[2] = new Pair<View, String>(sloganText, "logo_signin");
        pairs[3] = new Pair<View, String>(username, "edt_username");
        pairs[4] = new Pair<View, String>(login, "button_sign");

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
            startActivity(intent, options.toBundle());
        }
    }

    // [START on_start_check_user]
    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), ShopActivity.class);
            startActivity(intent);
            finish();
        }
        updateUI(currentUser);
    }

    // [START onactivityresult]
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
                Toast.makeText(this, "Google sign in success", Toast.LENGTH_SHORT).show();
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                Log.d("test: ", e+"");
                Toast.makeText(this, "Google sign in failed", Toast.LENGTH_SHORT).show();
            }
        }

    }

    // [START auth_with_google]
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            updateUI(null);
                        }
                    }
                });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void updateUI(FirebaseUser user) {
        if(user != null){
            Intent intent = new Intent(LoginActivity.this, ShopActivity.class);
            startActivity(intent);
            finish();
        }
    }

    // onclick login
    private void login(){
        String email = username.getText().toString();
        String pass = password.getText().toString();

        mAuth = FirebaseAuth.getInstance();
        if (email.isEmpty()) {
            username.setError("Plese enter email id");
            username.requestFocus();
        } else if (pass.isEmpty()) {
            password.setError("Plese enter your password");
            password.requestFocus();
        } else if (email.isEmpty() && pass.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Fialds Are Empty!", Toast.LENGTH_LONG).show();
        } else if (!(email.isEmpty() && pass.isEmpty())) {
            mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    LoadingDialog loadingDialog = new LoadingDialog(LoginActivity.this);
                    loadingDialog.startLoadingDialog();
                    Handler handler = new Handler();
                    if (!task.isSuccessful()) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loadingDialog.dismissDialog();
                                Toast.makeText(LoginActivity.this, "Login Error, Plese login Again!", Toast.LENGTH_LONG).show();
                            }
                        }, 1000);
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loadingDialog.dismissDialog();
                                Intent intent = new Intent(LoginActivity.this, ShopActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }, 3000);
                    }

                }
            });
        } else {
            Toast.makeText(LoginActivity.this, "Error Occurred!", Toast.LENGTH_LONG).show();
        }
    }
}