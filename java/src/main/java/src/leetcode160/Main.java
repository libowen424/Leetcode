package leetcode160;

  class ListNode {
      int val;
      ListNode next;

      ListNode(int x) {
          val = x;
          next = null;

      }
  }

public class Main
{
    public static void main(String[] args) {

    }
}


/**
 * 错误原因1：比较了val值，其实题目要求是比较节点是否相同
 * 错误原因2：在下面的代码中，会出现，如果没有相交，a,b最终都会走到终点，则，a先跳转到headB，而b则会继续到next成为null，报空指针错误
 */
 class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        while(a.val!=b.val)
        {
            if(a.next==null)
            {
                a=headB;
                b=b.next;
            }
            else if(b.next==null)
            {
                b=headA;
                a=a.next;
            }
            else
            {
                a=a.next;
                b=b.next;
            }
        }
        return a;
    }
}