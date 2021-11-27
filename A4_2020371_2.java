import java.util.Scanner;
import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.ArrayList;

class Image {
    int rows;
    int columns;
    Pixel[][] matrix;
    int c;

    public Image(int rows, int columns, int c) throws Exception {
        this.rows = rows;
        this.columns = columns;
        this.c=c;
        Pixel[][] matrix=new Pixel[rows][columns];
        this.matrix=matrix;
        input();
    }

    public void input() throws Exception{
        for (int i=0;i<rows;i++){
            for (int j=0;j<columns;j++){
                System.out.println(" ");
                System.out.println("Input the pixel for row "+(i+1)+" and column "+(j+1));
                Pixel temp_pixel;
                if (c==1){
                    System.out.print("Enter the red value: ");
                    int red= Reader.nextint();
                    System.out.print("Enter the green value: ");
                    int green= Reader.nextint();
                    System.out.print("Enter the blue value: ");
                    int blue= Reader.nextint();
                    temp_pixel=new Color(red,green,blue);
                }
                else{
                    System.out.print("Enter the gray value: ");
                    int gray= Reader.nextint();
                    temp_pixel=new Grayscale(gray);
                }
                matrix[i][j]=temp_pixel;
            }
        }
    }

    public void display() {
        for (int i=0;i<rows;i++){
            for (int j=0;j<columns;j++){
                Pixel temp_pixel= matrix[i][j];
                temp_pixel.display();
            }
            System.out.println(" ");
        }
    }

    public void update() throws Exception{
        System.out.print("Enter the row of pixel:");
        int row=Reader.nextint();
        System.out.print("Enter the column of pixel:");
        int column=Reader.nextint();
        Pixel temp_pixel;
        if (c==1){
            System.out.print("Enter the red value: ");
            int red= Reader.nextint();
            System.out.print("Enter the green value: ");
            int green= Reader.nextint();
            System.out.print("Enter the blue value: ");
            int blue= Reader.nextint();
            temp_pixel=new Color(red,green,blue);
        }
        else{
            System.out.print("Enter the gray value: ");
            int gray= Reader.nextint();
            temp_pixel=new Grayscale(gray);
        }
        matrix[row-1][column-1]=temp_pixel;
    }

    public void invert(){
        for (int i=0;i<rows;i++){
            for (int j=0;j<columns;j++){
                Pixel temp_pixel= matrix[i][j];
                Calculator<Pixel> cal = new Calculator<Pixel>();
                cal.negative(temp_pixel);
            }
        }
    }
}

abstract class Pixel extends Object {
    void update() throws Exception {
    }

    void display() {
    }

    void negative() {
    }
}

class Color extends Pixel {
    int red;
    int blue;
    int green;

    public Color(int red, int green, int blue) {
        this.red = red;
        this.blue = blue;
        this.green = green;
    }

    public void display() {
        System.out.print("Red=" + red + " Green=" + green + " Blue=" + blue + "    ");
    }

    public void negative() {
        red = 255 - red;
        green = 255 - green;
        blue = 255 - blue;
    }
}

class Grayscale extends Pixel {
    int gray;

    public Grayscale(int gray) {
        this.gray = gray;
    }

    public void display() {
        System.out.print("Gray=" + gray + "    ");
    }

    public void negative() {
        gray = 255 - gray;
    }
}

class Calculator<T extends Pixel> {
    public void negative(T pix) {
        pix.negative();
    }
}

class Menu {
 
    public static void start() throws Exception{
        System.out.print("Enter the type of image (1 for color and 0 for grayscale): ");
        int n=Reader.nextint();
        System.out.print("Enter the number of rows in the image: ");
        int rows=Reader.nextint();
        System.out.print("Enter the number of columns in the image: ");
        int columns=Reader.nextint();
        Image im=new Image(rows,columns,n);
        while (true){
            System.out.println(" ");
            System.out.println("=======================");
            System.out.println("1. Display image");
            System.out.println("2. Update image");
            System.out.println("3. Invert image");
            System.out.println("4. Exit");
            System.out.println("=======================");
            System.out.print("Enter the operation: ");
            int operation= Reader.nextint();
            System.out.println(" ");
            if (operation==4){
                break;
            }
            if (operation==1){
                im.display();
            }
            if (operation==2){
                im.update();
            }
            if (operation==3){
                im.invert();
            }
        }
    }
}

public class A4_2020371_2 {

    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        Menu.start();
    }
}

class Reader {
    /*
     * How to use ( All these points can be seen in the code as well): 1. Paste
     * Reader class below public class and import java.util.* and java.io.* 2. Write
     * the statement " throws Exception" without quotes after main(). 3. Write
     * Reader.init(System.in); 4. Use Reader.nextint() for integer input. 5. Use
     * Readeer.nextlong() for Long input. 6. Use Reader.next() for String Input. 7.
     * Use Reader.next().charAt(0) for character input.
     */

    static BufferedReader reader;
    static StringTokenizer tokenizer;

    static void init(InputStream input) {
        reader = new BufferedReader(new InputStreamReader(input));
        tokenizer = new StringTokenizer("");
    }

    static String next() throws IOException {
        while (!tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    static int nextint() throws IOException {
        return Integer.parseInt(next());
    }

    static long nextlong() throws IOException {
        return Long.parseLong(next());
    }

    static double nextdouble() throws IOException {
        return Double.parseDouble(next());
    }
}