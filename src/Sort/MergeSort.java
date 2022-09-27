package Sort;

//归并排序
/**
 * 先分解后合并
 */

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr={21,12,13,1,3,45,2,2,11,12,23};
        int[] temp=new int[arr.length];
        MergeSort(arr,0,arr.length-1,temp);
        System.out.println(Arrays.toString(arr));
    }

    public static void MergeSort(int[] arr ,int left,int right,int[] temp){
        if(left>=right){
            return;
        }
        //分解
        int mid=(left+right)/2;
        MergeSort(arr,left,mid,temp);
        MergeSort(arr,mid+1,right,temp);
        //合并
        Merge(arr,left,mid,right,temp);
    }

    public static void Merge(int[] arr ,int left,int mid,int right,int[] temp){
        int i=left;
        int j=mid+1;
        int t=0;//临时下标
        //将元素放在临时数组中
        while (i<=mid && j<=right){
            //将左右小的放在数组中
            if(arr[i]<=arr[j]){
                temp[t++]=arr[i++];
            }else {
                temp[t++]=arr[j++];
            }
        }
        //将左右剩余的元素放到数组中
        while (j<=right){
            temp[t++]=arr[j++];
        }
        while (i<=mid){
            temp[t++]=arr[i++];
        }

        //将临时数组放在原先数组中
        t=0;
        while (left<=right){
            arr[left++]=temp[t++];
        }
    }

}
