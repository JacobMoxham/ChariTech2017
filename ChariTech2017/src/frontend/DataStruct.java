package frontend;

import datastatistics.Statistic;

/**
 * Created by pighe on 17/03/2017.
 */
public class DataStruct {
    String mName;
    Statistic mStatistic;
    boolean mHasDateData;
    public DataStruct(String name, Statistic statistic, boolean hasDateData){
        mName = name;
        mStatistic = statistic;
        mHasDateData = hasDateData;
    }
    public String getName(){
        return mName;
    }
    public Statistic getStatistic(){
        return mStatistic;
    }
    public boolean getHasDateData(){
        return mHasDateData;
    }
}
