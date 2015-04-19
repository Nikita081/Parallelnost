import java.util.*;

public class Main {

	public static void main(String[] args) {

		long time = System.currentTimeMillis();
		int thread_num = 0;

		thread_num = 3; // Integer.valueOf(args[1]);
		int node_num = 0;

		BinaryTree<Integer, Integer> my_tree = new BinaryTree<Integer, Integer>();

		int[] keys = new int[50000];
		int[] values = new int[50000];

		// Creating binary tree
		for (int i = 0; i < 50000; i++) {

			keys[i] = (int) (Math.random() * 1000000000);

			values[i] = (int) (Math.random() * 1000000000);

			my_tree.addNode(keys[i], values[i]);
		}

		// creating queue for nodes
		Queue<BinaryTree<Integer, Integer>.Node> queue = new LinkedList<BinaryTree<Integer, Integer>.Node>();
		queue.add(my_tree.root);

		// master-thread starts forming tasks for the other threads, using
		// breadth-first-search
		while (!queue.isEmpty()) {
			BinaryTree<Integer, Integer>.Node tmpNode = queue.poll();
			node_num++;
			if (tmpNode.left != null) {

				queue.add(tmpNode.left);
			}
			if (tmpNode.right != null) {

				queue.add(tmpNode.right);
				if (queue.size() == thread_num) {
					break;
				}
			}
		}

		Thread[] thread = new Thread[thread_num];

		Count[] counter = new Count[thread_num];

		// give work
		for (int i = 0; i < thread_num; i++) {
			counter[i] = new Count(queue.poll());
			thread[i] = new Thread(counter[i]);
			thread[i].start();
		}

		// get result
		for (int i = 0; i < thread_num; i++) {
			try {
				thread[i].join();
			} catch (InterruptedException e) {
				System.out.println("Intrrupted!");
			}
			System.out.println(thread[i].getName() + " " + "returned"
					+ counter[i].getResult());
			node_num += counter[i].getResult();
		}

		System.out.println("Program has ended sucsessfully" + " "
				+ "and  quantity of n" + "" + "" + "" + "odes is:" + node_num);

		System.out.println("Work has been done for "
				+ (System.currentTimeMillis() - time) + " milliseconds.");
	}

}
