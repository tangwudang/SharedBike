package com.lishu.bike.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.TextView;

import com.lishu.bike.R;
import com.lishu.bike.http.HttpBase;
import com.lishu.bike.http.HttpLoader;
import com.lishu.bike.model.AppInfoDetailModel;
import com.lishu.bike.model.BaseModel;
import com.lishu.bike.utils.TimeUtil;
import com.lishu.bike.utils.ToastUtil;

public class AppInfoDetailActivity extends BaseActivity{
    private TextView info_title, author, time;
    private WebView webView;
    private String testData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appinfo_detail);

        initView();

        int infoId = getIntent().getIntExtra("infoId", -1);
        getAppInfoDetail(infoId);

        webView.getSettings().setDefaultTextEncodingName("utf-8");
    }

    private void initView() {
        setTopTitle("工作动态详情");

        info_title = findViewById(R.id.info_title);
        author = findViewById(R.id.author);
        time = findViewById(R.id.time);
        webView  = findViewById(R.id.webView);
    }

    /**
     * 获取详情
     */
    private void getAppInfoDetail(int infoId) {
        HttpLoader.getAppInfoDetail(infoId, new HttpBase.IResponseListener() {
            @Override
            public void onResponse(BaseModel model) {
                if (model == null) {
                    ToastUtil.showShort(R.string.please_check_network);
                    return;
                }
                if (!model.success()) {
                    ToastUtil.showShort(getString(R.string.get_data_fail) + model.getResMsg());
                    return;
                }

                AppInfoDetailModel appInfoDetail = (AppInfoDetailModel) model;
                if(appInfoDetail != null){
                    info_title.setText(appInfoDetail.getInfoTitle());
                    author.setText(appInfoDetail.getPublishUserName());
                    String publishTime = appInfoDetail.getPublishTime();
                    if(!TextUtils.isEmpty(publishTime)) {
                        time.setText(TimeUtil.getMessageTime(publishTime));
                    }
                    //String htmlData = appInfoDetail.getInfoContent();
                    webView.loadData(appInfoDetail.getInfoContent(), "text/html; charset=UTF-8", null);
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()){
            webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        testData = "\t\n" +
                "\t <html lang=\"en\">\n" +
                "\n" +
                "\t <head>\n" +
                "\n" +
                "\t \t<meta charset=\"utf-8\"/>\n" +
                "\n" +
                "\t \t<title>快乐公交管理系统</title>\n" +
                "\n" +
                "\t \t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n" +
                "\n" +
                "\t \t<meta name=\"description\" content=\"快乐公交管理系统\"/>\n" +
                "\n" +
                "\t \t<meta name=\"author\" content=\"Trigger\" />\n" +
                "\n" +
                "\t </head>\n" +
                "\n" +
                "\t <body>\n" +
                "\n" +
                "\t <p>1、如何手动上下调整卷画<br />\n" +
                "\t 在没有通电的情况下，按住&ldquo;时间+&rdquo;或&ldquo;时间一&rdquo;键不放，再接通电源后放手，出现&ldquo;---&rdquo;，进入手动上、下卷画状态，按&ldquo;时间+&rdquo;画面住上卷，按&ldquo;时间&mdash;&rdquo;画面往下卷。<br />\n" +
                "\t &nbsp;<br />\n" +
                "\t 2、如何调整画面幅数<br />\n" +
                "\t 在没有按住任何按键的情况下，接通电源后，按住&ldquo;设置&rdquo;键3秒后，会进入调整画面幅数状态，再按&ldquo;设置&rdquo;键来调整画面幅数调整自己需要的画面幅数后，放手3秒后默认进入调整后的工作状态。<br />\n" +
                "\t &nbsp;<br />\n" +
                "\t 3、如何调整每张画面停留时间<br />\n" +
                "\t 在正常工作状态下，按&ldquo;设置&rdquo;键，选择需要更改停留时间的画面幅数，再按&ldquo;时间+&rdquo;或&ldquo;时间&mdash;&rdquo;调整需要停留时间。<br />\n" +
                "\t &nbsp;<br />\n" +
                "\t 4、如何调整画面上下白边的数值<br />\n" +
                "\t 在没有通电情况下，同时按住&ldquo;时间+&rdquo;和&ldquo;设置&rdquo;键不放，再接通电源后放手，进入调整上部白边值的状态，再按&ldquo;时间+&rdquo;或&ldquo;时间&mdash;&rdquo;来调整上部白边相对应的数值。如要调整下部白边值，先同时按&ldquo;时间&mdash;&rdquo;和&ldquo;设置&rdquo;键不放，再接通电源后放手，进入调整下部白边值的状态，再按&ldquo;时间+&rdquo;或&ldquo;时间&mdash;&rdquo;来调整下部白边相对应的数值。<br />\n" +
                "\t &nbsp;<br />\n" +
                "\t 备注：调整上下白边的数值最低不能3.0。如：画面上部白边没有预留，数值为0，在调整上部白边数值的时候默认为3.0<br />\n" +
                "\t &nbsp;<br />\n" +
                "\t 5、设定定时停机后指定停留画面<br />\n" +
                "\t 在正常工作情况下，需要所设定的画面在换下一张画面的时候，瞬间拉住画面停留一下就可以了。</p>\n" +
                "\t <p style=\"margin-left:18pt\"><strong style=\"color:rgb(0, 0, 0); line-height:1.6\">百控厂家联系方式：</strong><strong style=\"color:rgb(0, 0, 0); line-height:1.6\">&nbsp;&nbsp;</strong><strong style=\"color:rgb(0, 0, 0); line-height:1.6\">刘卫军</strong><strong style=\"color:rgb(0, 0, 0); line-height:1.6\">&nbsp;&nbsp; 13761179621&nbsp;&nbsp; 13788996953</strong></p>\n" +
                "\t </body>\n" +
                "\t </html>";
        webView.loadData(testData, "text/html; charset=UTF-8", null);
    }
}
