
public class Main {
    public static void main(String[] args) {
        Ders Math1 = new Ders("Matematik 1", "MAT1085",
                "Doç. Dr. Bahar Kırık Racz", 1, 5);
        Ders LinearAlg = new Ders("Lineer Cebir", "MAT1087",
                "Doç. Dr. Hülya Yılmaz", 1, 5);
        Ders IntroAlgorithm = new Ders("Algoritma ve Prog. Giriş", "BLM1003",
                "Dr. Öğr. Üyesi Eyüp Emre Ülkü", 1, 4);
        Ders IntroComputer = new Ders("Bilgisayar Müh. Giriş", "BLM1001",
                "Doç. Dr. Önder Demir", 1, 4);
        Ders Physics1 = new Ders("Fizik 1", "FZK1071",
                "Dr. Öğr. Üyesi Ömer Akgün", 1, 4);

        Ders Math2 = new Ders("Matematik 2", "MAT1086",
                "Dr. Öğr. Üyesi Nazlı Yazıcı Gözütok", 2, 5);
        Ders BilProg1 = new Ders("Bilgisayar Programlama 1", "BLM1002",
                "Doç. Dr. Buket Doğan", 2, 6);
        Ders BilDon = new Ders("Bilgisayar Donanımı", "BLM1004",
                "Dr. Öğr. Üyesi Ali Sarıkaş", 2, 6);
        Ders ISG = new Ders("İş Sağlığı ve güvenliği", "ISG1081",
                "Prof. Dr. İsmail Kıyak", 2, 3);
        Ders Physics2 = new Ders("Fizik 2", "FZK1072",
                "Dr. Öğr. Üyesi Ömer Akgün", 2, 4);

        Ders LogicGate = new Ders("Mantık Devreleri", "BLM2007",
                "Dr. Öğr. Üyesi Ali Sarıkaş", 3, 5);


        LinkedList liste = new LinkedList();
        // randomly adding Nodes to the linked list
        liste.add(new Node(Math2));
        liste.add(new Node(Math1));
        liste.add(new Node(BilDon));
        liste.add(new Node(LinearAlg));
        liste.add(new Node(ISG));
        liste.add(new Node(IntroAlgorithm));
        liste.add(new Node(IntroComputer));
        liste.add(new Node(Physics1));
        liste.add(new Node(BilProg1));
        liste.add(new Node(LogicGate)); // semester 3
        liste.add(new Node(Physics2));


        System.out.println("liste boyutu: " + liste.size());
        System.out.println("\nders kodu MAT1087: " + liste.getByCode("MAT1087").ders_ismi);
        System.out.println("ders kodu MAT1087: " + liste.getByCode("BLM1002").ders_ismi);
        System.out.println("\nsemester 1: ");
        liste.listSemester(1);
        System.out.println("\n2-4 index: ");
        liste.getByRange(2, 4);

        liste.remove(LinearAlg);
        liste.remove(IntroComputer);
        System.out.println("\nafter removing two nodes: \nsemester 1 dersleri:");
        liste.listSemester(1);

        System.out.println("\nusing next method: ");
        Node listeNext = liste.next();
        while(listeNext != null){
            System.out.println(listeNext.ders.ders_ismi);
            listeNext = liste.next();
        }
        liste.setPointerToZero();

        System.out.println("\nusing next in semester method: ");
        Node listeNext2 = liste.nextInSemester();
        while(listeNext2 != null){
            System.out.println(listeNext2.ders.ders_ismi);
            listeNext2 = liste.nextInSemester();
        }

        System.out.println();
        liste.disable(ISG);
        // disable olan dersi getByCode ile almaya calisir
        System.out.println("ISG: \n" + liste.getByCode("ISG1081"));
        liste.enable(ISG);
        System.out.println("ISG: \n" + liste.getByCode("ISG1081").ders_ismi);

        liste.disable(BilDon);
        System.out.println("\nsemester 2 with bilgisayar donanimi disabled: ");
        liste.listSemester(2);

        System.out.println();
        liste.disable(IntroAlgorithm);
        liste.disable(Physics2);
        liste.showDisabled();

        CircularLinkedList liste2 = new CircularLinkedList();
        liste2.add(new Node(Math2));
        liste2.add(new Node(Math1));
        liste2.add(new Node(BilDon));
        liste2.add(new Node(LinearAlg));
        liste2.add(new Node(ISG));
        liste2.add(new Node(LogicGate)); // semester 3
        liste2.add(new Node(IntroAlgorithm));
        liste2.add(new Node(IntroComputer));
        liste2.add(new Node(Physics1));
        liste2.add(new Node(BilProg1));
        liste2.add(new Node(Physics2));

        System.out.println();
        liste2.showInSemester(1);
        System.out.println();
        liste2.showInSemester(2);
        System.out.println();
        liste2.showInSemester(3);

        System.out.println("\ncircular linked list using next method: ");
        Node circularListeNext = liste2.next();
        while(circularListeNext != null){
            System.out.println(circularListeNext.ders.ders_ismi);
            circularListeNext = liste2.next();
        }
        liste2.setPointerToZero();
    }
}

class LinkedList{
    protected Node head;

    public LinkedList(){
        head = null;
    }

    public void add(Node node){
        if (head != null) {
            node.next = head;
        }
        head = node;
    }

    public void remove(Ders ders){
        Node temp = head;
        if (head.ders == ders && head.enable){
            head = temp.next;
        }else{
            while(temp.next != null){
                if (temp.next.ders == ders){
                    if (temp.next.enable){ // control enable
                        temp.next = temp.next.next;
                    }
                    else{
                        System.out.println(ders.ders_kodu + " cannot be removed due to disabled");
                    }
                    break;
                }
                temp = temp.next;
            }
        }
    }

    int pointer = 0;
    public Node next(){
        Node temp = head;
        for (int i = 0; i < pointer; i++){
            temp = temp.next;
        }
        // control enable
        while (temp != null && !temp.enable){
            temp = temp.next;
            pointer++;
        }
        pointer++;
        return temp;
    }
    public void setPointerToZero(){
        pointer = 0;
    }

    int semesterPointer = 0;
    public Node nextInSemester(){
        Node temp = head;
        for (int i = 0; i < semesterPointer; i++){
            temp = temp.next;
        }
        int semester = temp.ders.semester;
        while (temp.next != null){
            semesterPointer++;
            if (temp.next.ders.semester == semester && temp.next.enable){ // control enable
                return temp.next;
            }
            temp = temp.next;
        }
        return null;
    }

    // disable olan nodelar sayılmayacak
    public int size(){
        int len = 0;
        Node temp = head;
        while (temp != null){
            if (temp.enable){
                // control enable
                len++;
            }
            temp = temp.next;
        }
        return len;
    }

    public Ders getByCode(String code){
        Node temp = head;
        while (temp.next != null){
            if (temp.ders.ders_kodu.equals(code)){
                if(temp.enable){
                    return temp.ders;
                }
                System.out.println(code + " is disabled");
                break;
            }
            temp = temp.next;
        }
        return null;
    }

    public void listSemester(int semester){
        Node temp = head;
        while (temp != null){
            if (temp.ders.semester == semester && temp.enable){ // control enable
                System.out.println(temp.ders.ders_ismi);
            }
            temp = temp.next;
        }
    }

    // head 0. index olarak kabul edilir
    public void getByRange(int start_index, int last_index){
        int x = last_index - start_index;
        if (start_index < 0 || last_index < 0 || x<0){
            throw new IllegalArgumentException("please enter a valid index");
        }
        Node temp = head;
        while(start_index>0){
            temp = temp.next;
            start_index--;
        }
        for (int i=0; i<=x; i++){
            if (temp.enable){ // control enable
                System.out.println(temp.ders.ders_ismi);
            }
            temp = temp.next;
        }
    }

    public void disable(Ders ders){
        Node temp = head;
        while(temp != null){
            if (temp.ders == ders){
                temp.enable = false;
                System.out.println(ders.ders_kodu + " disabled");
                break;
            }
            temp = temp.next;
        }
    }

    public void enable(Ders ders){
        Node temp = head;
        while(temp != null){
            if (temp.ders == ders){
                temp.enable = true;
                System.out.println(ders.ders_kodu + " enabled");
                break;
            }
            temp = temp.next;
        }
    }

    public void showDisabled(){
        Node temp = head;
        System.out.println("Disabled nodes: ");
        while(temp != null){
            if (!temp.enable){
                System.out.println(temp.ders.ders_kodu
                        + " - " + temp.ders.ders_ismi);
            }
            temp = temp.next;
        }
    }
}

class CircularLinkedList extends LinkedList{
    @Override
    public void add(Node node){
        super.add(node);

        // set up the circular linked list
        Node temp = head;
        while(temp.next != null){
            if (head.ders.semester == temp.next.ders.semester){
                head.nextSameSemester = temp.next;
                Node temp2 = temp.next;
                while (temp2.nextSameSemester != null &&
                        temp2.nextSameSemester != temp.next){
                    temp2 = temp2.nextSameSemester;
                }
                temp2.nextSameSemester = head;
                break;
            }
            temp = temp.next;
        }
    }

    @Override
    public void remove(Ders ders){
        Node temp = head;
        if (head.ders == ders && head.enable){
            head = temp.next;
            if (head.nextSameSemester != null){
                Node head2 = head.nextSameSemester;
                Node temp2 = head2.nextSameSemester;
                while(temp2.nextSameSemester != head ){
                    temp2 = temp2.nextSameSemester;
                }
                temp2.nextSameSemester = head2;
            }
        }else{
            while(temp.next != null){
                if (temp.next.ders == ders){
                    if (temp.next.enable){ // control enable
                        Node temp2 = temp.next;
                        temp.next = temp.next.next;
                        Node head2 = head;
                        while (head2.nextSameSemester != temp2 && head2.next != null){
                            head2 = head2.next;
                        }
                        head2.nextSameSemester = head2.nextSameSemester.nextSameSemester;
                        temp.nextSameSemester = temp.nextSameSemester.nextSameSemester;
                    }
                    else{
                        System.out.println(ders.ders_kodu + " cannot be removed due to disabled");
                    }
                    break;
                }
                temp = temp.next;
            }
        }

    }

    public void showInSemester(int semester){
        Node temp = head;
        System.out.println("Lectures in Semester " + semester);
        while (temp.next != null){
            if (temp.ders.semester == semester){
                Node head2 = temp;
                do{ System.out.println(temp.ders.ders_kodu + " " + temp.ders.ders_ismi);
                    temp = temp.nextSameSemester;
                }
                while(temp != null && temp != head2);
                break;
            }
            temp = temp.next;
        }
    }
}

class Ders{
    String ders_ismi;
    String ders_kodu;
    String ogretim_uyesi;
    int semester;
    int kredi;

    public Ders(String ders_ismi, String ders_kodu,
                String ogretim_uyesi, int semester, int kredi){
        this.ders_ismi = ders_ismi;
        this.ders_kodu = ders_kodu;
        this.semester = semester;
        this.ogretim_uyesi = ogretim_uyesi;
        this.kredi = kredi;
    }
}

class Node{
    Ders ders;
    Node next;
    Node nextSameSemester;
    boolean enable;

    public Node(Ders ders){
        this.ders = ders;
        this.enable = true;
        this.next = null;
        this.nextSameSemester = null;
    }
}