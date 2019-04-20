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
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @function
 * @Author: jugg can
 * @Time: 16:30
 * @Version: 1.0.0
 */
public class JcLinearItemDecoration implements IJcItemDecoration {
    //头部的空白
    private int headSpace = 0;

    //尾部的空白
    private int tailSpace = 0;

    //横向item之间的空白
    private int horiSpace = 0;
    //竖向item之间的空白
    private int vertSpace = 0;

    //item与recycler的边距
    private int startMarginSpace = 0;
    //item与recycler的边距
    private int endMarginSpace = 0;


    public JcLinearItemDecoration(JcItemDecorationConfig config) {
        this.headSpace = config.getHeadSpace();
        this.tailSpace = config.getTailSpace();
        this.horiSpace = config.getHoriSpace();
        this.vertSpace = config.getVertSpace();
        this.startMarginSpace = config.getStartMarginSpace();
        this.endMarginSpace = config.getEndMarginSpace();
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        LinearLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        int childPosition = parent.getChildAdapterPosition(view);
        int cout = parent.getAdapter().getItemCount();

        if (layoutManager.getOrientation() == RecyclerView.VERTICAL) {
            orientationVertical(parent, layoutManager, childPosition, cout, outRect);
        } else {
            orientationHorizontal(parent, layoutManager, childPosition, cout, outRect);
        }
    }

    private int getindex(int childPosition) {
        return childPosition;
    }


    //竖向布局
    private void orientationVertical(RecyclerView parent, LinearLayoutManager layoutManager, int childPosition, int cout, Rect outRect) {
        //判断是否在第一排
        if (getindex(childPosition) == 0) {
            outRect.top = headSpace;
            outRect.bottom = vertSpace;
        } else if ((cout - 1) == childPosition) {
            outRect.bottom = endMarginSpace;
        } else {
            outRect.bottom = vertSpace;
        }

        outRect.left = startMarginSpace;
        outRect.right = endMarginSpace;
    }

    //横向布局HORIZONTAL
    private void orientationHorizontal(RecyclerView parent, LinearLayoutManager layoutManager, int childPosition, int cout, Rect outRect) {
        //判断是否在第一排

    }
}
