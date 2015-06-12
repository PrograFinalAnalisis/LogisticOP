package com.example.curso.pruebadegui;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by nelson on 12/06/15.
 */
public class ListaClientes extends ArrayList<Cliente> implements Parcelable {

    public ListaClientes(int capacity) {
        super(capacity);
    }

    public ListaClientes() {
        super();
    }

    public ListaClientes(Collection<? extends Cliente> collection) {
        super(collection);
    }

    @Override
    public boolean add(Cliente object) {
        return super.add(object);
    }

    @Override
    public void add(int index, Cliente object) {
        super.add(index, object);
    }

    @Override
    public boolean addAll(Collection<? extends Cliente> collection) {
        return super.addAll(collection);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Cliente> collection) {
        return super.addAll(index, collection);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public Object clone() {
        return super.clone();
    }

    @Override
    public void ensureCapacity(int minimumCapacity) {
        super.ensureCapacity(minimumCapacity);
    }

    @Override
    public Cliente get(int index) {
        return super.get(index);
    }

    @Override
    public int size() {
        return super.size();
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public boolean contains(Object object) {
        return super.contains(object);
    }

    @Override
    public int indexOf(Object object) {
        return super.indexOf(object);
    }

    @Override
    public int lastIndexOf(Object object) {
        return super.lastIndexOf(object);
    }

    @Override
    public Cliente remove(int index) {
        return super.remove(index);
    }

    @Override
    public boolean remove(Object object) {
        return super.remove(object);
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        super.removeRange(fromIndex, toIndex);
    }

    @Override
    public Cliente set(int index, Cliente object) {
        return super.set(index, object);
    }

    @Override
    public Object[] toArray() {
        return super.toArray();
    }

    @Override
    public <T> T[] toArray(T[] contents) {
        return super.toArray(contents);
    }

    @Override
    public void trimToSize() {
        super.trimToSize();
    }

    @NonNull
    @Override
    public Iterator<Cliente> iterator() {
        return super.iterator();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public ListIterator<Cliente> listIterator() {
        return super.listIterator();
    }

    @Override
    public ListIterator<Cliente> listIterator(int location) {
        return super.listIterator(location);
    }

    @Override
    public List<Cliente> subList(int start, int end) {
        return super.subList(start, end);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return super.containsAll(collection);
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return super.removeAll(collection);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return super.retainAll(collection);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
