package com.pblgllgs.restfulhateoasvalidationjpa.dto.map;

import com.pblgllgs.restfulhateoasvalidationjpa.dto.ProductRecordDto;
import com.pblgllgs.restfulhateoasvalidationjpa.model.ProductModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
    @Mapping(target = "name", source = "name")
    ProductModel toEntity(ProductRecordDto productRecordDto);

    @Mapping(target = "name", source = "name")
    ProductRecordDto toDTO(ProductModel productModel);
}
