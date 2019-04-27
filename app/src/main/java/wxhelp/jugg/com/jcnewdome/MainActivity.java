package wxhelp.jugg.com.jcnewdome;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
    }


    @OnClick(R.id.button_v)
    public void buttonVClick() {
        startActivity(new Intent(MainActivity.this, MainActivity2.class));
    }

    @OnClick(R.id.button_linear_v)
    public void linearVClick() {
        startActivity(new Intent(MainActivity.this, LinearActivity.class));
    }


    @OnClick(R.id.button_linear_h)
    public void linearhClick() {
        startActivity(new Intent(MainActivity.this, LinearHActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
