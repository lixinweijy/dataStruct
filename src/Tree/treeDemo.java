package Tree;

//顺序存储二叉树的特点
/*
1.  顺序二叉树通常只考虑完全二叉树
2.  第n个元素的左子节点为2*n+1
3.  第n个元素的右子节点为2*n+2
4.  第n个节点的父节点为（n-1）/2
 */
public class treeDemo {
    public static void main(String[] args) {
        int[] arr={1,2,3,4,5,6,7};
        ArrBinaryATree aTree=new ArrBinaryATree(arr);

        aTree.preOrder();
    }
}

//实现顺序存储二叉树遍历
class ArrBinaryATree{
    private int[] arr;//存储数据节点的数组

    public void preOrder(){
        preOrder(0);
    }
    public ArrBinaryATree(int[] arr){
        this.arr =arr;
    }
    //实现顺序存储二叉树的前序遍历
    public void preOrder(int index){
        //如果数组为空，或者arr.length==0;
        if (arr==null || arr.length==0){
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        //输出当前这个元素
        System.out.print(arr[index]+" ");
        //向左递归
        if((index*2+1)<arr.length){
            preOrder(index*2+1);
        }
        //向右递归
        if((index*2+2)<arr.length){
            preOrder(2*index+2);
        }
    }
}
