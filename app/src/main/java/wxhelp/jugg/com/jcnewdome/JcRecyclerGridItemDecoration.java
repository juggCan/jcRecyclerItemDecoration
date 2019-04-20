package wxhelp.jugg.com.jcnewdome;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Author    Jugg_can
 * Time:     2017/6/2 16:23
 * Version:  1.0.0V
 */

public class JcRecyclerGridItemDecoration extends RecyclerView.ItemDecoration {


    private JcItemDecorationConfig config;

    private IJcItemDecoration jcItemDecoration;
    //item的宽度
//    private int itemWidth = 0;


    public JcRecyclerGridItemDecoration(Buidle buidle) {
        this.config = buidle.config;
        this.jcItemDecoration = buidle.jcItemDecoration;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        if (jcItemDecoration != null) {
            jcItemDecoration.getItemOffsets(outRect, view, parent, state);
            return;
        }

        LinearLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            jcItemDecoration = new JcGirdItemDecoration(config);
        } else if (layoutManager instanceof LinearLayoutManager) {
            jcItemDecoration = new JcLinearItemDecoration(config);

        }
        jcItemDecoration.getItemOffsets(outRect, view, parent, state);
    }


    static class Buidle {

        private JcItemDecorationConfig config;
        private IJcItemDecoration jcItemDecoration;

        /*
            默认   必填项
            横向item之间的空白
            竖向item之间的空白
            itemWidth  item的宽度
         */
        public Buidle(int horiSpace, int vertSpace) {
            config = new JcItemDecorationConfig(horiSpace, vertSpace);
//            this.itemWidth = itemWidth;
        }

        public Buidle setJcItemDecoration(IJcItemDecoration jcItemDecoration) {
            this.jcItemDecoration = jcItemDecoration;
            return this;
        }

        public Buidle setItemWidth(int itemWidth) {
            config.setItemWidth(itemWidth);
            return this;
        }

        public Buidle setHeadSpace(int headSpace) {
            config.setHeadSpace(headSpace);
            return this;
        }

        public Buidle setTailSpace(int tailSpace) {
            config.setTailSpace(tailSpace);
            return this;
        }

        public Buidle setHoriSpace(int horiSpace) {
            config.setHoriSpace(horiSpace);
            return this;
        }

        public Buidle setVertSpace(int vertSpace) {
            config.setVertSpace(vertSpace);
            return this;
        }

        public Buidle setStartMarginSpace(int startMarginSpace) {
            config.setStartMarginSpace(startMarginSpace);
            return this;
        }

        public Buidle setEndMarginSpace(int endMarginSpace) {
            config.setEndMarginSpace(endMarginSpace);
            return this;
        }

        public JcRecyclerGridItemDecoration buidle() {
            return new JcRecyclerGridItemDecoration(this);
        }
    }
}
