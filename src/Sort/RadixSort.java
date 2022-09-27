package Sort;
//基数排序

import java.util.Arrays;

/**
 * 从个位开始分类
 * 空间消耗大
 * 有负数时要改进
 */
public class RadixSort {
    public static void main(String[] args) {
        int arr[]={2,3,12,1,2,32,2,12,31,13};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void radixSort(int[] arr){
        int max=arr[0];
        for(int i=1;i<arr.length;i++){
            if (max<arr[i]){
                max=arr[i];
            }
        }
        //求出最大数的位数
        int len=(max+"").length();
        int maxLen=len;
        //记录每个数的位置
        int[][] bucket=new int[10][arr.length];
        //记录每个桶中已有元素的个数
        int[] bucketElementCounts=new int[10];//每次清空计数
        while (len!=0){
            for (int i=0;i<arr.length;i++){
                //得到基数
                int digitOfElement=(arr[i]/((int)Math.pow(10,maxLen-len)))%10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]]=arr[i];
                //对应位置计数加1
                bucketElementCounts[digitOfElement]++;
            }

            int index=0;
            //把桶中有数据的放回原数组
            for(int i=0;i<10;i++){
                for(int j=0;j<bucketElementCounts[i];j++){
                    arr[index++]=bucket[i][j];
                }
                bucketElementCounts[i]=0;
            }
            len--;
        }
    }

}
