package com.company.utils;

interface IMultisetSet<T extends Comparable<T>> {
	boolean contains(T itemToCheck);
	boolean isEmpty();
	void add(T item);
	void add(T item, int count);
	int get(T item);
	int size();
	boolean remove(T itemToRemove);
	boolean remove(T itemToRemove, int count);
	void removeAll(T itemToRemove);
	void clear();
}

/**
 * Set which allows storage several same values
 * @param <T> type of multiset. Should extend Comparable interface
 */
public class Multiset<T extends Comparable<T>> implements IMultisetSet<T>{
	/**
	 * Binary tree node with two siblings
	 * @param <T>
	 */
	static private class TreeNode<T> {
		public T value;
		public TreeNode<T> right, left;
		public int count;

		public TreeNode(T valueToSet, int cnt) {
			right = null;
			left = null;
			value = valueToSet;
			count = cnt;
		}

		public TreeNode(T valueToSet) {
			right = null;
			left = null;
			value = valueToSet;
			count = 0;
		}
	}

	/**
	 * Is item exists in this multiset
	 * @param itemToCheck item to find in multiset
	 * @return is multiset contains specified item
	 */
	@Override
	public boolean contains(T itemToCheck) {
		return this.get(itemToCheck) != 0;
	}

	/**
	 * is multiset emplty
	 * @return is multites empty
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	private TreeNode<T> root;
	private int size;

	/**
	 * Init an empty multiset
	 */
	public Multiset() {
		root = null;
		size = 0;
	}

	/**
	 * Add a single item to a multiset
	 * @param itemToAdd item which to be added
	 */
	@Override
	public void add(T itemToAdd) {
		root = add(itemToAdd, root, 1);
		size++;
	}

	/**
	 * Add a number of items to a multiset
	 * @param itemToAdd item which to be added
	 * @param count quantity of items
	 */
	@Override
	public void add(T itemToAdd, int count) {
		root = add(itemToAdd, root, count);
		size += count;
	}

	/**
	 *  Add item to a specific node
	 * @param item item to add
	 * @param node node to which will added item
	 * @param count quantity of items
	 * @return node with new item
	 */
	private TreeNode<T> add(T item, TreeNode<T> node, int count) {
		if (node == null) {
			return new TreeNode<>(item, count);
		}

		int compareResult = item.compareTo(node.value);
		if (compareResult == 0) {
			node.count += count;
			return node;
		}

		if (compareResult > 0) {
			node.right = add(item, node.right, count);
			return node;
		}

		node.left = add(item, node.left, count);
		return node;
	}

	/**
	 * get a quantity of item from set
	 * @param item item to search
	 * @return quantity of specific item
	 */
	@Override
	public int get(T item) {
		return get(item, root);
	}

	/**
	 * get a quantity of item from multiset
	 * @param item item to search
	 * @param node node to search
	 * @return quantity of specific item
	 */
	private int get(T item, TreeNode<T> node) {
		if (node == null) {
			return 0;
		}

		int compareResult = item.compareTo(node.value);
		if (compareResult == 0) {
			return node.count;
		}

		if (compareResult > 0) {
			return get(item, node.right);
		}

		return get(item, node.left);
	}

	/**
	 * gets a total size of multiset
	 * @return size
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * removes one instance of specific item
	 * @param itemToRemove item to be removed
	 */
	@Override
	public boolean remove(T itemToRemove) {
		boolean result = this.get(itemToRemove) != 0;
		remove(itemToRemove, 1);
		return result;
	}

	/**
	 * remove all instanced of specific item
	 * @param itemToRemove item to be removed
	 */
	@Override
	public void removeAll(T itemToRemove) {
		remove(itemToRemove, get(itemToRemove));
	}

	/**
	 * removes countToRemove instances of specific item
	 * @param itemToRemove item to be removed
	 * @param countToRemove count to remove
	 */
	@Override
	public boolean remove(T itemToRemove, int countToRemove) {
		TreeNode<T> currentNode = root;
		TreeNode<T> parentNode = root;
		boolean isLeft = true;

		while (currentNode.value != itemToRemove) {
			parentNode = currentNode;
			if (itemToRemove.compareTo(currentNode.value) < 0) {
				isLeft = true;
				currentNode = currentNode.left;
			} else {
				isLeft = false;
				currentNode = currentNode.right;
			}

			if (currentNode == null) {
				return false;
			}
		}

		if (currentNode.count > countToRemove) {
			currentNode.count -= countToRemove;
			size -= countToRemove;
			return true;
		}

		size -= currentNode.count;

		if (currentNode.left == null && currentNode.right == null) {
			if (currentNode == root) {
				root = null;
			} else if (isLeft) {
				parentNode.left = null;
			} else {
				parentNode.right = null;
			}
		} else if (currentNode.right == null) {
			if (currentNode == root) {
				root = currentNode.left;
			} else if (isLeft) {
				parentNode.left = currentNode.left;
			} else {
				parentNode.right = currentNode.left;
			}
		} else if (currentNode.left == null) {
			if (currentNode == root) {
				root = currentNode.right;
			} else if (isLeft) {
				parentNode.left = currentNode.right;
			} else {
				parentNode.right = currentNode.right;
			}
		} else {
			TreeNode<T> heir = getHeir(currentNode);
			if (currentNode == root) {
				root.value = heir.value;
				root.count = heir.count;
			} else if (isLeft) {
				parentNode.left = heir;
			} else {
				parentNode.right = heir;
			}
		}
		return true;
	}

	/**
	 * gets a node to replace after removing another
	 * @param node node to search
	 * @return a node
	 */
	private TreeNode<T> getHeir(TreeNode<T> node) {
		TreeNode<T> parentNode = node;
		TreeNode<T> heirNode = node;
		TreeNode<T> currentNode = node.right;
		while (currentNode != null) {
			parentNode = heirNode;
			heirNode = currentNode;
			currentNode = currentNode.left;
		}

		/*if (heirNode != node.right) {
			parentNode.left = heirNode.right;
			heirNode.right = node.right;
		}*/

		return heirNode;
	}

	public void clear() {
		this.root = null;
		this.size = 0;
	}
}
