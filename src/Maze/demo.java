package Maze;

//迷宫问题
public class demo {
    public static void main(String[] args) {
        int[][] maze=new int[8][7];
        for(int i=0;i<8;i++){
            maze[i][0]=1;
            maze[i][6]=1;
        }

        for(int i=0;i<7;i++){
            maze[0][i]=1;
            maze[7][i]=1;
        }

        maze[3][1]=1;
        maze[3][2]=1;
        for (int i=0;i<8;i++){
            for (int j=0;j<7;j++){
                System.out.print(maze[i][j]+" ");
            }
            System.out.println();
        }

        setBort(maze,1,1);
        System.out.println("------------------------------");
        for (int i=0;i<8;i++){
            for (int j=0;j<7;j++){
                System.out.print(maze[i][j]+" ");
            }
            System.out.println();
        }


    }

    static boolean setBort(int a[][],int i,int j){
        if(a[6][5]==2){
            return true;
        }
        if(a[i][j]==0){
            a[i][j]=2;
            //下-->右-->上-->左
            if(setBort(a,i+1,j)){
                return true;
            }else if(setBort(a,i,j+1)){
                return true;
            }else if(setBort(a,i-1,j)){
                return true;
            }else if(setBort(a,i,j-1)){
                return true;
            }else {
                a[i][j]=3;
                return false;
            }
        }else{
            return false;
        }
    }
}