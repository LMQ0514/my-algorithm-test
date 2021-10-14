import com.lmq.SparseArray;

public class SparseTest {
    public static void main(String[] args) {
        int[][] array =new int[11][11];
        array[2][3] = 1;
        array[5][8] = 2;
        array[10][10] = 1;
//        for (int[] sub : array) {
//
//            for (int data : sub) {
//                System.out.print(data+"\t");
//            }
//
//            System.out.println();
//        }

        SparseArray sparseArray = new SparseArray();
        int[][] sparse = sparseArray.sparse(array);

        for (int[] sub : sparse) {
            for (int data : sub) {
                System.out.print(data+"\t");
            }
            System.out.println();
        }

        System.out.println("*******************************");

        int[][] recover = sparseArray.recover(sparse);

        for (int[] sub : recover) {
            for (int data : sub) {
                System.out.print(data+"\t");
            }
            System.out.println();
        }
    }
}
