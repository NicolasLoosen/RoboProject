package com.mobileapplicationdev.roboproject.services;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.mobileapplicationdev.roboproject.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Thread that will insert values into the line graph
 */
@SuppressWarnings({"WeakerAccess", "CanBeFinal"})
public class AddEntryGraphThread implements Runnable {
    private final List<Float> entryData = Collections.synchronizedList(new ArrayList<Float>());
    private LineChart lineChart;
    private float targetValue;
    private boolean isRunning = false;
    private LineDataSet.Mode mode;

    /**
     * set the main values needed
     * @param lineChart used line chart
     * @param targetValue target speed
     * @param mode selected mode
     */
    public AddEntryGraphThread(LineChart lineChart, float targetValue, LineDataSet.Mode mode) {
        this.lineChart = lineChart;
        this.targetValue = targetValue;
        this.mode = mode;
    }

    /**
     * adds a value to the Graph
     * @param actualValue the value received
     */
    public void addEntry(float actualValue) {
        entryData.add(actualValue);
    }

    /**
     * remove the first Entry in the Collection<List>
     */
    private void removeData() {
        entryData.remove(0);
    }

    /**
     * returns the first value in the Collection<List>
     * @return first entry
     */
    private float getFirstData() {
        return entryData.get(0);
    }

    /**
     * Adds the data Sets and create the Charts entry
     * also update the graph and sets max view range
     */
    private void addEntryIntoGraph() {
        final float value = getFirstData();
        removeData();

        lineChart.post(new Runnable() {
            @Override
            public void run() {
                LineData data = lineChart.getData();

                if (data != null) {

                    ILineDataSet set = data.getDataSetByIndex(0);
                    ILineDataSet setTwo;

                    if (set == null) {
                        //initialize setOne / Dynamic Graph
                        set = Utils.createSet(mode);
                        //setTwo = Utils.createSetTwo();
                        setTwo = Utils.createSetTwo();
                        //initialize setTwo / static graph
                        data.addDataSet(set);
                        //data.addDataSet(setTwo);
                        data.addDataSet(setTwo);
                    }
                    int entryIndex = set.getEntryCount();

                    //add first data set Entry for the dynamic data
                    data.addEntry(new Entry(entryIndex, value), 0);

                    //add second data set Entry for the static allocated data
                    data.addEntry(new Entry(entryIndex, targetValue), 1);

                    data.notifyDataChanged();

                    // let the chart know it's data has changed
                    lineChart.notifyDataSetChanged();

                    // limit the number of visible entries
                    lineChart.setVisibleXRangeMaximum(120);
                    // mChart.setVisibleYRange(30, AxisDependency.LEFT);

                    // move to the latest entry
                    lineChart.moveViewToX(data.getEntryCount());

                    // this automatically refreshes the chart (calls invalidate())
                    // mChart.moveViewTo(data.getXValCount()-7, 55f,
                    // AxisDependency.LEFT);
                }
            }
        });
    }

    /**
     * addEntry over extra Thread
     * add entry while the list ( data ) is not empty
     */
    @Override
    public void run() {
        while (isRunning) {
            if (!entryData.isEmpty()) {
                addEntryIntoGraph();
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * start the addEntry Thread
     */
    public void startThread() {
        Thread runnerThread;

        runnerThread = new Thread(this, AddEntryGraphThread.class.getSimpleName());
        isRunning = true;
        runnerThread.start();
    }

    /**
     * stop running thread
     */
    public void stop() {
        isRunning = false;
    }
}
