package com.example.administrator.ccccc;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Main2Activity extends AppCompatActivity {
    TextView tw;
    private String url="http://192.168.20.9/quanao/public/quanao_details/";
    private String urldetail;
    private String urlImage="http://192.168.20.9/quanao/public/sanpham_img/";
    //private String tensp,mota,gia,kichthuoc,hinh,img;
    TextView tensp,mota,gia,kichthuoc,hinh;
    ImageView img;
    Button btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tensp=(TextView)findViewById(R.id.tensp);
        mota=(TextView)findViewById(R.id.mota);
        gia=(TextView)findViewById(R.id.gia);
        kichthuoc=(TextView)findViewById(R.id.kichthuoc);
        img=(ImageView) findViewById(R.id.img);
        btnback=(Button)findViewById(R.id.back);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mh1=new Intent(Main2Activity.this,MainActivity.class);
                startActivity(mh1);
            }
        });
        Bundle db=getIntent().getExtras();
        if(db!=null){
            urldetail=url+db.getString("ID");
        }
        new loadsp_detail().execute(urldetail);
    }
    public class loadsp_detail extends AsyncTask<String,Integer,String>{

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
                //Node tempnode=nodeList.item(i);
                //if(tempnode.getNodeType()==Node.ELEMENT_NODE){
                    Element element=(Element)nodeList.item(i);
                    //int id=Integer.parseInt(parser.getValue(element,"ID"));
                try {
                    tensp.setText(parser.getValue(element, "Tensp"));
                    mota.setText(parser.getValue(element, "Mota"));
                    gia.setText(parser.getValue(element, "Gia"));
                    kichthuoc.setText(String.valueOf(parser.getValue(element, "KichThuoc")));
                    String hinh = parser.getValue(element, "Hinh");
                    //img=urlImage+hinh;
                    func getimage = new func();
                    img.setImageBitmap(getimage.ViewImage(urlImage + hinh));
                }
                catch (Exception ex)
                {
                    Log.e("Error: ", ex.getMessage(), ex);

                }

                //}
            }
        }

    }
}
