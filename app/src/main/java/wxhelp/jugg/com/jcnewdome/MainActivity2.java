package wxhelp.jugg.com.jcnewdome;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity2 extends AppCompatActivity {


    @BindView(R.id.recycleView)
    public RecyclerView recycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        List<String> data = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            data.add("");
        }


        DemoAdapter adapter = new DemoAdapter(data, this);
        recycleView.setLayoutManager(new GridLayoutManager(this, 4, RecyclerView.VERTICAL, false));
        recycleView.setAdapter(adapter);
        int deci = (SystemUtil.getScreenWidth(this) - SystemUtil.dip2px(this, 328)) / 3;
//        int deci = SystemUtil.dip2px(this, 10);
        recycleView.addItemDecoration(
                new JcRecyclerGridItemDecoration
                        .Buidle(deci, deci)
                        .setStartMarginSpace(SystemUtil.dip2px(this, 14))
                        .setEndMarginSpace(SystemUtil.dip2px(this, 14))
                        .setHeadSpace(SystemUtil.dip2px(this, 14))
                        .setTailSpace(SystemUtil.dip2px(this, 14))
                        .buidle()
        );
//        recycleView.addItemDecoration(new RecyclerGrid2ItemDecoration(deci, deci));
        recycleView.setHasFixedSize(true);


        adapter.setItemClickListener(new IRcCurrencyClickListener() {
            @Override
            public void onItemClick(View view, int position, int group, int type) {
                Toast.makeText(MainActivity2.this, "" + view.getWidth(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
