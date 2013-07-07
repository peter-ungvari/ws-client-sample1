package ws.client.sample1;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.ListDataModel;

/**
 *
 * @author jupi
 */
@ManagedBean(name = "productController")
@SessionScoped
public class ProductController implements Serializable {

    public ProductController() {
        productServiceWS = new ProductServiceWS_Service().getProductServiceWSPort();
        productList = new ListDataModel<ProductListDto>(productServiceWS.getProductList(partOfNameLike));
    }
    
    private ProductServiceWS productServiceWS;
    private String partOfNameLike = "";
    private ProductDetailsDto selectedProduct;
    private ListDataModel<ProductListDto> productList;

    public ProductDetailsDto getSelectedProduct() {
        return selectedProduct;
    }

    public ListDataModel<ProductListDto> getProductList() {
        return productList;
    }

    public String getPartOfNameLike() {
        return partOfNameLike;
    }

    public void setPartOfNameLike(String partOfNameLike) {
        this.partOfNameLike = partOfNameLike;
    }
    
    public void showDetails(ActionEvent event) {
        long id = productList.getRowData().getId();
        selectedProduct = productServiceWS.getProductDetails(id);
    }
    
    public void filterProductList(ActionEvent event) {
        productList.setWrappedData(productServiceWS.getProductList(partOfNameLike));
    }
}
