package Hash;

import java.util.Scanner;

public class HashDemo {
    public static void main(String[] args) {
        //创建哈希表
        HashTab  hashTab=new HashTab(7);

        //写一个简单的菜单
        String key="";
        Scanner sc=new Scanner(System.in);
        while (true){
            System.out.println("add:   添加雇员");
            System.out.println("list:  显示雇员");
            System.out.println("find:  查找雇员");
            System.out.println("exit:  退出系统");
            key=sc.next();
            switch (key){
                case "add":
                    System.out.println("输入id:");
                    int id=sc.nextInt();
                    System.out.println("输入名字:");
                    String name=sc.next();
                    //创建雇员
                    Emp emp=new Emp(id,name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "exit":
                    sc.close();
                    System.exit(0);
                case "find":
                    System.out.println("输入id:");
                    int Id=sc.nextInt();
                    hashTab.findEmpById(Id);
                default:
                    break;
            }
        }
    }
}
class HashTab{
    private EmpLinkedList[] empLinkedListArray;
    private int size;
    //构造器
    public HashTab(int size){
        this.size=size;
        //初始化empLinkedListArray
        empLinkedListArray =new EmpLinkedList[size];
        //要分别初始化每一个元素
        for (int i=0;i<size;i++){
            empLinkedListArray[i]=new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp){
        //根据员工的id，得到该员工应当添加到哪条链表
        empLinkedListArray[hashFun(emp.id)].add(emp);
    }
    //编写散列函数，使用一个简单取模法
    public int hashFun(int id){
        return id%size;
    }

    //遍历
    public void list(){
        for (int i=0;i<size;i++){
            empLinkedListArray[i].list(i);
        }
    }

    //根据输入的id查找雇员
    public void findEmpById(int id){
        //使用散列函数确定到哪条链表查找
        Emp emp=empLinkedListArray[hashFun(id)].findEmpById(id);
        if (emp!=null){
            System.out.println("id=" + emp.id + "\tname=" + emp.name);
        }else {
            System.out.println("没找到");
        }
    }
}


//表示一个雇员
class Emp{
    public int id;
    public String name;
    public Emp next;//next默认为null

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

//创建EmpLinkedList,表示链表
class EmpLinkedList{
    //头指针，执行第一个Emp,因此我们这个链表的head是直接指向第一个Emp
    private Emp head;   //默认null

    //添加雇员到链表
    //说明
    //1.假定，当添加雇员时，id 是自增长，即id的分配总是从小到大
    //  因此我们将改雇员直接加入到本链表的最后即可
    public void add(Emp emp){
        //如果为空
        if(head==null){
            head=emp;
            return;
        }
        //如果不为空，使用一个辅助指针，帮助定位到最后
        Emp curEmp=head;
        while (true){
            if (curEmp.next==null){ //说明链表到最后
                break;
            }
            curEmp=curEmp.next;
        }
        //退出是直接将emp添加到链表
        curEmp.next=emp;
    }
    //遍历链表的雇员信息
    public void list(int no){
        if(head==null){
            //说明链表为空
            System.out.println("第"+(no+1)+"条链表为空");
            return;
        }
        System.out.println("第"+(no+1)+"条链表信息为：");
        Emp curEmp=head;//辅助指针
        while (true){
            System.out.println("id=" + curEmp.id + "\tname=" + curEmp.name);
            if (curEmp.next==null){ //判断是否到了最后节点
                break;
            }
            curEmp=curEmp.next;
        }
    }

    //根据id查找雇员
    //如果找到，返回Emo，没找到返回null
    public Emp findEmpById(int id){
        //判断链表是否为空
        if (head==null){
            return null;
        }
        Emp curEmp=head;
        while (true){
            if(curEmp.id==id){
                //找到
                break;
            }
            //退出
            if(curEmp.next==null){
                //没找到
                curEmp=null;
                break;
            }
            curEmp=curEmp.next;
        }
        return curEmp;
    }
}
