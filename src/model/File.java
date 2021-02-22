package model;

public class File implements Comparable<File> {
    private int number;
    private int peak;

    public File(int number, int peak) {
        this.number = number;
        this.peak = peak;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPeak() {
        return peak;
    }

    public void setPeak(int peak) {
        this.peak = peak;
    }

    @Override
    public int compareTo(File o) {
        return Integer.compare(this.number, o.number);
    }

    @Override
    public String toString() {
        return "File{" +
                "number=" + number +
                ", peak=" + peak +
                '}';
    }
}
