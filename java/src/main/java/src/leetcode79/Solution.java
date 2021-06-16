package leetcode79;

import java.util.Stack;

public class Solution {
    public static void main(String[] args) {
        System.out.println(exist(new char[][]{{'A','B','C','E'},{'S','F','E','S'},{'A','D','E','E'}},
        "ABCEFSADEESE"));
    }
    public static boolean exist(char[][] board, String word) {
        Stack stack = new Stack();
        int rowNum = board.length;
        int colNum = board[0].length;
        boolean visited[][];
        int wordLength = word.length();
        /**
         * 先把第一个字母符合的点放入
         */
        for(int ii =0;ii<rowNum;ii++)
        {
            for(int jj=0;jj<colNum;jj++)
            {
                if(board[ii][jj]==word.charAt(0))
                {
                    visited = new boolean[rowNum][colNum];
                    stack.push(new Node(new int[]{ii,jj},0,-1));
                    visited[ii][jj]=true;
                    while(!stack.isEmpty())
                    {
                        Node curNode = (Node)stack.pop();
                        int i = curNode.position[0];
                        int j = curNode.position[1];
                        visited[i][j]=false;
                        /**
                         * 当前节点的位置
                         */

                        if(curNode.wordIndex==wordLength-1)
                        {
                            return true;
                        }
                        switch(curNode.diraction){
                            case 0:
                                if(i-1>=0&&curNode.from!=0&&!visited[i-1][j])
                                {
                                    if(board[i-1][j]==word.charAt(curNode.wordIndex+1))
                                    {
                                        stack.push(curNode);
                                        visited[i][j]=true;
                                        stack.push(new Node(new int[] {i-1,j},curNode.wordIndex+1,2));
                                        visited[i-1][j]=true;
                                        curNode.diraction=1;
                                        break;
                                    }
                                }
                            case 1:
                                if(j+1<colNum&&curNode.from!=1&&!visited[i][j+1])
                                {
                                    if(board[i][j+1]==word.charAt(curNode.wordIndex+1))
                                    {
                                        curNode.diraction=2;
                                        stack.push(curNode);
                                        visited[i][j]=true;
                                        stack.push(new Node(new int[] {i,j+1},curNode.wordIndex+1,3));
                                        visited[i][j+1]=true;
                                        break;
                                    }
                                }
                            case 2:
                                if(i+1<rowNum&&curNode.from!=2&&!visited[i+1][j])
                                {
                                    if(board[i+1][j]==word.charAt(curNode.wordIndex+1))
                                    {
                                        curNode.diraction=3;
                                        stack.push(curNode);
                                        visited[i][j]=true;
                                        stack.push(new Node(new int[] {i+1,j},curNode.wordIndex+1,0));
                                        visited[i+1][j]=true;
                                        break;
                                    }
                                }
                            case 3:
                                if(j-1>=0&&curNode.from!=3&&!visited[i][j-1])
                                {
                                    if(board[i][j-1]==word.charAt(curNode.wordIndex+1))
                                    {
                                        curNode.diraction=-1;
                                        stack.push(curNode);
                                        visited[i][j]=true;
                                        stack.push(new Node(new int[] {i,j-1},curNode.wordIndex+1,1));
                                        visited[i][j-1]=true;
                                        break;
                                    }
                                }
                            default:
                                break;
                        }
                    }

                }
            }
        }


        return false;
    }
}

/**
 * 存入栈的每一个节点
 * 包括 1.位置
 *     2.正在走的方向
 *     3.字符在单词中的位置
 *     4.来自方向
 */
class Node{
    int[] position;
    int diraction;
    int wordIndex;
    int from;

    /**
     *
     * @param position 位置
     * @param wordIndex 字符在单词中的位置
     * @param from 来自的方向
     *        diraction 正在走的方向，默认0
     */
    public Node(int[] position,int wordIndex,int from)
    {
        this.position=position;
        this.diraction=0;
        this.wordIndex=wordIndex;
        this.from=from;
    }
}