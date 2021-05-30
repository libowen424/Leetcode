package leetcode147;
  class ListNode {
      int val;
      ListNode next;
     ListNode(int x) { val = x; }
  }
public class Main {
    public static ListNode insertionSortList(ListNode head) {
        if(head==null||head.next==null){
            return head;
        }
        ListNode dummy =new ListNode(0);
        dummy.next=head;
        ListNode curr=head.next;
        while(curr!=null){
            ListNode pre = dummy;
            while(pre.next!=curr){
                if(pre.next.val>curr.val){
                    ListNode sortNode = curr;
                    sortNode.next=pre.next;
                    pre.next=sortNode;
                    curr=curr.next;
                    
                    break;
                }
                pre=pre.next;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(4);
        ListNode b= new ListNode(2);
        ListNode c = new ListNode(1);
        ListNode d = new ListNode(3);
        a.next=b;
        b.next=c;
        c.next=d;
        d.next=null;
        insertionSortList(a);
    }
}
