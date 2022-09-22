package SparseArray;

import javax.annotation.processing.Filer;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class demo1 {
    public static void main(String[] args) throws Exception{
        System.out.println(System.getProperty("user.dir"));

        //原数组
        int[][] array=new int[10][10];
        array[1][1]=1;
        array[2][2]=2;

        //打印原始的二维数组，并得到非0数据个数
        int count=0;
        for(int[] nums:array){
            for(int num:nums){
                if(num!=0){
                    count++;
                }
            }
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
        //文件写入管道
        BufferedWriter outputStream=new BufferedWriter(new FileWriter("src/data.txt"));
        //开始写入
        for(int i=0;i<sparse.length;i++){
            outputStream.write(sparse[i][0]+"\t"+sparse[i][1]+"\t"+sparse[i][2]+"\n");
        }
        outputStream.close();

        BufferedReader inputStream=new BufferedReader(new FileReader("src/data.txt"));
        List<String> list=new ArrayList<>();
        String str=null;
        while((str=inputStream.readLine())!=null){
            list.add(str);
        }

        inputStream.close();

        //得到稀疏数组
        int[][] sparse_af=new int[list.size()][3];
        for(int i=0;i<list.size();i++){

            String[] temp=list.get(i).split("\t");
            sparse_af[i][0]=Integer.parseInt(temp[0]) ;
            sparse_af[i][1]=Integer.parseInt(temp[1]) ;
            sparse_af[i][2]=Integer.parseInt(temp[2]) ;
        }


        //还原原来数组
        int[][] array_n=new int[sparse_af[0][0]][sparse_af[0][1]];
        for (int i=0;i<sparse_af[0][0];i++){
            for (int j=0;j<sparse_af[0][1];j++){
                array_n[i][j]=sparse_af[0][2];
            }
        }
        for(int i=1;i<sparse_af.length;i++) {
            array_n[sparse_af[i][0]][sparse_af[i][1]]=sparse_af[i][2];
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
