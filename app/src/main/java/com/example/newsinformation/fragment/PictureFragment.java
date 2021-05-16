package com.example.newsinformation.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.example.newsinformation.R;
import com.example.newsinformation.adapter.PictureAdapter;
import com.example.newsinformation.po.Picture;
import com.example.newsinformation.util.DataTool;
import com.example.newsinformation.util.GridItemDivider;
import com.example.newsinformation.util.HttpUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class PictureFragment extends Fragment {
    private RecyclerView recyclerView;
    DataTool dataTool = new DataTool();
    private View view;
    private PictureAdapter pictureAdapter ;
    private RefreshLayout refreshLayout;
    private final static int GET_PICTURES = 1;
    private final static int NO_MORE = 2;
    private String url = "https://gank.io/api/v2/data/category/Girl/type/Girl/page/";
    int pageCount = 1;
    int pageSize = 10;
    private List<Picture> pictureList=new ArrayList<>();
    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case GET_PICTURES:
                    pictureAdapter.notifyDataSetChanged();//是通过Handler 设置notifyDataSetChanged方式来动态更新recyclerView
                    break;
                case NO_MORE:
                    Toast.makeText(getContext(), "没有更多资源了！", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dataTool = new DataTool();
        view = inflater.inflate(R.layout.fragment_picture, container, false);
        initData();
        initview();
        return view;
    }


    private void initview() {
        refreshLayout = view.findViewById(R.id.srl_pic);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        recyclerView = view.findViewById(R.id.recyclerView_picture);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        GridItemDivider divider = new GridItemDivider(getContext());
        recyclerView.addItemDecoration(divider);//添加分隔线
       // ((SimpleItemAnimator)recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);//取消刷新时的动画
        ((DefaultItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        pictureAdapter = new PictureAdapter(pictureList,getContext());
        recyclerView.setAdapter(pictureAdapter);//设置适配器

        //上拉加载更多
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pictureList.clear();
                pageCount = pageCount + 1;
                getPictures();
                refreshLayout.finishLoadMore(true);
            }
        });

        //下拉刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pictureList.clear();
                queryFromServer(url + pageCount + "/count/" + pageSize);
                refreshLayout.finishRefresh(true);
            }
        });
    }
    private void initData() {
        getPictures();
//        Bundle bundle = getArguments();
//        String type =null;
//        if (bundle!=null){
//            type = bundle.getString("type");
//        }
//        if (type!=null && type.equals("collect")){
//            pictureList.clear();
//            PictureCollectController pictureCollectController=new PictureCollectController(getContext());
//            PictureCollect pictureCollect=new PictureCollect();
//            String pictureUrl=pictureCollect.getUrl();
//            Log.d("PictureFragment", "n");
//            List<Picture> pictures=pictureCollectController.findCollect(pictureUrl);
//            pictureList.addAll(pictures);
            /*  List<PictureCollect> pictureCollectList= pictureCollectController.findSelected(pictureUrl);*/
//            refreshLayout.setEnableLoadMore(false);
//            refreshLayout.setEnableRefresh(false);
//            pictureAdapter.notifyDataSetChanged();
//        } else {
//
//        }
    }
    //加载图片
    private void getPictures() {
        List<Picture> pictures = new ArrayList<>();
        if (pictures.size() > 0 && pictures.size() == pageSize) {
            pictureList.clear();
            pictureList.addAll(pictures);
            pictureAdapter.notifyDataSetChanged();
        } else {
            queryFromServer(url + pageCount + "/count/" + pageSize);
        }
    }

    //从服务器获取数据
    private void queryFromServer(String url) {
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();//对异常情况进行处理
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData = response.body().string();
                pictureList.clear();
                List<Picture> pictures = new ArrayList<>();
                pictures.clear();
                pictures= dataTool.getPictureList(responseData);//将服务器获取的数据解析
                Log.i("pictures", String.valueOf(pictureList.size()));//打印pictures的长度
                if(pictures.size()>0){
                    pictureList.clear();
                    pictureList.addAll(pictures);
                    handler.sendEmptyMessage(GET_PICTURES);
                }else {
                    handler.sendEmptyMessage(NO_MORE);
                }
            }
        });
    }
}