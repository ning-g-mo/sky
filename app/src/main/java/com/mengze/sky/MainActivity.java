package com.mengze.sky;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.mengze.sky.model.ApifoxModel;
import com.mengze.sky.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText editTextCode;
    private MaterialButton buttonQuery;
    private MaterialButton buttonCopy;
    private MaterialTextView textViewResult;
    private String lastResult = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextCode = findViewById(R.id.editTextCode);
        buttonQuery = findViewById(R.id.buttonQuery);
        buttonCopy = findViewById(R.id.buttonCopy);
        textViewResult = findViewById(R.id.textViewResult);

        buttonQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = editTextCode.getText().toString().trim();
                if (!code.isEmpty()) {
                    queryHeight(code);
                } else {
                    Toast.makeText(MainActivity.this, "请输入好友码", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyToClipboard();
            }
        });
    }

    private void queryHeight(String code) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.ikun001.top/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<ApifoxModel> call = apiService.getHeight("one_d627d1guuid9qg12", code);

        call.enqueue(new Callback<ApifoxModel>() {
            @Override
            public void onResponse(Call<ApifoxModel> call, Response<ApifoxModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApifoxModel model = response.body();
                    if (model.getCode() == 200) {
                        String result = "当前身高: " + model.getData().getCurrentHeight() + "\n" +
                                "身高值: " + model.getData().getHeight() + "\n" +
                                "最高身高: " + model.getData().getMaxHeight() + "\n" +
                                "最矮身高: " + model.getData().getMinHeight() + "\n" +
                                "体型值: " + model.getData().getScale();
                        textViewResult.setText(result);
                        lastResult = result;
                        buttonCopy.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(MainActivity.this, model.getMsg(), Toast.LENGTH_SHORT).show();
                        buttonCopy.setVisibility(View.GONE);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    buttonCopy.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ApifoxModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "网络错误: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                buttonCopy.setVisibility(View.GONE);
            }
        });
    }

    private void copyToClipboard() {
        if (!lastResult.isEmpty()) {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("查询结果", lastResult);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "结果已复制到剪贴板", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "没有可复制的内容", Toast.LENGTH_SHORT).show();
        }
    }
}