public class MyHashTable {
    MyLinkedList[] hashTable;
    int size;
    MyHashTable(int size) {
        this.hashTable = new MyLinkedList[size];
        this.size = size;

        for(int i = 0; i < size; i++){
            hashTable[i] = new MyLinkedList();
        }
    }
    void insert(int data) {
        int index = data % size; // hash function
        hashTable[index].add(data);
        //System.out.println("data in head" + hashTable[index].head.data);
    }
    int find(int data) {
        int index = data % size;

//        while(hashTable[index].)
        return data;
    }
    void printHash() {
        for (int i = 0; i < size; i++) {
            hashTable[i].printLinkedList();
        }
    }
}
