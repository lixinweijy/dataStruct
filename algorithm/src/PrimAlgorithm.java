import Graph.Graph;

import java.util.ArrayList;
import java.util.Arrays;


//普利姆算法

public class PrimAlgorithm {
    public static void main(String[] args) {
        //测试看看图是否创建成功
        char[] data=new char[]{'A','B','C','D','E','F','G'};
        int verxs=data.length;
        //邻接矩阵的关系使用二维数组表示，10000个大数，表示这两个点不联通
        int [][]weight=new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,5,4,10000,6},
                {2,3,10000,10000,4,6,10000}
        };
        //创建一个MGraph对象
        MGraph mGraph=new MGraph(verxs);
        //创建一个MinTree对象
        MinTree minTree=new MinTree();
        minTree.createGraph(mGraph,verxs,data,weight);
        //输出
        minTree.showGraph(mGraph);
        minTree.prim(mGraph,0);  //从A开始找边
    }


}

class MinTree{
    //创建图的邻接矩阵

    /**
     *
     * @param graph 图对象
     * @param verxs 图对应的顶点个数
     * @param data 图的各个顶点的值
     * @param weight 图的理解矩阵
     */
    public void createGraph(MGraph graph,int verxs,char data[] ,int[][] weight){
        int i,j;
        for (i = 0; i < verxs; i++) {//顶点
            graph.data[i]=data[i];
            for (j=0;j<verxs;j++){
                graph.weight[i][j]=weight[i][j];
            }
        }
    }

    //显示图的邻接矩阵
    public void showGraph(MGraph graph){
        for (int [] link:graph.weight){
            System.out.println(Arrays.toString(link));
        }
    }

    //编写prim算法，得到最小生成树
    public void prim(MGraph mGraph,int v){
        //记录节点是否被访问过
        int visited[]=new int[mGraph.verxs];
        //默认是0，表示未被访问过
        visited[v]=1;
        //h1和h2记录两个顶点的下标
        int h1=-1;
        int h2=-1;
        int minWeight=10000;  //将minWeight初始成一个大数，后面在遍历过程中，会被替换
        for (int k=1;k<mGraph.verxs;k++){ //有mGraph.verxs个顶点，普利姆算法结束后，有graph.verxs-1边

            //用来确定每一次生成的子图和哪个节点的距离最近
            for (int i=0;i<mGraph.verxs;i++){
                for (int j=0;j<mGraph.verxs;j++){
                    if (visited[i]==1 && visited[j]==0 && mGraph.weight[i][j]<minWeight){
                        //替换minWeight（寻找已经范文过的节点和未访问过的节点间的权值最小的边）
                        minWeight=mGraph.weight[i][j];
                        h1=i;
                        h2=j;
                    }
                }
            }
            //打印出最小
            System.out.println(mGraph.data[h1]+"->"+mGraph.data[h2]+"="+minWeight);
            //当前这个节点标记为已访问
            visited[h2]=1;
            //重置minWeight
            minWeight=10000;

        }
    }
}

class MGraph{
    int verxs; //表示图的节点个数
    char[] data; //存放节点数据
    int[][] weight;  //存放边，就是我们的邻接矩阵

    public MGraph(int verxs){
        this.verxs=verxs;
        data=new char[verxs];
        weight=new int[verxs][verxs];
    }
}
