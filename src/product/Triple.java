package product;

public abstract class Triple implements Comparable<Triple> {
    public int amount;
    public Gender gender;
    private Object size;

    public Triple(Gender gender, Object size, int amount){
        this.amount = amount;
        this.gender = gender;
        this.size = size;
    }

    public Object getSize() {
        return size;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public int compareTo(Triple o) {

        if(this.getGender() != o.getGender())
            return this.getGender().compareTo(o.getGender());

        return ((Integer)this.getAmount()) .compareTo( o.getAmount());
    }

    public String getStringWithoutAmount() {
        return null;
    }

}
