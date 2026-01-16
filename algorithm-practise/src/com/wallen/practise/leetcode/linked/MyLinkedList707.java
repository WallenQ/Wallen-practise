package com.wallen.practise.leetcode.linked;

/**
 * 707.设计链表
 * <p>
 * 你可以选择使用单链表或者双链表，设计并实现自己的链表。
 * <p>
 * 单链表中的节点应该具备两个属性：val 和 next 。val 是当前节点的值，next 是指向下一个节点的指针/引用。
 * <p>
 * 如果是双向链表，则还需要属性 prev 以指示链表中的上一个节点。假设链表中的所有节点下标从 0 开始。
 * <p>
 * 实现 MyLinkedList 类：
 * <p>
 * MyLinkedList() 初始化 MyLinkedList 对象。
 * int get(int index) 获取链表中下标为 index 的节点的值。如果下标无效，则返回 -1 。
 * void addAtHead(int val) 将一个值为 val 的节点插入到链表中第一个元素之前。在插入完成后，新节点会成为链表的第一个节点。
 * void addAtTail(int val) 将一个值为 val 的节点追加到链表中作为链表的最后一个元素。
 * void addAtIndex(int index, int val) 将一个值为 val 的节点插入到链表中下标为 index 的节点之前。如果 index 等于链表的长度，那么该节点会被追加到链表的末尾。如果 index 比长度更大，该节点将 不会插入 到链表中。
 * void deleteAtIndex(int index) 如果下标有效，则删除链表中下标为 index 的节点。
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入
 * ["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", "deleteAtIndex", "get"]
 * [ [1], [3], [1, 2], [1], [1], [1]]
 * 输出
 * [null, null, null, null, 2, null, 3]
 * <p>
 * 解释
 * MyLinkedList myLinkedList = new MyLinkedList();
 * myLinkedList.addAtHead(1);
 * myLinkedList.addAtTail(3);
 * myLinkedList.addAtIndex(1, 2);    // 链表变为 1->2->3
 * myLinkedList.get(1);              // 返回 2
 * myLinkedList.deleteAtIndex(1);    // 现在，链表变为 1->3
 * myLinkedList.get(1);              // 返回 3
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= index, val <= 1000
 * 请不要使用内置的 LinkedList 库。
 * 调用 get、addAtHead、addAtTail、addAtIndex 和 deleteAtIndex 的次数不超过 2000 。
 *
 * @Author qianwenlong
 * @Date 2025/10/20 13:34
 */
public class MyLinkedList707 {
    public static void main(String[] args) {
        //MyLinkedList707 myLinkedList707 = new MyLinkedList707();
        //myLinkedList707.addAtHead(1);
        //myLinkedList707.addAtTail(3);
        //myLinkedList707.addAtIndex(1, 2);
        //System.out.println(myLinkedList707.get(1));
        //myLinkedList707.deleteAtIndex(0);
        //System.out.println(myLinkedList707.get(1));

        //["MyLinkedList","addAtHead","addAtHead","addAtHead","addAtIndex","deleteAtIndex","addAtHead","addAtTail","get","addAtHead","addAtIndex","addAtHead"]
        //[[7],[2],[1],[3,0],[2],[6],[4],[4],[4],[5,0],[6]]
        //MyLinkedList707 myLinkedList707 = new MyLinkedList707();
        //myLinkedList707.addAtHead(7);
        //myLinkedList707.addAtHead(2);
        //myLinkedList707.addAtHead(1);
        //myLinkedList707.addAtIndex(3, 0);
        //myLinkedList707.deleteAtIndex(2);
        //myLinkedList707.addAtHead(6);
        //myLinkedList707.addAtTail(4);
        //myLinkedList707.get(4);
        //myLinkedList707.addAtHead(4);
        //myLinkedList707.addAtIndex(5, 0);
        //myLinkedList707.addAtHead(6);

        //["MyLinkedList","addAtHead","addAtTail","addAtIndex","get","deleteAtIndex","get"]
        //[[1],[3],[1,2],[1],[1],[1]]
        //MyLinkedList707 myLinkedList707 = new MyLinkedList707();
        //myLinkedList707.addAtHead(1);
        //myLinkedList707.addAtTail(3);
        //myLinkedList707.addAtIndex(1,2);
        //myLinkedList707.get(1);
        //myLinkedList707.deleteAtIndex(1);
        //myLinkedList707.get(1);

        //["MyLinkedList","addAtHead","addAtIndex","addAtIndex","addAtHead","deleteAtIndex","addAtIndex","addAtHead","addAtTail","addAtHead","get","addAtTail","addAtTail","addAtIndex","addAtTail","addAtTail","addAtHead","addAtTail","addAtHead","addAtTail","addAtHead","addAtTail","addAtTail","addAtHead","addAtTail","addAtIndex","addAtHead","deleteAtIndex","addAtHead","addAtHead","addAtHead","addAtHead","addAtIndex","addAtHead","addAtTail","addAtHead","deleteAtIndex","addAtTail","deleteAtIndex","addAtTail","addAtTail","addAtTail","addAtTail","addAtHead","addAtTail","get","addAtIndex","get","deleteAtIndex","addAtTail","addAtHead","addAtTail","addAtTail","addAtIndex","addAtHead","addAtHead","addAtHead","addAtHead","addAtHead","addAtTail","deleteAtIndex","deleteAtIndex","addAtHead","addAtHead","addAtTail","addAtHead","get","addAtIndex","addAtIndex","get","addAtTail","get","addAtTail","deleteAtIndex","get","addAtTail","addAtTail","addAtHead","addAtTail","deleteAtIndex","addAtHead","addAtHead","addAtHead","deleteAtIndex","addAtTail","addAtIndex","addAtTail","addAtTail","addAtIndex","addAtIndex","addAtHead","addAtIndex","addAtHead","addAtHead","addAtTail","addAtIndex","addAtTail","get","addAtHead","addAtTail","addAtHead","addAtHead"]
        //[[86],[1,54],[1,14],[83],[4],[3,18],[46],[3],[76],[5],[79],[74],[7,28],[68],[16],[82],[24],[30],[96],[21],[79],[66],[2],[2],[7,57],[59],[21],[19],[55],[46],[17],[16,41],[97],[85],[63],[30],[11],[16],[91],[29],[47],[20],[12],[80],[15],[12,8],[21],[30],[11],[54],[51],[41],[8,88],[62],[7],[59],[73],[73],[55],[9],[7],[49],[99],[17],[44],[11],[26,86],[10,99],[19],[71],[29],[32],[2],[3],[16],[2],[83],[31],[3],[23],[64],[96],[27],[81],[12,78],[21],[69],[5,35],[8,72],[60],[19,73],[55],[83],[86],[31,70],[49],[19],[64],[22],[25],[13]]

        MyLinkedList707 myLinkedList707 = new MyLinkedList707();
        myLinkedList707.addAtHead(86);
        myLinkedList707.addAtIndex(1,54);
        myLinkedList707.addAtIndex(1,14);
        myLinkedList707.deleteAtIndex(4);
        myLinkedList707.addAtIndex(3,18);
        myLinkedList707.addAtTail(3);
        myLinkedList707.addAtHead(76);
        myLinkedList707.get(5);
        myLinkedList707.addAtTail(79);
        myLinkedList707.addAtTail(74);
        myLinkedList707.addAtIndex(7,28);
        myLinkedList707.addAtTail(68);
        myLinkedList707.addAtHead(82);
        myLinkedList707.addAtTail(24);
        myLinkedList707.addAtHead(30);
        myLinkedList707.addAtTail(96);
        myLinkedList707.addAtHead(21);
        myLinkedList707.addAtTail(79);
        myLinkedList707.addAtTail(66);
        myLinkedList707.addAtHead(2);
        myLinkedList707.addAtTail(2);
        myLinkedList707.addAtIndex(7,57);
        myLinkedList707.addAtHead(59);
        myLinkedList707.deleteAtIndex(21);
        myLinkedList707.addAtHead(19);
        myLinkedList707.addAtHead(55);
        myLinkedList707.addAtHead(46);
        myLinkedList707.addAtHead(17);
        myLinkedList707.addAtIndex(16,41);
        myLinkedList707.addAtHead(97);
        myLinkedList707.addAtTail(85);
        myLinkedList707.addAtHead(63);
        myLinkedList707.deleteAtIndex(30);
        myLinkedList707.addAtTail(11);
        myLinkedList707.deleteAtIndex(16);
        myLinkedList707.addAtTail(91);
        myLinkedList707.addAtTail(29);
        myLinkedList707.addAtTail(47);
        myLinkedList707.addAtTail(20);
        myLinkedList707.addAtHead(12);
        myLinkedList707.addAtTail(80);
        myLinkedList707.get(15);
        myLinkedList707.addAtIndex(12,8);
        myLinkedList707.get(21);
        myLinkedList707.deleteAtIndex(30);
        myLinkedList707.addAtTail(11);
        myLinkedList707.addAtHead(54);
        myLinkedList707.addAtTail(51);
        myLinkedList707.addAtTail(41);
        myLinkedList707.addAtIndex(8,88);
        myLinkedList707.addAtHead(62);
        myLinkedList707.addAtHead(7);
        myLinkedList707.addAtHead(59);
        myLinkedList707.addAtHead(73);
        myLinkedList707.addAtHead(73);
        myLinkedList707.addAtTail(55);
        myLinkedList707.deleteAtIndex(9);
        myLinkedList707.deleteAtIndex(7);
        myLinkedList707.addAtHead(49);
        myLinkedList707.addAtHead(99);
        myLinkedList707.addAtTail(17);
        myLinkedList707.addAtHead(44);
        myLinkedList707.get(11);
        myLinkedList707.addAtIndex(26,86);
        myLinkedList707.addAtIndex(10,99);
        myLinkedList707.get(19);
        myLinkedList707.addAtTail(71);
        myLinkedList707.get(29);
        myLinkedList707.addAtTail(32);
        myLinkedList707.deleteAtIndex(2);
        myLinkedList707.get(3);
        myLinkedList707.addAtTail(16);
        myLinkedList707.addAtTail(2);
        myLinkedList707.addAtHead(83);
        myLinkedList707.addAtTail(31);
        myLinkedList707.deleteAtIndex(3);
        myLinkedList707.addAtHead(23);
        myLinkedList707.addAtHead(64);
        myLinkedList707.addAtHead(96);
        myLinkedList707.deleteAtIndex(27);
        myLinkedList707.addAtTail(81);
        myLinkedList707.addAtIndex(12,78);
        myLinkedList707.addAtTail(21);
        myLinkedList707.addAtTail(69);
        myLinkedList707.addAtIndex(5,35);
        myLinkedList707.addAtIndex(8,72);
        myLinkedList707.addAtHead(60);
        myLinkedList707.addAtIndex(19,73);
        myLinkedList707.addAtHead(55);
        myLinkedList707.addAtHead(83);
        myLinkedList707.addAtTail(86);
        myLinkedList707.addAtIndex(31,70);
        myLinkedList707.addAtTail(49);
        myLinkedList707.get(19);
        myLinkedList707.addAtHead(64);
        myLinkedList707.addAtTail(22);
        myLinkedList707.addAtHead(25);
        myLinkedList707.addAtHead(13);
    }

    public static class LinkedNode {
        int val;
        LinkedNode next;

        LinkedNode(int val) {
            this.val = val;
        }
    }

    int size;
    LinkedNode node;

    public MyLinkedList707() {
        this.node = null;
        this.size = 0;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        LinkedNode current = node;
        int i = 0;
        while (i < index) {
            current = current.next;
            i++;
        }
        return current == null ? -1 : current.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) {
            return;
        }

        LinkedNode linkedNode = new LinkedNode(val);
        if (size == 0) {
            this.node = linkedNode;
        } else {
            LinkedNode pre = null;
            LinkedNode current = node;
            if (index == 0){
                this.node = linkedNode;
            } else {
                int i = 0;
                while (i < index) {
                    pre = current;
                    current = current.next;
                    i++;
                }
            }
            if (pre != null) {
                pre.next = linkedNode;
            }
            linkedNode.next = current;
        }

        size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        LinkedNode pre = null;
        LinkedNode current = node;

        int i = 0;
        while (i < index) {
            pre = current;
            current = current.next;
            i++;
        }
        if (pre == null) {
            this.node = current.next;
        } else {
            pre.next = current.next;
        }
        size--;
    }
}
