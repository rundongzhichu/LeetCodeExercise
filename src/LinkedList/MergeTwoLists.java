package LinkedList;

public class MergeTwoLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode();
        ListNode tail = head;

        while (list1!=null || list2!=null){

            if(list1 != null && list2 == null){ tail.next = list1;break;}
            if(list1 == null && list2 != null) {tail.next = list2;break;}

            while (list1!=null & list2 != null){
                ListNode node1 = list1;
                ListNode node2 = list2;
                if(node1.val <= node2.val){
                    tail.next=node1;
                    list1 = list1.next;
                    tail = tail.next;
                }else{
                    tail.next=node2;
                    list2 = list2.next;
                    tail = tail.next;
                }
            }
        }
        return head.next;
    }
}
