package lesson_5;

import java.util.Arrays;

public class AppData {
    private String[] header;
    private int[][] data;

    public AppData(String[] header, int[][] data) {
        this.header = header;
        this.data = data;
    }

    public String[] getHeader() {
        return header;
    }

    public void setHeader(String[] header) {
        this.header = header;
    }

    public int[][] getData() {
        return data;
    }

    public void setData(int[][] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return Arrays.toString(header) + "\n" +
                Arrays.toString(data[0]) + "\n" +
                Arrays.toString(data[1]);
    }

    public void save (AppData data){
        setHeader(data.getHeader());
        setData(data.getData());
    }
}
