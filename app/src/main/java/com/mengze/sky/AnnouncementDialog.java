package com.mengze.sky;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class AnnouncementDialog {
    
    private static final String ANNOUNCEMENT_TEXT = 
            "📢 加入我们的交流群：523800529，与更多光遇玩家互动！\n" +
            "感谢柠枺支持的APP开发！\n\n" +
            "🔑 如果你要赞助本项目，联系方式如下：\n\n" +
            "QQ：3268612069\n" +
            "微信：qwp0739\n" +
            "感谢您的支持，祝您查询愉快！";
    
    public static void showAnnouncement(Context context) {
        // 创建自定义布局
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_announcement, null);
        
        // 获取布局中的控件
        TextView tvContent = dialogView.findViewById(R.id.tv_announcement_content);
        MaterialButton btnOk = dialogView.findViewById(R.id.btn_announcement_ok);
        
        // 设置公告内容
        tvContent.setText(ANNOUNCEMENT_TEXT);
        
        // 创建对话框
        Dialog dialog = new MaterialAlertDialogBuilder(context)
                .setView(dialogView)
                .setCancelable(true)
                .create();
        
        // 设置按钮点击事件
        btnOk.setOnClickListener(v -> dialog.dismiss());
        
        // 显示对话框
        dialog.show();
    }
}