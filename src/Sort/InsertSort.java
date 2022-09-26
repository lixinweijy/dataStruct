package Sort;
//插入排序

public class InsertSort {
    public static void main(String[] args) {
        int[] arr={1,43,54,21,55,22};
        insertSort(arr);
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }

    //插入排序
    public static void insertSort(int[] arr){
        for(int i=1;i<arr.length;i++){
            int insertValue=arr[i];
            int insertIndex=i-1;

            while(insertIndex>=0 && insertValue<arr[insertIndex]){  //比前面一个小就交换位置
                arr[insertIndex+1]=arr[insertIndex];
                insertIndex--;
            }
            //退出while循环是找到插入位置
            arr[insertIndex+1]=insertValue;

        }


    }
}
