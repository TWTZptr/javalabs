package com.company.utils;

import org.junit.Assert;
import org.junit.Test;

public class MultisetTest {
	@Test
	public void MultisetSizeTest() {
		Multiset<Integer> a = new Multiset<>();
		a.add(2);
		a.add(1,2);
		a.add(3);
		a.add(2, 5);

		final int expectedSize = 9;
		final int actualSize = a.size();
		Assert.assertEquals(expectedSize, actualSize);
	}

	@Test
	public void MultisetAddGetTest() {
		Multiset<Integer> a = new Multiset<>();
		a.add(2);
		a.add(1,2);
		a.add(3);
		a.add(2, 5);

		final int expectedCount = 6;
		final int actualCount = a.get(2);
		Assert.assertEquals(expectedCount, actualCount);
	}

	@Test
	public void MultisetRemoveTest1() {
		Multiset<Integer> a = new Multiset<>();
		a.add(2);
		a.add(1,2);
		a.add(3);
		a.add(2, 5);
		a.remove(3);

		final int expectedCount = 0;
		final int actualCount = a.get(3);
		Assert.assertEquals(expectedCount, actualCount);
	}

	@Test
	public void MultisetRemoveTest2() {
		Multiset<Integer> a = new Multiset<>();
		a.add(2);
		a.add(1,2);
		a.add(3);
		a.add(2, 5);
		a.remove(2, 3);

		final int expectedCount = 3;
		final int actualCount = a.get(2);
		Assert.assertEquals(expectedCount, actualCount);
	}

	@Test
	public void MultisetRemoveAllTest() {
		Multiset<Integer> a = new Multiset<>();
		a.add(2);
		a.add(1,2);
		a.add(3);
		a.add(2, 5);
		a.removeAll(2);

		final int expectedCount = 0;
		final int actualCount = a.get(2);
		Assert.assertEquals(expectedCount, actualCount);
	}

	@Test
	public void MultisetComplexTest() {
		Multiset<Integer> a = new Multiset<>();
		a.add(2);
		a.add(1,2);
		a.add(3);
		a.add(2, 5);

		a.removeAll(2);
		a.remove(1);

		final int expectedCount = 1;
		final int actualCount = a.get(3);
		Assert.assertEquals(expectedCount, actualCount);

		final int expectedSize = 2;
		final int actualSize = a.size();
		Assert.assertEquals(expectedSize, actualSize);
	}

	@Test
	public void UnexistElementTest() {
		Multiset<Integer> a = new Multiset<>();
		a.add(2);
		a.add(1,2);
		a.add(3);
		a.add(2, 5);

		a.removeAll(2);
		a.remove(1);

		final int expectedCount = 0;
		final int actualCount = a.get(10);
		Assert.assertEquals(expectedCount, actualCount);

		final int expectedSize = 2;
		final int actualSize = a.size();
		Assert.assertEquals(expectedSize, actualSize);
	}

	@Test
	public void ZeroSizeMultisetTest() {
		Multiset<Integer> a = new Multiset<>();
		final int expectedSize = 0;
		final int actualSize = a.size();
		Assert.assertEquals(expectedSize, actualSize);
	}

	@Test
	public void EmptyMultisetTest() {
		Multiset<Integer> a = new Multiset<>();
		Assert.assertTrue(a.isEmpty());
	}

	@Test
	public void NotEmptyMultisetTest() {
		Multiset<Integer> a = new Multiset<>();
		a.add(2);
		Assert.assertFalse(a.isEmpty());
	}

	@Test
	public void EmptyMultisetAfterDeleteTest() {
		Multiset<Integer> a = new Multiset<>();
		a.add(1,2);
		a.remove(1,2);
		final int expectedSize = 0;
		final int actualSize = a.size();
		Assert.assertEquals(expectedSize, actualSize);
	}

	@Test
	public void MultisetContainsTest() {
		Multiset<Integer> a = new Multiset<>();
		a.add(1,2);
		Assert.assertTrue(a.contains(1));
	}

	@Test
	public void DeleteElementsMoreThanExists() {
		Multiset<Integer> a = new Multiset<>();
		a.add(1,2);
		a.add(3,2);
		a.remove(1,5);

		int expectedCount = 0;
		int actualCount = a.get(1);
		Assert.assertEquals(expectedCount, actualCount);

		expectedCount = 2;
		actualCount = a.get(3);
		Assert.assertEquals(expectedCount, actualCount);
	}

	@Test
	public void ClearMultisetTest() {
		Multiset<Integer> a = new Multiset<>();
		a.add(2);
		a.add(1,2);
		a.add(3);
		a.add(2, 5);
		a.clear();
		Assert.assertTrue(a.isEmpty());
	}
}