package com.mengze.sky;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
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
        new MaterialAlertDialogBuilder(context)
                .setTitle("📢 重要公告")
                .setMessage(ANNOUNCEMENT_TEXT)
                .setPositiveButton("我知道了", (dialog, which) -> dialog.dismiss())
                .setCancelable(true)
                .show();
    }
}