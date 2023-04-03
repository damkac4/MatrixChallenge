package org.example;

import java.util.Stack;

public class Main {
    public static void main( String[] args ) {

        String[] array = {"1011","0011","0111","1111"};
        matrixChallenge( array );


    }
    public static int[][] convert(String[] array, int rows, int columns){


        int[][] matrix = new int[rows][columns];
        int area;

        for ( int i = 0;i<rows;i++ )
            for ( int j = 0; j<columns;j++ ){
                matrix[i][j]=Integer.parseInt(String.valueOf( array[i].charAt( j ) ));
            }

        for ( int i = 0;i<rows;i++ ) {
            for ( int j = 0 ; j < columns ; j++ ) {
                System.out.print( matrix[ i ][ j ] );
                System.out.print(" ");
            }
            System.out.println("");
        }
        return matrix;
    }
    public static void matrixChallenge(String[] array){
        int rows = array.length;
        int columns = array[0].length();
        int[][] matrix = convert( array, rows, columns );
        System.out.println(maxRectangle(rows, columns,  matrix));
    }





    // Returns area of the largest rectangle with all 1s in
    // A[][]
    static int maxRectangle(int R, int C, int A[][])
    {
        // Calculate area for first row and initialize it as
        // result
        int result = maxHist(C, A[0]);

        // iterate over row to find maximum rectangular area
        // considering each row as histogram
        for (int i = 1; i < R; i++) {

            for (int j = 0; j < C; j++)

                // if A[i][j] is 1 then add A[i -1][j]
                if (A[i][j] == 1)
                    A[i][j] += A[i - 1][j];

            // Update result if area with current row (as
            // last row of rectangle) is more
            result = Math.max(result, maxHist(C, A[i]));
        }








        System.out.println("");
        for ( int i = 0;i<R;i++ ) {
            for ( int j = 0 ; j < C; j++ ) {
                System.out.print( A[ i ][ j ] );
                System.out.print(" ");
            }
            System.out.println("");
        }
        System.out.println("");
        return result;
    }

    static int maxHist(int C, int row[])
    {
        // Create an empty stack. The stack holds indexes of
        // hist[] array/ The bars stored in stack are always
        // in increasing order of their heights.
        Stack<Integer> result = new Stack<Integer>();

        int top_val; // Top of stack

        int max_area = 0; // Initialize max area in current
        // row (or histogram)

        int area = 0; // Initialize area with current top

        // Run through all bars of given histogram (or row)
        int i = 0;
        while (i < C) {
            // If this bar is higher than the bar on top
            // stack, push it to stack
            if (result.empty()
                    || row[result.peek()] <= row[i]){
                result.push(i++);
                System.out.print("fir ");
            }


            else {
                // If this bar is lower than top of stack,
                // then calculate area of rectangle with
                // stack top as the smallest (or minimum
                // height) bar. 'i' is 'right index' for the
                // top and element before top in stack is
                // 'left index'
                System.out.print("sec ");
                top_val = row[result.peek()];
                result.pop();
                area = top_val * i;

                if (!result.empty())
                    area = top_val * (i - result.peek() - 1);
                max_area = Math.max(area, max_area);
            }
        }

        // Now pop the remaining bars from stack and
        // calculate area with every popped bar as the
        // smallest bar
        while (!result.empty()) {
            top_val = row[ result.peek() ];
            result.pop();
            area = top_val * i;
            if (!result.empty())
                area = top_val * (i - result.peek() - 1);

            max_area = Math.max(area, max_area);
        }
        System.out.println("");
        return max_area;
    }



}