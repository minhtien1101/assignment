
package model;

public class ProductDetail {
    private Product product;
    private Dimension dimension;
    private int totalQuantity;

    public ProductDetail() {
        product = new Product();
        dimension = new Dimension();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
    
    
}
