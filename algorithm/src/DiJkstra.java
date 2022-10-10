import java.util.Arrays;

/**
 * 迪杰斯特拉算法
 *
 * 1）战争时期，胜利乡有7个村庄（A,B,C,D,E,F,G），现在有6个邮件，从G点出发，分别送到A,B,C,D,E,F,G六个村庄
 * 2）各个村庄的距离用边线表示（权），比如A-B距离五公里
 * 3）问：如何计算出G村庄到其他各个村庄的最短距离？
 * 4）如果从其他点出发到各个点的最短距离又是多少？
 */

public class DiJkstra {
    public static void main(String[] args) {
        char[] vertex={'A','B','C','D','E','F','G'};
        //邻接矩阵
        int[][] matrix=new int[vertex.length][vertex.length];
        final int N=65535; //表示不可连接
        matrix[0]=new int[]{N,5,7,N,N,N,2};
        matrix[1]=new int[]{5,N,N,9,N,N,3};
        matrix[2]=new int[]{7,N,N,N,8,N,N};
        matrix[3]=new int[]{N,9,N,N,N,4,N};
        matrix[4]=new int[]{N,N,8,N,N,5,4};
        matrix[5]=new int[]{N,N,N,4,5,N,6};
        matrix[6]=new int[]{2,3,N,N,4,6,N};
        //创建Graph对象
        Graph graph=new Graph(vertex,matrix);
        //测试
        graph.showGraph();
        graph.dsj(vertex.length-1);
        graph.show();
    }
}

class Graph{
    private char[] vertex; //顶点数组
    private int[][] matrix; //邻接矩阵

    private VisitedVertex vv;
    //构造器
    public Graph(char[] vertex,int[][] matrix){
        this.vertex=vertex;
        this.matrix=matrix;
    }

    //显示图
    public void showGraph(){
        for (int[] link:matrix){
            System.out.println(Arrays.toString(link));
        }
    }

    //迪杰斯特拉算法实现

    /**
     *
     * @param index  表示出发节点对应的下标
     */
    public void dsj(int index){
        vv=new VisitedVertex(vertex.length,index);
        update(index); //更新index节点到周围节点的距离
        for (int j=1;j<vertex.length;j++){
            index =vv.updateArr();// 选择并放回新的访问节点顶点
            update(index); //更新index顶点到周围顶点的距离和前驱节点
        }
    }

    //更新index下标顶点到周围顶点的距离和周围顶点的前驱顶点
    private void update(int index){
        int len=0;
        //根据遍历我们的邻接矩阵的matrix[index] 行
        for (int j=0;j<matrix[index].length;j++){
            //len 含义是：出发顶点到index顶点的距离+从index顶点到j顶点的距离的和
            len=vv.getDis(index)+matrix[index][j];
            //如果jd顶点没有被访问过，并且新路线比原路线更短，就更新
            if (!vv.in(j) && len<vv.getDis(j)){
                vv.updatePre(j,index); //更新j顶点的前驱为index顶点
                vv.updateDis(j,len); //更新出发顶点到j顶点的距离
            }
        }
    }
    //显示结果
    public void show(){
        vv.show();
    }
}
//已访问顶点集合
class VisitedVertex{
    //记录各个顶点是否访问过，1表示访问过，0表示未访问，会动态更新
    public int[] already_arr;
    //每个下表对应的值为前一个顶点下标，会动态更新
    public int[] pre_visited;
    //记录出发顶点到其它所有顶点的距离，比如G为出发顶点，就会记录G到其它顶点的距离，会动态更新，求的最短距离会存放在dis
    public int[] dis;


    //构造器
    public VisitedVertex(int length,int index){
        this.already_arr=new int[length];
        this.pre_visited=new int[length];
        this.dis=new int[length];
        //初始化dis数组
        Arrays.fill(dis,65535);
        already_arr[index]=1; //设置出发顶点已被访问过
        this.dis[index]=0; //设置出发顶点的访问距离为0
    }

    /**
     * 判断index顶点是否呗访问过
     * @param index
     * @return
     */
    public boolean in(int index){
        return already_arr[index]==1;
    }

    /**
     * 更新出发顶点到index顶点的距离
     * @param index
     * @param len
     */
    public void updateDis(int index,int len){
        dis[index]=len;
    }

    /**
     * 更新顶点的前驱为index节点
     * @param pre
     * @param index
     */
    public void updatePre(int pre,int index){
        pre_visited[pre]=index;
    }

    /**
     * 返回出发节点到index顶点的距离
     * @param index
     */
    public int getDis(int index){
        return dis[index];
    }

    //继续选择并返回新的访问节顶点，比如这里的G访问完后，就是A点做新的访问节点（注意不是出发点）

    /**
     * 继续选择并访问新的节点
     * @return
     */
    public int updateArr(){
        int min=65535,index=0;
        for (int i=0;i<already_arr.length;i++){
            if (already_arr[i]==0 && dis[i]<min){
                index=i;
                min=dis[i];
            }
        }
        //跟新index顶点被访问过
        already_arr[index]=1;
        return index;
    }

    //显示最后的结果
    //即将三个数组的情况输出
    public void show(){
        System.out.println("============already_arr============");
        //输出already_arr
        for (int i:already_arr){
            System.out.print(i + " ");
        }
        System.out.println("\n============pre_visited============");
        //输出pre_visited
        for (int i:pre_visited){
            System.out.print(i + " ");
        }
        System.out.println("\n============dis============");
        //输出dis
        for (int i:dis){
            System.out.print(i + " ");
        }
        System.out.println();

        char[] vertex={'A','B','C','D','E','F','G'};
        int count=0;
        for (int i:dis){
            if (i!=65535){
                System.out.print("("+vertex[count]+")"+i+"  ");
            }
            count++;
        }
    }
}
