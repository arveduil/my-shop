package product;

public abstract class Shoes extends Product {

    private int size;
    private ShoeType shoeType;
    private  Gender gender;
    private String mainMaterial;

    public Shoes(int productId)
    {
        super(productId);
    }

    public Shoes(int productId, int size, ShoeType shoeType, Gender gender, String mainMaterial ) {
        super(productId);
        this.size = size;
        this.shoeType = shoeType;
        this.gender = gender;
        this.mainMaterial = mainMaterial;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ShoeType getShoeType() {
        return shoeType;
    }

    public void setShoeType(ShoeType shoeType) {
        this.shoeType = shoeType;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getMainMaterial() {
        return mainMaterial;
    }

    public void setMainMaterial(String mainMaterial) {
        this.mainMaterial = mainMaterial;
    }
}
