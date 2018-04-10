package org.pcsoft.framework.jnode3d.type.collection;

import java.util.*;

public final class ObservableCollection<T> implements Iterable<T> {
    private final UUID uuid = UUID.randomUUID();
    private final List<T> list = new ArrayList<>();

    private final List<ChangeListener<T>> changeListenerList = new ArrayList<>();

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean contains(T o) {
        return list.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    public Object[] toArray() {
        return list.toArray();
    }

    public <T1> T1[] toArray(T1[] a) {
        return list.toArray(a);
    }

    public boolean add(T t) {
        final boolean result = list.add(t);
        if (result) {
            onAddItems(Collections.singletonList(t));
            onChanged();
        }

        return result;
    }

    public boolean remove(T o) {
        final boolean result = list.remove(o);
        if (result) {
            onRemoveItems(Collections.singletonList(o));
            onChanged();
        }

        return result;
    }

    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    public boolean addAll(Collection<? extends T> c) {
        final boolean result = list.addAll(c);
        if (result) {
            onAddItems(Collections.unmodifiableCollection(c));
            onChanged();
        }

        return result;
    }
    public boolean addAll(int index, Collection<? extends T> c) {
        final boolean result = list.addAll(index, c);
        if (result) {
            onAddItems(Collections.unmodifiableCollection(c));
            onChanged();
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public boolean removeAll(Collection<T> c) {
        final boolean result = list.removeAll(c);
        if (result) {
            onRemoveItems(Collections.unmodifiableCollection(c));
            onChanged();
        }

        return result;
    }

    public void clear() {
        final ArrayList<T> tmpCopy = new ArrayList<>(list);
        list.clear();
        onRemoveItems(Collections.unmodifiableCollection(tmpCopy));
        onChanged();
    }

    public T get(int index) {
        return list.get(index);
    }

    public void add(int index, T element) {
        list.add(index, element);
        onAddItems(Collections.singletonList(element));
        onChanged();
    }

    public T remove(int index) {
        final T result = list.remove(index);
        onRemoveItems(Collections.singletonList(list.get(index)));
        onChanged();

        return result;
    }

    public Collection<T> toCollection() {
        return Collections.unmodifiableCollection(list);
    }

    public ListIterator<T> listIterator() {
        return list.listIterator();
    }

    public ListIterator<T> listIterator(int index) {
        return list.listIterator(index);
    }

    public void addChangeListener(ChangeListener<T> changeListener) {
        changeListenerList.add(changeListener);
    }

    public void removeChangeListener(ChangeListener<T> changeListener) {
        changeListenerList.remove(changeListener);
    }

    protected void onAddItems(Collection<T> items) {
        //Empty
    }

    protected void onRemoveItems(Collection<T> items) {
        //Empty
    }

    protected void onChanged() {
        for (final ChangeListener<T> changeListener : changeListenerList) {
            changeListener.onChanged(this);
        }
    }

    /**
     * <b>Not content depend equals function!</b>
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObservableCollection<?> that = (ObservableCollection<?>) o;
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    public interface ChangeListener<T> {
        void onChanged(final ObservableCollection<T> collection);
    }
}
