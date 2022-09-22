package ReerseSignleArray;

public class lianbiao {
    singleArray head;
    singleArray h=head;
    void add(singleArray node){

        if(head!=null){
            while (head.next!=null){
                head=head.next;
            }
            head.next=node;
            node.next=null;
            head=h;
        }else {
            head=node;
            node.next=null;
            h=head;
        }
    }

    void reverse(){
        if(head!=null){
            singleArray temp=new singleArray();
            singleArray aa;
            while (head!=null){
                aa=head.next;   //先把head的下一个节点存储起来，因为下一步会让head指向temp后面的链表，不保存会丢失head后面的节点
                head.next=temp.next; //让head指向temp后面的链表
                temp.next=head; //再让temp指向head

                head=aa;    //接下来又找head的下一个节点
            }
            h=head=temp.next;   //temp的开头一个节点只是过程需要，最终不用
        }

    }

    void show(){
        while (head!=null){
            System.out.println(head.name);
            head=head.next;
        }
        head=h;
    }
}
