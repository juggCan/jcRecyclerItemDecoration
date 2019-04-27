package com.jugg.library.itemdecoration;
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

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @function
 * @Author: jugg can
 * @Time: 16:16
 * @Version: 1.0.0
 * <p>
 * <p>
 * grid  Item Decoration
 */
public class JcGirdItemDecoration implements IJcItemDecoration {

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

    private List<JcSpecialType> typeList;


    private int[] cacheRight;

    public JcGirdItemDecoration(JcItemDecorationConfig config) {
        this.headSpace = config.getHeadSpace();
        this.tailSpace = config.getTailSpace();
        this.horiSpace = config.getHoriSpace();
        this.vertSpace = config.getVertSpace();
        this.startMarginSpace = config.getStartMarginSpace();
        this.endMarginSpace = config.getEndMarginSpace();

        if (config.getTypeList() != null) {
            this.typeList = config.getTypeList();
        } else {
            this.typeList = new ArrayList<>();
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        final GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        final int childPosition = parent.getChildAdapterPosition(view);
        final int spanCount = layoutManager.getSpanCount();

        if (cacheRight == null) {
            cacheRight = new int[spanCount];
        }

        boolean isSpecialType = false;
        //处理自定义view 的情况
        for (JcSpecialType type : this.typeList) {
            if (type.getType() == parent.getAdapter().getItemViewType(childPosition)) {
                outRect.top = type.getTop();
                outRect.right = type.getRight();
                outRect.bottom = type.getBottom();
                outRect.left = type.getLeft();
                isSpecialType = true;
            }
        }
        if (isSpecialType) {
            return;
        }


        if (layoutManager.getOrientation() == RecyclerView.VERTICAL) {
            orientationVertical(parent, layoutManager, childPosition, spanCount, outRect, lp);
        } else {
            orientationHorizontal(parent, layoutManager, childPosition, spanCount, outRect, lp);
        }
    }


    //竖向布局
    private void orientationVertical(RecyclerView parent, GridLayoutManager layoutManager, int childPosition, int spanCount, Rect outRect, GridLayoutManager.LayoutParams lp) {


        //判断是否在第一排
        int spanGroup = layoutManager.getSpanSizeLookup().getSpanGroupIndex(childPosition, spanCount);
        if (spanGroup == 0) {//第一排的需要上面
            outRect.top = headSpace;
        }
        int availableWidth = getavailableWidth(parent, spanCount);
        Log.i("orientationVertical", childPosition + "---spanGroup:" + spanGroup + "~~~~~:" + layoutManager.getSpanSizeLookup().getSpanGroupIndex(parent.getAdapter().getItemCount(), spanCount));
        //最后一排
        if (layoutManager.getSpanSizeLookup().getSpanGroupIndex(parent.getAdapter().getItemCount(), spanCount) == (spanGroup + 1)) {
            outRect.bottom = tailSpace;
        } else {
            outRect.bottom = vertSpace;
        }


        //占满一屏的情况
        if (lp.getSpanSize() == spanCount) {
            outRect.left = startMarginSpace;
            outRect.right = endMarginSpace;
        } else if (lp.getSpanIndex() == 0) {
            outRect.left = startMarginSpace;
            outRect.right = availableWidth - startMarginSpace;
            cacheRight[0] = outRect.right;
        } else if (lp.getSpanIndex() == (spanCount - 1)) {
            outRect.right = endMarginSpace;
            outRect.left = availableWidth - endMarginSpace;
        } else {
            outRect.left = horiSpace - cacheRight[lp.getSpanIndex() - 1];
            outRect.right = availableWidth - outRect.left;

            cacheRight[lp.getSpanIndex()] = outRect.right;
        }
    }

    private int getavailableWidth(RecyclerView parent, int spanCount) {
        //得到 RecyclerView 的宽度
        //然后减去item需要展示的宽度 算出可以用来展示间隙的宽度
        int parentWidth = parent.getWidth();

        //item的宽度
        int itemWidth = (parentWidth - (spanCount - 1) * horiSpace - startMarginSpace - endMarginSpace) / spanCount;
        int availableWidth = (parentWidth / spanCount) - itemWidth;

        return availableWidth;
    }


    private int getavailableHeight(RecyclerView parent, int spanCount) {
        //得到 RecyclerView 的高度
        //然后减去item需要展示的高度 算出可以用来展示间隙的高度
        int parentWidth = parent.getHeight();

        //item的宽度
        int itemHeight = (parentWidth - (spanCount - 1) * vertSpace - startMarginSpace - endMarginSpace) / spanCount;
        int availableHeight = (parentWidth / spanCount) - itemHeight;

        return availableHeight;
    }


    //横向布局HORIZONTAL
    private void orientationHorizontal(RecyclerView parent, GridLayoutManager layoutManager, int childPosition, int spanCount, Rect outRect, GridLayoutManager.LayoutParams lp) {
        //判断是否在第一排
        int spanGroup = layoutManager.getSpanSizeLookup().getSpanGroupIndex(childPosition, spanCount);
        if (spanGroup == 0) {//第一排的需要上面
            outRect.left = headSpace;
        }
        int availableWidth = getavailableHeight(parent, spanCount);
        Log.i("orientationVertical", childPosition + "---spanGroup:" + spanGroup + "~~~~~:" + layoutManager.getSpanSizeLookup().getSpanGroupIndex(parent.getAdapter().getItemCount(), spanCount));
        //最后一排
        if (layoutManager.getSpanSizeLookup().getSpanGroupIndex(parent.getAdapter().getItemCount(), spanCount) == (spanGroup + 1)) {
            outRect.right = tailSpace;
        } else {
            outRect.right = vertSpace;
        }
        //占满一屏的情况
        if (lp.getSpanSize() == spanCount) {
            outRect.top = startMarginSpace;
            outRect.bottom = endMarginSpace;
        } else if (lp.getSpanIndex() == 0) {
            outRect.top = startMarginSpace;
            outRect.bottom = availableWidth - startMarginSpace;
            cacheRight[0] = outRect.bottom;
        } else if (lp.getSpanIndex() == (spanCount - 1)) {
            outRect.bottom = endMarginSpace;
            outRect.top = availableWidth - endMarginSpace;
        } else {
            outRect.top = horiSpace - cacheRight[lp.getSpanIndex() - 1];
            outRect.bottom = availableWidth - outRect.top;

            cacheRight[lp.getSpanIndex()] = outRect.bottom;
        }

//        if (layoutManager.getSpanSizeLookup().getSpanGroupIndex(childPosition, spanCount) == 0) {//第一排的需要left
//            outRect.left = spaceHori;
//        }
//        outRect.right = spaceHori;
//        //这里忽略和合并项的问题，只考虑占满和单一的问题
//        if (lp.getSpanSize() == spanCount) {//占满
//            outRect.top = spaceVert;
//            outRect.bottom = spaceVert;
//        } else {
//            outRect.top = (int) (((float) (spanCount - lp.getSpanIndex())) / spanCount * spaceVert);
//            outRect.bottom = (int) (((float) spaceVert * (spanCount + 1) / spanCount) - outRect.top);
//        }
    }


}
