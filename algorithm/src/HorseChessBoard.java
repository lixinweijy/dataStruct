import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * 马踏棋盘问题
 */

public class HorseChessBoard {
    private static int X;// 棋盘的行数
    private static int Y;//棋盘的列数
    //创建一个数组，标记是否棋盘的所有位置被访问
    private static boolean visited[];
    //使用一个属性，标记是否棋盘的所有位置都被访问
    private static boolean finished; //如果为true表示成功

    public static void main(String[] args) {
        X=8;
        Y=8;
        int row=1;  //马儿的初始位置
        int colume=1;

        //创建棋盘
        int[][] chessboard=new int[X][Y];
        visited=new boolean[X*Y];//初始值是false
        traversalChessboard(chessboard,row-1,colume-1,1);

        //输出棋盘最后的情况
        for (int[] rows:chessboard){
            for (int step:rows){
                System.out.print(step+"\t");
            }
            System.out.println();
        }
    }

    /**
     * 完成骑士周游问题的算法
     * @param chessboard 棋盘
     * @param row 马儿当前的位置的行 从0开始
     * @param column 马儿当前的位置的列 从0开始
     * @param step 是第几步，初始位置就是第1步
     */
    public static void traversalChessboard(int[][] chessboard,int row,int column,int step){
        chessboard[row][column]=step;
        visited[row*Y+column]=true; //标记该位置已经访问
        //获取当前位置可以走的下一个位置的集合
        ArrayList<Point> ps=next(new Point(row,column));
        //对ps进行排序，排序的规则就是对ps的所有的Point对象的下一步的位置的数目进行非递减排序
        sort(ps);
        //遍历ps
        while (!ps.isEmpty()){
            Point p=ps.remove(0); //取出下一个可以走的位置
            //判断该点是否已经访问过
            if (!visited[p.x*Y+p.y]) {// 说明还没有访问过
                traversalChessboard(chessboard,p.x,p.y,step+1);
            }
        }
        //判断马儿是否完成了任务，使用step和应该走的步数比较
        //如果没有达到数量，则表示没有完成任务，将整个棋盘置0
        if (step<X*Y && !finished){
            chessboard[row][column]=0;
            visited[row*Y+column]=false;
        }else {
            finished=true;
        }
    }

    /**
     * 根据当前位置（Point对象），计算马儿还能走那些位置（Point），并放入到一个集合中，最多有八个位置
     * @param curPoint
     * @return
     */
    public static ArrayList<Point> next(Point curPoint){
        //创建一个ArrayList
        ArrayList<Point> ps=new ArrayList<Point>();
        //创建一个Point
        Point p1=new Point();
        //表示马儿可以走5这个位置
        if ((p1.x=curPoint.x-2)>=0 && (p1.y=curPoint.y-1)>=0){
            ps.add(new Point(p1));
        }
        //判断马儿可以走6这个位置
        if ((p1.x=curPoint.x-1)>=0 && (p1.y=curPoint.y-2)>=0){
            ps.add(new Point(p1));
        }
        //判断马儿可以走7这个位置
        if ((p1.x=curPoint.x+1)<X && (p1.y=curPoint.y-2)>=0){
            ps.add(new Point(p1));
        }
        //判断马儿可以走0这个位置
        if ((p1.x=curPoint.x+2)<X && (p1.y=curPoint.y-1)>=0){
            ps.add(new Point(p1));
        }
        //判断马儿可以走1这个位置
        if ((p1.x=curPoint.x+2)<X && (p1.y=curPoint.y+1)<Y){
            ps.add(new Point(p1));
        }
        //判断马儿可以走2这个位置
        if ((p1.x=curPoint.x+1)<X && (p1.y=curPoint.y+2)<Y){
            ps.add(new Point(p1));
        }
        //判断马儿可以走3这个位置
        if ((p1.x=curPoint.x-1)>=0 && (p1.y=curPoint.y+2)<Y){
            ps.add(new Point(p1));
        }
        //判断马儿可以走4这个位置
        if ((p1.x=curPoint.x-2)>=0 && (p1.y=curPoint.y+1)<Y){
            ps.add(new Point(p1));
        }

        return ps;
    }

    //根据当前这一步的所有的下一步的选择位置，进行非递减排序
    public static void sort(ArrayList<Point> ps){
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                //获取到o1的下一步的所有位置的个数
                int count1=next(o1).size();
                int count2=next(o2).size();

                if (count1<count2){
                    return -1;
                }else if (count1==count2){
                    return 0;
                }else {
                    return -1;
                }
            }
        });
    }
}
