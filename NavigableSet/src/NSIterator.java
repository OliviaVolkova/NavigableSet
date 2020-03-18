import java.util.List;

public class NSIterator<T> implements java.util.Iterator<T> {
    public int cursor;
    protected List<T> list;

    NSIterator(List list){
        this.list=list;
        this.cursor=0;
    }

    public boolean hasNext(){
        return list.size()>cursor;
    }

    public T next() {
        try {
            T t = (T)list.get(cursor);
            cursor++;
            return t;
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new java.util.NoSuchElementException();
        }
    }
}

