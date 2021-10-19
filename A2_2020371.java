import java.util.Scanner;
import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.ArrayList;

interface person_interface{
    class comment{
        String com;
        Date date;
        person_interface poster;
        public comment(String com, Date date, person_interface poster){
            this.com=com;
            this.date=date;
            this.poster=poster;
        }
    }
    String id=null;
    static ArrayList<comment> comments_arr= new ArrayList<comment>();
    void view_comment();
}

class student implements person_interface{
    static ArrayList<student> student_arr = new ArrayList<student>();
    ArrayList<assessment_interface> ass_arr= new ArrayList<assessment_interface>();
    String id;
    public student(String id){
        this.id=id;
        this.ass_arr=ass_arr;
    }
    @Override
    public void view_comment(){
        int size= person_interface.comments_arr.size();
        for (int i=0;i<size;i++){
            person_interface.comment com=person_interface.comments_arr.get(i);
            System.out.println(com.com+" - "+com.poster.id);
            System.out.println(com.date);
            System.out.println("");
        }
    }
}

class instructor implements person_interface{
    static ArrayList<instructor> instructor_arr = new ArrayList<instructor>();
    String id;
    public instructor(String id){
        this.id=id;
    }
    @Override
    public void view_comment(){
        int size= person_interface.comments_arr.size();
        for (int i=0;i<size;i++){
            person_interface.comment com=person_interface.comments_arr.get(i);
            System.out.println(com.com+" - "+com.poster.id);
            System.out.println(com.date);
            System.out.println("");
        }
    }
}

interface lecture_interface{
    static ArrayList<lecture_interface> lecture_arr= new ArrayList<lecture_interface>();
    void lecture_view();
}

class lecture_slides implements lecture_interface{
    String title;
    int num;
    String[] slides;
    Date date;
    instructor poster;
    public lecture_slides(String title,int num, String[] slides,Date date, instructor poster ){
        this.title=title;
        this.num=num;
        this.slides=slides;
        this.poster=poster;
        this.date=date;
    }    
    @Override
    public void lecture_view(){
        System.out.println("Title: "+this.title);
        for (int i=0;i<this.num;i++){
            System.out.println("Slide "+i+": "+this.slides[i]);
        }
        System.out.println("Number of slides: "+this.num);
        System.out.println("Date of upload: "+this.date);
        System.out.println("Uploaded by: "+this.poster.id);
        System.out.println(" ");
    }
}

class lecture_video implements lecture_interface{
    String title;
    String video;
    Date date;
    instructor poster;

    public lecture_video(String title,String video,Date date, instructor poster ){
        this.title=title;
        this.video=video;
        this.poster=poster;
        this.date=date;
    }
    @Override
    public void lecture_view(){
        System.out.println("Title: "+this.title);
        System.out.println("Video file: "+this.video);
        System.out.println("Date of upload: "+this.date);
        System.out.println("Uploaded by: "+this.poster.id);
        System.out.println(" ");
    }
}

interface assessment_interface{
    static ArrayList<assessment_interface> assessment_arr =new ArrayList<assessment_interface>();
    String title=null;
    int max_marks=1;
    int grade=0;
    instructor poster=null;
    boolean assessment_submitted=false;
    boolean assessment_closed=false;
    boolean assessment_graded=false;
    void assessment_view();
    void assesment_grade();
    void assessment_close();
    void assessment_submit();
}

class quiz implements assessment_interface{

}

class assignment implements assessment_interface{

}


class menu{
    public static int start() throws IOException{
        System.out.println("Welcome to Backpack");
        System.out.println("1.Enter as instructor");
        System.out.println("2.Enter as student");
        System.out.println("3.Exit");
        int ret= Reader.nextint();
        return ret;
    }
    public static int instructor(instructor ins) throws IOException{
        System.out.println("");
        System.out.println("Welcome "+ins.id);
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
    public static int student(student stu) throws IOException{
        System.out.println("");
        System.out.println("Welcome "+stu.id);
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
    public static instructor print_instructors() throws IOException{
        System.out.println("Instructors:");
        int size=instructor.instructor_arr.size();
        for (int i=0;i<size;i++){
            instructor ins= instructor.instructor_arr.get(i);
            System.out.println(i+". "+ins.id);
        }
        int num=Reader.nextint();
        return (instructor.instructor_arr.get(num));
    }
    public static student print_students() throws IOException{
        System.out.println("Students:");
        int size= student.student_arr.size();
        for (int i=0;i<size;i++){
            student stu= student.student_arr.get(i);
            System.out.println(i+". "+stu.id);
        }
        int num=Reader.nextint();
        return (student.student_arr.get(num));
    }
    public static void lecture_slides_input(instructor ins) throws IOException{
        System.out.print("Enter the topic of slides: ");
        String t= Reader.next();
        System.out.print("Enter the number of slides: ");
        int n= Reader.nextint();
        String[] arr= new String[n];
        System.out.println("Enter the content of slides");
        for (int i=0; i<n; i++){
            System.out.print("Content of slide "+i+": ");
            String content= Reader.next();
            arr[i]=content;
        }
        Date d=new Date();
        lecture_slides l=new lecture_slides(t,n,arr,d,ins);
        lecture_interface.lecture_arr.add(l);
    }
    public static void lecture_vid_input(instructor ins) throws IOException{
        System.out.print("Enter the topic of video: ");
        String t= Reader.next();
        System.out.print("Enter the filename of video: ");
        String vid= Reader.next();
        int s_size=vid.length();
        if (vid.substring(s_size-4).equals(".mp4")==false){
            System.out.println("Video file not in correct format.");
            return;
        }
        Date d = new Date();
        lecture_video v=new lecture_video(t, vid, d, ins);
        lecture_interface.lecture_arr.add(v);
    }
    public static void add_comment_ins(instructor ins) throws IOException{
        System.out.print("Enter comment: ");
        String com= Reader.next();
        Date date= new Date();
        person_interface.comment c= new person_interface.comment(com,date,ins);
        person_interface.comments_arr.add(c);
    }
    public static void add_comment_stu(student stu) throws IOException{
        System.out.print("Enter comment: ");
        String com= Reader.next();
        Date date= new Date();
        person_interface.comment c= new person_interface.comment(com,date,stu);
        person_interface.comments_arr.add(c);
    }
}

public class A2_2020371 {
    public static void main(String[] args) throws IOException{
        Reader.init(System.in);
        instructor I0= new instructor("I0");
        instructor I1= new instructor("I1");
        instructor.instructor_arr.add(I0);
        instructor.instructor_arr.add(I1);
        student S0=new student("S0");
        student S1=new student("S1");
        student S2=new student("S2");
        student.student_arr.add(S0);
        student.student_arr.add(S1);
        student.student_arr.add(S2);

        int start_num=menu.start();

        while (true){
            if (start_num==3)
                break;

            if (start_num==1){
                instructor curr_instructor= menu.print_instructors();
                int instructor_num=menu.instructor(curr_instructor);
                while (true){
                    if (instructor_num==9)
                        break;
                    if (instructor_num==1){
                        System.out.println("1.Add Lecture Slide");
                        System.out.println("2.Add Lecture Video");
                        int c=Reader.nextint();
                        if (c==1)
                            menu.lecture_slides_input(curr_instructor);
                        else{
                            menu.lecture_vid_input(curr_instructor);
                        }
                    }
                    if (instructor_num==2){
                        
                    }
                    if (instructor_num==3){
                        int s=lecture_interface.lecture_arr.size();
                        for (int i=0;i<s;i++){
                            lecture_interface lec= lecture_interface.lecture_arr.get(i);
                            lec.lecture_view();
                        }
                    }
                    if (instructor_num==4){
                        
                    }
                    if (instructor_num==5){
                        
                    }
                    if (instructor_num==6){
                        
                    }
                    if (instructor_num==7){
                        curr_instructor.view_comment();
                    }
                    if (instructor_num==8){
                        menu.add_comment_ins(curr_instructor);
                    }
                    instructor_num=menu.instructor(curr_instructor);
                }
            }

            else{
                student curr_student= menu.print_students();
                int student_num=menu.student(curr_student);
                while (true){
                    if (student_num==7)
                        break;
                    if (student_num==1){
                        int s=lecture_interface.lecture_arr.size();
                        for (int i=0;i<s;i++){
                            lecture_interface lec= lecture_interface.lecture_arr.get(i);
                            lec.lecture_view();
                        }
                    }
                    if (student_num==2){
                        
                    }
                    if (student_num==3){
                        
                    }
                    if (student_num==4){
                        
                    }
                    if (student_num==5){
                        curr_student.view_comment();
                    }
                    if (student_num==6){
                        menu.add_comment_stu(curr_student);
                    }
                    student_num=menu.student(curr_student);
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