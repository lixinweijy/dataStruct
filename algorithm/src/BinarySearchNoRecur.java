
//二分查找的非递归形式

public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] arr={1,3,8,10,11,67,100};
        int index=binarySearch(arr,8);
        System.out.println(index);
    }

    //二分查找的非递归实现
    public static int binarySearch(int[] arr,int target){
        int left=0;
        int right=arr.length-1;
        while (left<=right){
            //说明继续查找
            int mid=(left+right)/2;
            if (arr[mid]==target){
                return mid;
            }else if(arr[mid]>target){
                right=mid-1; //继续向左边查找
            }else {
                left=mid+1;//继续向右边查找
            }
        }
        return -1;
    }
}