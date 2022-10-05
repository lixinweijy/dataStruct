package NQueue;

//八皇后
public class NQueueDemo {
    public static int len=8;
    public static int[] array=new  int[len];
    public static int num=0;
    public static void main(String[] args) {

        startGame(0);
        System.out.println(num);
    }

    static void startGame(int n){
        if(n==len){
            num++;
            for (int i=0;i<len;i++){
                System.out.print(array[i]+" ");
            }
            System.out.println("\n----------------------");
            return;
        }

        for (int i=0;i<len;i++){
            array[n]=i;
            if(check(n)){
                startGame(n+1);
            }
        }
    }

    static boolean check(int n){
        for(int i=0;i<n;i++){
            if (array[i]==array[n] || Math.abs(array[i]-array[n])==Math.abs(i-n)){
                return false;
            }
        }return true;
    }
}
