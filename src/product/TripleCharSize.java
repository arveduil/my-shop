package product;

public class TripleCharSize extends Triple {
    public Size size;

    public TripleCharSize(Size size, int ammount, Gender gender) {
        super(gender, size, ammount);
        this.size = size;
    }

    public Size getSize() {
        return size;
    }

    @Override
    public int compareTo(Triple o) {

        if (this.getGender() != o.getGender())
            return this.getGender().compareTo(o.getGender());
        if (this.getSize() != ((TripleCharSize) o).getSize())
            return this.getSize().compareTo(((TripleCharSize) o).getSize());
        return ((Integer) this.getAmount()).compareTo((Integer) o.getAmount());
    }

    public String getStringWithoutAmount() {
        return (this.getGender().toString() + " " + this.getSize().toString());
    }
}
