package com.lishu.bike.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.content.FileProvider;

import com.lishu.bike.utils.FileUtil;
import com.lishu.bike.utils.ToastUtil;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DowloadApkTask extends AsyncTask<String, Integer, String> {
    private ProgressDialog progressDialog;
    private Context context;

    public DowloadApkTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... sUrl) {
        try {

            URL url = new URL(sUrl[0]);
            URLConnection connection = url.openConnection();
            connection.connect();
            //这将是有用的，这样你可以显示一个典型的0-100%的进度条
            int fileLength = connection.getContentLength();

            // 下载文件
            InputStream input = new BufferedInputStream(url.openStream());
            String apkPath = FileUtil.getTempPath() + "/SharedBike.apk";
            File apkFile = new File(apkPath);
            if (!apkFile.getParentFile().exists()) {
                apkFile.getParentFile().mkdirs();
            }

            OutputStream output = new FileOutputStream(apkPath);
            byte data[] = new byte[1024];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
                total += count;
                publishProgress((int) (total * 100 / fileLength));
                output.write(data, 0, count);
            }

            output.flush();
            output.close();
            input.close();

            return apkPath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (progressDialog == null){
            progressDialog = new ProgressDialog(context);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setCancelable(true);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setTitle("正在下载");
            progressDialog.setMax(100);
            progressDialog.show();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (progressDialog != null){
            progressDialog.dismiss();
        }

        if (s == null) {
            ToastUtil.showShort("下载升級包失败");
            return;
        }

        File mFile = new File(s);
        if (mFile.getName().endsWith(".apk")) {
            Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            if(Build.VERSION.SDK_INT >= 24) { //判读版本是否在7.0以上
                //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
                Uri apkUri = FileProvider.getUriForFile(context, "com.lishu.bike.fileprovider", mFile);
                //添加这一句表示对目标应用临时授权该Uri所代表的文件
                install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                install.setDataAndType(apkUri, "application/vnd.android.package-archive");
            }else {
                install.setDataAndType(Uri.fromFile(mFile), "application/vnd.android.package-archive");
            }
            context.startActivity(install);
        }
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        super.onProgressUpdate(progress);
        if (progressDialog != null){
            progressDialog.setProgress(progress[0]);
        }
    }
}
