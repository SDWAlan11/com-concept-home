package com.concept.test.profiling.memory.binarytree;

import org.assertj.core.api.ProxifyMethodChangingTheObjectUnderTest;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.BinaryOperator;

public class BinaryTree {

    class TreeNode {
        int val;
        TreeNode parent;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public TreeNode buildTree(int[] x,TreeNode parent, int index) {
        if (x == null)
            return null;
        if (index >= x.length)
            return null;
        TreeNode root = new TreeNode(x[index]);
        root.parent = parent;
        root.left = buildTree(x, root, 2 * index + 1);
        root.right = buildTree(x, root, 2 * index + 2);
        return root;

    }
     public void printTree(TreeNode node){
        if(node == null) {
            System.out.print("-null-");
            return;
        }

         System.out.print("-"+node.val+"-");
         printTree(node.left);
         printTree(node.right);

     }

    public void reversePrintTree(TreeNode node){
        if(node == null) {
            System.out.print("-null-");
            return;
        }

        printTree(node.left);
        printTree(node.right);
        System.out.print("-"+node.val+"-");

    }

     public TreeNode returnRoot(TreeNode node){
        if (node.parent == null)
            return node;
        return returnRoot(node.parent);
     }

     public boolean busquedaAnchura(TreeNode node){
         Queue<TreeNode> queue = new LinkedList<>();
         queue.add(node);
         while (queue.size() != 0){
             TreeNode treeNode = queue.poll();
             System.out.println("Value-" + treeNode.val);
             if(treeNode.val == node.val)
                 return true;
             else {
                 queue.add(treeNode.left);
                 queue.add(treeNode.right);
             }
         }
         return false;
     }

    public static void main(String[] args){
        BinaryTree binaryTree = new BinaryTree();
        TreeNode rootGuess = binaryTree.buildTree(new int[] {1,2,3,4,5,6},null, 0);
        System.out.println(rootGuess.val);
        System.out.println(rootGuess.left.val);
        System.out.println(rootGuess.left.parent.val);

        binaryTree.printTree(rootGuess);

        System.out.println("Padre = " + binaryTree.returnRoot(rootGuess).val);

        binaryTree.reversePrintTree(rootGuess);

        System.out.println("-");
        binaryTree.busquedaAnchura(rootGuess.right.left);
    }
}
