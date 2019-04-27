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


import java.util.List;

/**
 * @function
 * @Author: jugg can
 * @Time: 16:33
 * @Version: 1.0.0
 */
public class JcItemDecorationConfig {
    private int headSpace = 0;

    private int tailSpace = 0;

    private int horiSpace = 0;
    private int vertSpace = 0;
    private int startMarginSpace = 0;
    private int endMarginSpace = 0;
    private int itemWidth = 0;

    private List<JcSpecialType> typeList;

    public List<JcSpecialType> getTypeList() {
        return typeList;
    }

    public JcItemDecorationConfig(int horiSpace, int vertSpace) {
        this.horiSpace = horiSpace;
        this.vertSpace = vertSpace;
    }

    public void setTypeList(List<JcSpecialType> typeList) {
        this.typeList = typeList;
    }

    public void setHeadSpace(int headSpace) {
        this.headSpace = headSpace;
    }

    public void setTailSpace(int tailSpace) {
        this.tailSpace = tailSpace;
    }

    public void setHoriSpace(int horiSpace) {
        this.horiSpace = horiSpace;
    }

    public void setVertSpace(int vertSpace) {
        this.vertSpace = vertSpace;
    }

    public void setStartMarginSpace(int startMarginSpace) {
        this.startMarginSpace = startMarginSpace;
    }

    public void setEndMarginSpace(int endMarginSpace) {
        this.endMarginSpace = endMarginSpace;
    }

    public void setItemWidth(int itemWidth) {
        this.itemWidth = itemWidth;
    }

    public int getHeadSpace() {
        return headSpace;
    }

    public int getTailSpace() {
        return tailSpace;
    }

    public int getHoriSpace() {
        return horiSpace;
    }

    public int getVertSpace() {
        return vertSpace;
    }

    public int getStartMarginSpace() {
        return startMarginSpace;
    }

    public int getEndMarginSpace() {
        return endMarginSpace;
    }

    public int getItemWidth() {
        return itemWidth;
    }
}
