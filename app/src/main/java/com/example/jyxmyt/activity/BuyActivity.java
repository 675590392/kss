package com.example.jyxmyt.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.jyxmyt.R;
import com.example.jyxmyt.entity.Entity;
import com.example.jyxmyt.entity.Order;
import com.example.jyxmyt.entity.OrderReq;
import com.example.jyxmyt.entity.Shopping;
import com.example.jyxmyt.http.RetrofitClient;
import com.example.jyxmyt.util.CommonUtil;
import com.example.jyxmyt.util.ToastUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import org.greenrobot.eventbus.EventBus;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tanghao on 2017/3/8.
 *
 * 购物车结算页面
 *
 */

public class BuyActivity extends BaseActivity {

    List<Shopping.RowsBean> rowsBeanList;
    RecyclerView rv;
    CommonAdapter commonAdapter;
    Button btnSubmit;
    private TextView now_money;
    int number = 1;
    String kssPrisonerNum;
    double limit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        Log.d("wh","BuyActivity");
        rowsBeanList = (List<Shopping.RowsBean>) getIntent().getSerializableExtra("shops");
        kssPrisonerNum = getIntent().getExtras().getString("kssPrisonerNum", "");
        limit = getIntent().getExtras().getDouble("limit");
        //购买总价格
        now_money= (TextView) findViewById(R.id.now_money);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(onClickListener);

        findViewById(R.id.return_but).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.home_but).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        commonAdapter = new CommonAdapter<Shopping.RowsBean>(this, R.layout.gw_list_item, rowsBeanList) {
            @Override
            protected void convert(ViewHolder holder, final Shopping.RowsBean rowsBean, final int position) {
                Log.d("wh","position="+position);
                try {
                    ImageView imageView = holder.getView(R.id.wp_iamge);
                    ImageView iv_delete = holder.getView(R.id.wp_cancel);
                    ImageView btnReduce = holder.getView(R.id.redudction_but);
                    ImageView btnAdd = holder.getView(R.id.add_but);
                    TextView tvNumber = holder.getView(R.id.number_edit);//数字
                    tvNumber.setText(rowsBean.getCount() + "");
                    holder.setText(R.id.wp_name, "名称：" + rowsBean.getShopName());
                    holder.setText(R.id.wp_price, "单价：" + rowsBean.getShopPrice());
                    holder.setText(R.id.wp_standard, "规格：" + rowsBean.getShopStandard());

                    //double prices=0;对消费
                    BigDecimal bigDecimali = new BigDecimal(Double.toString(0));
                        for (int rowsBean2 = 0; rowsBean2 < rowsBeanList.size(); rowsBean2++) {
                            BigDecimal bigDecimali1 = new BigDecimal(Double.toString(rowsBeanList.get(rowsBean2).getShopPrice()));
                            BigDecimal bigDecimali2 = new BigDecimal(Double.toString(rowsBeanList.get(rowsBean2).getCount()));
                            BigDecimal bigDecimali3 = new BigDecimal(Double.toString(bigDecimali1.multiply(bigDecimali2).doubleValue()));
                            bigDecimali = bigDecimali.add(bigDecimali3);
                        }
                    Log.d("wh","消费金额="+bigDecimali.doubleValue());
                    now_money.setText("您已消费购买了" + bigDecimali.doubleValue() + "元");
                    //添加购物数量
                    btnAdd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //判断是否到达购买要求
                            //1、判断如果购买数量达到限制数量   则点击事件失效
                            //2、
                            if (rowsBean.getCount() >= rowsBean.getLimitedNumber() || rowsBean.getCount() >= (rowsBean.getLimitedNumber()-rowsBean.getBuyShopNum()))
                                return;

                            int number = rowsBean.getCount()+1;
                            rowsBeanList.get(position).setCount(number);

                            //rowsBeanList.get(position).setLimitedNumber(number);//new
                            notifyDataSetChanged();
                        }
                    });
                    //减少购物数量
                    btnReduce.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (rowsBean.getCount() == 1)
                                return;
                            rowsBeanList.get(position).setCount(rowsBean.getCount()-1);
                            notifyDataSetChanged();

                        }
                    });
                    //删除加入购物车的物品
                    iv_delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (rowsBeanList.contains(rowsBean)) {
                                rowsBeanList.remove(rowsBean);
                                EventBus.getDefault().post(rowsBean);
                                if (rowsBeanList.size()==0){
                                    now_money.setText("您已消费购买了" + 0 + "元");
                                }
                                notifyDataSetChanged();
                            }
                        }
                    });
                    if (rowsBean.getShopPicture() != null && !rowsBean.getShopPicture().equals("")) {
//                        byte[] data = Base64.decode(rowsBean.getShopPicture(), Base64.DEFAULT);

                        String url = RetrofitClient.getBasePictureUrl() + rowsBean.getShopPicture();
                        String s=url.replace("\\","/");
                        Glide.with(BuyActivity.this).load(s).into(imageView);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        };
        rv.setAdapter(commonAdapter);

    }

    @Override
    public void initView() {}


    private boolean compare(double price) {
        if (limit >= price) {
            return true;
        } else {
            ToastUtil.showToast(BuyActivity.this, "已超过限额");
            return false;
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            double price = 0;
            final OrderReq orderReq = new OrderReq();
            orderReq.setKssPrisonerNum(kssPrisonerNum);

            List<Order> orders = new ArrayList<>();
            for (Shopping.RowsBean rowsBean : rowsBeanList) {
                Order order = new Order();
                order.setShopUnit(rowsBean.getShopUnit());
              //  order.setLimitedNumber(rowsBean.getLimitedNumber());
                order.setLimitedNumber(rowsBean.getCount());//
                order.setShopName(rowsBean.getShopName());
                order.setShopPrice(rowsBean.getShopPrice());
                order.setShopType(rowsBean.getShopType());
                order.setShopCode(rowsBean.getShopCode());
                order.setShopStandard(rowsBean.getShopStandard());
                price += rowsBean.getShopPrice() * rowsBean.getCount();

                orders.add(order);
            }
            orderReq.setData(orders);
            if(price==0){
                new AlertDialog.Builder(BuyActivity.this)
                        .setTitle("提示")
                        .setMessage("请确认是否已选择物品")
                        .setCancelable(false)
                        .setPositiveButton("确定", null)
                        .show();
                return;
            }
          else  if (compare(price))//比较价格

                new AlertDialog.Builder(BuyActivity.this)
                        .setTitle("提示")
                        .setMessage("订单是否提交？")
                        .setCancelable(false)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                submitOrders(orderReq);

                            }
                        })
                         .setNegativeButton("取消",null)
                        .show();
        }

        private void submitOrders(OrderReq orderReq) {
            RetrofitClient.getCommonApi().submitOrders(orderReq)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Entity>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            doError(e);
                        }

                        @Override
                        public void onNext(Entity entity) {
                            if (checkEntity(entity)) {
                                Intent intent = new Intent(BuyActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                CommonUtil.finishAll();
                                finish();
                            }
                        }
                    });
        }
    };



}
