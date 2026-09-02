import java.util.Scanner;

public class TreeDemo {
    public static void main(String[] args) {
        Node5 root = Node5.createTree();
        System.out.println("Tree Created");
    }
}

class Node5{
    int data;
    Node5 left;
    Node5 right;
}