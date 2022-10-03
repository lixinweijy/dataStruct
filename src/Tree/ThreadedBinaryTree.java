package Tree;

import java.awt.*;

public class ThreadedBinaryTree {
    public static void main(String[] args) {
        //测试一把中序线索二叉树的功能
        HeroNode root=new HeroNode(1,"tom");
        HeroNode node2=new HeroNode(3,"jack");
        HeroNode node3=new HeroNode(6,"lucia");
        HeroNode node4=new HeroNode(8,"andy");
        HeroNode node5=new HeroNode(10,"xander");
        HeroNode node6=new HeroNode(14,"jerry");

        //二叉树，一般递归创建，现在简单梳理，手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试中序线索化
        BinaryTree binaryTree=new BinaryTree();
        binaryTree.setRoot(root);
        binaryTree.threadedNodes();

        //测试：以10号节点测试
        HeroNode nd1=node5.getLeft();
        HeroNode nd2=node5.getRight();
        System.out.println("十号结点的前驱结点为：" + nd1);
        System.out.println("十号结点的后继结点为：" + nd2);

        //此时遍历二叉树不能用原本的方法了，不然可能会死循环
        System.out.println("使用线索化的方式遍历线索化二叉树");
        binaryTree.threadedList();

    }
}

//创建HeroNode节点
class HeroNode{
    private int no;
    private String name;
    private HeroNode left; //默认null
    private HeroNode right; //默认null
    //说明
    //1. 如果leftType==0，表示指向的是左子树，如果1则表示指向前驱结点
    //2. 如果rightType==0，表示指向的是右子树，如果1表示指向后继节点
    private int leftType;
    private int rightType;


    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public HeroNode() {
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public void delNode(int no){
        if (this.left!=null && this.left.no==no){
            this.left=null;
            return;
        }

        if (this.right!=null && this.right.no==no){
            this.right=null;
            return;
        }

        if (this.left!=null){
            this.left.delNode(no);
        }
        if (this.right!=null){
            this.right.delNode(no);
        }
    }
    //前序遍历
    public void preOrder(){
        //输出父节点
        System.out.println(this);
        //递归向左子树中序遍历
        if (this.left!=null){
            this.left.preOrder();
        }
        //递归向右子树中序遍历
        if (this.right!=null){
            this.right.preOrder();
        }
    }
    //中序遍历
    public void infixOrder(){
        //递归向左子树中序遍历
        if (this.left!=null){
            this.left.infixOrder();
        }
        //输出父节点
        System.out.println(this);
        //递归向右子树中序遍历
        if (this.right!=null){
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder(){
        //递归向左子树中序遍历
        if (this.left!=null){
            this.left.postOrder();
        }

        //递归向右子树中序遍历
        if (this.right!=null){
            this.right.postOrder();
        }
        //输出父节点
        System.out.println(this);
    }
    //前序遍历
    public HeroNode preOrderSearch(int no){
        HeroNode resNode=null;
        //比较当前节点
        if(this.no==no){
            return this;
        }
        if (this.left!=null){
            resNode=this.left.preOrderSearch(no);
        }
        if (resNode!=null){//说明左子树找到了
            return resNode;
        }
        //递归向右子树遍历
        if (this.right!=null){
            resNode=this.right.postOrderSearch(no);
        }
        if (resNode!=null){//说明右子树找到了
            return resNode;
        }

        return resNode;
    }
    //中序遍历
    public HeroNode infixOrderSearch(int no){
        HeroNode resNode=null;

        if (this.left!=null){
            resNode=this.left.infixOrderSearch(no);
        }
        if (resNode!=null){//说明左子树找到了
            return resNode;
        }
        //比较当前节点
        if(this.no==no){
            return this;
        }
        //递归向右子树遍历
        if (this.right!=null){
            resNode=this.right.infixOrderSearch(no);
        }
        if (resNode!=null){//说明右子树找到了
            return resNode;
        }

        return resNode;
    }
    //后序遍历
    public HeroNode postOrderSearch(int no){
        HeroNode resNode=null;
        if (this.left!=null){
            resNode=this.left.postOrderSearch(no);
        }
        if (resNode!=null){//说明左子树找到了
            return resNode;
        }
        //递归向右子树遍历
        if (this.right!=null){
            resNode=this.right.postOrderSearch(no);
        }
        if (resNode!=null){//说明右子树找到了
            return resNode;
        }

        //说明都没有找到,比较当前节点是不是
        if(this.no==no){
            return this;
        }
        return resNode;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}

//定义ThreadedBinaryTree 实现了线索化功能的二叉树
class BinaryTree{
    private HeroNode root;

    //为了实现线索化，需要创建要给指向当前节点的前驱节点的指针
    //在递归进行线索化时，pre总是保留前一个节点
    private HeroNode pre=null;

    public void setRoot(HeroNode root){
        this.root=root;
    }

    //重载，简化调用
    public void threadedNodes(){
        this.threadedNodes(root);
    }
    //编写对二叉树进行中序线索化的方法
    public void threadedNodes(HeroNode node){
        //如果node==null,不能线索化
        if (node==null){
            return;
        }

        //1.先线索化左子树
        threadedNodes(node.getLeft());
        //2.线索化当前结点

        //处理前驱结点
        if (node.getLeft()==null){
            //让当前结点的左指针指向前驱结点
            node.setLeft(pre);
            //修改当前结点的左指针的类型
            node.setLeftType(1);
        }
        //处理后继节点
        if(pre!=null && pre.getRight()==null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        //保存上一个节点
        pre=node;
        //3.再线索化右节点
        threadedNodes(node.getRight());
    }

    //删除结点
    public void delNode(int no){
        if(root!=null){
            //如果只有一个root节点，这里理解判断root是不是就是要删除结点
            if(root.getNo()==no){
                root=null;
            }else {
                //递归删除
                root.delNode(no);
            }
        }else {
            System.out.println("空树，不能删除");
        }
    }

    //前序遍历
    public void preOrder(){
        if (this.root!=null){
            this.root.preOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder(){
        if (this.root!=null){
            this.root.infixOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //后序遍历
    public void postOrder(){
        if (this.root!=null){
            this.root.postOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序遍历
    public HeroNode preOrderSearch(int no){
        if (root!=null){
            return root.preOrderSearch(no);
        }else {
            return null;
        }
    }

    //中序遍历
    public HeroNode infixOrderSearch(int no){
        if (root!=null){
            return root.infixOrderSearch(no);
        }else {
            return null;
        }
    }
    //后序遍历
    public HeroNode postOrderSearch(int no){
        if (root!=null){
            return root.postOrderSearch(no);
        }else {
            return null;
        }
    }
    //遍历线索化二叉树的方法
    public void threadedList(){
        //定义一个变量，存储当前的节点，从root开始
        HeroNode node= root;
        while (node!=null){
            //循环的找到leftType==1的节点，第一个找到的就是8结点
            //后面随着遍历而变化，因为当leftType==1时，说明该节点是按照线索化
            //处理后的有效节点
            while (node.getLeftType()==0){
                node=node.getLeft();
            }
            //打印当前这个节点
            System.out.println(node);
            //如果当前节点的右指针指向的是后继节点，就一直输出
            while (node.getRightType()==1){
                //获取到当前节点的后继节点
                node=node.getRight();
                System.out.println(node);
            }

            //替换这个遍历的节点
            node=node.getRight();
        }
    }
}