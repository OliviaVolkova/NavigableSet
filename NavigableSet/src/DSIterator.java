import java.util.List;

public class DSIterator<T> implements java.util.Iterator<T>{

    public int cursor;
    protected List<T> list;

    DSIterator(List list){
        this.list=list;
        this.cursor=list.size()-1;
    }

    public boolean hasNext(){
        return list.size()>cursor;
    }

    public T next() {
        try {
            T t = (T)list.get(cursor);
            cursor--;
            return t;
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new java.util.NoSuchElementException();
        }
    }
}


