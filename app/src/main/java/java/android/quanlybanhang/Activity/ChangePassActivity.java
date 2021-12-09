package java.android.quanlybanhang.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.android.quanlybanhang.R;

public class ChangePassActivity extends AppCompatActivity {

    private EditText edtOldPass,edtNewPass,edtCfPass;
    private Button btn_changepass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        edtOldPass = findViewById(R.id.edt_passold);
        edtNewPass = findViewById(R.id.edt_newpass);
        edtCfPass = findViewById(R.id.edt_cfpass);
        btn_changepass=findViewById(R.id.btn_changepass);
        btn_changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edtNewPass.getText().toString().equals(edtCfPass.getText().toString())){
                    onClickChangePassword();
                }else {
                    Toast.makeText(ChangePassActivity.this,"Đổi mật khẩu không thành công",Toast.LENGTH_SHORT).show();
                    edtCfPass.setError("Mật khẩu xác nhận không đúng");
                }
            }
        });
    }
    public void onClickChangePassword()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String newpass=edtNewPass.getText().toString().trim();
        String newPassword = newpass;
        AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(),edtOldPass.getText().toString().trim());
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(ChangePassActivity.this,KhachHangActivity.class);
                        if (task.isSuccessful()) {
                            user.updatePassword(newpass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(intent);
                                        Toast.makeText(ChangePassActivity.this,"Đổi mật khẩu thành công",Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(ChangePassActivity.this,"Đổi mật khẩu không thành công",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(ChangePassActivity.this,"Authe Fail",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
