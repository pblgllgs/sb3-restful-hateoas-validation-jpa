package com.pblgllgs.restfulhateoasvalidationjpa.dto.map;
/*
 *
 * @author pblgl
 * Created on 20-02-2024
 *
 */

import com.pblgllgs.restfulhateoasvalidationjpa.dto.ProductRecordDto;
import com.pblgllgs.restfulhateoasvalidationjpa.model.ProductModel;
import org.springframework.beans.BeanUtils;

public class ProductMapper {

    public static ProductModel dtoToEntity(ProductRecordDto productRecordDto){
        ProductModel productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto,productModel);
        return productModel;
    }
}
