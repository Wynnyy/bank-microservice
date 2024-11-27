package sk.zentity.model.mapper;

import sk.zentity.model.dto.TransferDto;
import sk.zentity.model.entities.Transfer;

import java.util.List;

public interface TransferDtoMapper {

    TransferDto toDto(Transfer transfer);
    List<TransferDto> toDtoListTransfers(List<Transfer> transfer);

}
