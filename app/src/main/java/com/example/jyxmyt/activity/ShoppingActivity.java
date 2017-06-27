package com.example.jyxmyt.activity;

import android.content.Intent;
import android.content.SyncStatusObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jyxmyt.R;
import com.example.jyxmyt.adapter.DZGWGridViewAdapter;
import com.example.jyxmyt.entity.Entity;
import com.example.jyxmyt.entity.Finger;
import com.example.jyxmyt.entity.ShopReq;
import com.example.jyxmyt.entity.Shopping;
import com.example.jyxmyt.http.RetrofitClient;
import com.example.jyxmyt.util.CommonUtil;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 大帐购物
 *
 * @author 123
 */
public class ShoppingActivity extends BaseActivity implements OnClickListener {

    private ImageView home_but; // 返回主页
    private ImageView return_but; // 返回上一步
    private Spinner gw_lx; // 类型
    private XRecyclerView rv; // 物品显示列表
    private CommonAdapter commonAdapter;
    private List<Shopping.RowsBean> rowsBeanList = new ArrayList<>();//商品列表

//    private DZGWListViewAdapter dzgwListViewAdapter;

    private int pageIndex = 1;
    private String shopType = "";
    private Finger finger;
    private double limit = 0;

    TextView tvNmae, tvNumber, tvMoney;
    Button btnBuy;//购物车结算
     private TextView limit_money;
    ArrayList<Shopping.RowsBean> shops = new ArrayList<>();//购物车

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getExtras() != null)
            finger = (Finger) getIntent().getExtras().getSerializable("finger");

        initView();
        getShops();

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Shopping.RowsBean bean) {/* Do something */

//        for (Shopping.RowsBean rowsBean : rowsBeanList) {
//            if (rowsBean.getId() == bean.getId())
//                rowsBeanList.remove(rowsBean);
//
//        }
        for (Shopping.RowsBean rowsBean : shops)
            if (rowsBean.getId() == bean.getId())
                shops.remove(rowsBean);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("wh","onResume");
        commonAdapter.notifyDataSetChanged();
    }

    @Override
    public void initView() {
        setContentView(R.layout.shopping_activity);
        return_but = (ImageView) findViewById(R.id.return_but);
        return_but.setOnClickListener(this);
        home_but = (ImageView) findViewById(R.id.home_but);
        home_but.setOnClickListener(this);

        tvNmae = (TextView) findViewById(R.id.tv_name);//对象姓名
        tvNumber = (TextView) findViewById(R.id.tv_number);//对象编号
        tvMoney = (TextView) findViewById(R.id.tv_money);//账户余额
        btnBuy = (Button) findViewById(R.id.btn_buy);//购物车结算
        limit_money=(TextView) findViewById(R.id.limit_money);//本月可用余额。
        btnBuy.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ShoppingActivity.this, BuyActivity.class);
                intent.putExtra("shops", shops);
                intent.putExtra("limit", limit);
                String number = tvNumber.getText().toString();
                intent.putExtra("kssPrisonerNum", number);
                startActivity(intent);
            }
        });


        gw_lx = (Spinner) findViewById(R.id.gw_lx);
        gw_lx.setAdapter(new com.example.jyxmyt.adapter.SpinnerAdapter(this,
                getResources().getStringArray(R.array.gw_lx)));

        gw_lx.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                shopType = position + "";
                pageIndex=1;
                getShops();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        rv = (XRecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new GridLayoutManager(this, 3));//显示4列
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setLoadingListener(new XRecyclerView.LoadingListener() {
            //刷新
            @Override
            public void onRefresh() {
                pageIndex = 1;
                getShops();
            }
           //加载更多
            @Override
            public void onLoadMore() {
                pageIndex++;
                getShops();
            }
        });
            //以下是商品列表
        commonAdapter = new CommonAdapter<Shopping.RowsBean>(this, R.layout.gw_grid_item, rowsBeanList) {
            @Override
            protected void convert(ViewHolder holder, final Shopping.RowsBean rowsBean, int position) {

                try {
                    ImageView ivImage = holder.getView(R.id.wp_image);
                    final Button btn = holder.getView(R.id.buy_but);

                    holder.setText(R.id.wp_name, "名称：" + rowsBean.getShopName());
                    holder.setText(R.id.wp_price, "价格：" + rowsBean.getShopPrice());
                    holder.setText(R.id.wp_standard, "规格：" + rowsBean.getShopStandard());

                    //判断是否缺货，0为缺货
                    if(rowsBean.getLimitedNumber()==0 || rowsBean.getBuyShopNum()>=rowsBean.getLimitedNumber()){
                        btn.setEnabled(false);
                    }else{
                        btn.setBackground(getResources().getDrawable(R.drawable.dzgw_but_up));
                    }

                    for (Shopping.RowsBean bean : shops) {
                        if (bean.getId() == rowsBean.getId()) {
                            btn.setBackgroundColor(Color.GRAY);
                        }

                    }
                    //添加按钮加入购物车
                    btn.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!shops.contains(rowsBean))
                                shops.add(rowsBean);
                            btn.setBackgroundColor(Color.GRAY);
                        }
                    });
                    //点击图片加入购物车
                    ivImage.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!shops.contains(rowsBean))
                                shops.add(rowsBean);
                            btn.setBackgroundColor(Color.GRAY);
                        }
                    });

                    if (rowsBean.getShopPicture() != null && !rowsBean.getShopPicture().equals("")) {
//                        byte[] data = Base64.decode(rowsBean.getShopPicture(), Base64.DEFAULT);
                        String url = RetrofitClient.getBasePictureUrl() + rowsBean.getShopPicture();
                        String s=url.replace("\\","/");
                        Glide.with(ShoppingActivity.this).load(s).into(ivImage);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };

        rv.setAdapter(commonAdapter);


    }


    @Override
    public void onClick(View v) {

        if (v == return_but) {
            finish();
        } else if (v == home_but) {
            finish();
        }
    }

    private void getShops() {
        ShopReq shopReq = new ShopReq();
        shopReq.setPage(pageIndex + "");
        shopReq.setKssPrisonerNum(finger.getKssPrisonerNum());
        shopReq.setShopType(shopType);
        shopReq.setShopName("");
        shopReq.setStartPrice(0);
        shopReq.setEndPrice(0);

        if (checkNetwork()){
            RetrofitClient.getCommonApi().getShops(shopReq)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Entity<Shopping>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            rv.refreshComplete();
                            rv.loadMoreComplete();

                            doError(e);
                        }

                        @Override
                        public void onNext(Entity<Shopping> shoppingEntity) {
                            rv.refreshComplete();
                            rv.loadMoreComplete();

                            if (checkEntity(shoppingEntity)) {
                                //本月可用余额
                                limit = shoppingEntity.getData().getUserInfo().getPurchaseQuantity()
                                        - shoppingEntity.getData().getUserInfo().getConsumeMoney();

                                List list = shoppingEntity.getData().getRows();
                                if (pageIndex == 1) {
                                    try {
                                        tvNmae.setText(shoppingEntity.getData().getUserInfo().getKssPrisonerName());
                                        tvNumber.setText(shoppingEntity.getData().getUserInfo().getKssPrisonerNum());
                                        tvMoney.setText(shoppingEntity.getData().getUserInfo().getTotalMoney() + "元");
                                        limit_money.setText(limit+"元");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    rowsBeanList.clear();
                                }
                                    rowsBeanList.addAll(list);
                                    commonAdapter.notifyDataSetChanged();

                            }else{
                                checkEntityCode(shoppingEntity);
                            }
                        }
                    });
        }
    }


}
