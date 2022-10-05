package Tree;

public class BinartSortTreeDemo {
    public static void main(String[] args) {
        int[] arr={7,3,10,12,5,1,9};


        BinarySortTree binarySortTree=new BinarySortTree();
        for (int i=0;i<arr.length;i++){
            binarySortTree.add(new Nde(arr[i]));
        }

        binarySortTree.infixOrder();
        binarySortTree.delNode(7);
        binarySortTree.delNode(3);
        binarySortTree.delNode(10);
        binarySortTree.delNode(12);
        binarySortTree.delNode(5);
        binarySortTree.delNode(1);
        binarySortTree.delNode(9);
        binarySortTree.infixOrder();

    }


}

//创建二叉排序树
class BinarySortTree{
    private Nde root;

    //查找要删除的节点
    public Nde search(int value){
        if (root==null){
            return null;
        }else {
            return root.search(value);
        }
    }
    //查找父节点
    public Nde searchParent(int value){
        if (root==null){
            return null;
        }else {
            return root.searchParent(value);
        }
    }

    /**
     * 1.返回的以node为根节点的二叉排序树的最小节点的值
     * 2.删除node为根节点的二叉排序树的最小节点
     * @param nde 传入的节点
     * @return 返回以node为根节点的二叉排序树的最小节点的值
     */
    public int delRightTreeMin(Nde nde){
        Nde target=nde;
        //循环的查找左子节点，就会找到最小值
        while (target.left!=null){
            target=target.left;
        }
        //这时taget就指向了最小节点
        //删除最小节点
        delNode(target.value);
        return target.value;
    }
    //删除节点
    public void delNode(int value){
        if (root==null){
            return;
        }else {
            //1.需求先去找到要删除的节点，targetNode
            Nde targetNode=search(value);
            //如果没有找到要删除的节点
            if (targetNode==null){
                return;
            }
            //如果我们发现当前这颗二叉排序树只有一个节点
            if (root.left==null && root.right==null){
                root=null;
                return;
            }

            //去找到targetNode的父节点
            Nde parent=searchParent(value);
            //如果要删除的节点是叶子节点
            if (targetNode.left==null && targetNode.right==null){
                //判断targetNode是父节点的左子节点，还是右子节点
                if (parent.left!=null && parent.left.value==value){
                    //是左子节点
                    parent.left=null;
                }else if (parent.right!=null && parent.right.value==value){
                    //是右子节点
                    parent.right=null;
                }
            }else if(targetNode.left!=null && targetNode.right!=null){//删除由两颗子树的节点
                int minVal=delRightTreeMin(targetNode.right);
                targetNode.value=minVal;

            }else { //删除只有一颗子树的节点
                //如果要删除的节点有左子节点
                if (targetNode.left!=null){
                    if (parent!=null){
                        if (parent.left.value==value) {
                            parent.left = targetNode.left;
                        }else {
                            parent.right=targetNode.left;
                        }
                    }else {
                        root=targetNode.left;
                    }

                }else { //如果要删除的节点是parent的右子节点
                    if (parent!=null){
                        //targetNode是parent的右子节点
                        if (parent.left.value==value){
                            parent.left=targetNode.right;
                        }else {
                            //如果targetNode是parent的右子节点
                            parent.right=targetNode.right;
                        }
                    }else {
                        root=targetNode.right;
                    }

                }
            }
        }
    }

    //添加节点的方法
    public void add(Nde nde){
        if (root==null){
            root=nde;//如果root为空则直接让root指向nde
        }else {
            root.add(nde);
        }
    }


    //中序遍历
    public void infixOrder(){
        if (root!=null){
            root.infixOrder();
        }else {
            System.out.println("此二叉排列树为空");
        }
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