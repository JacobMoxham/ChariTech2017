package frontend;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Created by pighe on 17/03/2017.
 */
public class Metric {
    public Metric(String name, boolean hasDateData, double primaryData, List<Double> dataByMonth){
        mName = name;
        mHasDateData = hasDateData;
        mPrimaryData = primaryData;
        mDataByMonth = dataByMonth;
    }

    private String mName;
    private boolean mHasDateData;
    private double mPrimaryData;
    private List<Double> mDataByMonth; //filled iff mHasDateData = true


    public double getPrimaryData(){
        return mPrimaryData;
    }

    public String getName(){
        return mName;
    }

    public boolean getHasDateData(){
        return mHasDateData;
    }
}