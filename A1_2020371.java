import java.util.Scanner;
import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.ArrayList;

class citizen{
    String name;
    int age;
    String citizen_id;
    String status;
    vaccine vac;
    int vaccine_day;
    int doses;
    public citizen(String name, int age, String citizen_id,String status){
        this.name=name;
        this.age=age;
        this.citizen_id=citizen_id;
        this.status=status;
        this.vac=vac;
        this.vaccine_day=vaccine_day;
        this.doses=doses;
    }

    public static void add_citizen(ArrayList<citizen> citizen_arr) throws Exception{
        System.out.print("Citizen Name: ");
        String name= Reader.next();
        System.out.print("Age: ");
        int age= Reader.nextint();
        System.out.print("Unique ID: ");
        String id= Reader.next();
        if (age<18){
            System.out.println("Only above 18 are allowed");
            return;
        }
        String status="REGISTERED";
        citizen temp= new citizen(name,age,id,status);
        citizen_arr.add(temp);
        System.out.println("Citizen Name:"+name+", Age:"+age+", Unique ID:"+id);
    }

    public static void check_status(ArrayList<citizen> citizen_arr)throws IOException{
        System.out.print("Enter patient Unique ID: ");
        String id= Reader.next();
        citizen person=null;
        boolean person_exists=false;
        for (int i=0;i<citizen_arr.size();i++){
            if (id.equals(citizen_arr.get(i).citizen_id)){
                person=citizen_arr.get(i);
                person_exists=true;
                break;
            }
        }
        if (person_exists==false){
            System.out.println("Patient not registered");
            return;
        }
        System.out.println(person.status);
        if (person.status.equals("Citizen REGISTERED")){
        }
        else if (person.status.equals("PARTIALLY VACCINATED")){
            System.out.println("Vaccine given: "+person.vac.name);
            System.out.println("Number of doses given: "+person.doses);
            System.out.println("Due date: "+(person.vaccine_day+person.vac.gap_doses));
        }
        else if(person.status.equals("FULLY VACCINATED")){
            System.out.println("Vaccine given: "+person.vac.name);
            System.out.println("Number of doses given: "+person.doses);
        }  
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

    public static void add_vaccine(ArrayList<vaccine> vaccine_arr) throws Exception{
        System.out.print("Vaccine Name: ");
        String name= Reader.next();
        System.out.print("Number of doses: ");
        int doses= Reader.nextint();
        int gap_doses=0;
        if (doses>1){
            System.out.print("Gap between doses: ");
            gap_doses= Reader.nextint();
        }
        vaccine temp= new vaccine(name, doses, gap_doses);
        vaccine_arr.add(temp);
        System.out.println("Vaccine Name:"+name+", Number of doses:"+doses+", Gap between doses:"+ gap_doses);
    }
}

class hospital{

    class slot{
        vaccine vac;
        int quantity;
        int day;
        public slot(vaccine vac, int quantity, int day){
            this.vac=vac;
            this.quantity=quantity;
            this.day=day;
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

    public static void add_hospital(ArrayList<hospital> hospital_arr, int hospital_count) throws Exception{
        System.out.print("Hospital Name: ");
        String name= Reader.next();
        System.out.print("Pincode: ");
        String pincode=Reader.next();
        String id=String.valueOf(hospital_count);
        hospital temp= new hospital(name,pincode,id);
        hospital_arr.add(temp);
        System.out.println("Hospital Name: "+name+", PinCode: "+pincode+", Unique ID: "+id);
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
        boolean day_present=false;
        for (int i=0;i<slots.size();i++){
            if (slots.get(i).get(0).day==day){
                day_present=true;
                slot temp_slot= new slot(vac,vac_quan,day);
                slots.get(i).add(temp_slot);
                break;
            }
        }
        if (day_present==false){
            slot temp_slot= new slot(vac,vac_quan,day);
            ArrayList<slot> temp_day= new ArrayList<slot>();
            temp_day.add(temp_slot);
            slots.add(temp_day);
        }
        System.out.println("Slot added to hospital "+hospital_id+" for Day: "+day+", Available quantity:"+vac_quan+" of Vaccine "+vac.name);
    }
    
    public static void list_slots(ArrayList<hospital> hospital_arr)throws IOException{
        System.out.print("Enter hospital Id: ");
        String hos_id=Reader.next();
        boolean exists=false;
        hospital hos=null;
        for (int i=0;i<hospital_arr.size();i++){
            hos=hospital_arr.get(i);
            if (hos_id.equals(hos.hospital_id)){
                exists=true;
                break;
            }
        }
        if (exists=false){
            System.out.println("Invalid hospital ID");
            return;
        }
        int times=hos.slots.size();
        for (int i=0;i<times;i++){
            ArrayList<hospital.slot> day_slots=hos.slots.get(i);
            int v_times= day_slots.size();
            for (int j=0;j<v_times;j++){
                hospital.slot temp_slot=day_slots.get(j);
                System.out.println("Day: "+temp_slot.day+" Vaccine: "+temp_slot.vac.name+" Available Qty: "+temp_slot.quantity);
            }
        }
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
        citizen person=null;
        for (int i=0;i<citizen_arr.size();i++){
            citizen temp_citizen=citizen_arr.get(i);
            if (temp_citizen.citizen_id.equals(person_id)){
                person_exists=true;
                person=temp_citizen;
            }
        }
        if (person_exists==false){
            System.out.println("Enter valid person ID");
            return;
        }
        if (person.status.equals("FULLY VACCINATED")){ 
            System.out.println("Already fully vaccinated");
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
            search_area(hospital_arr,person,vaccine_arr);
        }
        else {
            search_vaccine(hospital_arr,vaccine_arr,person);
        }
    }

    public static void search_area(ArrayList<hospital> hospital_arr,citizen person,ArrayList<vaccine> vaccine_arr) throws IOException{
        System.out.print("Enter Pincode: ");
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
        hospital final_hos=null;
        for (int i=0;i<matches.size();i++){
            final_hos=matches.get(i);
            if (hos_id.equals(final_hos.hospital_id)){
                exists=true;
                break;
            }
        }
        if (exists=false){
            System.out.println("Invalid hospital ID");
            return;
        }
        int min_day=0;
        if (person.status=="PARTIALLY VACCINATED"){
            min_day=person.vac.gap_doses+person.vaccine_day;
        }
        ArrayList<hospital.slot> slots_avail= new ArrayList<hospital.slot>();
        int times=final_hos.slots.size();
        for (int i=0;i<times;i++){
            ArrayList<hospital.slot> day_slots=final_hos.slots.get(i);
            if (day_slots.get(0).day>=min_day){
                int v_times= day_slots.size();
                for (int j=0;j<v_times;j++){
                    if (person.status=="PARTIALLY VACCINATED"){
                        if (day_slots.get(j).vac==person.vac){
                            slots_avail.add(day_slots.get(j));
                        }
                    }
                    else{
                        slots_avail.add(day_slots.get(j));
                    }
                }
            }
        }
        for (int i=0;i<slots_avail.size();i++){
            hospital.slot temp_slot=slots_avail.get(i);
            System.out.println(i+"-> Day:"+temp_slot.day+" Available Qty:"+temp_slot.quantity+" Vaccine:"+temp_slot.vac.name);
        }
        if (slots_avail.size()==0){
            System.out.println("No slots available");
            return;
        }
        System.out.print("Choose Slot: ");
        int slot_num=Reader.nextint();
        slots_avail.get(slot_num).quantity--;
        person.doses++;
        if (person.status.equals("REGISTERED")){
            person.status="PARTIALLY VACCINATED";
            person.vac=slots_avail.get(slot_num).vac;
        }
        if (person.doses==person.vac.doses){
            person.status="FULLY VACCINATED";
        }
        person.vaccine_day=slots_avail.get(slot_num).day;
        System.out.println(person.name+" vaccinated with "+slots_avail.get(slot_num).vac.name);
    }
    
    public static void search_vaccine(ArrayList<hospital> hospital_arr,ArrayList<vaccine> vaccine_arr,citizen person) throws IOException{
        int vac_times=vaccine_arr.size();
        System.out.print("Enter Vaccine name: ");
        String vac_name=Reader.next();
        boolean vac_exists=false;
        for (int i=0;i<vac_times;i++){
            vaccine vac_temp=vaccine_arr.get(i);
            if (vac_name.equals(vac_temp.name)){
                vac_exists=true;
                break;
            }
        }
        if (vac_exists=false){
            System.out.println("Not a valid vaccine");
            return;
        }
        ArrayList<hospital> matches= new ArrayList<hospital>();
        int hos_size=hospital_arr.size();
        for (int i=0;i<hos_size;i++){
            hospital temp_hospital=hospital_arr.get(i);
            int days_size=temp_hospital.slots.size();
            boolean hospital_valid=false;
            for (int j=0;j<days_size;j++){
                if (hospital_valid==true){
                    break;
                }
                ArrayList<hospital.slot> day_slots=temp_hospital.slots.get(j);
                int size=day_slots.size();
                for (int k=0;k<size;k++){
                    if (day_slots.get(k).vac.name.equals(vac_name)){
                        hospital_valid=true;
                        matches.add(temp_hospital);
                        System.out.println(temp_hospital.hospital_id+" "+temp_hospital.name);
                        break;
                    }
                }
            }
        }
        System.out.print("Enter hospital id: ");
        String hos_id=Reader.next();
        hospital final_hos=null;
        boolean exists=false;
        for (int i=0;i<matches.size();i++){
            final_hos=matches.get(i);
            if (hos_id.equals(final_hos.hospital_id)){
                exists=true;
                break;
            }
        }
        if (exists==false){
            System.out.println("Invalid hospital ID");
            return;
        }
        int min_day=0;
        if (person.status=="PARTIALLY VACCINATED"){
            min_day=person.vac.gap_doses+person.vaccine_day;
        }
        ArrayList<hospital.slot> slots_avail= new ArrayList<hospital.slot>();
        int times=final_hos.slots.size();
        for (int i=0;i<times;i++){
            ArrayList<hospital.slot> day_slots=final_hos.slots.get(i);
            if (day_slots.get(0).day>=min_day){
                int v_times= day_slots.size();
                for (int j=0;j<v_times;j++){
                    if(day_slots.get(j).vac.name.equals(vac_name)){
                        if (person.status=="PARTIALLY VACCINATED"){
                            if (day_slots.get(j).vac==person.vac){
                                slots_avail.add(day_slots.get(j));
                            }
                        }
                        else{
                            slots_avail.add(day_slots.get(j));
                        }
                    }
                }
            }
        }
        for (int i=0;i<slots_avail.size();i++){
            hospital.slot temp_slot=slots_avail.get(i);
            System.out.println(i+"-> Day:"+temp_slot.day+" Available Qty:"+temp_slot.quantity+" Vaccine:"+temp_slot.vac.name);
        }
        if (slots_avail.size()==0){
            System.out.println("No slots available");
            return;
        }
        System.out.print("Choose Slot: ");
        int slot_num=Reader.nextint();
        slots_avail.get(slot_num).quantity--;
        person.doses++;
        if (person.status.equals("REGISTERED")){
            person.status="PARTIALLY VACCINATED";
            person.vac=slots_avail.get(slot_num).vac;
        }
        if (person.doses==person.vac.doses){
            person.status="FULLY VACCINATED";
        }
        person.vaccine_day=slots_avail.get(slot_num).day;
        System.out.println(person.name+" vaccinated with "+slots_avail.get(slot_num).vac.name);
    }
}

public class A1_2020371{
    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        
        int task;
        int hospital_count=100000;
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
                vaccine.add_vaccine(vaccine_arr);
            }
            if (task==2){
                hospital_count++;
                hospital.add_hospital(hospital_arr,hospital_count);
            }
            if (task==3){
                citizen.add_citizen(citizen_arr);
            }
            if (task==4){
                task_functions.create_slot(vaccine_arr,hospital_arr);
            }
            if (task==5){
                task_functions.book_slot(citizen_arr,hospital_arr,vaccine_arr);
            }
            if (task==6){
                hospital.list_slots(hospital_arr);
            }
            if (task==7){
                citizen.check_status(citizen_arr);
            }
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
