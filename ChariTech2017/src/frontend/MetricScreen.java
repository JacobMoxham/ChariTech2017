package frontend;

import datastatistics.Tutor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.io.FileNotFoundException;
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

    }

    protected void generateScene(){
        StackPane frame = new StackPane();

        List<MetricType> categories = Arrays.asList(MetricType.values());
    }
}
