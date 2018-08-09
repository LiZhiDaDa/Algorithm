package com.tfq;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//这里的算法都是根据LeetCode题目写的，大家可以根据每个方法的注释去LeetCode寻找具体的题目解释。
//可以在LeetCode中搜索编号或者题目

public class Algorithm {
	public static void main(String[] args) {
		Solution solution = new Solution();
		// int result = solution.reverse(-2147483648);
		// boolean result = solution.isPalindrome(23432);
		// int result = solution.romanToInt("MCMXCIV");
		// String[] strs = { "aa", "a" };
		// String result = solution.longestCommonPrefix(strs);
		// boolean result = solution.isValid("([)]");
		// int[] nums = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		// int result = solution.removeDuplicates(nums);
		// int result = solution.removeElement(nums, 3);
		// int result = solution.strStr("a", "a");
		// String result = solution.countAndSay(6);
		// int result = solution.maxSubArray(nums);
		// int result = solution.lengthOfLastWord("hello world");
		// int[] nums = { 9 };
		// int[] result = solution.plusOne(nums);
		// int i = 0;
		// while (i < result.length) {
		// System.out.println(result[i]);
		// i++;
		// }
		// String result = solution.addBinary("11", "1");
		// int result = solution.mySqrt(3);
		// int result = solution.climbStairs(44);
		// List<List<Integer>> result = solution.levelOrderBottom(null);
//		List<List<Integer>> result = solution.generate(4);
//		int[] nums = {3,4,5,6,7,9};
//		int result = solution.search(nums, 8);
		boolean result = solution.isPerfectSquare(5);
		System.out.println(result);
	}
}

class Solution {
	
	
	// 367. 有效的完全平方数
	public boolean isPerfectSquare(int num) {
		if(num < 1) {
			return false;
		}
		if(num == 1) {
			return true;
		}
		
		int begin = 0;
		int end = num/2;
		float mid = 0.0f;
		while(begin <= end) {
			mid = (begin + end)/2;
			if(mid < num/mid) {
				begin = (int) (mid+1);
			}else if(mid > num/mid) {
				end = (int) (mid-1);
			}else {
				return true;
			}
		}
		return false;
	}



	// 704、二分查找
	public int search(int[] nums, int target) {
		int begin = 0;
		int end = nums.length-1;
		int mid = 0;
		while(begin <= end) {
			mid = (begin+end)/2;
			if(target < nums[mid]) {
				end = mid-1;
			}else if(target > nums[mid]){
				begin = mid+1;
			}else {
				return mid;
			}
		}
		return -1;
	}
	
	// 118、杨辉三角
	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> sum = new ArrayList<>();
		if(numRows < 0) {
			return null;
		}
		if(numRows >= 1) {
			List<Integer> list = new ArrayList<Integer>();
			list.add(1);
			sum.add(list);
		}
		if (numRows >= 2) {
			List<Integer> list = new ArrayList<Integer>();
			list.add(1);
			list.add(1);
			sum.add(list);
		}
		if (numRows >= 3){
			for(int i=2; i<numRows; i++) {
				List<Integer> list = new ArrayList<Integer>();
				list.add(1);
				for(int j=1; j<i; j++) {
					List<Integer> listIn = sum.get(i-1);
					list.add(listIn.get(j-1)+listIn.get(j));
				}
				list.add(1);
				sum.add(list);
			}
		}
		return sum;
	}
	
	// 107、二叉树的层次遍历
	List<List<TreeNode>> listSum = new ArrayList<List<TreeNode>>();
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
//		TreeNode root = new TreeNode(1);
//		TreeNode n9 = new TreeNode(9);
//		TreeNode n20 = new TreeNode(20);
//		TreeNode n15 = new TreeNode(15);
//		TreeNode n7 = new TreeNode(7);
//		root.left = n9;
//		root.right = n20;
//		n20.left = n15;
//		n20.right = n7;
		if(root == null) {
			return new ArrayList<>();
		}
		List<TreeNode> list = new ArrayList<TreeNode>();
		list.add(root);
		listSum.add(list);
		List<TreeNode> bList = b(list);
		if(bList != null) {
			listSum.add(bList);
		}
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		for(int i=1; i<listSum.size(); i++) {
			List<TreeNode> model = listSum.get(i);
			List<Integer> resultIn = new ArrayList<Integer>();
			for(int j=0; j<model.size(); j++) {
				TreeNode node = model.get(j);
				resultIn.add(node.val);
			}
			result.add(resultIn);
		}
		List<Integer> first = new ArrayList<Integer>();
		List<TreeNode> firstModel = listSum.get(0);
		first.add(firstModel.get(0).val);
		result.add(first);
		return result;
	}
	
	public List<TreeNode> b(List<TreeNode> tree) {
		List<TreeNode> list = new ArrayList<TreeNode>();
		for(int i=0; i<tree.size(); i++) {
			TreeNode node = tree.get(i);
			if(node.left != null) {
				list.add(node.left);
			}
			if(node.right != null) {
				list.add(node.right);
			}
		}
		if(list.size() == 0) {
			return null;
		}else {
			List<TreeNode> bList = b(list);
			if(bList != null) {
				listSum.add(bList);
			}
		}
		return list;
	}

	// 104、二叉树的最大深度
	public int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		if (root.left == null) {
			return maxDepth(root.right) + 1;
		}
		if (root.right == null) {
			return maxDepth(root.left) + 1;
		}
		return max(maxDepth(root.left), maxDepth(root.right)) + 1;
	}

	public int max(int a, int b) {
		if (a > b) {
			return a;
		}
		return b;
	}

	// 100、相同的树
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		}
		if (p == null || q == null) {
			return false;
		}
		if (p.val != q.val) {
			return false;
		}
		return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
	}

	// 101、对称二叉树
	public boolean isSymmetric(TreeNode root) {
		// 这里多了一步操作，省了非空判断
		return symmetric(root, root);
	}

	public boolean symmetric(TreeNode left, TreeNode right) {
		if (left == null && right == null) {
			return true;
		}
		if (left == null || right == null) {
			return false;
		}
		if (left.val != right.val) {
			return false;
		} else {
			return symmetric(left.left, right.right) && symmetric(left.right, right.left);
		}
	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	// 70、爬楼梯
	// 可以参考斐波那契数列，
	public int climbStairs(int n) {
		// 最优解法
		int[] dp = new int[n + 3]; // 为了避免下面给数组赋值时 若n<3 就容易出现数组越界 所以在这里多申请了3个空间来赋值dp初始条件
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 2; //
		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];

		}
		return dp[n];
		// 此方法超时
		// int m = 0;
		// if(n == 2) {
		// m = 2;
		// }else if(n == 1) {
		// m = 1;
		// }else {
		// m = climbStairs(n-2)+climbStairs(n-1);
		// }
		// return m;
	}

	// 69、x的平方根
	public int mySqrt(int x) {
		if (x == 0) {
			return 0;
		}
		if (x == 1) {
			return 1;
		}
		int begin = 0;
		int end = x;
		int mid = x / 2;
		while (begin <= end) {
			mid = (begin + end) / 2;
			if (mid == x / mid) {
				return mid;
			}
			if (mid < x / mid) {
				begin = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return end;
	}

	// 67、二进制求和
	public String addBinary(String a, String b) {
		int[] A = toIntArray(a);
		int[] B = toIntArray(b);
		int carry = 0;
		int len = A.length > B.length ? A.length : B.length;
		int tmp = len + 1;
		int[] result = new int[len];
		int i = A.length - 1, j = B.length - 1;
		while (i >= 0 || j >= 0) {
			int num = 0;
			if (i >= 0) {
				num += A[i];
			}
			if (j >= 0) {
				num += B[j];
			}
			num += carry;
			carry = num / 2;
			len--;
			result[len] = num % 2;
			i--;
			j--;
		}
		if (carry > 0) {
			int[] tmpArray = new int[tmp];
			for (int k = 0; k < tmp; k++) {
				if (k == 0) {
					tmpArray[0] = carry;
				} else {
					tmpArray[k] = result[k - 1];
				}
			}
			return arrayToString(tmpArray);
		} else {
			return arrayToString(result);
		}
	}

	public int[] toIntArray(String a) {
		char[] A = a.toCharArray();
		int[] arrayA = new int[A.length];
		for (int i = 0; i < A.length; i++) {
			arrayA[i] = Integer.parseInt(String.valueOf(A[i]));
		}
		return arrayA;
	}

	public String arrayToString(int[] a) {
		StringBuffer b = new StringBuffer();
		for (int i = 0; i < a.length; i++) {
			b.append(a[i]);
		}
		return b.toString();
	}

	// 66、加一
	public int[] plusOne(int[] digits) {
		int i = digits.length - 1;
		int carry = 1;
		while (i >= 0) {
			int num = digits[i] + carry;
			digits[i] = num % 10;
			carry = num / 10;
			i--;
		}
		if (carry > 0) {
			int[] array = new int[digits.length + 1];
			for (int j = 0; j < array.length; j++) {
				if (j == array.length - 1) {
					array[0] = carry;
					break;
				}
				array[j + 1] = digits[j];
			}
			return array;
		} else {
			return digits;
		}
	}

	// 58、最后一个单词的长度
	// 最优算法是java自带的一个方法，return s.trim().length()-s.trim().lastIndexOf(" ")-1;
	// trim只是去掉前后空格
	// 其次的算法是根据空格为分隔符分成数组求最后一个元素的length
	public int lengthOfLastWord(String s) {
		if (s.trim().length() == 0) {
			return 0;
		}
		int len = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			char c = s.charAt(i);
			System.out.println(String.valueOf(c));
			if (String.valueOf(c).equals(" ")) {
				if (len > 0) {
					break;
				}
			} else {
				len++;
			}
		}
		return len;
	}

	// 53、最大子序和
	public int maxSubArray(int[] nums) {
		// 第一种方法时间复杂度为 O(n^2)
		// int max = nums[0];
		// int sum = 0;
		// for(int i=0; i<nums.length; i++) {
		// sum = 0;
		// for(int j=i; j<nums.length; j++) {
		// sum += nums[j];
		// if(sum > max) {
		// max = sum;
		// }
		// }
		// }
		// return max;

		// 第二种方法时间复杂度为 O(n)
		int max = nums[0];
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if (max < sum) {
				max = sum;
			}
			if (sum < 0) {
				sum = 0;
			}
		}
		return max;
	}

	// 38、报数
	public String countAndSay(int n) {
		if (n == 1) {
			return "1";
		}
		int i = 2;
		String result = "1";
		while (i <= n) {
			result = nextNum(result);
			i++;
		}
		return result;
	}

	public String nextNum(String str) {
		String say = "";
		char[] c = str.toCharArray();
		int j = 0;
		while (j < c.length) {
			char value = c[j];
			int count = 0;
			while (j < c.length && value == c[j]) {
				j++;
				count++;
			}
			say += count;
			say += value;
		}
		return say;
	}

	// 35、搜索插入位置
	public int searchInsert(int[] nums, int target) {
		if (target <= nums[0]) {
			return 0;
		} else if (target > nums[nums.length - 1]) {
			return nums.length;
		}
		for (int i = 1; i < nums.length; i++) {
			if (target <= nums[i]) {
				return i;
			}
		}
		return 1;
	}

	// 28、实现strStr()
	public int strStr(String haystack, String needle) {
		if (needle.isEmpty()) {
			return 0;
		}
		int l1 = haystack.length();
		int l2 = needle.length();
		for (int i = 0; i <= l1 - l2; i++) {
			int flag = 1;
			for (int j = 0; j < l2; j++) {
				if (haystack.charAt(i + j) != needle.charAt(j)) {
					flag = 0;
					break;
				}
			}
			if (flag == 1) {
				return i;
			}
		}
		return -1;
	}

	// 27、移除元素
	public int removeElement(int[] nums, int val) {
		int n = nums.length;
		int i = 0;
		while (i < n) {
			if (nums[i] == val) {
				n--;
				nums[i] = nums[n];
			} else {
				i++;
			}
		}
		return i;
	}

	// 26、删除排序数组中的重复项
	public int removeDuplicates(int[] nums) {
		int j = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[j] != nums[i]) {
				j++;
				nums[j] = nums[i];
			}
		}
		return j + 1;
	}

	// 21、合并两个有序链表
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		// 递归解决好高效，哈哈
		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}
		ListNode head = null;
		if (l1.val <= l2.val) {
			head = l1;
			head.next = mergeTwoLists(l1.next, l2);
		} else {
			head = l2;
			head.next = mergeTwoLists(l1, l2.next);
		}
		return head;
		// ListNode list = new ListNode(0);
		// ListNode first = list;
		// while(l1 != null && l2 != null) {
		// if(l1.val <= l2.val) {
		// list.next = l1;
		// l1 = l1.next;
		// }else {
		// list.next = l2;
		// l2 = l2.next;
		// }
		// list = list.next;
		// }
		// while(l1 != null) {
		// list.next = l1;
		// l1 = l1.next;
		// list = list.next;
		// }
		// while(l2 != null) {
		// list.next = l2;
		// l2 = l2.next;
		// list = list.next;
		// }
		// return first.next;
	}

	// 20、有效的括号 ()({{)}
	public boolean isValid(String s) {
		// 长度不是2的整数倍肯定错
		if (s.length() % 2 != 0) {
			return false;
		}
		Deque<Character> stack = new LinkedList<>();
		Character top;
		for (int i = 0; i < s.length(); i++) {
			Character str = s.charAt(i);
			switch (str) {
			case '(':
			case '[':
			case '{':
				stack.addFirst(str);
				break;
			case ')':
				if (stack.isEmpty()) {
					return false;
				}
				top = stack.getFirst();
				if (top == '(') {
					stack.removeFirst();
				} else if (top == ']' || top == '}') {
					return false;
				} else {
					stack.add(str);
				}
				break;
			case ']':
				if (stack.isEmpty()) {
					return false;
				}
				top = stack.getFirst();
				if (top == '[') {
					stack.removeFirst();
				} else if (top == ')' || top == '}') {
					return false;
				} else {
					stack.add(str);
				}
				break;
			case '}':
				if (stack.isEmpty()) {
					return false;
				}
				top = stack.getFirst();
				if (top == '{') {
					stack.removeFirst();
				} else if (top == ']' || top == ')') {
					return false;
				} else {
					stack.add(str);
				}
				break;
			default:
				break;
			}
		}
		if (stack.isEmpty() == false) {
			return false;
		}
		return true;
	}

	// 14、最长公共前缀
	public String longestCommonPrefix(String[] strs) {
		if (strs.length == 0) {
			return "";
		}
		String firstStr = strs[0];
		String result = "";
		for (int i = 0; i < firstStr.length(); i++) {
			String firstChar = String.valueOf(firstStr.charAt(i));
			boolean isEqual = true;
			for (String str : strs) {
				if (str.length() == i) {
					return result;
				}
				String otherFirstChar = String.valueOf(str.charAt(i));
				if (firstChar.equals(otherFirstChar) == false) {
					isEqual = false;
				}
			}
			if (isEqual) {
				result += firstChar;
			} else {
				return result;
			}
		}
		return result;
	}

	// 13、罗马数字转整数
	public int romanToInt(String s) {
		int result = 0;
		for (int i = 0; i < s.length(); i++) {
			String str = String.valueOf(s.charAt(i));
			switch (str) {
			case "I":
				if (i < s.length() - 1) {
					String nextStr = String.valueOf(s.charAt(i + 1));
					if (nextStr.equals("V") == true) {
						i++;
						result += 4;
						break;
					} else if (nextStr.equals("X") == true) {
						result += 9;
						i++;
						break;
					}
				}
				result += 1;
				break;
			case "V":
				result += 5;
				break;
			case "X":
				if (i < s.length() - 1) {
					String nextStr = String.valueOf(s.charAt(i + 1));
					if (nextStr.equals("L") == true) {
						i++;
						result += 40;
						break;
					} else if (nextStr.equals("C") == true) {
						result += 90;
						i++;
						break;
					}
				}
				result += 10;
				break;
			case "L":
				result += 50;
				break;
			case "C":
				if (i < s.length() - 1) {
					String nextStr = String.valueOf(s.charAt(i + 1));
					if (nextStr.equals("D") == true) {
						i++;
						result += 400;
						break;
					} else if (nextStr.equals("M") == true) {
						result += 900;
						i++;
						break;
					}
				}
				result += 100;
				break;
			case "D":
				result += 500;
				break;
			case "M":
				result += 1000;
				break;
			default:
				break;
			}
		}
		return result;
	}

	// 9、回文数
	public boolean isPalindrome(int x) {
		String str = String.valueOf(x);
		String str1 = "";
		for (int i = str.length() - 1; i >= 0; i--) {
			str1 += str.charAt(i);
		}
		if (str.equals(str1) == true) {
			return true;
		} else {
			return false;
		}
	}

	// 7、反转整数
	public int reverse(int x) {
		long result = 0;
		while (x != 0) {
			long value = x % 10;
			result = result * 10 + value;
			if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
				return 0;
			}
			x /= 10;
		}
		return (int) result;
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}