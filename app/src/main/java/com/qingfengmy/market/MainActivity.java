package com.qingfengmy.market;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    ListView listView;

    List<String> packages = new ArrayList<>();
    List<String> names = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        editText = (EditText) findViewById(R.id.packageName);
        listView = (ListView) findViewById(R.id.listView);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Snackbar.make(view, "请输入包名", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    getMarkets();
                }
            }
        });

    }

    public void getMarkets() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.APP_MARKET");
        PackageManager pm = this.getPackageManager();
        List<ResolveInfo> infos = pm.queryIntentActivities(intent, 0);
        int size = infos.size();
        for (int i = 0; i < size; i++) {
            ActivityInfo activityInfo = infos.get(i).activityInfo;
            String packageName = activityInfo.packageName;
            packages.add(packageName);
            names.add(getMarketName(activityInfo.name));

        }

        listView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, names));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("market://details?id=" + editText.getText().toString());
                intent.setData(uri);
                intent.setPackage(packages.get(position));
                startActivity(intent);
            }
        });
    }

    private String getMarketName(String name) {
        if (name.contains("com.qihoo.appstore")){
            return "360市场";
        }else if (name.contains("com.hiapk.marketpho")){
            return "安卓市场";
        }else if (name.contains("com.wandoujia.phoenix2")){
            return "豌豆荚市场";
        }else if (name.contains("com.tencent")){
            return "应用宝";
        }else if (name.contains("com.baidu")){
            return "百度市场";
        }else if (name.contains("com.yingyonghui")){
            return "应用汇";
        }else if (name.contains("com.dragon")){
            return "91手机助手";
        }else if (name.contains("com.nduoa.nmarket")){
            return "N多手机助手";
        }else if (name.contains("com.eoe")) {
            return "优亿市场";
        }else if (name.contains("cn.goapk")){
            return "安智市场";
        }else if (name.contains("com.android")){
            return "google android market";
        }
        return name;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_1) {
            editText.setText("");
            return true;
        }else if (id == R.id.action_2){
            editText.setText("");
            return true;
        }else if (id == R.id.action_3){
            editText.setText("");
            return true;
        }else if (id == R.id.action_4){
            editText.setText("");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
