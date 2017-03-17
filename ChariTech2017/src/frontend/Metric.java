package frontend;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by pighe on 17/03/2017.
 */
public class Metric {
    public Metric(String name, boolean hasDateData, Method primaryDataFunction, Method monthlyDataFunction){
        mName = name;
        mHasDateData = hasDateData;

        //TODO: change all of this
        mPrimaryDataFunction = primaryDataFunction;
        mMonthlyDataFunction = monthlyDataFunction;


        //Most attributes set outside of constructor in inherited files
        try {
            mPrimaryData = (double)getPrimaryDataFunction().invoke(null);
            if(mHasDateData){
                mDataByMonth = (Map<Integer, Double>)getMonthlyDataFunction().invoke(null);
            }
        }catch(IllegalAccessException e){
            System.out.println("Illegal access exception: " + e.getMessage());
        }catch(InvocationTargetException e){
            System.out.println("Invocation target exception: " + e.getMessage());
        }
    }

    private String mName;
    private boolean mHasDateData;
    private double mPrimaryData;
    private Map<Integer, Double> mDataByMonth; //filled iff mHasDateData = true
    private Method mPrimaryDataFunction;
    private Method mMonthlyDataFunction; //not null iff mHasDateData = true

    protected double getPrimaryData(){
        return mPrimaryData;
    }

    protected String getName(){
        return mName;
    }

    protected boolean getHasDateData(){
        return mHasDateData;
    }

    protected Map<Integer, Double> getDataByMonth(){
        return mDataByMonth;
    }

    protected Method getPrimaryDataFunction(){
        return mPrimaryDataFunction;
    }

    protected Method getMonthlyDataFunction(){
        return mMonthlyDataFunction;
    }
}