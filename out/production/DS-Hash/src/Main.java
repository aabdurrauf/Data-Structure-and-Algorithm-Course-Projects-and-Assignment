import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MyHashTable hashTable = new MyHashTable(10);
//        hashTable.insert(10);
//        hashTable.insert(2);
//        hashTable.insert(13);
//        hashTable.insert(90);
//        hashTable.insert(3);
//        hashTable.insert(24);
//        hashTable.insert(76);
//        hashTable.insert(81);
//        hashTable.insert(8);
//        hashTable.insert(40);
//        hashTable.insert(91);
//        hashTable.insert(10); // repeated value
//        hashTable.insert(1);
//        hashTable.insert(7);
//        hashTable.insert(15);
//        hashTable.insert(23);
        Random rand = new Random();
        for(int i = 0; i <30; i++) {
            hashTable.insert(rand.nextInt(100));
        }

        hashTable.printHash();
    }
}