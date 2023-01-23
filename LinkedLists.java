public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
    int sum = 0, carry = 0;
    ListNode dummy = new ListNode(0);
    ListNode prev = dummy;
    while(l1 != null || l2 != null) {
        int l1val = (l1 == null ? 0 : l1.val);
        int l2val = (l2 == null ? 0 : l2.val);
        sum = l1val + l2val + carry;
        carry = sum / 10;
        prev.next = new ListNode(sum % 10);// % 10
        prev = prev.next;
        if (l1 != null) l1 = l1.next;
        if (l2 !=null) l2 = l2.next;
    }
    if(carry > 0) {
        prev.next = new ListNode(carry);
    }
    return dummy.next;
}
// l1 = [7,2,4,3], l2 = [5,6,4] --> [7,8,0,7]; [5][5]-->[1,0]
public ListNode addTwoNumbers445(ListNode l1, ListNode l2) {
    Stack<Integer> sk1 = new Stack<>(), sk2 = new Stack<>();
    ListNode dummy = null, resHead = dummy;
    ListNode h1 = l1, h2 = l2;
    int carry = 0;
    while(h1 != null) {
        sk1.push(h1.val);
        h1 = h1.next;
    }
    while(h2 != null) {
        sk2.push(h2.val);
        h2 = h2.next;
    }
    while(!sk1.isEmpty() || !sk2.isEmpty()) {
        int sum = carry;
        if (!sk1.isEmpty()) {
            sum += sk1.pop();
        }
        if (!sk2.isEmpty()) {
            sum += sk2.pop();
        }
        resHead = new ListNode(sum % 10);
        resHead.next = dummy;
        dummy = resHead;
        carry = sum / 10;
    }
    if(carry > 0) {
        ListNode carryNode = new ListNode(carry);
        carryNode.next = resHead;
        return carryNode;
    }
    return resHead;
}
// num1 = "11", num2 = "123" --> "134"
public String addStrings415(String num1, String num2) {
    https://github.com/HuiHuangSue/lc/blob/main/A-library/addString415.java
}
//Input: a = "1010", b = "1011" --> "10101"
public String addBinary67(String a, String b) {
    int i = a.length()-1, j = b.length()-1, carry = 0;
    StringBuilder sb = new StringBuilder();
    while(i >= 0 || j >= 0) {
        int x = (i >= 0 ? (a.charAt(i)-'0') : 0);
        int y = (j >= 0 ? (b.charAt(j)-'0') : 0);
        int sum = x + y + carry;
        sb.append(sum % 2);
        carry = (sum / 2);
        i--;
        j--;
    }
    if(carry > 0) sb.append(carry);
    return sb.reverse().toString();
}
// [4,3,2,1] --> [4,3,2,2]; [9,9] --> [1,0,0]
public int[] plusOne66(int[] digits) {
    if(digits[digits.length - 1] < 9) {
        digits[digits.length - 1]++;
        return digits;
    }
    int carry = 1;
    LinkedList<Integer> list = new LinkedList<>();
    for(int i = digits.length - 1; i>=0; i--) {
        int sum = digits[i] + carry;
        list.add(sum % 10);
        carry = sum / 10;
    }
    if (carry > 0) {
        list.add(1);
    }
    Collections.reverse(list);
    return list.stream().mapToInt(i->i).toArray();
}
// [2,1,5], k = 806  [1,0,2,1]
public List<Integer> addToArrayForm989(int[] num, int k) {
    LinkedList<Integer> res = new LinkedList<>();
    int carry = k;
    for(int i = num.length - 1; i >= 0; i--) {
        int sum = num[i] + carry;
        res.addFirst(sum % 10);
        carry = sum/10;
    }
    while(carry > 0) { // [0], 10000 carry>10
        res.addFirst(carry%10);
        carry/=10;
    }
    return res;
}

public String multiplytring43(String nums1, String nums2) {
    if (nums1 == null || nums1.length() == 0 || nums2 == null || nums2.length() == 0) return "0";
    int[] digits = new int[nums1.length() + nums2.length()];//len total
    StringBuilder res = new StringBuilder();
    for(int i = nums1.length()-1; i>=0;i--) {
        for(int j = nums2.length() - 1; j >= 0; j--) {
            int product = (nums1.charAt(i) - '0') * (nums2.charAt(j) - '0');
            int p1 = i + j, p2 = i + j + 1; //everytime, p1=i+j; i+j+1
            int sum = product + digits[p2];//settle down digits p1,p2
            digits[p1] += sum / 10; //carry
            digits[p2] = sum % 10;
        }
    }
    // digits[0,0,1,0,3];
    for(int digit : digits) {
        if(!(digit == 0 && res.length() == 0)) {//leading 0s
            res.append(digit);
        }
    }
    return res.length() == 0 ? "0" : res.toString();
}
