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
        
        // 显示公告
        AnnouncementDialog.showAnnouncement(this);
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
                        StringBuilder resultBuilder = new StringBuilder();
                        
                        // 身高信息
                        resultBuilder.append("=== 身高信息 ===\n");
                        resultBuilder.append("当前身高: ").append(model.getData().getCurrentHeight()).append("\n");
                        resultBuilder.append("身高值: ").append(model.getData().getHeight()).append("\n");
                        resultBuilder.append("最高身高: ").append(model.getData().getMaxHeight()).append("\n");
                        resultBuilder.append("最矮身高: ").append(model.getData().getMinHeight()).append("\n");
                        resultBuilder.append("体型值: ").append(model.getData().getScale()).append("\n\n");
                        
                        // 装饰品信息
                        if (model.getAdorn() != null) {
                            resultBuilder.append("=== 装饰品信息 ===\n");
                            resultBuilder.append("斗篷: ").append(model.getAdorn().getWing() != null ? model.getAdorn().getWing() : "未穿戴").append("\n");
                            resultBuilder.append("面具: ").append(model.getAdorn().getMask() != null ? model.getAdorn().getMask() : "未穿戴").append("\n");
                            resultBuilder.append("发型: ").append(model.getAdorn().getHair() != null ? model.getAdorn().getHair() : "未穿戴").append("\n");
                            resultBuilder.append("发饰: ").append(model.getAdorn().getHat() != null ? model.getAdorn().getHat() : "未穿戴").append("\n");
                            resultBuilder.append("耳饰: ").append(model.getAdorn().getHorn() != null ? model.getAdorn().getHorn() : "未穿戴").append("\n");
                            resultBuilder.append("裤子: ").append(model.getAdorn().getBody() != null ? model.getAdorn().getBody() : "未穿戴").append("\n");
                            resultBuilder.append("鞋子: ").append(model.getAdorn().getFeet() != null ? model.getAdorn().getFeet() : "未穿戴").append("\n");
                            resultBuilder.append("面饰: ").append(model.getAdorn().getFace() != null ? model.getAdorn().getFace() : "未穿戴").append("\n");
                            resultBuilder.append("项链: ").append(model.getAdorn().getNeck() != null ? model.getAdorn().getNeck() : "未穿戴").append("\n");
                            resultBuilder.append("道具: ").append(model.getAdorn().getProp() != null ? model.getAdorn().getProp() : "未穿戴").append("\n\n");
                        }
                        
                        // 其他信息
                        resultBuilder.append("=== 其他信息 ===\n");
                        resultBuilder.append("剩余查询次数: ").append(model.getBalance());
                        
                        String result = resultBuilder.toString();
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