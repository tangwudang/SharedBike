package com.lishu.bike.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.lishu.bike.R;
import com.lishu.bike.http.HttpBase;
import com.lishu.bike.http.HttpLoader;
import com.lishu.bike.model.AnalyzesModel;
import com.lishu.bike.model.BaseModel;
import com.lishu.bike.model.InspectModel;
import com.lishu.bike.model.UserModel;
import com.lishu.bike.utils.TimeUtil;
import com.lishu.bike.utils.ToastUtil;
import com.lishu.bike.widget.MyDatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AnalyzeActivity extends BaseActivity implements View.OnClickListener {
    //任务tab栏
    private final int PRESSED_TEXT_COLOR = 0xffffffff;
    private final int NORMAL_TEXT_COLOR = 0xff666666;
    private TextView dayAnalyze, monthAnalyze, yearAnalyze;
    private int curTab = 1;
    //所选日期
    private Calendar calender = Calendar.getInstance();
    private String selectedDate;//yyyymmdd
    private TextView dateTv;
    //动画时间
    private final int ANIMATE_TIME = 1000;//1秒钟
    //柱状图
    private HorizontalBarChart bar_chart;
    private float barWidth = 50f;//彩色宽度
    private float spaceForBar = 60f;//总宽度
    //饼状图
    private PieChart illegal_chart, complaint_chart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze);

        initView();
        initEvent();
        initData();
    }

    private void initView() {
        setTopTitle(R.string.analyzes);
        setTopRight(R.drawable.icon_calendar);

        dayAnalyze = findViewById(R.id.day_analyze);
        monthAnalyze = findViewById(R.id.month_analyze);
        yearAnalyze = findViewById(R.id.year_analyze);
        dateTv = findViewById(R.id.dateTv);

        bar_chart = findViewById(R.id.bar_chart);
        illegal_chart = findViewById(R.id.illegal_chart);
        complaint_chart = findViewById(R.id.complaint_chart);
    }

    private void initEvent() {
        dayAnalyze.setOnClickListener(this);
        monthAnalyze.setOnClickListener(this);
        yearAnalyze.setOnClickListener(this);
        findViewById(R.id.title_right).setOnClickListener(this);
    }

    private void initData() {
        dateTv.setText(TimeUtil.getCurDatetimeByPattern("yyyy年MM月dd日"));
        selectedDate = TimeUtil.getCurDatetimeByPattern("yyyyMMdd");
        getData(0, selectedDate);
    }

    /**
     *  type(int):(0:日,1:月,2:年)
     * 	time(String):时间（日：yyyyMMdd，月：yyyyMM，年：yyyy）
     */
    private void getData(int type, String time) {
        // just for testing, begin =======================
        setBarData(4, 2*type, 0);
        setPieData(illegal_chart, 10f, 30f*type, 30f);
        setPieData(complaint_chart, 0, 0, 0);
        // just for testing, end ==========================
        HttpLoader.getAnalyzes(type, time, new HttpBase.IResponseListener() {
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

                AnalyzesModel aModel = (AnalyzesModel) model;
                if (aModel != null) {
                    setBarData(aModel.getMobikeCount(), aModel.getOfoCount(), aModel.getHellobikeCount());

                    float helloIllegal = aModel.getHellobikeIllegalCount();
                    float ofoIllegal = aModel.getOfoIllegalCount();
                    float moIllegal = aModel.getMobikeIllegalCount();
                    float illegalTotal = helloIllegal + ofoIllegal + moIllegal;
                    setPieData(illegal_chart, helloIllegal / illegalTotal, ofoIllegal / illegalTotal, moIllegal / illegalTotal);

                    float helloComplain = aModel.getHellobikeComplainCount();
                    float ofoComplain = aModel.getOfoComplainCount();
                    float moComplain = aModel.getMobikeComplainCount();
                    float complainTotal = helloIllegal + ofoIllegal + moIllegal;
                    setPieData(complaint_chart, helloComplain / complainTotal, ofoComplain / complainTotal, moComplain / complainTotal);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.day_analyze:
                curTab = 1;
                setTabBackground(curTab);
                dateTv.setText(TimeUtil.getCurDatetimeByPattern("yyyy年MM月dd日"));
                selectedDate = TimeUtil.getCurDatetimeByPattern("yyyyMMdd");
                getData(0, selectedDate);
                break;
            case R.id.month_analyze:
                curTab = 2;
                setTabBackground(curTab);
                dateTv.setText(TimeUtil.getCurDatetimeByPattern("yyyy年MM月"));
                selectedDate = TimeUtil.getCurDatetimeByPattern("yyyyMM");
                getData(1, selectedDate);
                break;
            case R.id.year_analyze:
                curTab = 3;
                setTabBackground(curTab);
                dateTv.setText(TimeUtil.getCurDatetimeByPattern("yyyy年"));
                selectedDate = TimeUtil.getCurDatetimeByPattern("yyyy");
                getData(2, selectedDate);
                break;
            case R.id.title_right:
                showDatePickerDialog(curTab);
                break;
        }
    }

    private void initBarChart() {
        //设置相关属性
        bar_chart.setDrawBarShadow(false);
        bar_chart.setDrawValueAboveBar(true);
        //不显示描述
        bar_chart.getDescription().setEnabled(false);
        bar_chart.setPinchZoom(false);
        bar_chart.setDrawGridBackground(false);
        //显示Legend
        bar_chart.getLegend().setEnabled(false);
        /*Legend l = bar_chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);;*/

        bar_chart.setFitBars(true);
        bar_chart.setTouchEnabled(false);
        bar_chart.setClickable(false);
        bar_chart.animateY(ANIMATE_TIME);
    }

    private void initBarXYAxis(int moNum, int ofoNum, int helloNum){
        //显示x轴
        //bar_chart.getXAxis().setEnabled(false);
        XAxis xl = bar_chart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(false);
        xl.setTextSize(12f);
        //X轴自定义值
        xl.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if(value > -0.1 && value < 0.1){
                    return "mobike";
                }else if((spaceForBar - value) > -0.1 &&  (spaceForBar - value) < 0.1){
                    return "ofo";
                }else if((2 * spaceForBar - value) > -0.1 && (2 * spaceForBar - value) < 0.1){
                    return "hello";
                }
                return "";
            }
        });
        //显示y轴
        bar_chart.getAxisRight().setEnabled(false);
        //bar_chart.getAxisLeft().setEnabled(false);
        //y轴自定义值
        YAxis yl = bar_chart.getAxisLeft();
        yl.setDrawAxisLine(true);
        yl.setDrawGridLines(true);
        yl.setTextSize(10f);
        yl.setAxisMinimum(0f);
        if(moNum > 9 || ofoNum > 9 || helloNum > 9) {
            yl.resetAxisMaximum();
        }else{
            yl.setAxisMaximum(10f);
        }
        yl.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return ("" + (int) value);
            }
        });
    }

    private void setBarData(int moNum, int ofoNum, int helloNum) {
        initBarChart();
        initBarXYAxis(moNum, ofoNum, helloNum);

        ArrayList<BarEntry> values = new ArrayList<>();
        values.add(new BarEntry(0f, moNum));
        values.add(new BarEntry(1 * spaceForBar, ofoNum));
        values.add(new BarEntry(2 * spaceForBar, helloNum));

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.chart_mobike));
        colors.add(getResources().getColor(R.color.chart_ofo));
        colors.add(getResources().getColor(R.color.chart_hellobike));

        BarDataSet set1 = new BarDataSet(values, "");
        set1.setColors(colors);
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        BarData data = new BarData(dataSets);
        data.setValueTextSize(12f);
        data.setValueTextColor(0xff666666);
        data.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return ((int) value + "辆");
            }
        });
        data.setBarWidth(barWidth);

        bar_chart.setData(data);
    }

    private void initPieChart(PieChart chart){
        //Legend显示位置
        Legend l = chart.getLegend();
        //l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        //l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setTextSize(12f);
        //去掉描述文字
        chart.getDescription().setEnabled(false);
        //去掉图中label
        chart.setDrawEntryLabels(false);

        chart.animateY(ANIMATE_TIME, Easing.EaseInOutQuad);
    }

    private void setPieData(PieChart chart, float helloNum, float ofoNum, float moNum) {
        if((helloNum + ofoNum + moNum) < 1) {
            chart.setNoDataText("暂无数据");
            return;
        }
        initPieChart(chart);

        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(helloNum, "hello"));
        entries.add(new PieEntry(ofoNum, "ofo"));
        entries.add(new PieEntry(moNum, "mobike"));

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.chart_hellobike));
        colors.add(getResources().getColor(R.color.chart_ofo));
        colors.add(getResources().getColor(R.color.chart_mobike));

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(colors);
        PieData pieData = new PieData(dataSet);
        //显示百分比
        pieData.setDrawValues(true);
        pieData.setValueFormatter(new PercentFormatter());
        //字体大小
        pieData.setValueTextSize(12);

        //设置数据
        chart.setData(pieData);
        chart.invalidate();
    }

    private void setTabBackground(int type){
        switch (type){
            case 1:
                dayAnalyze.setTextColor(PRESSED_TEXT_COLOR);
                dayAnalyze.setBackgroundResource(R.color.tab_pressed_bgd);
                monthAnalyze.setTextColor(NORMAL_TEXT_COLOR);
                monthAnalyze.setBackgroundResource(R.color.tab_normal_bgd);
                yearAnalyze.setTextColor(NORMAL_TEXT_COLOR);
                yearAnalyze.setBackgroundResource(R.color.tab_normal_bgd);
                break;
            case 2:
                dayAnalyze.setTextColor(NORMAL_TEXT_COLOR);
                dayAnalyze.setBackgroundResource(R.color.tab_normal_bgd);
                monthAnalyze.setTextColor(PRESSED_TEXT_COLOR);
                monthAnalyze.setBackgroundResource(R.color.tab_pressed_bgd);
                yearAnalyze.setTextColor(NORMAL_TEXT_COLOR);
                yearAnalyze.setBackgroundResource(R.color.tab_normal_bgd);
                break;
            case 3:
                dayAnalyze.setTextColor(NORMAL_TEXT_COLOR);
                dayAnalyze.setBackgroundResource(R.color.tab_normal_bgd);
                monthAnalyze.setTextColor(NORMAL_TEXT_COLOR);
                monthAnalyze.setBackgroundResource(R.color.tab_normal_bgd);
                yearAnalyze.setTextColor(PRESSED_TEXT_COLOR);
                yearAnalyze.setBackgroundResource(R.color.tab_pressed_bgd);
                break;
        }
    }

    private void showDatePickerDialog(final int curTab) {
        DatePickerDialog dialog = new MyDatePickerDialog(AnalyzeActivity.this, 1,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        monthOfYear = monthOfYear + 1;
                        //修改时间显示，例如 7月8日，显示为 07月08日
                        String myMonth, myDay;
                        if(monthOfYear < 10){
                            myMonth = "0" + monthOfYear;
                        }else{
                            myMonth = "" + monthOfYear;
                        }
                        if(dayOfMonth < 10){
                            myDay = "0" + dayOfMonth;
                        }else{
                            myDay = "" + dayOfMonth;
                        }
                        if (curTab == 1) {
                            selectedDate = year + myMonth + myDay;
                            dateTv.setText(year + "年" + myMonth + "月" + myDay + "日");
                            getData(0, selectedDate);
                        } else if (curTab == 2) {
                            selectedDate = year + myMonth;
                            dateTv.setText(year + "年" + myMonth + "月");
                            getData(1, selectedDate);
                        } else if (curTab == 3) {
                            selectedDate = "" + year;
                            dateTv.setText(year + "年");
                            getData(2, selectedDate);
                        }
                    }
                },
                calender.get(Calendar.YEAR), // 传入年份
                calender.get(Calendar.MONTH), // 传入月份
                calender.get(Calendar.DAY_OF_MONTH) // 传入天数
        );
        dialog.show();

        DatePicker dp = findDatePicker((ViewGroup) dialog.getWindow().getDecorView());

        if (dp != null) {
            if (curTab == 2) {//只显示年月，隐藏掉日
                ((ViewGroup)((ViewGroup)dp.getChildAt(0)).getChildAt(0))
                        .getChildAt(2).setVisibility(View.GONE);
            } else if (curTab == 3) {//只显示年，隐藏掉日月
                ((ViewGroup)((ViewGroup)dp.getChildAt(0)).getChildAt(0))
                        .getChildAt(2).setVisibility(View.GONE);
                ((ViewGroup)((ViewGroup)dp.getChildAt(0)).getChildAt(0))
                        .getChildAt(1).setVisibility(View.GONE);
            }
        }
    }

    /**
     * 从当前Dialog中查找DatePicker子控件
     */
    private DatePicker findDatePicker(ViewGroup group) {
        if (group != null) {
            for (int i = 0, j = group.getChildCount(); i < j; i++) {
                View child = group.getChildAt(i);
                if (child instanceof DatePicker) {
                    return (DatePicker) child;
                } else if (child instanceof ViewGroup) {
                    DatePicker result = findDatePicker((ViewGroup) child);
                    if (result != null)
                        return result;
                }
            }
        }
        return null;
    }
}
