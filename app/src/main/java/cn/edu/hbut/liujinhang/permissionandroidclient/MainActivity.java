package cn.edu.hbut.liujinhang.permissionandroidclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import cn.edu.hbut.liujinhang.permissionandroidclient.adapter.PermissionAdapter;
import cn.edu.hbut.liujinhang.permissionandroidclient.entity.User;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.viewPermissions);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Button button = this.findViewById(R.id.btnCheck);
        button.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Clicked", Toast.LENGTH_SHORT).show();

            AsyncTask task = new AsyncTask() {

                @Override
                protected Object doInBackground(Object[] objects) {

                    try {
                        // 安卓模拟器本身有自己的子网，在该子网中计算机本身（上层网络）的地址被映射到10.0.2.2，或者使用计算机自身的IP访问
                        URL endpoint = new URL("http://10.0.2.2:8080/user/" + objects[0].toString());
                        HttpURLConnection connection = (HttpURLConnection) endpoint.openConnection();

                        if (connection.getResponseCode() == 200) {
                            // Success
                            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                            StringBuilder sb = new StringBuilder();

                            String line;
                            while ((line = br.readLine()) != null) {
                                sb.append(line + "\n");
                            }
                            br.close();

                            User user = new Gson().fromJson(sb.toString(), User.class);

                            return user;

                        } else {
                            // Error
                        }

                        connection.disconnect();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Object o) {
                    super.onPostExecute(o);
                    User user = (User)o;
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.viewPermissions);
                    PermissionAdapter adapter = new PermissionAdapter(user.getPermissions());
                    recyclerView.setAdapter(adapter);
                }

            };

            String id = ((EditText)this.findViewById(R.id.txtId)).getText().toString();
            task.execute(id);

        });

    }
}