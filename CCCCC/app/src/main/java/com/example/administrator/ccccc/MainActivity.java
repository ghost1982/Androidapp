package com.example.administrator.ccccc;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Adaptersanpham ada;
    private List<sanpham> splist=new ArrayList<>();
    private RecyclerView re;
    public String url="http://192.168.20.9/quanao/public/quanao";
    public  String urlImage="http://192.168.20.9/quanao/public/sanpham_img/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        re=(RecyclerView)findViewById(R.id.recycler);

        ada=new Adaptersanpham(splist,getApplicationContext());
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        re.setLayoutManager(layoutManager);
        re.setItemAnimator(new DefaultItemAnimator());
        re.setAdapter(ada);
        //callsp();
        new loadContent().execute(url);

    }
    public  class loadContent extends AsyncTask<String,Integer,String>{

        @Override
        protected String doInBackground(String... params) {
            func c=new func();
            String content=c.docNoiDung_Tu_URL(params[0]);
            return content;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            XMLDOMParser parser=new XMLDOMParser();
            Document doc=parser.getDocument(s);
            NodeList nodeList=doc.getElementsByTagName("item");
            for(int i=0;i<nodeList.getLength();i++){
                Node tempnode=nodeList.item(i);
                if(tempnode.getNodeType()==Node.ELEMENT_NODE){
                    Element element=(Element)tempnode;
                    int id=Integer.parseInt(parser.getValue(element,"ID"));
                    String tensp=parser.getValue(element,"tenSp");
                    String hinh=parser.getValue(element,"Hinh");
                    String img=urlImage+hinh;
                    sanpham a=new sanpham(id,tensp,img);
                    splist.add(a);
                    ada.notifyDataSetChanged();
                }
            }
        }
    }
    public  void callsp(){

        sanpham sp=new sanpham(2000,"AAAAAAA","BBBBBBB");
        splist.add(sp);
        sp=new sanpham(2000,"AAAAAAA","CCCCCCCCCC");
        splist.add(sp);
        sp=new sanpham(2000,"AAAAAAA","DDDDDDDDD");
        splist.add(sp);
    }

}
