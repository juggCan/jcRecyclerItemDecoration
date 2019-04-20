package wxhelp.jugg.com.jcnewdome;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
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

/**
 * @function 基类   recyclerview adapter
 * @Author: jugg
 * @Time: 2018/9/27 19:05
 * @Version: 2.2.0
 * <p>
 * 功能：
 * 1.增加头功能
 * 2.item 点击功能
 * 3.item ItemTouchHelper功能
 * <p>
 * <p>
 * TYPE_EMPTYVIEW 由于有些自定义的type 用的是2  所以取值为-1
 */
abstract class BaseRecyclerViewAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {

    public static final int TYPE_HEADER = 0;
    //    public static final int TYPE_EMPTY = 1;
    public static final int TYPE_NORMAL = 1;
    public static final int TYPE_EMPTYVIEW = -1;
    private View mHeaderView;
    private View mEmptyView;

    protected IRcCurrencyClickListener itemClickListener;

    public void setItemClickListener(IRcCurrencyClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    protected ItemTouchHelper mItemHelper;

    public void setmItemHelper(ItemTouchHelper mItemHelper) {
        this.mItemHelper = mItemHelper;
    }


    private GridLayoutManager glManager;

    public void setGlm(GridLayoutManager glm) {
        this.glManager = glm;
        glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (getItemViewType(position) == TYPE_HEADER) {
                    return glManager.getSpanCount();
                }
                if (getItemViewType(position) == TYPE_EMPTYVIEW) {
                    return glManager.getSpanCount();
                }
                return 1;
            }
        });
    }


    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }


    public void setEmptyView(View mEmptyView) {
        this.mEmptyView = mEmptyView;
        if (mHeaderView == null)
            notifyItemInserted(0);
        else {
            notifyItemInserted(1);
        }
    }

    @NonNull
    @Override
    public T onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (mHeaderView != null && i == TYPE_HEADER) {
//            return new RecyclerViewHeaderView(mHeaderView);
            return (T) new RecyclerViewHeaderView(mHeaderView);
        }
        if (mEmptyView != null && i == TYPE_EMPTYVIEW) {
//            return new RecyclerViewHeaderView(mHeaderView);
            return (T) new RecyclerViewHeaderView(mEmptyView);
        }
        return createViewHolder_my(viewGroup, i);
    }

//    public abstract T createHeaderView(View view);

    public abstract T createViewHolder_my(@NonNull ViewGroup viewGroup, int i);

    public abstract void bindHolder(@NonNull T viewHolder, int i);

    @Override
    public void onBindViewHolder(@NonNull T viewHolder, int i) {
        int type_v = getItemViewType(i);

        if (type_v == TYPE_HEADER)
            return;
        if (type_v == TYPE_EMPTYVIEW)
            return;

        bindHolder(viewHolder, getRealPosition(i));
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView != null && position == 0) return TYPE_HEADER;
        if (mEmptyView != null && (position == 0 || position == 1) && itemCount() == 0)
            return TYPE_EMPTYVIEW;
        return itemType(position);
    }

    public int itemType(int position) {
        return TYPE_NORMAL;
    }


    @Override
    public int getItemCount() {

        int item_sum = itemCount();

        if (item_sum == 0 && mEmptyView != null) {
            item_sum = 1;
        }
        if (mHeaderView != null) {
            item_sum++;
        }
        return item_sum;
    }

    protected abstract int itemCount();

    public int getRealPosition(int index) {
        return mHeaderView == null ? index : index - 1;
    }


    class RecyclerViewHeaderView extends RecyclerView.ViewHolder {


        public RecyclerViewHeaderView(View itemView) {
            super(itemView);
        }
    }
}
