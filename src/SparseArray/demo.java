package SparseArray;

public class demo {
    public static void main(String[] args) {
        //原数组
        int[][] array=new int[10][10];
        array[1][1]=1;
        array[2][2]=2;

        System.out.println("---------原数组--------");
        //打印原始的二维数组，并得到非0数据个数
        int count=0;
        for(int[] nums:array){
            for(int num:nums){
                if(num!=0){
                    count++;
                }
                System.out.print(num+"\t");
            }
            System.out.println();
        }

        //稀疏数组
        int[][] sparse=new int [count+1][3];

        //给稀疏数组第一行赋值
        sparse[0][0]=10;
        sparse[0][1]=10;
        sparse[0][2]=0;
        //给稀疏数组其他行赋值
        count=0;
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(array[i][j]!=0){
                    count++;
                    sparse[count][0]=i;
                    sparse[count][1]=j;
                    sparse[count][2]=array[i][j];
                }
            }
        }
        System.out.println("---------稀疏数组------------");
        //打印稀疏数组
        for(int i=0;i<sparse.length;i++){
            System.out.println(sparse[i][0]+"\t"+sparse[i][1]+"\t"+sparse[i][2]);
        }

        //还原原来数组
        int[][] array_n=new int[sparse[0][0]][sparse[0][1]];
        for (int i=0;i<array_n[0][0];i++){
            for (int j=0;j<array_n[0][1];j++){
                array_n[i][j]=sparse[0][2];
            }
        }
        for(int i=1;i<sparse.length;i++) {
            array_n[sparse[i][0]][sparse[i][1]]=sparse[i][2];
        }

        System.out.println("-------还原后的原数组--------");
        //打印原来数组
        for(int[] nums:array_n){
            for(int num:nums){
                System.out.print(num+"\t");
            }
            System.out.println();
        }

    }
}
