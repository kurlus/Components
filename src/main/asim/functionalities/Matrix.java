package asim.functionalities;

import java.util.NavigableSet;
import java.util.TreeSet;

public class Matrix
{
    public static void main(String[] args){
        Matrix matrix = new Matrix();

        int[] newInterval = {4,8};
        matrix.addIntervalInMatrix(newInterval);
    }

    /*
    assumes newInterval has two elements. Further first
    elementis lesser than the other
     */
    public void addIntervalInMatrix(int[] newInterval){
        TreeSet<Integer> treeSet = new TreeSet<Integer>();
        int[][] matrix = {{1,2},{3,5},{6,7},{8,10},{12,16}};

        Integer firstInteger = newInterval[0];
        Integer scondInteger = newInterval[1];

        for (int ri=0; ri< matrix.length; ri++){
            for (int ci=0; ci<matrix[ri].length; ci++){
                treeSet.add(matrix[ri][ci]);
            }
        }
        Integer ceiling = treeSet.ceiling(firstInteger);
        Integer floor = treeSet.floor(scondInteger);
        /*System.out.println(ceiling.toString()+"\t"+floor.toString()+"\t"+
                treeSet.subSet(ceiling, true, floor, true));*/
        NavigableSet navSet = treeSet.subSet(ceiling, true, floor, true);
        navSet.clear();
        treeSet.add(firstInteger);
        treeSet.add(scondInteger);
        System.out.println(treeSet);
    }// method end

    public void dispalyMatrix(){
        int[][] matrix = {{2,3,4},{7,8,9},{10,11}};

        for (int ri=0; ri< matrix.length; ri++){
            for (int ci=0; ci<matrix[ri].length; ci++){
                System.out.print(matrix[ri][ci]+"\t");
            }
            System.out.println();
        }
    }// method end
}
