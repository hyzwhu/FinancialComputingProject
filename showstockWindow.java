package com.group.financialcomputing;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;
//author xiaoyang guo
public class showstockWindow extends ApplicationFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private StockManagementWindow smw;
    private String name;
    public showstockWindow (StockManagementWindow smw,String s) {
        super(s);
        this.smw = smw;
        Map<String, List<OneDayStock>> pickedStocks = this.smw.getPickedStocks();
        //遍历map
        Iterator<Map.Entry<String, List<OneDayStock>>> maplist = pickedStocks.entrySet().iterator();
        while (maplist.hasNext()) {
            Map.Entry<String, List<OneDayStock>> entry = maplist.next();
            //获取该股票的所有数据
            List<OneDayStock> stockList = entry.getValue();

            createKLine(stockList);
        }

    }
//

    // 生成数据
    public void createKLine(List<OneDayStock> stockList) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        double highValue = Double.MIN_VALUE;//设置K线数据当中的最大值
        double minValue = Double.MAX_VALUE;//设置K线数据当中的最小值
        double high2Value = Double.MIN_VALUE;//设置成交量的最大值
        double min2Value = Double.MAX_VALUE;//设置成交量的最低值
        final OHLCSeriesCollection seriesCollection = new OHLCSeriesCollection();//保留K线数据的数据集，必须申明为final，后面要在匿名内部类里面用到
        TimeSeriesCollection timeSeriesCollection=new TimeSeriesCollection();//保留成交量数据的集合
        OHLCSeries series = new OHLCSeries("");//高开低收数据序列，股票K线图的四个数据，依次是开，高，低，收
        TimeSeries series2=new TimeSeries("");//对应时间成交量数据


        //遍历数组
        for (int i = 0; i < stockList.size(); i++) {
            //遍历这个list，获取该股票的每天的数据
            OneDayStock stock = stockList.get(i);
            this.name = stock.getTicker();
            SimpleDateFormat sf2 = new SimpleDateFormat("yyyyMMdd");
            try{

                Date date = sf2.parse(stock.getDate());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                Day day = new Day(calendar.get(Calendar.DAY_OF_MONTH),(calendar.get(Calendar.MONTH) + 1), calendar.get(Calendar.YEAR));
                series.add(day, stock.getOpen(), stock.getHigh(), stock.getLow(), stock.getClose());
                series2.add(day, stock.getVolume());
            }catch(Exception e){
            }


        }


        seriesCollection.addSeries(series);
        timeSeriesCollection.addSeries(series2);        //获取K线数据的最高值和最低值
        int seriesCount = seriesCollection.getSeriesCount();//一共有多少个序列，目前为一个
        for (int i = 0; i < seriesCount; i++) {
            int itemCount = seriesCollection.getItemCount(i);//每一个序列有多少个数据项
            for (int j = 0; j < itemCount; j++) {
                if (highValue < seriesCollection.getHighValue(i, j)) {//取第i个序列中的第j个数据项的最大值
                    highValue = seriesCollection.getHighValue(i, j);
                }
                if (minValue > seriesCollection.getLowValue(i, j)) {//取第i个序列中的第j个数据项的最小值
                    minValue = seriesCollection.getLowValue(i, j);
                }
            }
        }
        //获取最高值和最低值
        int seriesCount2 = timeSeriesCollection.getSeriesCount();//一共有多少个序列，目前为一个
        for (int i = 0; i < seriesCount2; i++) {
            int itemCount = timeSeriesCollection.getItemCount(i);//每一个序列有多少个数据项
            for (int j = 0; j < itemCount; j++) {
                if (high2Value < timeSeriesCollection.getYValue(i,j)) {//取第i个序列中的第j个数据项的值
                    high2Value = timeSeriesCollection.getYValue(i,j);
                }
                if (min2Value > timeSeriesCollection.getYValue(i, j)) {//取第i个序列中的第j个数据项的值
                    min2Value = timeSeriesCollection.getYValue(i, j);
                }
            }
        }


        final CandlestickRenderer candlestickRender=new CandlestickRenderer();//设置K线图的画图器，必须申明为final，后面要在匿名内部类里面用到
        candlestickRender.setUseOutlinePaint(true); //设置是否使用自定义的边框线，程序自带的边框线的颜色不符合中国股票市场的习惯
        candlestickRender.setAutoWidthMethod(CandlestickRenderer.WIDTHMETHOD_AVERAGE);//设置如何对K线图的宽度进行设定
        candlestickRender.setAutoWidthGap(0.001);//设置各个K线图之间的间隔
        candlestickRender.setUpPaint(Color.RED);//设置股票上涨的K线图颜色
        candlestickRender.setDownPaint(Color.GREEN);//设置股票下跌的K线图颜色

        DateAxis x1Axis=new DateAxis();//设置x轴，也就是时间轴
        x1Axis.setAutoRange(true);
        x1Axis.setTimeline(SegmentedTimeline.newMondayThroughFridayTimeline());// 设置时间线显示的规则，用这个方法就摒除掉了周六和周日这些没有交易的日期，使图形看上去连续
        x1Axis.setAutoTickUnitSelection(true);//设置不采用自动选择刻度值
        x1Axis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);//设置标记的位置
//    x1Axis.setStandardTickUnits(DateAxis.createStandardDateTickUnits());//设置标准的时间刻度单位
//    x1Axis.setTickUnit(new DateTickUnit(DateTickUnit.DAY,7));//设置时间刻度的间隔，一般以周为单位
        x1Axis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));//设置显示时间的格式
        NumberAxis y1Axis=new NumberAxis();//设定y轴，就是数字轴
        y1Axis.setAutoRange(false);//不不使用自动设定范围
        y1Axis.setRange(minValue*0.9, highValue*1.1);//设定y轴值的范围，比最低值要低一些，比最大值要大一些，这样图形看起来会美观些
        y1Axis.setTickUnit(new NumberTickUnit((highValue*1.1-minValue*0.9)/10));//设置刻度显示的密度
        XYPlot plot1=new XYPlot(seriesCollection,x1Axis,y1Axis,candlestickRender);//设置画图区域对象
        XYBarRenderer xyBarRender=new XYBarRenderer(){
            private static final long serialVersionUID = 1L;//为了避免出现警告消息，特设定此值
            public Paint getItemPaint(int i, int j){//匿名内部类用来处理当日的成交量柱形图的颜色与K线图的颜色保持一致
                if(seriesCollection.getCloseValue(i,j)>seriesCollection.getOpenValue(i,j)){//收盘价高于开盘价，股票上涨，选用股票上涨的颜色
                    return candlestickRender.getUpPaint();
                }else{
                    return candlestickRender.getDownPaint();
                }
            }
        };
        xyBarRender.setMargin(0.1);//设置柱形图之间的间隔
        NumberAxis y2Axis=new NumberAxis();//设置Y轴，为数值,后面的设置，参考上面的y轴设置
        y2Axis.setAutoRange(false);
        y2Axis.setRange(min2Value*0.9, high2Value*1.1);
        y2Axis.setTickUnit(new NumberTickUnit((high2Value*1.1-min2Value*0.9)/4));
        XYPlot plot2=new XYPlot(timeSeriesCollection,null,y2Axis,xyBarRender);
        //建立第二个画图区域对象，主要此时的 x轴设为了null值，因为要与第一个画图区域对象共享x轴
        CombinedDomainXYPlot combineddomainxyplot = new CombinedDomainXYPlot(x1Axis);
        //建立一个恰当的联合图形区域对象，以x轴为共享轴
        combineddomainxyplot.add(plot1, 2);
        //添加图形区域对象，后面的数字是计算这个区域对象应该占据多大的区域2/3
        combineddomainxyplot.add(plot2, 1);//添加图形区域对象，后面的数字是计算这个区域对象应该占据多大的区域1/3
        combineddomainxyplot.setGap(10);//设置两个图形区域对象之间的间隔空间
        JFreeChart chart = new JFreeChart(this.name, JFreeChart.DEFAULT_TITLE_FONT, combineddomainxyplot, false);
        ChartFrame frame = new ChartFrame(this.name, chart);      frame.pack();      frame.setVisible(true);
    }
}
