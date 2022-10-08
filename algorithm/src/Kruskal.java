import java.security.spec.EdDSAParameterSpec;
import java.util.Arrays;
import java.util.Date;

//克鲁思卡尔算法
public class Kruskal {

    private int edgeNum; //边的个数
    private char[] vertexs; //顶点数组
    private int[][] matrix; //邻接矩阵
    //使用INF 表示两个顶点不能连通
    private static final int INF=Integer.MAX_VALUE;


    public static void main(String[] args) {
        char[] vertexs={'A','B','C','D','E','F','G'};
        int matrix[][]={
                {0,12,INF,INF,INF,16,14},
                {12,0,10,INF,INF,7,INF},
                {INF,10,0,3,5,6,INF},
                {INF,INF,3,0,4,INF,INF},
                {INF,INF,5,4,0,2,8},
                {16,7,6,INF,2,0,9},
                {14,INF,INF,INF,8,9,0}
        };
        Kruskal kruskal=new Kruskal(vertexs,matrix);
       // kruskal.print();
        kruskal.kruskal();

    }
    public void kruskal(){
        int index=0; //表示最后结果数组的索引
        int ends[]=new int[edgeNum]; //用于保存“已有最小生成树”中的每个顶点在最小生成树中的终点
        //创建结果数组，保存最后的最小生成树
        EData[] retds=new EData[edgeNum];

        //获取图中所有的边的集合，一共12边
        EData[]  edges=getEdges();

        //按照边的权值大小进行排序（从小到大）
        sortEdges(edges);
        //遍历edges数组，将边添加到最小生成树中时，判断准备加入的边是否形成了回路，如果没有，就加入rets，否则不能加入
        for (int i=0;i<edgeNum;i++){
            //获取到第i条边的第一个顶点（起点）
            int p1=getPosition(edges[i].start);
            int p2=getPosition(edges[i].end);

            //获取p1和p2在已有的最小生成树中的终点
            int m=getEnd(ends,p1);
            int n=getEnd(ends,p2);

            //是否构成回路
            if (m!=n){
                //没有构成回路
                ends[m]=n; //即下标是m的终点是n
                retds[index++]=edges[i]; //有一条边加入到rets数组
            }
        }
        //打印最小生成树
        System.out.println("最小生成树为：");
        for (int i=0;i<index;i++){
            System.out.println(retds[i]);
        }


    }

    //构造器
    public Kruskal(char[] vertexs,int[][] matrix){
        //初始化顶点数和边的个数
        int vlen=vertexs.length;


        //初始话顶点，复制拷贝的方式
        this.vertexs=new char[vlen];
        for (int i=0;i<vlen;i++){
            this.vertexs[i]=vertexs[i];
        }
        //初始化边
        this.matrix=new int[vlen][vlen];
        for (int i=0;i<vlen;i++){
            for (int j=0;j<vlen;j++){
                this.matrix[i][j]=matrix[i][j];
            }
        }

        //统计边
        for (int i=0;i<vlen;i++){
            for (int j=0;j<vlen;j++){
                if (this.matrix[i][j]!=INF){
                    edgeNum++;
                }
            }
        }

    }

    //打印邻接矩阵
    public void print(){
        System.out.println("邻接矩阵为：");
        for (int i=0;i< vertexs.length;i++){
            for (int j=0;j< vertexs.length;j++){
                System.out.printf("%20d\t",matrix[i][j]);
            }
            System.out.println(); //换行
        }
    }

    //对边进行排序处理
    private void sortEdges(EData[] edges){
        for (int i=0;i<edges.length-1;i++){
            for (int j=0;j<edges.length-i-1;j++ ){
                if (edges[j+1]!=null && edges[j].weigth>edges[j+1].weigth) { //交换
                    EData tmp=edges[j];
                    edges[j]=edges[j+1];
                    edges[j+1]=tmp;
                }
            }
        }
    }

    //返回对应顶点的下标
    private int getPosition(char ch){
        for (int i=0;i<vertexs.length;i++){
            if (vertexs[i]==ch){
                return i;
            }
        }
        return -1;
    }

    //获取图中边，放到EData[]数组中，后面需要遍历数组
    private EData[] getEdges(){
        int index=0;
        EData[] edges=new EData[edgeNum];
        for (int i=0;i<vertexs.length;i++){
            for (int j=0;j<vertexs.length;j++){
                if (matrix[i][j]!=INF){
                    edges[index++]=new EData(vertexs[i],vertexs[j],matrix[i][j] );
                }
            }
        }
        return edges;
    }

    /**
     * 获取下标为i的顶点的终点(),用于后面判断两个顶点的终点是否相同
     * @param ends 终点表，实在遍历过程中逐渐形成的
     * @param i 表示传入的顶点对应得到洗标
     * @return 返回下标为i的这个顶点对应的终点的下标
     */
    private int getEnd(int[] ends,int i){
        while(ends[i]!=0){
            i=ends[i];
        }
        return i;
    }
}


//创建一个类EData,他的对象示例就表示一条边
class EData{
    char start; //边的一个点
    char end;//边的另一个点
    int weigth; //边的权值

    //构造器
    public EData(char start,char end,int weight){
        this.start=start;
        this.end=end;
        this.weigth=weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "<" + start +
                "," + end +
                "> =" + weigth +
                '}';
    }
}