import java.util.*;

public class NavigableSet<T extends Comparable<T>> extends AbstractSet<T> implements java.util.NavigableSet<T> {
    protected Comparator<T> comparator;
    protected List<T> list;
    protected boolean desc;

    NavigableSet(){
        list=new ArrayList<>();
        this.comparator=null;
    }
    /**
     * Constructor for NavigableSet without Comparator
     * @param
     * @return
     */


    NavigableSet(Comparator comparator){
        list=new ArrayList<>();
        this.comparator=comparator;
    }
    /**
     * Constructor for NavigableSet with Comparator
     * @param
     * @return
     */


    NavigableSet(Collection<? extends  T> col){
        Iterator t=col.iterator();
        list=new ArrayList<>();
        comparator=null;
        while (t.hasNext())add((T)t.next());
    }
    /**
     * Constructor for NavigableSet with Iterator without Comparator
     * @param
     * @return
     */


    NavigableSet(Collection<? extends  T> col,Comparator comparator){
        Iterator t=col.iterator();
        list=new ArrayList<>();
        this.comparator=comparator;
        while (t.hasNext())add((T)t.next());
    }
    /**
     * Constructor for NavigableSet with Iterator and Comparator
     * @param
     * @return
     */


    public boolean add(T e){
        for(int i=0;i<list.size();i++){
            if(compare(e,list.get(i))>0){
                list.add(i+1,e);
                return true;
            }
            if(compare(e,list.get(i))==0){
                return false;
            }
        }
        return false;
    }
    /**
     * Adds element in the set, which not breaking the order
     * @param t
     * @return
     */


    @Override
    public T lower(T t) {
        T max=null;
        for(int i=0;i<list.size();i++){
            if (compare(list.get(i),t)<0){
                max=list.get(i);
            }
        }
        return max;
    }
    /**
     *Returns the greatest element in this set strictly less than the given element, or null if there is no such element
     * @param t
     * @return
     */


    @Override
    public T floor(T t) {
        T max=null;
        for(int i=0;i<list.size();i++){
            if (compare(list.get(i),t)<=0){
                max=list.get(i);
            }
            else{
                return max;
            }
        }
        return max;
    }
    /**
     * Returns the greatest element in this set less than or equal to the given element, or null if there is no such element
     * @param t
     * @return
     */


    @Override
    public T ceiling(T t) {
        for(int i=0;i<list.size();i++){
            if(compare(list.get(i),t)>=0){
                return list.get(i);
            }
        }
        return null;
    }
    /**
     * Returns the least element in this set greater than or equal to the given element, or null if there is no such element
     * @param t
     * @return
     */


    @Override
    public T higher(T t) {
        for(int i=0;i<list.size();i++){
            if(compare(list.get(i),t)>0){
                return list.get(i);
            }
        }
        return null;
    }
    /**
     * Returns the least element in this set strictly greater than the given element, or null if there is no such element
     * @return
     */


    @Override
    public T pollFirst() {
        T t=list.get(0);
        if(t!=null){
            list.remove(0);
        }
        return t;
    }
    /**
     * Retrieves and removes the first (lowest) element, or returns null if this set is empty
     * @return
     */


    @Override
    public T pollLast() {
        T t=list.get(list.size());
        if(t!=null){
            list.remove(t);
        }
        return t;
    }
    /**
     * Retrieves and removes the last (highest) element, or returns null if this set is empty
     * @return
     */


    @Override
    public Iterator<T> iterator() {
        return new NSIterator<T>(list);
    }
    /**
     * Returns an iterator over the elements in this set, in ascending order
     * @return
     */


    @Override
    public java.util.NavigableSet<T> descendingSet() {
        NavigableSet<T> ns;
        if(comparator!= null) {
            ns=new NavigableSet<>(list,comparator);
        }
        else {
            ns = new NavigableSet<T>(list);
        }
        ns.desc=true;
        return ns;
    }
    /**
     * Returns a reverse order view of the elements contained in this set
     * @param e1
     * @param b1
     * @param e2
     * @param b2
     * @return
     */

    @Override
    public java.util.NavigableSet<T> subSet(T e1, boolean b1, T e2, boolean b2) {
        NavigableSet ns= new NavigableSet();
        int a1=0;
        int a2=0;
        if(b1)a1 =-1;
        if(b2)a2 =1;
        for(int i=0;i<list.size() && compare(list.get(i),e2)<a2;i++){
            if(compare(list.get(i),e1)>a1 ){
                ns.add(list.get(i));
            }
        }
        return ns;
    }
    /**
     * Returns a view of the portion of this set whose elements range from fromElement, inclusive, to toElement, exclusive
     * @param t
     * @param b
     * @return
     */



    @Override
    public java.util.NavigableSet<T> headSet(T t, boolean b) {
        NavigableSet ns=new NavigableSet();
        int j=0;
        int a=0;
        if(b) a=-1;
        for (int i = 0; i < this.list.size(); i++) {
                if (compare(t, list.get(i))> a) {
                    ns.list.set(i, list.get(i));
                }
        }
        return ns;
    }
    /**
     * Returns a view of the portion of this set whose elements are less than (or equal to, if inclusive is true) toElement
     * @param t
     * @return
     */


    @Override
    public java.util.NavigableSet<T> tailSet(T t, boolean b) {
        NavigableSet ns=new NavigableSet();
        int j=0;
        int a=0;
        if(b){
            a=1;
        }
        for (int i = 0; i < this.list.size(); i++) {
            if (compare(t, list.get(i)) <a) {
                ns.list.set(j, list.get(i));
                j++;
                }
            }
        return ns;
    }
    /**
     * Returns a view of the portion of this set whose elements are greater than (or equal to, if inclusive is true) fromElement
     * @return
     */


    @Override
    public Comparator<? super T> comparator() {
        return comparator;
    }

    @Override
    public SortedSet<T> subSet(T e1, T e2) {
        NavigableSet ns= new NavigableSet();
        for(int i=0;i<list.size() && compare(list.get(i),e2)<0;i++){
            if(compare(list.get(i),e1)>=0){
                ns.add(list.get(i));
            }
        }
        return ns;
    }

    /**
     *Returns a view of the portion of this set whose elements range from fromElement, inclusive, to toElement, exclusive
     * @param t
     * @return
     */


    @Override
    public SortedSet<T> headSet(T t) {
        NavigableSet ns=new NavigableSet();
        for (int i = 0; i < this.list.size(); i++) {
            if (compare(t, list.get(i))>0) {
                ns.list.set(i, list.get(i));
            }
        }
        return ns;
    }
    /**
     *Returns a view of the portion of this set whose elements are strictly less than toElement
     * @param t
     * @return
     */


    @Override
    public SortedSet<T> tailSet(T t) {
        NavigableSet ns=new NavigableSet();
        for (int i = 0; i < this.list.size(); i++) {
            if (compare(t, list.get(i)) <=0) {
                ns.list.set(i, list.get(i));
            }
        }
        return ns;
    }
    /**
     * Returns a view of the portion of this set whose elements are greater than or equal to fromElement
     * @return
     */


    @Override
    public Iterator<T> descendingIterator() {
        return new DSIterator<T>(list);
    }
    /**
     * Returns an iterator over the elements in this set, in descending order
     * @return
     */

    @Override
    public T first() {
        return list.get(0);
    }
    /**
     * Returns the first element of set
     * @return
     */


    @Override
    public T last() {
        return list.get(list.size()-1);
    }
    /**
     * Returns the last element of set
     * @return
     */


    @Override
    public int size() {
        return list.size();
    }
    /**
     * Returns the size of set
     */

    private int compare(T o1, T o2){
        int a=1;
        if(desc)a=-1;
        if(comparator==null) return  a*o1.compareTo(o2);
        return a*comparator.compare(o1,o2);
    }
    /**
     * Compares elements of set
     */
}
