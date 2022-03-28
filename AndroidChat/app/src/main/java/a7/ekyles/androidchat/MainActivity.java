package a7.ekyles.androidchat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    EditText txtUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUserName = findViewById(R.id.txtUserName);
    }

    public void onLoginClicked(View v) {
        String userName = txtUserName.getText().toString();
        boolean nameIsValid = userName.matches("^\\w{2,9}[a-zA-Z0-9]$");

        if(nameIsValid) {
        //Move to the next activity and chat
        Log.i("LOGIN", "Name was vaild");
        } else {
            Log.i("LOGIN", "Name was NOT valid");
            Snackbar snackbar = Snackbar.make( txtUserName,
                    "User Name must be 3-10 characters long and alpha-numberic only",
                    Snackbar.LENGTH_LONG);
            snackbar.setDuration(5000);
            snackbar.setAnchorView(txtUserName);
            snackbar.show();
        }
    }
}