public class Count implements Runnable {
	BinaryTree<Integer, Integer>.Node starter;
	int result = 0;

	public Count(BinaryTree<Integer, Integer>.Node starter) {
		this.starter = starter;
	}

	public int countNumber(BinaryTree<Integer, Integer>.Node x) {
		if (x != null) {

			countNumber(x.left);
			result++;
			countNumber(x.right);
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
