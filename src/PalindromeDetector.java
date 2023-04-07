import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PalindromeDetector {
    static boolean isPalindrome(String cumle){
        // boş cümle ise yanlış
        if (cumle.length() == 0) return false;
        // sadece bir harf bulunursa doğru
        else if (cumle.length() == 1) return true;

        MyStack<Character> myStack1 = new MyStack<>();
        MyStack<Character> myStack2 = new MyStack<>();

        // cümledeki her karakter kontrol edilir. A-Z vyea a-z ise MyStack1'e eklenir
        Scanner lineScanner = new Scanner(cumle);
        while (lineScanner.hasNext()){
            String word = lineScanner.next();
            for(int i = 0; i < word.length(); i++){
                if (isAlpha(word.charAt(i))){
                    myStack1.push(word.charAt(i));
                }
            }
        }

        // myStack1'deki harflerin yarısı myStack2'ye aktarılır
        int myStack1Size = myStack1.size();
        for (int i = 0; i < myStack1Size/2; i++)
            myStack2.push(myStack1.pop());

        // cümlenin boyutu tek sayı ise 1. stack pop edilir
        if ((myStack1.size() + myStack2.size()) % 2 != 0)
            myStack1.pop();

        // myStack1 ile myStack2 arasında karakterler karşılaştırlır
        for(int i = 0; i < myStack1.size(); i++){
            int c1 = myStack1.pop();
            int c2 = myStack2.pop();

            // karakterler büyük veya küçük olarak bakılmaksızın
            // ASCII kodları kontrol edilir.
            // karakterler farklı ise Yanlış (false) değeri döndürür
            if((c1 != c2) && (c1 != c2 + 32) && (c2 != c1 + 32)) return false;
        }
        return true;
    }

    // parametre olarak alınan karakteri bir harf olup olmadığını kontrol eder
    static boolean isAlpha(char c){
        return (c >= 65 && c <= 90) || (c >= 97 && c <= 122);
    }

    public static void main(String[] args) throws FileNotFoundException{
        File file = new File("D:\\Programming\\Java\\DS-HW-3\\src\\odev.txt");
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()){
            String satir = scanner.nextLine();
            if (isPalindrome(satir))
                System.out.println(satir +
                        "\n^^^ BİR PALİNDROMDUR ^^^\n");
            else
                System.out.println(satir +
                        "\n^^^ PALİNDROM DEĞİLDİR ^^^\n");
        }
    }
}

// MyStack sınıfı uygulamak için linked list kullanılır
class MyStack <T>{
    Node<T> top;

    MyStack(){top = null;}

    void push(T value){
        if (top == null){
            top = new Node<>(value);
        }
        else{
            Node<T> temp = new Node<>(value);
            temp.next = top;
            top = temp;
        }
    }

    T pop(){
        if (isEmpty()) return null;
        else {
            T value = top.value;
            top = top.next;
            return value;
        }
    }

    int size(){
        Node<T> temp = top;
        int n = 0;
        while (temp!=null) {
            temp = temp.next;
            n++;
        }
        return n;
    }

    boolean isEmpty(){
        return top == null;
    }

    void printStack(){
        Node<T> temp = top;
        while (temp!=null) {
            System.out.print(temp.value);
            temp = temp.next;
        }
    }
}

class Node <T>{
    T value;
    Node<T> next;

    Node(T value){
        this.value = value;
        next = null;
    }
}
