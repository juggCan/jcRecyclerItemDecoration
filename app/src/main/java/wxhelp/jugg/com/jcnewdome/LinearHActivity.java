package wxhelp.jugg.com.jcnewdome;

import android.os.Bundle;

import com.jugg.library.itemdecoration.JcRecyclerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LinearHActivity extends AppCompatActivity {

    private Unbinder unbinder;


    @BindView(R.id.recycleView)
    public RecyclerView recycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linearh_layout);
        unbinder = ButterKnife.bind(this);


        List<String> data = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            data.add("");
        }


        DemoLinearHAdapter adapter = new DemoLinearHAdapter(data, this);
        recycleView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recycleView.setAdapter(adapter);
//        int deci = SystemUtil.dip2px(this, 10);
        recycleView.addItemDecoration(
                new JcRecyclerItemDecoration
                        .Buidle(SystemUtil.dip2px(this, 14), 0)
                        .setStartMarginSpace(SystemUtil.dip2px(this, 14))
                        .setEndMarginSpace(SystemUtil.dip2px(this, 14))
                        .setHeadSpace(SystemUtil.dip2px(this, 14))
                        .setTailSpace(SystemUtil.dip2px(this, 14))
                        .buidle()
        );
//        recycleView.addItemDecoration(new RecyclerGrid2ItemDecoration(deci, deci));
        recycleView.setHasFixedSize(true);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
