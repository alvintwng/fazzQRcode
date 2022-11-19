package com.zero1;

public class CallString {
	public static final int MAX_NUM = 10;
	private String[] obj;
	private int index;

	public CallString() {
		obj = new String[MAX_NUM];
		index = 0;
	}

	public void add(String obj) {
		if (index >= MAX_NUM) {
			// System.out.println("Error, reached maximum limit of posts.");
			// System.exit(0);
			removePost(0);
		}
		this.obj[index] = obj;
		index++;
	}

	public boolean removePost(int indexNo) {
		while (indexNo < index) {
			for (int j = indexNo; j < (index - 1); j++) {
				obj[j] = obj[j + 1];
			}
			index--;
			return true;
		}

		return false;
	}

	public String printAll() {
		String s = "<p>Response (latest " + MAX_NUM + "):</p> ";
		for (int i = 0; i < index; i++) {
			s += (obj[i] + "  <br><br> ");
		}
		return s;
	}
}