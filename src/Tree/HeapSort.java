package Tree;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        //要求将数组进行升序排序
        int arr[]={4,6,8,5,9};
        heapSort(arr);
    }

    //编写一个堆排序的方法
    public static  void heapSort(int arr[]){
        System.out.println("堆排序");
        //分步完成
        //adjustHeap(arr,1,arr.length);
        //System.out.println("第一次调整" + Arrays.toString(arr));
        int temp=0;
        //1.完成我们的最终代码，将无序序列构建成一个堆，根据升序降序需求选择大项堆或小项堆
        for (int i=arr.length/2-1;i>=0;i--){
            adjustHeap(arr,i,arr.length);
        }

        //2.将堆顶元素与末尾元素交换，将最大元素“沉”到数组末端
        for(int j=arr.length-1;j>0;j--){
            temp=arr[j];
            arr[j]=arr[0];
            arr[0]=temp;
            adjustHeap(arr,0,j);
        }
        System.out.println("数组=" + Arrays.toString(arr));
    }

    /**
     * @brief 完成将以i对应得到非叶子节点的树调整成大顶堆
     * @param arr 待调整得到数组
     * @param i 表示非叶子节点在数组中索引
     * @param length 表示对多少个元素进行调整，是在逐渐减少的
     */
    public static void adjustHeap(int arr[] ,int i,int length){
        int temp=arr[i]; //先取出当前元素的值，保存在临时变量
        //开始调整
        //说明
        //1.k=i*2+1   k是i节点的左子节点
        for (int k=2*i+1;k<length;k=k*2+1){
            if (k + 1 < length && arr[k] < arr[k+1]) { //说明左子节点的值小于右子节点的值
                k++; //k 指向右子节点
            }
            if (arr[k]>temp){
                arr[i]=arr[k]; //把较大的值赋给当前节点
                i=k;
            }else{
                break;
            }
        }
        //当for循环结束后，我们已经将以i为父节点的树的最大值放在最顶部
        arr[i]=temp; //将temp值放在调整后的位置
    }
}
