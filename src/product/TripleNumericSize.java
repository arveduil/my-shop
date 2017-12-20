package product;

public class TripleNumericSize extends Tuple {
    private Integer size;

    public TripleNumericSize(Integer size, int ammount, Gender gender) {
        super(gender,size, ammount);
        this.size = size;
    }


    public Integer getSize() {
        return size;
    }


    public int compareTo(Tuple o) {

        if(this.getGender() != o.getGender())
            return this.getGender().compareTo(o.getGender());
        if(this.getSize() != ((TripleNumericSize)o).getSize())
            return  this.getSize().compareTo(((TripleNumericSize)o).getSize());
        return ((Integer)this.getAmmount()) .compareTo((Integer) o.getAmmount());
    }
}
