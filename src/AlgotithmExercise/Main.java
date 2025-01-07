package AlgotithmExercise;


import AlgotithmExercise.BinaryTree.TreeNode;
import AlgotithmExercise.DoublePointer.ListNode;
import AlgotithmExercise.LinkedList.MyLinkedList;

import java.util.*;
import java.util.concurrent.Semaphore;

public class Main {

    private Semaphore aSem = new Semaphore(1);
    private Semaphore bSem = new Semaphore(0);
    private Semaphore cSem = new Semaphore(0);

    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println("args[i] = " + args[i]);
        }
        System.out.println("args = " + args);
        System.out.println("Hello World!");
        new Main().test();
    }

    public void test() {
        int i =12;
        i*=++i;
        System.out.println("i*=++i = " + i);
    }

}