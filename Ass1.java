import java.util.Scanner;
import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.ArrayList;

class citizen{
    String name;
    int age;
    String citizen_id;
    String status="REGISTERED";
    String vaccine;
    public citizen(String name, int age, String citizen_id){
        this.name=name;
        this.age=age;
        this.citizen_id=citizen_id;
        this.status=status;
        this.vaccine=vaccine;
    }
}
class vaccine{
    String name;
    int doses;
    int gap_doses;
    public vaccine(String name, int doses, int gap_doses){
        this.name=name;
        this.doses= doses;
        this.gap_doses= gap_doses;
    }
}
class hospital{
    class slot{
        String vac_name;
        int quantity;
        public slot(String vac_name, int quantity){
            this.vac_name=vac_name;
            this.quantity=quantity;
        }
    }
    String name;
    String pincode;
    String hospital_id;
    ArrayList<ArrayList<slot>> slots= new ArrayList<ArrayList<slot>>();
    public hospital(String name, String pincode, String hospital_id){
        this.name=name;
        this.pincode=pincode;
        this.hospital_id=hospital_id;
        this.slots=slots;
    }
    public void create_slot(ArrayList<vaccine> vaccine_arr) throws IOException{
        System.out.print("Enter Day Number: ");
        int day= Reader.nextint();
        System.out.print("Enter quantity: ");
        int vac_quan= Reader.nextint();
        System.out.println("Select Vaccine");
        int size= vaccine_arr.size();
        for (int i=0;i<size;i++){
            vaccine temp= vaccine_arr.get(i);
            System.out.println(i+"."+temp.name);
        }
        int num=Reader.nextint();
        vaccine vac=vaccine_arr.get(num);
        ArrayList<slot> day_slots= slots.get(day);
        int n= day_slots.size();
        int final_quantity=vac_quan;
        boolean exists=false;
        for (int i=0;i<n;i++){
            if (day_slots.get(i).vac_name.equals(vac.name)){
                exists=true;
                final_quantity=day_slots.get(i).quantity+vac_quan;
                day_slots.get(i).quantity=final_quantity;
            }
        }
        if (exists==false){
            slot temp_slot= new slot(vac.name,vac_quan);
            day_slots.add(temp_slot);
        }
        System.out.println("Slot added to hospital "+hospital_id+" for Day:"+day+", Available quantity:"+final_quantity+"of Vaccine "+vac.name);
    }
}

class task_functions{
    public static int menu() throws Exception{
        System.out.println("---------------------------------");
        System.out.println("1. Add Vaccine");
        System.out.println("2. Register Hospital");
        System.out.println("3. Register Citizen");
        System.out.println("4. Add Slot for Vaccination");
        System.out.println("5. Book Slot for Vaccination");
        System.out.println("6. List all slots for a hostpital");
        System.out.println("7. Check Vaccination Status");
        System.out.println("8. Exit");
        System.out.println("---------------------------------");
        return Reader.nextint();
    }
    public static void add_vaccine(ArrayList<vaccine> vaccine_arr) throws Exception{
        System.out.print("Vaccine Name: ");
        String name= Reader.next();
        System.out.print("Number of doses: ");
        int doses= Reader.nextint();
        System.out.print("Gap between doses: ");
        int gap_doses= Reader.nextint();
        vaccine temp= new vaccine(name, doses, gap_doses);
        vaccine_arr.add(temp);
        System.out.println("Vaccine Name:"+name+", Number of doses:"+doses+", Gap between doses:"+ gap_doses);
    }
    public static void add_hospital(ArrayList<hospital> hospital_arr, int hospital_count) throws Exception{
        System.out.print("Hospital Name:");
        String name= Reader.next();
        System.out.print("Pincode:");
        String pincode=Reader.next();
        String id=String.valueOf(hospital_count);
        hospital temp= new hospital(name,pincode,id);
        hospital_arr.add(temp);
        System.out.println("Hospital Name:"+name+", PinCode:"+pincode+", Unique ID:"+id);
    }
    public static void add_citizen(ArrayList<citizen> citizen_arr, long citizen_count) throws Exception{
        System.out.print("Citizen Name: ");
        String name= Reader.next();
        System.out.print("Age: ");
        int age= Reader.nextint();
        if (age<18){
            System.out.println("Only above 18 are allowed");
            return;
        }
        String id= String.valueOf(citizen_count);
        citizen temp= new citizen(name,age,id);
        citizen_arr.add(temp);
        System.out.println("Citizen Name:"+name+", Age:"+age+", Unique ID:"+id);
    }
    public static void create_slot(ArrayList<vaccine> vaccine_arr, ArrayList<hospital> hospital_arr) throws IOException{
        System.out.print("Enter hospital ID: ");
        String id= Reader.next();
        for (int i=0; i<hospital_arr.size(); i++){
            hospital temp= hospital_arr.get(i);
            if (temp.hospital_id.equals(id)){
                System.out.print("Enter the number of slots to be added: ");
                int quantity= Reader.nextint();
                for (int j=0;j<quantity;j++){
                    temp.create_slot(vaccine_arr);
                }
                return;
            }
        }
        System.out.println("Hospital with hospital ID "+id+" not found.");
    }
    public static void book_slot(ArrayList<citizen> citizen_arr, ArrayList<hospital> hospital_arr, ArrayList<vaccine> vaccine_arr) throws IOException{
        System.out.print("Enter patient unique ID: ");
        String person_id= Reader.next();
        boolean person_exists=false;
        for (int i=0;i<citizen_array.size();i++){
            citizen temp_citizen=citizen_arr.get(i);
            if (temp_citizen.citizen_id.equals(person_id)){
                person_exists=true;
                citizen person=temp_citizen;
            }
        }
        if (person_exists==false){
            System.out.println("Enter valid person ID");
            return;
        }
        boolean citizen_exists=false;
        for (int i=0;i<citizen_arr.size();i++){
            citizen temp=citizen_arr.get(i);
            if (temp.citizen_id.equals(id)){
                citizen_exists=true;
                break;
            }
        }
        if (citizen_exists==false){
            System.out.println("Citizen not found");
            return;
        }
        System.out.println("1.Search by area");
        System.out.println("2.Search by vaccine");
        System.out.println("3.exit");
        System.out.print("Enter option: ");
        int input=Reader.nextint();
        if (input==3){
            return;
        }
        else if (input==1){
            search_area(hospital_arr,person);
        }
        else {
            search_vaccine(hospital_arr,vaccine_arr,person);
        }
    }
    public static void search_area(ArrayList<hospital> hospital_arr,citizen person) throws IOException{
        System.out.println("Enter pincode:");
        String area=Reader.next();
        int size=hospital_arr.size();
        ArrayList<hospital> matches= new ArrayList<hospital>();
        for (int i=0;i<size;i++){
            hospital temp_hospital=hospital_arr.get(i);
            if (temp_hospital.pincode.equals(area)){
                matches.add(temp_hospital);
                System.out.println(temp_hospital.hospital_id+" "+temp_hospital.name);
            }
        }
        System.out.print("Enter hospital id: ");
        String hos_id=Reader.next();
        boolean exists=false;
        for (int i=0;i<matches.size();i++){
            hospital final_hos=matches.get(i);
            if (hos_id.equals(final_hos.hospital_id)){
                exists=true;
                break;
            }
        }
        if (exists=false){
            System.out.println("Invalid hospital ID");
            return;
        }

    }
    public static void search_vaccine(ArrayList<hospital> hospital_arr,ArrayList<vaccine> vaccine_arr,citizen person){

    }
}

public class Ass1{
    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        
        int task;
        int hospital_count=100000;
        long citizen_count=100000000000L;
        ArrayList<vaccine> vaccine_arr= new ArrayList<vaccine>();
        ArrayList<citizen> citizen_arr= new ArrayList<citizen>();
        ArrayList<hospital> hospital_arr= new ArrayList<hospital>();

        System.out.println("CoWin Portal Initialized....");

        while (true){
            task= task_functions.menu();
            if (task==8){
                break;
            }
            if (task==1){
                task_functions.add_vaccine(vaccine_arr);
            }
            if (task==2){
                hospital_count++;
                task_functions.add_hospital(hospital_arr,hospital_count);
            }
            if (task==3){
                citizen_count++;
                task_functions.add_citizen(citizen_arr,citizen_count);
            }
            if (task==4){
                task_functions.create_slot(vaccine_arr,hospital_arr);
            }
            if (task==5){
                task_functions.book_slot(citizen_arr,hospital_arr,vaccine_arr);
            }
            System.out.println(hospital_arr.get(0).hospital_id);
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
