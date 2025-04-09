import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class TransactionData
{
    private final int MONTHS = 12;
    private double[] monthlySpending; // stores the total spent for a particular month
    // used to find average spending for a particular month
    private int[] daysInMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    private HashMap<Integer, String> map;

    public TransactionData()
    {
        monthlySpending = new double[MONTHS];
        map = new HashMap<Integer, String>();

        map.put(0, "January");
        map.put(1, "February");
        map.put(2, "March");
        map.put(3, "April");
        map.put(4, "May");
        map.put(5, "June");
        map.put(6, "July");
        map.put(7, "August");
        map.put(8, "September");
        map.put(9, "October");
        map.put(10, "November");
        map.put(11, "December");
    }

    private String getMonth(String date, char delimiter)
    {
        int i = 0;
        String month = "";

        while (date.charAt(i) != delimiter) {
            month += date.charAt(i);
            ++i;
        }

        return month;
    }

    // sums up the total spending for each month
    // and stores it in the monthlySpending array
    public void computeTotalSpending(String filePath)
    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            String[] values;

            while ((line = reader.readLine()) != null) {
                // split will split the csv file into tokens
                // split returns a String array containing each token
                values = line.split(",");

                // the first column of the csv file holds the date
                String date = values[0];
                String month = getMonth(date, '/');
                if (!month.isEmpty())
                    monthlySpending[Integer.parseInt(month) - 1] += Double.parseDouble(values[2]);
            }
        }
        catch (IOException e) {
            System.err.println("File not found");
        }
    }

    public double[] getMonthlySpending()
    {
        return monthlySpending;
    }

    public int[] getDaysInMonth()
    {
        return daysInMonth;
    }

    public HashMap<Integer, String> getMap()
    {
        return map;
    }
}
