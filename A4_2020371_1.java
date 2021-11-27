import java.util.Scanner;
import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.ArrayList;

public class A4_2020371_1{
    private static class book extends Object{
        String title;
        String ISBN;
        String barcode;
        public book(String title,String ISBN,String barcode){
            this.title=title;
            this.ISBN=ISBN;
            this.barcode=barcode;
        }
        public String get_title(){
            return title;
        }
        public String get_ISBN(){
            return ISBN;
        }
        public String get_barcode(){
            return barcode;
        }
    }
    
    static class book_comparator implements Comparator<book>{
        @Override
        public int compare(book b1, book b2){
            int result= b1.get_title().compareTo(b2.get_title());
            if (result!=0){
                return result;
            }
            result= b1.get_ISBN().compareTo(b2.get_ISBN());
            if (result!=0){
                return result;
            }
            result= b1.get_barcode().compareTo(b2.get_barcode());
            return result;
        }
    }
    
    private class bill{
        static ArrayList<book> book_arr= new ArrayList<book>();
        public static ArrayList<book> get_bill(){
            return book_arr;
        }
    }
    
    private class menu{
        public static void start() throws Exception{
            int[] return_vals= new int[2];
            Reader.init(System.in);
            System.out.print("Enter the number of books (N): ");
            int n=Reader.nextint();
            System.out.print("Enter the nubmer of racks (K): ");     
            int k=Reader.nextint();
            while (true){
                System.out.println("=================================");
                System.out.println("1. Input book in the bill.");
                System.out.println("2. Display the rack.");
                System.out.println("3. Exit");
                System.out.println("=================================");
                System.out.print("Enter the operation:");
                int operation = Reader.nextint();
                if (operation==3){
                    break;
                }
                if (operation==1){
                    input_book();
                }
                if (operation==2){
                    display_rack(n,k);
                }
            }  
            return;   
        }
        public static void input_book() throws Exception{
            Scanner scanner=new Scanner(System.in);
            System.out.print("Enter the title of the book: ");
            String title=scanner.nextLine();
            System.out.print("Enter the ISBN of the book: ");
            String ISBN=Reader.next();
            System.out.print("Enter the barcode of the book: ");
            String barcode=Reader.next();
            book temp_book= new book(title,ISBN, barcode);
            ArrayList<book> book_arr= bill.get_bill();
            book_arr.add(temp_book);
            System.out.println("The book "+title+" has been added to the bill.");
        }
        public static void display_rack(int n,int k){
            ArrayList<book> book_arr= bill.get_bill();
            int a=n/k;
            Collections.sort(book_arr, new book_comparator());
            for (int i=0;i<k;i++){
                System.out.println("Rack "+(i+1)+":");
                for (int j=0;j<a;j++){
                    book temp_book=book_arr.get((i*a)+j);
                    System.out.print(temp_book.get_title()+" "+temp_book.get_ISBN()+" "+temp_book.get_barcode()+"        ");
                }
                System.out.println("  ");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        menu.start();
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