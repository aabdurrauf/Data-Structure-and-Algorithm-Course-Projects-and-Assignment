public class MyLinkedList {
    MyNode head;
    MyLinkedList(){
        head = null;
    }
    public void add(int data) {
        if (head == null){
            head = new MyNode(data);
        }
        else{
            MyNode temp = new MyNode(data);
            temp.next = head;
            head = temp;
        }
//    int getIndex(){}
//        MyNode temp = head;
//        while (temp.next != null){
//            temp = temp.next;
//        }
//        temp.next = new MyNode(data);
    }

    void printLinkedList(){
        MyNode temp = head;
        if (temp == null){
            System.out.println("null");
        }
        else{
            while(temp != null){
                System.out.print(temp.data);
                if (temp.next != null){
                    System.out.print(" -> ");
                }
                temp = temp.next;
            }
            System.out.println();
        }
    }
}

class MyNode{
    int data;
    MyNode next;

    MyNode(int data){
        this.data = data;
        this.next = null;
    }
}
