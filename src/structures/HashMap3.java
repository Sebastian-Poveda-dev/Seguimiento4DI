package structures;

public class HashMap3<K, V> {


    private final int SIZE = 5;

    private Entry<K,V>[] table;

    public HashMap3() {
        table = new Entry[SIZE];
    }

    public void put(K key, V value) {
        int hash = key.hashCode();
        int index = hash % SIZE;
        Entry<K,V> entry = table[index];

        if (entry == null) {
            table[index] = new Entry<>(key, value);
        } else {
            while (entry != null) {
                if (entry.getKey().equals(key)) {
                    entry.setValue(value);
                    return;
                }
                entry = entry.getNext();
            }
        }

        if (entry.getKey().equals(key)) {
            entry.setValue(value);
            return;
        }

        entry.next = new Entry<K, V>(key, value);
    }

    public V get(K key) {
        int hash = key.hashCode();
        int index = hash % SIZE;
        Entry<K,V> entry = table[index];

        if (entry == null) {
            return null;
        }
        while (entry != null) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
            entry = entry.getNext();
        }
        return null;
    }

    public Entry<K, V> remove(K key) {
        int hash = key.hashCode();
        int index = hash % SIZE;
        Entry<K,V> entry = table[index];

        if (entry == null) {
            return null;
        }
        if (entry.getKey().equals(key)) {
            table[index] = entry.getNext();
            entry.next = null;
        }

        Entry<K, V> previous = entry;
        entry = entry.getNext();

        while (entry != null) {
            if (entry.getKey().equals(key)) {
                previous.next = entry.getNext();
                entry.next = null;
                return entry;
            }
        }
        return null;
    }


}
