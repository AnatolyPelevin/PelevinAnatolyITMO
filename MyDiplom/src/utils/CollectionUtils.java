package utils;


import java.util.*;

public class CollectionUtils {
    private static final Iterator EMPTY_ITERATOR = new EmptyIterator();

    public static boolean containsAll(Collection<String> container, Collection<String> objects) {
        return containsAll(container, objects, true);
    }

    public static boolean containsAll(Collection<String> container,
                                      Collection<String> objects,
                                      boolean caseSensitive) {
        if (!caseSensitive) {
            HashSet<String> tmpContainer = new HashSet<String>();

            for (String s : container) {
                tmpContainer.add(s.toUpperCase());
            }

            container = tmpContainer;

            HashSet<String> tmpObjects = new HashSet<String>();

            for (String object : objects) {
                tmpObjects.add(object.toUpperCase());
            }

            objects = tmpObjects;
        }

        return container.containsAll(objects);
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static <T> Collection<T> safeCollection(Collection<T> collection) {
        if (collection == null) {
            return Collections.emptyList();
        }
        return collection;
    }

    public static <K, V> Map<K, V> safeMap(Map<K, V> map) {
        if (map == null) {
            return Collections.emptyMap();
        }

        return map;
    }

    public static <T> Collection<T> safeCollection(T[] array) {
        if (array == null) {
            return Collections.emptyList();
        }

        return Arrays.asList(array);
    }

    public static <T> int binarySearch(List<? extends T> list, T key, Comparator<? super T> c) {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            T midVal = list.get(mid);
            int cmp = c.compare(midVal, key);

            if (cmp < 0) {
                low = mid + 1;
            }
            else if (cmp > 0) {
                high = mid - 1;
            }
            else {
                return mid;
            }
        }

        return low - 1;
    }

    public static <T extends Comparable<? super T>> int binarySearch(List<? extends T> list, T key) {
        return binarySearch(list, key, new ComparableComparator<T>());
    }

    @SuppressWarnings({ "unchecked" })
    public static <T> Iterator<T> emptyIterator() {
        return (Iterator<T>) EMPTY_ITERATOR;
    }

    public static String toString(Collection<?> collection, char delimiter, boolean escapeDelimiter) {
        StringBuilder sb = new StringBuilder();

        for (Object element : collection) {
            String stringElement = element.toString();

            if (escapeDelimiter) {
                stringElement = StringUtils.qouteChar(stringElement, delimiter);
            }

            sb.append(stringElement).append(delimiter);
        }

        if (collection.size() > 0) {
            StringUtils.cutTail(sb, 1);
        }

        return sb.toString();
    }

    public static String toString(Collection<?> collection, char delimiter) {
        return toString(collection, delimiter, false);
    }

    public static <T> List<Collection<T>> split(Collection<T> collection, int splitSize) {
        if (collection == null) {
            throw new NullPointerException("list");
        }

        if (splitSize == 0) {
            throw new IllegalArgumentException("size: " + splitSize);
        }

        if (collection.isEmpty()) {
            return Collections.singletonList(collection);
        }

        List<T> list = new ArrayList<T>(collection);

        int chunksNumber = list.size() / splitSize;
        int remainder = list.size() % splitSize;
        int resultSize = (remainder == 0) ? chunksNumber : chunksNumber + 1;

        List<Collection<T>> result = new ArrayList<Collection<T>>(resultSize);

        for (int i = 0; i < chunksNumber; i++) {
            result.add(list.subList(i * splitSize, (i + 1) * splitSize));
        }

        if (remainder != 0) {
            result.add(list.subList(chunksNumber * splitSize, list.size()));
        }

        return result;
    }

    public static <K, V> List<Map<K, V>> split(Map<K, V> map, int splitSize) {
        if (map == null) {
            throw new NullPointerException("map");
        }

        List<Collection<K>> keyGroups = split(map.keySet(), splitSize);
        List<Map<K, V>> result = new ArrayList<Map<K,V>>(keyGroups.size());

        for (Collection<K> keyGroup : keyGroups) {
            Map<K, V> subMap = new HashMap<K,V>();

            for (K key : keyGroup) {
                subMap.put(key, map.get(key));
            }

            result.add(subMap);
        }

        return result;
    }

    public static Set<String> toUpperCase(Collection<String> collection) {
        Set<String> set = new HashSet<String>();

        for (String string : collection) {
            set.add((string == null) ? null : string.toUpperCase());
        }

        return set;
    }

    public static <T> Collection<T> intersection(Collection<T> collection1, Collection<T> collection2) {
        if (collection1 == null || collection2 == null) {
            return Collections.emptySet();
        }

        Set<T> result = new HashSet<T>(collection1);

        result.retainAll(collection2);

        return result;
    }
}

