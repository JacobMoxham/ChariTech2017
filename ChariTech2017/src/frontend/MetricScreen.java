package frontend;

import datastatistics.Statistic;
import datastatistics.Tutor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pighe on 17/03/2017.
 */
public class MetricScreen extends Screen {
    private Tutor mTutor;
    private List<DataStruct> data;
    private List<Metric> mMetrics;
    private MetricType mType;
    public MetricScreen(Main parent, Tutor tutor, MetricType type){
        super(parent);
        mTutor = tutor;
        mType = type;

        data = new ArrayList<DataStruct>();
        mMetrics = new ArrayList<Metric>();

        switch(type){
            case Admin:
                data.add(new DataStruct("Session length", Statistic.SESSION_LENGTH, true));
                break;
            case Business:
                data.add(new DataStruct("Monthly income", Statistic.INCOME, true));
                data.add(new DataStruct("New customers", Statistic.NEW_CUSTOMERS, true));
                data.add(new DataStruct("Attrition", Statistic.ATTRITION, false));
                data.add(new DataStruct("Conversion rate", Statistic.CONVERSION_RATE, false));
                break;
        }



        System.out.println(data.size());
        for(DataStruct d : data){
            boolean t = d.getHasDateData();
            List<Double> dataByMonth = null;
            if(t){
                try {
                    switch (mType) {
                        case Admin:
                            dataByMonth = mTutor.getAllAdministrativeData(d.getStatistic());
                            break;
                        case Business:
                            dataByMonth = mTutor.getAllBusinessData(d.getStatistic());
                            break;
                        case Learning:
                            dataByMonth = mTutor.getAllLearningData(d.getStatistic());
                    }
                }catch(FileNotFoundException e){
                    System.out.println("File not found: " + e.getMessage());
                }
            }

            double data = 0.0;
            try {
                switch (mType) {
                    case Admin:
                        data = mTutor.getAdministrativeData(d.getStatistic());
                        break;
                    case Business:
                        data = mTutor.getBusinessData(d.getStatistic());
                        break;
                    case Learning:
                        data = mTutor.getLearningData(d.getStatistic());
                }
            }catch(FileNotFoundException e){
                System.out.println("File not found: " + e.getMessage());
            }

            Metric metric = new Metric(d.getName(), t, data, dataByMonth);
            mMetrics.add(metric);
        }
        generateScene();
    }

    protected void generateScene(){
        GridPane frame = this.drawMetricButtons(mMetrics, mType.toString());
        setScene(new Scene(frame, getParent().getWidth(), getParent().getHeight()));
    }
}