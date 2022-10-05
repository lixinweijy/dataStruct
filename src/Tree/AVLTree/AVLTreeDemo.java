package Tree.AVLTree;

public class AVLTreeDemo {
    public static void main(String[] args) {

    }
}
//创建Nde节点
class Nde{
    int value;
    Nde left;
    Nde right;

    public Nde(int value){
        this.value=value;
    }
    //返回以当前节点为根节点的树的高度
    public int height(){
        return Math.max(left==null?0:left.height(), right==null?0:right.hashCode())+1;
    }

    //返回左子树高度
    public int leftHeight(){
        if (left==null){
            return 0;
        }
        return left.height();
    }
    //返回右子树的高度
    public int rightHeight(){
        if (right==null){
            return 0;
        }
        return right.height();
    }

    /**
     * 查找要珊瑚的节点
     * @param value 希望删除的节点的值
     * @return
     */
    public Nde search(int value){
        if(value==this.value){
            return this;
        }else if(value<this.value){
            //如果查找的值小于当前节点，向左子树递归查找
            //如果左子节点为空
            if (this.left==null){
                return null;
            }
            return this.left.search(value);
        }else {
            //查找的值大于当前节点，向右子树递归查找
            if (this.right==null){
                return null;
            }
            return this.right.search(value);
        }
    }
    //查找要删除的节点的父节点

    /**
     *
     * @param value 要找到的节点的值
     * @return 返回要删除的节点的父节点，如果没有就返回null
     */
    public Nde searchParent(int value){
        if ((this.left!=null && this.left.value==value)||
                (this.right!=null && this.right.value==value)){
            return this;
        }else {
            //如果查找得到值小于当前节点的值，并且当前节点的左子节点不为空
            if (value<this.value && this.left!=null){
                return this.left.searchParent(value);
            }else if(value>=this.value && this.right!=null){
                return this.right.searchParent(value);
            }else {
                return null; //没有找到父节点
            }
        }
    }

    //添加节点的方法
    //递归的形式添加节点，注意需要瞒住二叉排序树的要求
    public void add(Nde nde){
        if (nde==null){
            return;
        }
        //判断传入的节点的值，和当前子树的根节点的值关系
        if (nde.value<this.value){
            //如果当前节点左子节点为null
            if (this.left==null){
                this.left=nde;
            }else {
                //递归的向左子树添加
                this.left.add(nde);
            }
        }else {
            //添加的节点得到值大于当前节点的值
            if (this.right==null){
                this.right=nde;
            }else {
                //递归的向右子树添加
                this.right.add(nde);
            }
        }
    }

    //中序遍历
    public void infixOrder(){
        if (this.left!=null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right!=null){
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Nde{" +
                "value=" + value +
                '}';
    }
}