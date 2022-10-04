package Tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

//哈夫曼树
public class HafTree {
    public static void main(String[] args) {
        int arr[]={13,7,8,3,29,6,1};
        Node root=createHafTree(arr);

        preOrder(root);
    }

    //编写一个前序遍历的方法
    public static void preOrder(Node root){
        if(root!=null){
            root.preOrder();
        }else {
            System.out.println("是空树，不能遍历~");
        }
    }

    //创建哈夫曼树的方法
    public static Node createHafTree(int[] arr){
        //第一步为了操作方便
        //1.遍历arr数组
        //2.将arr的每个元素构成一个Node
        //3.将Node放入到ArrayList中
        List<Node> nodes=new ArrayList<Node>();
        for (int value:arr){
            nodes.add(new Node(value));
        }

        while (nodes.size()>1){
            //排序 从小到大
            Collections.sort(nodes);

            //取出根节点权值最小的两颗二叉树
            //(1) 取出权值最小的节点（二叉树）
            Node leftNode=nodes.get(0);
            //(2) 取出权值第二小的节点（二叉树）
            Node rightNode=nodes.get(1);

            //(3) 构建一颗新的二叉树
            Node parent=new Node(leftNode.value+rightNode.value);
            parent.left=leftNode;
            parent.right=rightNode;

            //(4) 从ArrayList中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            //(5)将parent加入到nodes
            nodes.add(parent);
        }

        //返回哈夫曼树的头
        return nodes.get(0);
    }
}

//创建结点类
class Node implements Comparable<Node>{
    int value; //结点权值
    Node left; //指向左子节点
    Node right; //指向右子节点
    Node father;

    //写一个前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left!=null){
            this.left.preOrder();
        }
        if (this.right!=null){
            this.right.preOrder();
        }
    }

    public Node(int value){
        this.value=value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //表示从小到大排
        return this.value-o.value;
    }

}