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


import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @function
 * @Author: jugg can
 * @Time: 16:13
 * @Version: 1.0.0
 */
public interface IJcItemDecoration {

    void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state);
}
