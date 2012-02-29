 
　　package cn.com.chenzheng_java;
　　import java.io.File;
　　import android.app.Activity;
　　import android.os.Bundle;
　　import android.os.Environment;
　　import android.os.StatFs;
　　import android.util.Log;
　　import android.view.View;
　　import android.view.View.OnClickListener;
　　import android.widget.Button;
　　import android.widget.ProgressBar;
　　import android.widget.Toast;
　　public class SDCardActivity extends Activity implements OnClickListener {
　　String result = "SDCard容量相关信息：/n";
　　ProgressBar progressBar ;
　　Button button;
　　@Override
　　protected void onCreate(Bundle savedInstanceState) {
　　super.onCreate(savedInstanceState);
　　setContentView(R.layout.sdcard);
　　progressBar = (ProgressBar) findViewById(R.id.progressBar_sdcard);
　　button = (Button) findViewById(R.id.button_sdcard);
　　button.setOnClickListener(this);
　　}
　　private void showSDCardSize(){
　　progressBar.setProgress(0);
　　File sdcard = Environment.getExternalStorageDirectory();
　　/**
　　* 我们可以通过StatFs访问文件系统的空间容量等信息
　　*/
　　StatFs statFs = new StatFs(sdcard.getPath());
　　/**
　　* statFs.getBlockSize可以获取当前的文件系统中，一个block所占有的字节数
　　*/
　　int blockSize = statFs.getBlockSize();
　　/**
　　* statFs.getAvaliableBlocks方法可以返回尚未使用的block的数量
　　*/
　　int avaliableBlocks = statFs.getAvailableBlocks();
　　/**
　　* statFs.getBlockCount可以获取总的block数量
　　*/
　　int totalBlocks = statFs.getBlockCount();
　　result+="/n 尚未被使用的空间大小："+avaliableBlocks*blockSize+"byte";
　　result+="/n 总空间大小："+totalBlocks*blockSize+"byte";
　　float a = (float)avaliableBlocks/totalBlocks;
　　int b = Integer.valueOf(Float.valueOf(a*100).toString().substring(0,2));
　　progressBar.setProgress(90);
　　Log.i("通知", result);
　　Toast.makeText(this, b+" "+result, Toast.LENGTH_LONG).show();
　　}
　　@Override
　　public void onClick(View v) {
　　showSDCardSize();
　　}
　　}