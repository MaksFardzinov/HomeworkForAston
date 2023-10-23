package org.example.Arrays;

import java.util.Comparator;
import java.util.Iterator;

public class CustomArrayList <T extends Comparable<T>> implements CustomList<T> {
    private final int initial_size = 16;
    private  Object[] array = new Object[initial_size];
    private int list_size =0;
    private void resize(int length){
        Object[] newArray = new Object[length];
        System.arraycopy(array,0,newArray,0,list_size);
        array = newArray;

    }
    public void add(int index, T element) {
        if (index<0||index> list_size){
            throw new IndexOutOfBoundsException("invalid index");
        }
        if(list_size == array.length-1){
            resize(array.length*2);
        }
        if(index!=list_size){
            Object[] newArray = new  Object[array.length];
            int index1 = index;
            for(int i=0;i<list_size;i++){
                if(i==index1){
                newArray[i+1]= array[i];
                index1++;
                }
                else {
                    newArray[i]=array[i];
                }
            }
            array = newArray;
        }
        array[index] = element;
        list_size++;

    }

    @Override
    public void addAll(CustomList<? extends  T> customList) {
        for (int i=0;i<customList.size();i++){
            add(list_size,customList.get(i));
        }
    }

    @Override
    public void clear() {
        array = new Object[initial_size];
        list_size=0;
    }

    @Override
    public T get(int index) {
        if (index<0||index> list_size){
            throw new IndexOutOfBoundsException("invalid index");
        }
        return (T) array[index];
    }

    @Override
    public boolean isEmpty() {
        return list_size == 0;
    }

    @Override
    public void remove(int index) {
        list_size--;
        if (index<0||index>= list_size){
            throw new IndexOutOfBoundsException("invalid index");
        }
        for(int i=index;i<list_size;i++){
            array[i] = array[i+1];
        }
    }

    @Override
    public void remove(Object o) {
        for (int i =0;i<list_size;i++){
            if (array[i].equals(o)){
                remove(i);
                break;
            }
        }
    }
    public int size(){
        return list_size;
    }

    @Override
    public void sort(Comparator<? super T> comparator) {
        quicksort(0,list_size-1,comparator);
    }
    private void quicksort(int begin,int end,Comparator comparator){
        if(begin< end){
            int index = partition(begin,end,comparator);
            quicksort( begin, index-1,comparator);
            quicksort(index+1, end,comparator);
        }
    }
    private int partition(int begin, int end,Comparator comparator) {
        T pivot = (T) array[end];
        int i = begin;

        for (int j = begin; j < end; j++) {
            int i1=i;
            int j2=j;
            int compare = comparator.compare(array[j],pivot);
            if (compare<=0) {
                i++;
                T  swapTemp =(T) array[i];
                array[i] = array[j];
                array[j] = swapTemp;
            }
        }
        T  swapTemp =(T) array[i];
        array[i] = array[end];
        array[end] = swapTemp;

        return i+1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index =0;
            @Override
            public boolean hasNext() {
                return index != size();
            }

            @Override
            public T next() {
                T element = (T) array[index];
                index++;
                return  element;
            }
        };
    }
}
