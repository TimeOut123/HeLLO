package com.example.newsinformation.util;
import android.util.Log;

import com.example.newsinformation.po.News;
import com.example.newsinformation.po.NewsContent;
import com.example.newsinformation.po.NewsData;
import com.example.newsinformation.po.Picture;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DataTool {
    public String getNetData(URL connectURL) throws IOException {
        String result = "";
        //用来取得返回的字符串
        HttpURLConnection connection = (HttpURLConnection) connectURL.openConnection();
        connection.setRequestMethod("GET");
        if(connection.getResponseCode()==200){
            Log.i("ljh","链接成功");
            InputStream is = connection.getInputStream();
            //把数据流中的json数据 转换成字符串
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len = -1;
            byte[] buff = new byte[1024];
            while((len=is.read(buff))!=-1){
                bos.write(buff,0,len);
            }
            result = new String(bos.toByteArray());
        }
        return result;
    }
    public List<News> getNewses(List<NewsContent> newsContents) throws IOException,JSONException{
        List<News> newsList = new ArrayList<>();
        for (NewsContent newsContent: newsContents) {
            String str = getNetData(new URL(newsContent.getUrl()));
            News news = getNews(str);
            newsList.add(news);
        }
        return newsList;
    }

    public News getNews(String str) throws JSONException {
        News news = new News();
        JSONObject jsonObject2 = new JSONObject(str);
        String str2 = jsonObject2.getString("data");
        JSONObject jsonObject = new JSONObject(str2);
        news.set_id(jsonObject.optString("_id"));
        news.setAuthor(jsonObject.optString("author"));
        news.setPublishedAt(jsonObject.optString("publishedAt"));
        news.setDesc(jsonObject.optString("desc"));
        news.setTitle(jsonObject.optString("title"));
        news.setUrl(jsonObject.optString("url"));
        Log.i("myLog",news.toString());
        return news;
    }
    public NewsData getNewsData(String str) throws JSONException {
        JSONObject jsonObject = new JSONObject(str);
        String str2 = jsonObject.getString("data");
        List<News> newsList = getNewsList(str2);
        NewsData newsData = new NewsData();
        newsData.setNewsList(newsList);
        return newsData;
    }

    public List<News> getNewsList(String str) throws JSONException {
        List<News> newsList = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(str);
        for (int i = 0; i < jsonArray.length(); i++) {
            News news = new News();
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            news.set_id(jsonObject.optString("_id"));
            news.setAuthor(jsonObject.optString("author"));
            news.setPublishedAt(jsonObject.optString("publishedAt"));
            news.setDesc(jsonObject.optString("desc"));
            news.setTitle(jsonObject.optString("title"));
            news.setUrl(jsonObject.optString("url"));
            newsList.add(news);
        }
        return newsList;
    }



    /**
     * 解析图片
     * @param jsonData
     * @return
     */
    public List<Picture> getPictureList(String jsonData){
        List<Picture> pictureList = new ArrayList<>();
        try{
                JSONObject json=new JSONObject(jsonData);
                JSONArray data = json.getJSONArray("data");//将服务器返回数据传入jsonArray中
                for (int i=0;i<data.length();i++){
                    Picture picture=new Picture();
                    picture.setId(data.getJSONObject(i).getString("_id"));
                    picture.setUrl(data.getJSONObject(i).getString("url"));
                    picture.setType(data.getJSONObject(i).getString("type"));
                    picture.setCategory(data.getJSONObject(i).getString("category"));
                    picture.setLikeCounts(data.getJSONObject(i).getInt("likeCounts"));
                    pictureList.add(picture);
        }
                return pictureList;
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }







}
