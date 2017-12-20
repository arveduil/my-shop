package product;

public abstract class Tuple implements Comparable<Tuple> {


    public int ammount;
    public Gender gender;
    private Object size;

    public Tuple( Gender gender, Object size, int ammount ){
        this.ammount = ammount;
        this.gender = gender;
        this.size = size;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }

    public Object getSize() {
        return size;
    }
    public int getAmmount() {
        return ammount;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public int compareTo(Tuple o) {

        if(this.getGender() != o.getGender())
            return this.getGender().compareTo(o.getGender());
   //     if(this.getSize() != o.getSize())
   //         return  this.getSize().compareTo(o.getSize());
        return ((Integer)this.getAmmount()) .compareTo((Integer) o.getAmmount());
    }
}
