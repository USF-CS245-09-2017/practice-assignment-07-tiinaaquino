public class Hashtable {
	
	private HashNode[] arr;
	private int size;
	
	// constructor
	public Hashtable(){
		arr = new HashNode[10];
		size = 0;
	}
	
	private class HashNode {
		private String key;
		private String value;
		HashNode next;
		
		public HashNode(String key1, String value1){
			this.key = key1;
			this.value = value1;
		}
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 * 		"true" if a key/value object pair
	 */
	public boolean containsKey(String key) {
		int pos = hashCode(key) % arr.length;
		HashNode start = arr[pos];
		while (start != null) {
			if (start.key.equals(key))
				return true;
			start = start.next;
		}
		return false;
	}
	
	/**
	 * @param key
	 * @return
	 * 		value associated with the key which
	 * 		is passed as an argument, otherwise
	 * 		returns null if no key/value pair
	 * 		is contained by the Hashtable instance
	 */
	public String get(String key) {
		if (!containsKey(key))
			return null;
		
		int pos = hashCode(key) % arr.length;
		HashNode start = arr[pos];
		while (start != null) {
			if (start.key.equals(key))
				return start.value;
			start = start.next;
		}
		return null;
	}
	
	/**
	 * adds the key/value pair into the Hashtable
	 * instance, and replaces the stores value with
	 * the argument value is there is an existing
	 * key/value pair
	 * @param key
	 * @param value
	 */
	public void put(String key, String value) {
		int pos = hashCode(key) % arr.length;
		HashNode start = new HashNode(key,value);
		start.next = arr[pos];
		arr[pos] = start;
		if (!containsKey(key))
			size++;
	}
	
	/**
	 * removes the key/value pair from the Hashtable
	 * instance, throws an Exception instance if the
	 * key is not present in the Hashtable instance
	 * @param key
	 * @return
	 * 		value associated with the key
	 */
	public String remove(String key) throws Exception{
		if (!containsKey(key))
			throw new Exception();
		
		int pos = hashCode(key) % arr.length;
		HashNode start = arr[pos];
		if (start == null)
			return null;
		else {
			String removed = null;
			int count = 0;
			
			if (start.key.equals(key)) {
				removed = start.value;
				arr[pos] = start.next;
				size--;
				start = arr[pos];
				count++;
			}
			
			if (containsKey(key)) {
				while (start.next != null) {
					while (start.next != null && start.next.equals(key)) {
						count++;
						if (count == 1) {
							removed = start.next.value;
							size--;
						}
						start.next = start.next.next;
					}
					start = start.next;
				}
			}
			return removed;
		}

	}

	
	public int hashCode(String key) {
		return (int) Math.pow(Character.getNumericValue(key.charAt(key.length()/2)), 2);
	}
	
}
