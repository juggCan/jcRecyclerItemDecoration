package wxhelp.jugg.com.jcnewdome;
//  ┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//   ┃　　　┃   神兽保佑
//   ┃　　　┃   代码无BUG！
//   ┃　　　┗━━━┓
//   ┃　　　　　　　┣┓
//   ┃　　　　　　　┏┛
//   ┗┓┓┏━┳┓┏┛
//     ┃┫┫　┃┫┫
//     ┗┻┛　┗┻┛


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @function
 * @Author: jugg can
 * @Time: 17:33
 * @Version: 1.0.0
 */
public class DemoLinearHAdapter extends BaseRecyclerViewAdapter<DemoLinearHAdapter.HolderView> {


    private List<String> data;

    private Activity activity;

    public DemoLinearHAdapter(List<String> data, Activity activity) {
        this.data = data;
        this.activity = activity;
    }

    @Override
    public HolderView createViewHolder_my(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(activity).inflate(R.layout.recyclerview_linearh_item, viewGroup, false);
        HolderView holder = new HolderView(view);
        return holder;
    }

    @Override
    public void bindHolder(@NonNull HolderView viewHolder, int i) {
        viewHolder.itemView.getWidth();
        viewHolder.itemView.getHeight();

        viewHolder.itemView.getMeasuredWidth();
        viewHolder.itemView.getMeasuredHeight();
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(v, 0, 0, 0);
                }
            }
        });
    }

    @Override
    protected int itemCount() {
        if (data == null)
            return 0;
        return data.size();
    }

    class HolderView extends RecyclerView.ViewHolder {

        public HolderView(@NonNull View itemView) {
            super(itemView);
        }
    }
}
