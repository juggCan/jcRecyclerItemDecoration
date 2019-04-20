/*
 *Recyclerview  点击事件接口
 */
package wxhelp.jugg.com.jcnewdome;

import android.view.View;

/**
 * Author    Jugg_can
 * Time:     2017/6/23 14:45
 * Version:  1.0.0V
 */

public interface IRcCurrencyClickListener {
    /*
     *item点击事件
     *点击的view
     * 点击的是哪一个项
     * 点击的是哪一个组
     * 点击的是类别
     */
    void onItemClick(View view, int position, int group, int type);
}
