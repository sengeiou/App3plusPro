package com.zjw.apps3pluspro.module.device;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.zjw.apps3pluspro.R;


/**
 * 蓝牙连接问题
 */
public class BleConnectProblemActivity extends Activity implements OnClickListener {


    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ble_problem);
        initView();
        final RecyclerView rv = (RecyclerView) findViewById(R.id.rv);

        // 设置启动列表的修改动画效果(默认为关闭状态) 23.0.1以后使用方法，23.0.1之前用setSupportsChangeAnimations方法
        RecyclerView.ItemAnimator animator = rv.getItemAnimator();
        if (animator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) animator).setSupportsChangeAnimations(false);
        }
        // 设置动画时长
        rv.getItemAnimator().setChangeDuration(300);
        rv.getItemAnimator().setMoveDuration(300);

        // 实现RecyclerView实现竖向列表展示模式
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        // 实例化数据适配器并绑定在控件上
        final MainAdapter adapter = new MainAdapter();
        rv.setAdapter(adapter);


    }


    public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {


        // 为列表提供数据的数据集合
        final String[] contacts = new String[]{
                getString(R.string.ble_connect_problem_title1),
                getString(R.string.ble_connect_problem_title2),
                getString(R.string.ble_connect_problem_title3),
                getString(R.string.ble_connect_problem_title4),
                getString(R.string.ble_connect_problem_title5)
        };

        final int[] imgs = new int[]{
                R.drawable.my_icon_dengpao_black,
                R.drawable.my_icon_dengpao_black,
                R.drawable.my_icon_dengpao_black,
                R.drawable.my_icon_dengpao_black,
                R.drawable.my_icon_dengpao_black,
                R.drawable.my_icon_dengpao_black
        };

        final String[] infos = new String[]{
                getString(R.string.ble_connect_problem_count1),
                getString(R.string.ble_connect_problem_count2),
                getString(R.string.ble_connect_problem_count3),
                getString(R.string.ble_connect_problem_count4),
                getString(R.string.ble_connect_problem_count5)
        };


        // 列表展开标识
        int opened = -1;

        /**
         * 绑定item布局
         *
         * @param parent
         * @param pos
         * @return
         */
        @Override
        public MainViewHolder onCreateViewHolder(ViewGroup parent, int pos) {
            // 标题

            return new MainViewHolder((ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_common_problem_, parent, false));
        }

        /**
         * 绑定数据到控件
         *
         * @param holder
         * @param pos
         */
        @Override
        public void onBindViewHolder(MainViewHolder holder, int pos) {
            final String contact = contacts[pos];
            final String info = infos[pos];
            final int img = imgs[pos];
            holder.bind(pos, contact, info, img);
        }

        /**
         * 返回列表条数
         *
         * @return
         */
        @Override
        public int getItemCount() {
            return contacts.length;
        }

        /**
         * 实例化控件等操作
         */
        public class MainViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

            public final TextView contactNameTV;
            public final TextView infos;
            public final LinearLayout list_lin;

            // 实例化
            public MainViewHolder(ViewGroup itemView) {
                super(itemView);
                contactNameTV = ((TextView) itemView.findViewById(R.id.contactName));
                infos = ((TextView) itemView.findViewById(R.id.infos));
                list_lin = ((LinearLayout) itemView.findViewById(R.id.list_lin));
                itemView.setOnClickListener(this);
            }

            // 此方法实现列表的展开和关闭
            public void bind(int pos, String name, String count, int my_img) {
//                MyLog.i(TAG,"输出001 = name = " + name);
//                MyLog.i(TAG,"输出001 = pos = " + pos);
                contactNameTV.setText(name);
                infos.setText(count);

                if (pos == opened) {
                    list_lin.setVisibility(View.VISIBLE);
                    changeAlpha(list_lin, true);
                } else {
                    list_lin.setVisibility(View.GONE);
                    changeAlpha(list_lin, false);

                }

            }

            /**
             * 为item添加点击效果,根据业务需求实现不同的效果
             * (recyclerView是不提供onItemClickListener的。所以列表的点击事件需要我们自己来实现)
             *
             * @param v
             */
            @Override
            public void onClick(View v) {
                if (opened == getLayoutPosition()) {
                    opened = -1;
                    notifyItemChanged(getLayoutPosition());
                } else {
                    int oldOpened = opened;
                    opened = getLayoutPosition();
                    notifyItemChanged(oldOpened);
                    notifyItemChanged(opened);
                }
            }
        }
    }


    void initView() {

        ((TextView) findViewById(R.id.public_head_title)).setText(getString(R.string.ble_connect_problem_title));
        findViewById(R.id.public_head_back).setOnClickListener(this);

    }


    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {

            case R.id.public_head_back:
                finish();
                break;
        }
    }

    public void changeAlpha(View v, boolean isAlphaZero) {
        if (isAlphaZero) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);//初始化操作，参数传入0和1，即由透明度0变化到透明度为1
            v.startAnimation(alphaAnimation);//开始动画
            alphaAnimation.setFillAfter(true);//动画结束后保持状态
            alphaAnimation.setDuration(1000);//动画持续时间，单位为毫秒
            isAlphaZero = false;//标识位
        } else {
            AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);//初始化操作，参数传入1和0，即由透明度1变化到透明度为0
            v.startAnimation(alphaAnimation);//开始动画
            isAlphaZero = true;//标识位
            alphaAnimation.setFillAfter(true);//动画结束后保持状态
            alphaAnimation.setDuration(1000);//动画持续时间
        }
    }


}
