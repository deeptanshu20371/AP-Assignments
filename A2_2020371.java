import java.util.Scanner;
import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.ArrayList;

interface person_interface{
    static ArrayList<person_interface> person_arr = new ArrayList<person_interface>();
    String id=null;
    void add_comment();
    void view_comment();
}

interface lecture_interface{
    static ArrayList<lecture_interface> lecture_arr= new ArrayList<lecture_interface>();
    String title=null;
    void lecture_input();
    void lecture_view();
}

interface assessment_interface{
    static ArrayList<assessment_interface> assessment_arr =new ArrayList<assessment_interface>();
    String title=null;
    boolean assessment_submitted=false;
    boolean assessment_open=true;
    boolean assessment_graded=false;
    void assessment_view();
    void assesment_grade();
    void assessment_close();
    void assessment_submit();
}

private class student{

}

private class instructor{

}

private class menu{
    public int start(){
        System.out.println("Welcome to Backpack");
        System.out.println("1.Enter as instructor");
        System.out.println("2.Enter as student");
        System.out.println("3.Exit");
        int ret= Reader.nextint();
        return ret;
    }
    public int instructor(){
        System.out.println("1.Add class material");
        System.out.println("2.Add assessments");
        System.out.println("3. View lecture materials");
        System.out.println("4. View assessments");
        System.out.println("5. Grade assessments");
        System.out.println("6. Close assessments");
        System.out.println("7. View comments");
        System.out.println("8. Add comments");
        System.out.println("9. Logout");
        int ret = Reader.nextint();
        return ret;
    }
    public int student(){
        System.out.println("1.View lecture materials");
        System.out.println("2.View assessments");
        System.out.println("3.Submit assesment");
        System.out.println("4.View grades");
        System.out.println("5.View comments");
        System.out.println("6.Add comments");
        System.out.println("7.Logout");
        int ret= Reader.nextint();
        return ret;
    }
}

public class A2_2020371 {
    public static void main(String[] args) throws Exception{
        Date date=new Date();
        System.out.println(date);

        int start_num=menu.start();

        while (true){
            if (start_num==3)
                break;

            if (start_num==1){
                int instructor_num=instructor();
                while (true){
                    if (instructor_num==9)
                        break;
                    instructor_num=instructor();
                }
            }

            else{
                int student_num=student();
                while (true){
                    if (student_num==7)
                        break;
                    student_num=student();
                }
            }
            start_num=menu.start();
        }
        
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