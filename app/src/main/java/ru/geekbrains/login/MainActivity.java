package ru.geekbrains.login;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private Pattern checkLogin = Pattern.compile("^[A-Z][a-z]{2,}$");
    private Pattern checkPassword = Pattern.compile("^(?=^.{6,}$)(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$");
    private TextInputEditText login;
    private TextInputEditText password;
    private TextInputLayout loginLayout;
    private TextInputLayout passwordLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.inputLoginName);
        password = findViewById(R.id.inputPassword);
        loginLayout = findViewById(R.id.userName);
        passwordLayout = findViewById(R.id.password);

        login.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) return;
                TextView tv = (TextView) v;
                validate(tv, checkLogin, loginLayout, "Это не имя!");
            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) return;
                TextView tv = (TextView) v;
                validate(tv, checkPassword, passwordLayout, "Пароль слишком простой!");
            }
        });
    }

    private void validate(TextView tv, Pattern check, TextInputLayout textInputLayout, String message) {
        String value = tv.getText().toString();
        if (check.matcher(value).matches()) {
            showError(textInputLayout, null);
        } else {
            showError(textInputLayout, message);
        }
    }

    private void showError(TextInputLayout textInputLayout, String message) {
        textInputLayout.setError(message);
    }
}
