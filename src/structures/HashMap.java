package structures;

public class HashMap <K, V> {

    private int SIZE = 5;
    private final float LOAD_FACTOR = 0.75f;
    private Entry<K,V>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public HashMap() {
        table = new Entry[SIZE];
        size = 0;
    }

    public void put(K key, V value) {
        int hash = key.hashCode();
        int index = Math.abs(hash % SIZE);
        Entry<K,V> entry = table[index];

        if (entry == null) {
            table[index] = new Entry<>(key, value);
            size++;
        } else {
            // Verificar si la clave ya existe
            Entry<K,V> current = entry;
            Entry<K,V> previous = null;

            while (current != null) {
                if (current.getKey().equals(key)) {
                    current.setValue(value);
                    return;
                }
                previous = current;
                current = current.getNext();
            }

            // La clave no existe, añadir al final de la lista
            previous.next = new Entry<>(key, value);
            size++;
        }

        // Verificar si es necesario redimensionar
        if ((float) size / SIZE > LOAD_FACTOR) {
            resize();
        }
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newSize = SIZE * 2;
        Entry<K,V>[] oldTable = table;
        table = new Entry[newSize];
        size = 0;
        SIZE = newSize;

        // Reinserta todos los elementos
        for (int i = 0; i < oldTable.length; i++) {
            Entry<K,V> entry = oldTable[i];
            while (entry != null) {
                put(entry.getKey(), entry.getValue());
                entry = entry.getNext();
            }
        }
    }

    public V get(K key) {
        int hash = key.hashCode();
        int index = Math.abs(hash % SIZE);
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
        int index = Math.abs(hash % SIZE);
        Entry<K,V> entry = table[index];

        if (entry == null) {
            return null;
        }

        // Si el elemento a eliminar es el primero
        if (entry.getKey().equals(key)) {
            table[index] = entry.getNext();
            entry.next = null;
            size--;
            return entry;
        }

        // Si no es el primero, buscar en la lista
        Entry<K, V> previous = entry;
        entry = entry.getNext();

        while (entry != null) {
            if (entry.getKey().equals(key)) {
                previous.next = entry.getNext();
                entry.next = null;
                size--;
                return entry;
            }
            previous = entry;
            entry = entry.getNext();
        }
        return null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}