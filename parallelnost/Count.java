public class Count implements Runnable {
	private	int starter;
	private	int result = 0;
	private BinaryTree<Integer, Integer> tree = new BinaryTree<Integer, Integer>();

	public Count(int starter, BinaryTree<Integer, Integer> tree) {
		this.starter = starter;
		this.tree = tree;
	}

	private int countNumber(int key) {

		if (tree.getLeftChildKey(key) != null) {
			countNumber(tree.getLeftChildKey(key));
		}
		result++;
		if (tree.getRightChildKey(key) != null) {
			countNumber(tree.getRightChildKey(key));
		}
		return result;
	}

	@Override
	public void run() {
		countNumber(starter);
		return;
	}

	// returned number of nodes
	public int getResult() {

		return result;
	}
}
