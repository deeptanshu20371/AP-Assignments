import java.util.Scanner;
import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.ArrayList;

interface person_interface{
    class comment{
        String com;
        Date date;
        String poster;
        public comment(String com, Date date, String poster){
            this.com=com;
            this.date=date;
            this.poster=poster;
        }
    }
    String id=null;
    static ArrayList<comment> comments_arr= new ArrayList<comment>();
    void view_comment();
    void add_comment(String p) throws IOException;
    void view_assessment();
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
            System.out.println(com.com+" - "+com.poster);
            System.out.println(com.date);
            System.out.println("");
        }
    }
    
    @Override
    public void add_comment(String stu) throws IOException{
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter comment: ");
        String com= scanner.nextLine();
        Date date= new Date();
        person_interface.comment c= new person_interface.comment(com,date,stu);
        c.poster=stu;
        person_interface.comments_arr.add(c);
    }
    
    @Override
    public void view_assessment(){
        ArrayList<assessment_interface> arr= assessment_interface.assessment_arr;
        int size=arr.size();
        for (int i=0;i<size;i++){
            assessment_interface assess=arr.get(i);
            System.out.print("ID: "+i+" ");
            assess.assessment_view();
            System.out.println("---------");
        }
    }
    public void view_grades(int id){
        System.out.println("Graded Submissions");
        ArrayList<assessment_interface> arr= assessment_interface.assessment_arr;
        int size=arr.size();
        for (int i=0;i<size;i++){
            assessment_interface assess=arr.get(i);
            if (assess.grades.get(id).submission==null){
                continue;
            }
            if (assess.grades.get(id).grade==0){
                continue;
            }
            System.out.println("Submission:"+assess.grades.get(id).submission);
            System.out.println("Marks scored: "+assess.grades.get(id).grade);
            System.out.println("Graded by: "+assess.grades.get(id).grader);
        }
        System.out.println();
        System.out.println("Ungraded Submissions");
        for (int i=0;i<size;i++){
            assessment_interface assess=arr.get(i);
            if (assess.grades.get(id).submission==null){
                continue;
            }
            if (assess.grades.get(id).grade!=0){
                continue;
            }
            System.out.println("Submission:"+assess.grades.get(id).submission);
        }
    }
    public void submit(int id)throws IOException{
        Scanner scanner=new Scanner(System.in);
        System.out.println("Pending assignments");
        ArrayList<assessment_interface> arr= assessment_interface.assessment_arr;
        int size=arr.size();
        for (int i=0;i<size;i++){
            assessment_interface assess=arr.get(i);
            if (assess.grades.get(id).submission==null){
                System.out.print("ID:"+i+" ");
                assess.assessment_view();
            }
        }
        System.out.print("Enter ID of assessment: ");
        int ass_id=Reader.nextint();    
        System.out.print("Enter filename of assignment: ");
        String filename= scanner.nextLine();
        int s_size=filename.length();
        if (filename.substring(s_size-4).equals(".zip")==false){
            System.out.println("Subimission not in correct format.");
            return;
        }
        arr.get(ass_id).assessment_submit(filename,id);
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
            System.out.println(com.com+" - "+com.poster);
            System.out.println(com.date);
            System.out.println("");
        }
    }
    public void add_assessment(instructor ins)throws IOException{
        System.out.println("1.Add Assignment");
        System.out.println("2.Add quiz");
        int num=Reader.nextint();
        if (num==1){
            Scanner scanner= new Scanner(System.in);
            System.out.print("Enter problem statement: ");
            String ques= scanner.nextLine();
            System.out.print("Enter max marks: ");
            int max_marks=Reader.nextint();
            assignment ass=new assignment(ques, ins, max_marks);
            int size= student.student_arr.size();
            for (int i=0;i<size;i++){
                assessment_interface.stu_assessment grade= new assessment_interface.stu_assessment();
                ass.grades.add(grade);
            }
            assignment.assessment_arr.add(ass);
        }
        else{
            Scanner scanner= new Scanner(System.in);
            System.out.print("Enter quiz question: ");
            String ques= scanner.nextLine();
            quiz q= new quiz(ques, ins);
            quiz.assessment_arr.add(q);
        }
    }
    
    @Override
    public void add_comment(String ins) throws IOException{
        Scanner scan=new Scanner(System.in);
        System.out.print("Enter comment: ");
        String com= scan.nextLine();
        Date date= new Date();
        person_interface.comment c= new person_interface.comment(com,date,ins);
        c.poster=ins;
        person_interface.comments_arr.add(c);
    }
    
    @Override
    public void view_assessment(){
        ArrayList<assessment_interface> arr= assessment_interface.assessment_arr;
        int size=arr.size();
        for (int i=0;i<size;i++){
            assessment_interface assess=arr.get(i);
            System.out.print("ID: "+i+" ");
            assess.assessment_view();
            System.out.println("---------");
        }
    }
    public void grade_assignment(instructor ins)throws IOException{
        System.out.println("List of assessments");
        ArrayList<assessment_interface> ass_arr= assessment_interface.assessment_arr;
        view_assessment();
        int ass_num=Reader.nextint();
        assessment_interface ass= ass_arr.get(ass_num);
        int size= student.student_arr.size();
        for (int i=0;i<size;i++){
            if (ass.grades.get(i).submission==null){
                continue;
            }
            System.out.println(i+". "+student.student_arr.get(i).id);
        }
        int stu_num=Reader.nextint();
        System.out.println("Submission: "+ass.grades.get(stu_num).submission);
        System.out.println("----------------");
        System.out.println("Max Marks:"+ass.get_max());
        System.out.print("Marks scored: ");
        int marks=Reader.nextint();
        if (marks>ass.get_max()){
            System.out.println("Marks cannot be more than the maximum marks");
            return;
        }
        ass.grades.get(stu_num).grade=marks;
        ass.grades.get(stu_num).grader=ins.id;
        ass.graded();
    }
    public void close_assessment()throws IOException{
        System.out.println("List of open assessments");
        ArrayList<assessment_interface> arr= assessment_interface.assessment_arr;
        int size=arr.size();
        for (int i=0;i<size;i++){
            assessment_interface assess=arr.get(i);
            if (assess.assessment_closed==true){
                continue;
            }
            System.out.print("ID: "+i+" ");
            assess.assessment_view();
            System.out.println("---------");
        }
        System.out.print("Enter ID of assignment to close: ");
        int ass_id=Reader.nextint();
        arr.get(ass_id).assessment_close();
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
    class stu_assessment{
        String submission;
        int grade;
        String grader;
    }
    static ArrayList<assessment_interface> assessment_arr =new ArrayList<assessment_interface>();
    int stu_num=student.student_arr.size();
    ArrayList<stu_assessment> grades= new ArrayList<stu_assessment>(stu_num);
    String title=null;
    instructor poster=null;
    int max_marks=10;
    boolean assessment_submitted=false;
    boolean assessment_closed=false;
    boolean assessment_graded=false;
    void assessment_view();
    void assessment_close();
    void assessment_submit(String filename,int id);
    void graded();
    int get_max();
}

class quiz implements assessment_interface{
    String title;
    instructor poster;
    int max_marks=1;
    boolean assessment_submitted;
    boolean assessment_closed;
    boolean assessment_graded;
    public quiz(String title, instructor poster){
        this.title=title;
        this.poster=poster;
    }
    @Override
    public void assessment_view(){
        System.out.println("Question: "+title);
    }
    @Override
    public void assessment_close(){
        assessment_closed=true;
    }
    @Override
    public void assessment_submit(String filename, int id){
        grades.get(id).submission=filename;
    }
    @Override
    public void graded(){
        assessment_graded=true;
    }
    public int get_max(){
        return max_marks;
    }
}

class assignment implements assessment_interface{
    String title;
    instructor poster;
    int max_marks;
    boolean assessment_submitted=false;
    boolean assessment_closed=false;
    boolean assessment_graded=false;
    public assignment(String title, instructor poster, int max_marks){
        this.title=title;
        this.poster=poster;
        this.max_marks=max_marks;
    }
    @Override
    public void assessment_view(){
        System.out.println("Assignment: "+title+"  Max marks: "+max_marks);
    }
    @Override
    public void assessment_close(){
        assessment_closed=true;
    }
    @Override
    public void assessment_submit(String filename, int id){
        grades.get(id).submission=filename;
    }
    @Override
    public void graded(){
        assessment_graded=true;
    }
    public int get_max(){
        return max_marks;
    }
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
    public static int print_students() throws IOException{
        System.out.println("Students:");
        int size= student.student_arr.size();
        for (int i=0;i<size;i++){
            student stu= student.student_arr.get(i);
            System.out.println(i+". "+stu.id);
        }
        int num=Reader.nextint();
        return (num);
    }
    public static void lecture_slides_input(instructor ins) throws IOException{
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter the topic of slides: ");
        String t= scanner.nextLine();
        System.out.print("Enter the number of slides: ");
        int n= Reader.nextint();
        String[] arr= new String[n];
        System.out.println("Enter the content of slides");
        for (int i=0; i<n; i++){
            System.out.print("Content of slide "+i+": ");
            String content= scanner.nextLine();
            arr[i]=content;
        }
        Date d=new Date();
        lecture_slides l=new lecture_slides(t,n,arr,d,ins);
        lecture_interface.lecture_arr.add(l);
    }
    public static void lecture_vid_input(instructor ins) throws IOException{
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter the topic of video: ");
        String t= scanner.nextLine();
        System.out.print("Enter the filename of video: ");
        String vid= scanner.nextLine();
        int s_size=vid.length();
        if (vid.substring(s_size-4).equals(".mp4")==false){
            System.out.println("Video file not in correct format.");
            return;
        }
        Date d = new Date();
        lecture_video v=new lecture_video(t, vid, d, ins);
        lecture_interface.lecture_arr.add(v);
    }    
}

public class A2_2020371 {
    public static void main(String[] args) throws IOException{
        Scanner scanner=new Scanner(System.in);
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
                        curr_instructor.add_assessment(curr_instructor);
                    }
                    if (instructor_num==3){
                        int s=lecture_interface.lecture_arr.size();
                        for (int i=0;i<s;i++){
                            lecture_interface lec= lecture_interface.lecture_arr.get(i);
                            lec.lecture_view();
                        }
                    }
                    if (instructor_num==4){
                        curr_instructor.view_assessment();
                    }
                    if (instructor_num==5){
                        curr_instructor.grade_assignment(curr_instructor);
                    }
                    if (instructor_num==6){
                        curr_instructor.close_assessment();
                    }
                    if (instructor_num==7){
                        curr_instructor.view_comment();
                    }
                    if (instructor_num==8){
                        curr_instructor.add_comment(curr_instructor.id);
                    }
                    instructor_num=menu.instructor(curr_instructor);
                }
            }

            else{

                int num= menu.print_students();
                student curr_student=student.student_arr.get(num);
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
                        curr_student.view_assessment();
                    }
                    if (student_num==3){
                        curr_student.submit(num);
                    }
                    if (student_num==4){
                        curr_student.view_grades(num);
                    }
                    if (student_num==5){
                        curr_student.view_comment();
                    }
                    if (student_num==6){
                        curr_student.add_comment(curr_student.id);
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