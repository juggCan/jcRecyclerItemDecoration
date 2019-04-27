package wxhelp.jugg.com.jcnewdome;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.jugg.library.itemdecoration.JcRecyclerItemDecoration;
import com.jugg.library.itemdecoration.JcSpecialType;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import wxhelp.jugg.com.jcnewdome.adapter.IRcCurrencyClickListener;

public class MainActivity2 extends AppCompatActivity {


    @BindView(R.id.recycleView)
    public RecyclerView recycleView;


    private DemoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        List<String> data = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            data.add("");
        }


        adapter = new DemoAdapter(data, this);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4, RecyclerView.VERTICAL, false);
        recycleView.setLayoutManager(gridLayoutManager);
        recycleView.setAdapter(adapter);

        //添加头
        addHeadView(gridLayoutManager);


        //计算出间隙
        int deci = (SystemUtil.getScreenWidth(this) - SystemUtil.dip2px(this, 328)) / 3;
//        int deci = SystemUtil.dip2px(this, 10);

        //加间隙
        recycleView.addItemDecoration(
                new JcRecyclerItemDecoration
                        .Buidle(deci, deci)
                        .setStartMarginSpace(SystemUtil.dip2px(this, 14))
                        .setEndMarginSpace(SystemUtil.dip2px(this, 14))
                        .setHeadSpace(SystemUtil.dip2px(this, 14))
                        .setTailSpace(SystemUtil.dip2px(this, 14))
                        .setSpecialTypes(new JcSpecialType(0, 0, 0, 0, SystemUtil.dip2px(this, 14)),
                                new JcSpecialType(-1, 0, 0, 0, 0))
                        .buidle()
        );
//        recycleView.addItemDecoration(new RecyclerGrid2ItemDecoration(deci, deci));
        recycleView.setHasFixedSize(true);


        //加点击事件
        adapter.setItemClickListener(new IRcCurrencyClickListener() {
            @Override
            public void onItemClick(View view, int position, int group, int type) {
                Toast.makeText(MainActivity2.this, "" + view.getWidth(), Toast.LENGTH_LONG).show();
            }
        });
    }


    //因为这里用的是 Grid  所以需要设置GLM
    private void addHeadView(GridLayoutManager gridLayoutManager) {

        adapter.setGlm(gridLayoutManager);

        //这里 没有传入ViewGroup  所以要手动设置一个属性给它
        View headView = getLayoutInflater().inflate(R.layout.main_headview, null);
        headView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        adapter.setHeaderView(headView);
    }
}
