package wxhelp.jugg.com.jcnewdome;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Author    Jugg_can
 * Time:     2017/6/2 16:23
 * Version:  1.0.0V
 */

public class RecyclerGrid2ItemDecoration extends RecyclerView.ItemDecoration {
    private int spaceHori = 0;
    private int spaceVert = 0;
    /*
     *行数
     */
    private int sum = 0;

    public RecyclerGrid2ItemDecoration(int spaceVert, int spaceHori) {
        this.spaceVert = spaceVert;
        this.spaceHori = spaceHori;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        final GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        final int childPosition = parent.getChildAdapterPosition(view);
        final int spanCount = layoutManager.getSpanCount();
        if (layoutManager.getOrientation() == RecyclerView.VERTICAL) {
            //判断是否在第一排
            if (layoutManager.getSpanSizeLookup().getSpanGroupIndex(childPosition, spanCount) == 0) {//第一排的需要上面
                outRect.top = spaceVert;
            }
            outRect.bottom = spaceVert;
            //这里忽略和合并项的问题，只考虑占满和单一的问题
            if (lp.getSpanSize() == spanCount) {//占满
                outRect.left = spaceHori;
                outRect.top = 0;
                outRect.right = spaceHori;
            } else {
                outRect.left = (int) (((float) (spanCount - lp.getSpanIndex())) / spanCount * spaceHori);
                outRect.right = (int) (((float) spaceHori * (spanCount + 1) / spanCount) - outRect.left);
            }
        } else {
            if (layoutManager.getSpanSizeLookup().getSpanGroupIndex(childPosition, spanCount) == 0) {//第一排的需要left
                outRect.left = spaceHori;
            }
            outRect.right = spaceHori;
            //这里忽略和合并项的问题，只考虑占满和单一的问题
            if (lp.getSpanSize() == spanCount) {//占满
                outRect.top = spaceVert;
                outRect.bottom = spaceVert;
            } else {
                outRect.top = (int) (((float) (spanCount - lp.getSpanIndex())) / spanCount * spaceVert);
                outRect.bottom = (int) (((float) spaceVert * (spanCount + 1) / spanCount) - outRect.top);
            }
        }
    }
}
