
// ========================== Tree path sum===========================================
// Time Complexity : O(n)
// Space Complexity :O(n) We use Temporary array list for maintaining the list for all the leaf matching the target
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No.

//  Your code here along with comments explaining your approach
// Calculate sum at each node
// Add each node value to the temp list - currPath(To keep track of the path we counted)
// If it reaches leaf node and currSum matches target value - Add the Temp list(currPath) to the result array list
// Remove the last node from temp list each time after reaching the leaf node to move over next nodes.

// NOTE  - We need to add new array list to result. Reason - Only one copy of temp list is maintained as we are passing object reference in the recursive call. This will remove all the elements at the end of the travarsal. To preserve the result create new array list and add to result when there is a match with target.
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    List<List<Integer>> result; 
   
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        result = new ArrayList<>();
        List<Integer> curr_path = new ArrayList<>();
         helper(root, 0, sum,curr_path );
        
        return result;
    }
    
    private void helper(TreeNode root, int curr_sum, int target, List<Integer> curr_path ){
        
        // base
        if(root == null)
            return;
        
        // logic
        curr_sum += root.val;
        curr_path.add(root.val);
        if(root.left == null && root.right == null) 
           {
               if(curr_sum == target)
               {
                 result.add(new ArrayList<>(curr_path));
                   
                }
               
           }
        
        System.out.println("Before root left call" + curr_path.get(curr_path.size()-1));
        helper(root.left, curr_sum, target, curr_path);
        System.out.println("Before root right call" +curr_path.get(curr_path.size()-1));
        helper(root.right, curr_sum, target, curr_path);
        
        curr_path.remove(curr_path.size()-1);    
                    
    }
}

==============================Symmetric Tree validation===================
  // Time Complexity : O(n)
// Space Complexity :O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Yes. In iterative solution, I should to continue when I encounter left AND right nodes are null. I was returning which breaks the while loop when it encounters first leaf node.


// Your code here along with comments explaining your approach
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isSymmetric(TreeNode root) {
        
        
        // return helper(root, root);
    // Iterative solution
        if(root == null)
            return true;
        
        Stack<TreeNode> st = new Stack();
        st.push(root.left);
        st.push(root.right);
        while(!st.isEmpty()){
            TreeNode right = st.pop();
            TreeNode left = st.pop();
            if(right == null && left == null) continue; // Should continue when both nodes are null.
            if((right == null || left == null ) || (right.val != left.val)) return false;
            st.push(left.left);
            st.push(right.right);
            st.push(left.right);
            st.push(right.left);
            
        }
        return true;
    }
    
    // Recursive solution
    
    private boolean helper(TreeNode left,TreeNode right){
        //base

        if(left == null && right == null)
            return true;
        if((left == null || right == null) || (left.val != right.val))
            return false;
        
        //logic
            return helper(left.left, right.right) && helper(left.right, right.left);
    }
    
    
}
