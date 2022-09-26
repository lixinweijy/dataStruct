package Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
//希尔排序，就是把插入排序分组后再排序

public class ShellSort {
    public static void main(String[] args) {
        //创建100000个元素的随机数组

        int arr[]=new int[100000];
        for(int i=0;i<100000;i++){
            arr[i]=(int)(Math.random()*100000);//生成一个[0,100000)的数
        }
        //System.out.println(Arrays.toString(arr));
        Date date1=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str =simpleDateFormat.format(date1);
        System.out.println("排序前时间是=" + date1Str);

        shellSort2(arr);

        String date2Str=simpleDateFormat.format(new Date());
        System.out.println("排序后时间是=" + date2Str);
        //System.out.println(Arrays.toString(arr));
    }

    public static void shellSort1(int arr[]){    //移动法，慢
        int len=arr.length,temp=0;
        while ((len=len/2)!=0){
            //按2分组
            for(int i=len;i<arr.length;i++){
                //遍历每组中的元素
                for(int j=i-len;j>=0;j-=len){//限制了j是从前半部分遍历，所以不会超出索引
                    //如果当前元素大于加上步长后的元素，就交换
                    if(arr[j]>arr[j+len]){
                        temp=arr[j];
                        arr[j]=arr[j+len];
                        arr[j+len]=temp;
                    }
                }
            }
        }
    }

    public static void shellSort2(int arr[]){    //移动法，慢
        int len=arr.length,temp=0;
        while ((len=len/2)!=0){
            //按2分组
            for(int i=len;i<arr.length;i++){
                int insertValue=arr[i];
                int insertIndex=i-len;

                while(insertIndex>=0 && insertValue<arr[insertIndex]){  //比前面一个小就交换位置
                    arr[insertIndex+len]=arr[insertIndex];
                    insertIndex-=len;
                }
                //退出while循环是找到插入位置
                arr[insertIndex+len]=insertValue;
            }
        }
    }
}
