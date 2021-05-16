package com.example.newsinformation.fragment;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsinformation.R;
import com.example.newsinformation.adapter.NewsAdapter;
import com.example.newsinformation.pageTransformer.MyDecoration;
import com.example.newsinformation.po.News;
import com.example.newsinformation.util.DataTool;
import org.json.JSONException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class NewsFragment extends Fragment {
    private RecyclerView recyclerView;
    private String str;
    private List<News> newsList = new ArrayList<>();
    private NewsAdapter newsAdapter;
    private DataTool dataTool;
    private Handler handler;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       final View view = inflater.inflate(R.layout.fragment_news, container, false);
        dataTool = new DataTool();//自己封装处理数据的工具
      if(Build.VERSION.SDK_INT>9){
          StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
          StrictMode.setThreadPolicy(policy);
      }
    handler= new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what==1){
                    Bundle data = msg.getData();
                    newsList = data.getParcelableArrayList("newsList");
                    //设置UI
                    initView(view);
                    newsAdapter.notifyDataSetChanged();
                }else if(msg.what==0) {
                    Toast.makeText(getActivity(),"请求资源不成功",Toast.LENGTH_SHORT).show();
                }

            }
        };

      new Thread(new Runnable() {
          @Override
          public void run() {
              try {

                  str = dataTool.getNetData(new URL("https://gank.io/api/v2/data/category/Article/type/Android/page/1/count/10"));
                 // str = dataTool.getNetData(new URL("https://gank.io/api/search/query/listview/category/Android/count/10/page/1 "));
                  newsList = dataTool.getNewsData(str).getNewsList();
                  Message msg = new Message();
                  Bundle data = new Bundle();
                  data.putParcelableArrayList("newsList",(ArrayList<? extends Parcelable>) newsList);
                  msg.setData(data);
                  msg.what=1;
                  //发消息到主线程
                  handler.sendMessage(msg);
              }catch (IOException | JSONException e) {
                  e.printStackTrace();
              }
          }
      }).start();
        return view;
    }
//初始化界面，绑定适配器和布局管理器
    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView_news);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        newsAdapter = new NewsAdapter(newsList, getActivity());
        recyclerView.setAdapter(newsAdapter);
        recyclerView.addItemDecoration(new MyDecoration(getActivity(), MyDecoration.VERTICAL_LIST));
    }


}
