package Search;

import java.util.Arrays;

/*
斐波拉契查找
在二分查找基础上改变mid
mid=left+F(n-1)-1   //斐波拉契对应的坐标 F[n]=F[n-1]+F[n-2]      也就是F[n-1]的末尾的位置
 */
public class FeiboSearch {
    public static int maxSize=20;
    public static void main(String[] args) {
        int arr[]={1,2,3,4,5,6,7,8,9,11,22,33,44,55,66};
        System.out.println(fibSearch(arr, 52));
        String s="3232";
        System.out.println(s.charAt(0)-'0');
    }
    public static int[] fib(){
        int[] f=new int[maxSize];
        f[0]=1;
        f[1]=1;
        for (int i=2;i<maxSize;i++){
            f[i]=f[i-1]+f[i-2];
        }
        return f;
    }

    public static int fibSearch(int[] a,int key){
        int left=0,right=a.length-1;
        int k=0;//表示斐波拉契分割数值的下标
        int mid=0;//存放mid值
        int f[]=fib(); //获取到斐波拉契数列
        //获取斐波拉契分割值的下标
        while (right>f[k]-1){
            k++;
        }
        //f[k]的值可能大于a的长度，需要使用Arrays类，构造一个新的数组，并指向a
        //不足的部分用0填充
        int[] temp= Arrays.copyOf(a,f[k]);
        //实际中需求使用a数组最后的数填充temp
        for (int i=right+1;i<temp.length;i++){
            temp[i]=a[right];
        }
        //使用while循环找到key
        while (left<=right){
            mid=left+f[k-1]-1;
            if (key<temp[mid]){//向左边去找
                right=mid-1;
                k--;//斐波拉契数列向前移一位
                /*
                说明：
                f[k]=f[k-1]+f[k-2]
                找前半部分就是f[k-1]，f[k-1]=f[k-2]+f[k-3]
                所以要k向前移一步
                 */
            }else if(key>temp[mid]){
                left=mid+1;
                k-=2;
                /*
                说明：
                f[k]=f[k-1]+f[k-2]
                找后半部分就是f[k-2]，f[k-2]=f[k-3]+f[k-4]
                所以要k向前移两步
                 */
            }else {//找到了
                //需要确定，返回的是超出的下标还是没超出的下标
                if(mid<=right){
                    return mid;
                }else {
                    return right;
                }
            }
        }
        return -1;

    }

}
