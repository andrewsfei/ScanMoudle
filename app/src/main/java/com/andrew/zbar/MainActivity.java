package com.andrew.zbar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.bertsir.zbar.Qr.ScanResult;
import cn.bertsir.zbar.QrConfig;
import cn.bertsir.zbar.QrManager;
import cn.bertsir.zbar.view.ScanLineView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_scan = findViewById(R.id.btn_scan);
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQRCode();
            }
        });
    }


    private void startQRCode() {
        int scan_type = 0;
        int scan_view_type = 0;
        int screen = 1;
        //扫描区域样式
        int line_style = ScanLineView.style_radar;
        //只有二维码：TYPE_QRCODE   条形码和二维码：TYPE_ALL
        scan_type = QrConfig.TYPE_ALL;
        scan_view_type = QrConfig.SCANVIEW_TYPE_QRCODE;
        //只有条形码
//        scan_type = QrConfig.TYPE_BARCODE;
//        scan_view_type = QrConfig.SCANVIEW_TYPE_BARCODE;

        //条形码和二维码
//        scan_type = QrConfig.TYPE_ALL;
//        scan_view_type = QrConfig.SCANVIEW_TYPE_QRCODE;
        //屏幕自动
//        screen = QrConfig.SCREEN_SENSOR;
        //屏幕横向
//        screen = QrConfig.SCREEN_LANDSCAPE;
        //屏幕竖向
        screen = QrConfig.SCREEN_PORTRAIT;
        //雷达样式（扫描区域样式）
//        line_style = ScanLineView.style_radar;
        //网格样式（扫描区域样式）
//        line_style = ScanLineView.style_gridding;
        //雷达+网格样式（扫描区域样式）
        line_style = ScanLineView.style_hybrid;
        //线形样式（扫描区域样式）
        line_style = ScanLineView.style_line;


        QrConfig qrConfig = new QrConfig.Builder()
//                .setDesText(et_qr_des.getText().toString())//扫描框下文字
                //是否显示扫描框下面文字
                .setShowDes(true)
                //显示手电筒按钮
                .setShowLight(false)
                //显示Title
                .setShowTitle(true)
                //显示从相册选择按钮
                .setShowAlbum(false)
                //是否从相册选择后裁剪图片
                .setNeedCrop(false)
                //设置扫描框颜色
                .setCornerColor(Color.parseColor("#03DAC5"))
                //设置扫描线颜色
                .setLineColor(Color.parseColor("#03DAC5"))
                //设置扫描线速度
                .setLineSpeed(QrConfig.LINE_MEDIUM)
                //设置扫码类型（二维码，条形码，全部，自定义，默认为二维码）
                .setScanType(scan_type)
                //设置扫描框类型（二维码还是条形码，默认为二维码）
                .setScanViewType(scan_view_type)
                //此项只有在扫码类型为TYPE_CUSTOM时才有效
                .setCustombarcodeformat(QrConfig.BARCODE_PDF417)
                //是否扫描成功后bi~的声音
                .setPlaySound(false)
//                .setDingPath(cb_show_custom_ding.isChecked() ? R.raw.test : R.raw.qrcode)//设置提示音(不设置为默认的Ding~)
                //是否只识别框中内容(默认为全屏识别)
                .setIsOnlyCenter(true)
//                .setTitleText(et_qr_title.getText().toString())//设置Tilte文字
                //设置状态栏颜色
                .setTitleBackgroudColor(Color.parseColor("#262020"))
                //设置Title文字颜色
                .setTitleTextColor(Color.WHITE)
                //是否开始滑块的缩放（开启手动缩放(滑块)）
                .setShowZoom(false)
                //是否开启自动缩放(实验性功能，不建议使用)
                .setAutoZoom(false)
                //是否开始双指缩放
                .setFingerZoom(true)
                //是否开启双引擎识别(仅对识别二维码有效，并且开启后只识别框内功能将失效)
                .setDoubleEngine(true)
                //设置屏幕方式
                .setScreenOrientation(screen)
                //打开相册的文字
                .setOpenAlbumText("选择要识别的图片")
                //是否连续扫描二维码
                .setLooperScan(false)
                //连续扫描间隔时间
                .setLooperWaitTime(5 * 1000)
                //扫描线样式
                .setScanLineStyle(line_style)
                //自动灯光
                .setAutoLight(false)
                //是否震动提醒
                .setShowVibrator(true)
                .create();
        QrManager.getInstance().init(qrConfig).startScan(MainActivity.this, new QrManager.OnScanResultCallback() {
            @Override
            public void onScanSuccess(ScanResult result) {
                Log.e("onScanSuccess", "onScanSuccess: " + result);
                Toast.makeText(MainActivity.this, "内容：" + result.getContent(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}