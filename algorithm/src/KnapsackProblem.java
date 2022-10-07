
//背包问题  动态规划算法

public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w={1,4,3};//物品的重量
        int[] val={1500,3000,2000};//物品的价值
        int m=4;//背包容量
        int n=val.length; //物品个数

        //创建二维数组
        int[][] v=new int[n+1][m+1];
        int[][] path=new int[n+1][m+1];  //用来存储记录

        //初始化第一行和第一列，可以不去处理，因为默认就是0
        for (int i=0;i<v.length;i++){
            v[i][0]=0; //将第一列设置为0
        }
        for (int i=0;i<v[0].length;i++){
            v[0][i]=0; //将第一行设置为0
        }

        //根据前面得到公式来动态规划处理
        for (int i=1;i<v.length;i++){ //不处理第一行第一列
            for (int j=1;j<v[0].length;j++){
                //公式
                if (w[i-1]>j){ //w中还是从零开始
                    v[i][j]=v[i-1][j];
                }else { //val和w都是从0开始的，所以都要减1
                    if (v[i-1][j]<val[i-1]+v[i-1][j-w[i-1]]){
                        v[i][j]=val[i-1]+v[i-1][j-w[i-1]];
                        //把当前的情况记录到path
                        path[i][j]=1;
                    }else {
                        v[i][j]=v[i-1][j];
                    }

                }
            }
        }

        //输出一下v 看看目前的情况
        for (int i=0;i<v.length;i++){
            for (int j=0;j<v[i].length;j++){
                System.out.print(v[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("=============================");
        int i=path.length-1; //行最大下标
        int j=path[0].length-1; //列最大下标
        while (i>0 && j>0){ //从path的最后开始找
            if (path[i][j]==1){
                System.out.printf("第%d个商品放入到背包\n",i);
                j-=w[i-1];
            }
            i--;
        }
    }
}
