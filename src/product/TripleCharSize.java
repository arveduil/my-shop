package product;

public class TripleCharSize extends Tuple {
    public Size size;

    public TripleCharSize(Size size, int ammount, Gender gender) {
        super(gender, size, ammount);
        this.size = size;
    }


    public Size getSize() {
        return size;
    }

    @Override
    public int compareTo(Tuple o) {

        if(this.getGender() != o.getGender())
            return this.getGender().compareTo(o.getGender());
        if(this.getSize() != ((TripleCharSize)o).getSize())
            return  this.getSize().compareTo(((TripleCharSize)o).getSize());
        return ((Integer)this.getAmmount()) .compareTo((Integer) o.getAmmount());
    }
}
