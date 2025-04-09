import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.BorderPane;
import java.util.HashMap;

public class Main extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        final int MONTHS = 12;
        TransactionData data = new TransactionData();
        String filePath = "Enter your path to csv here";

        data.computeTotalSpending(filePath);

        // Setting up the bar chart for average monthly spending
        CategoryAxis xAxis = new CategoryAxis();

        xAxis.setLabel("Month");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Average Monthly Spending");

        BarChart barChart = new BarChart(xAxis, yAxis);

        XYChart.Series dataSeries = new XYChart.Series();

        dataSeries.setName("Spending Overview");

        int average;
        double[] monthlySpending = data.getMonthlySpending();
        int[] daysInMonth = data.getDaysInMonth();
        HashMap<Integer, String> map = data.getMap();

        for (int i = 0; i < MONTHS; ++i) {
            if (((int) monthlySpending[i]) == 0)
                continue;

            average = (int) (Math.round(monthlySpending[i] / (double) daysInMonth[i]));
            dataSeries.getData().add(new XYChart.Data(map.get(i), average));
        }

        barChart.getData().add(dataSeries);

        BorderPane borderPane = new BorderPane(barChart);

        Scene scene = new Scene(borderPane, 1100, 600);

        primaryStage.setTitle("Bar Chart Spending");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
