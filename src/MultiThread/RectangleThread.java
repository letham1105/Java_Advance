package MultiThread;

import java.util.Scanner;

public class RectangleThread {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Tuyen 1: Nhap chieu dai va chieu rong cua hinh chu nhat
        double length = sc.nextDouble();
        double width = sc.nextDouble();;

        // tao va khoi chay tuyen tinh dien tich
        calculateArea t2 = new calculateArea(length, width);
        t2.start();
        // tao va khoi chay tuyen tinh chu vi
        calculatePerimeter t3 = new calculatePerimeter(length, width);
        t3.start();
        //  cac tuyen hoan thanh cong viec cua minh
        try{
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // lay result
        double area = t2.getResult();
        double perimeter = t3.getResult();
        // in ra ket qua
        System.out.println("S="+area);
        System.out.println("P="+perimeter);
        // dong scanner
        sc.close();
    }

}
class calculateArea extends Thread{
    private double length;
    private double width;
    private double area;
    public calculateArea(double length, double width){
        this.length = length;
        this.width = width;
    }

    @Override
    public void run() {
        area = length*width;
    }
    public double getResult(){
        return area;
    }
}
class  calculatePerimeter extends Thread{
    private double length;
    private double width;
    private double perimeter;

    public calculatePerimeter (double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public void run() {
        perimeter = 2 * (length + width);
    }

    public double getResult() {
        return perimeter;
    }
}