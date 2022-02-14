package com.example.myShop.domain.mapper;

import com.example.myShop.domain.wrapper.CollectionWrapper;
import com.example.myShop.domain.dto.collectionWrapper.CollectionWrapperDto;
import com.example.myShop.domain.dto.billStatus.BillStatusDto;
import com.example.myShop.domain.enums.BillStatus;
import org.mapstruct.Mapper;

/**
 * @author nafis
 * @since 13.02.2022
 */

@Mapper
public interface BillStatusMapper {
    BillStatusDto toDto(BillStatus billStatus);

    CollectionWrapperDto<BillStatusDto> toWrapperDto(CollectionWrapper<BillStatus> collection);
}
