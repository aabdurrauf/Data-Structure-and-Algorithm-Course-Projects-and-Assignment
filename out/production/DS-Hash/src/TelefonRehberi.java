public class TelefonRehberi {
    public static void main(String[] args) {
        HashTable telefonRehberi = new HashTable(26);
        telefonRehberi.insert("Ammar, 552 123 45 67");
        telefonRehberi.insert("Mehmet, 555 591 09 21");
        telefonRehberi.insert("Arda, 561 719 50 38");
        telefonRehberi.insert("Yusuf, 505 220 40 60");
        telefonRehberi.insert("Ege, 505 454 46 83");
        telefonRehberi.insert("Hatice, 561 613 31 98");
        telefonRehberi.insert("Fahri, 501 763 99 65");
        telefonRehberi.insert("Fatih, 524 441 00 45");
        telefonRehberi.insert("Fatma, 507 303 96 23");
        telefonRehberi.insert("Umut, 524 654 42 21");
        telefonRehberi.insert("Erdem, 561 321 27 88");
        telefonRehberi.insert("Kaan, 555 812 00 23");
        telefonRehberi.insert("Pelinsu, 505 789 12 34");
        telefonRehberi.insert("Hilal, 552 1254 03 32");
        telefonRehberi.insert("Demiral, 561 615 77 90");
        telefonRehberi.insert("George, 506 868 31 54");
        telefonRehberi.insert("Kerem, 510 109 88 14");
        telefonRehberi.insert("Mustafa, 505 707 55 08");
        telefonRehberi.insert("Melih, 516 720 89 38");
        telefonRehberi.insert("Ali, 561 611 69 58");

        telefonRehberi.printHashTable();
    }

}

class HashTable {
    LinkedList[] hashTable;
    int size;
    HashTable(int size) {
        this.hashTable = new LinkedList[size];
        this.size = size;

        for (int i = 0; i < size; i++){
            hashTable[i] = new LinkedList();
        }
    }
    void insert(String data) {
        char ilkHarf = data.charAt(0);
        if (ilkHarf >= 97 && ilkHarf <= 122){ilkHarf -= 32;}
        int index = ilkHarf - 65; // hash function
        String[] isim_numara = data.split(", ");
        try {
            hashTable[index].add(isim_numara[0], isim_numara[1]);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("Hatali giris");
        }
    }
    String find(String isim) {
        char ilkHarf = isim.charAt(0);
        if (ilkHarf >= 97 && ilkHarf <= 122){ilkHarf -= 32;}
        int index = ilkHarf - 65; // hash function
        try{
            if (hashTable[index].head != null){
                KisiNumara temp = hashTable[index].head;
                while (temp != null){
                    if (temp.isim.equals(isim)){
                        return temp.isim + ", " + temp.numara;
                    }
                    temp = temp.next;
                }
            }
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("hatali giris");
        }
        return "kisi bulunamadi";
    }
    void delete(String isim){
        char ilkHarf = isim.charAt(0);
        if (ilkHarf >= 97 && ilkHarf <= 122){ilkHarf -= 32;}
        int index = ilkHarf - 65; // hash function
        try{
            hashTable[index].delete(isim);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("hatali giris");
        }
    }
    void printHashTable(){
        for (LinkedList i : hashTable){
            i.printLinkedList();
        }
    }
}

class LinkedList {
    KisiNumara head;
    LinkedList(){
        head = null;
    }

    public void add(String isim, String numara) {
        if (head == null){
            head = new KisiNumara(isim, numara);
        }
        else{
            KisiNumara start = head;
            while (start.next != null){
                start = start.next;
            }
            start.next = new KisiNumara(isim, numara);
        }
    }
    void delete(String isim) {
        if(head != null){
            if (head.isim.equals(isim)){
                head = head.next;
                System.out.println("kisi silindi");
            }
            else {
                KisiNumara temp = head;
                while(temp.next != null){
                    if (temp.next.isim.equals(isim)){
                        temp.next = temp.next.next;
                        System.out.println("kisi silindi");
                        break;
                    }
                    temp = temp.next;
                }
            }
        }
    }
    void printLinkedList() {
        KisiNumara temp = head;
        if (temp == null){
            System.out.println("null");
        }
        else{
            while(temp != null){
                System.out.print(temp.isim + ", " + temp.numara);
                if (temp.next != null){
                    System.out.print(" -> ");
                }
                temp = temp.next;
            }
            System.out.println();
        }
    }
}

class KisiNumara {
    String isim;
    String numara;
    KisiNumara next;
    KisiNumara(String isim, String numara){
        this.isim = isim;
        this.numara = numara;
        next = null;
    }
}